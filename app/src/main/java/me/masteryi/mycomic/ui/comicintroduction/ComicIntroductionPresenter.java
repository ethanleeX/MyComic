package me.masteryi.mycomic.ui.comicintroduction;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.base.BasePresenter;
import me.masteryi.mycomic.beans.Chapter;
import me.masteryi.mycomic.beans.ComicIntroductionDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */

public class ComicIntroductionPresenter extends BasePresenter<ComicIntroductionContract.IView>
    implements ComicIntroductionContract.IPresenter {
    public ComicIntroductionPresenter (ComicIntroductionContract.IView view) {
        super(view);
    }

    @Override
    public void initData () {
    }

    @Override
    public void loadData (String url) {
        mSubscription.add(mComicApi.getComicChapter(url)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(Schedulers.computation())
                                   .map(new Function<String, ComicIntroductionDetail>() {
                                       @Override
                                       public ComicIntroductionDetail apply (String s)
                                           throws Exception {
                                           return parseHtml(s);
                                       }
                                   })
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Consumer<ComicIntroductionDetail>() {
                                       @Override
                                       public void accept (
                                           ComicIntroductionDetail comicIntroductionDetail)
                                           throws Exception {
                                           mView.loadDataSuccess(comicIntroductionDetail);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           mView.loadDataFailure(throwable);
                                       }
                                   }, new Action() {
                                       @Override
                                       public void run () throws Exception {
                                           mView.loadDataFinish();
                                       }
                                   }));
    }

    private ComicIntroductionDetail parseHtml (String html) {
        ComicIntroductionDetail comicIntroductionDetail = new ComicIntroductionDetail();
        Document document = Jsoup.parse(html);
        Element body = document.body();
        Element bookDetail = body.getElementsByClass("book-detail").first();
        Element contList = bookDetail.getElementsByClass("book-detail").first();
        Elements details = contList.getElementsByTag("dl");
        comicIntroductionDetail.setLastUpdateTime(details.get(1).child(1).text());
        comicIntroductionDetail.setAuthor(details.get(2).child(1).text());
        comicIntroductionDetail.setType(details.get(3).child(1).text());
        Element bookIntro = body.getElementById("bookIntro");
        //// TODO: 2016/12/11 去<a>标签
        comicIntroductionDetail.setIntroduction(bookIntro.text());

        Element chapterList = body.getElementById("chapterList");
        Elements chapterListElements = chapterList.child(0).getElementsByTag("li");
        List<Chapter> chapters = new ArrayList<>();
        for(Element element : chapterListElements) {
            Chapter chapter = new Chapter();
            chapter.setTitle(element.child(0).attr("title"));
            chapter.setUrl(element.child(0).attr("href"));
            chapters.add(chapter);
        }
        comicIntroductionDetail.setChapters(chapters);
        return comicIntroductionDetail;
    }
}
