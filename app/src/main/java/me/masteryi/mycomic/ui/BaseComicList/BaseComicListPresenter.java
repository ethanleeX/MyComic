package me.masteryi.mycomic.ui.BaseComicList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.base.BasePresenter;
import me.masteryi.mycomic.beans.ComicCover;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author master.yi
 * @date 2017/1/2
 * @blog masteryi.me
 */

public abstract class BaseComicListPresenter extends BasePresenter<BaseComicListContract.IView>
    implements BaseComicListContract.IPresenter {

    public BaseComicListPresenter (BaseComicListContract.IView view) {
        super(view);
    }

    /**
     * 根据页数获得数据
     *
     * @param page
     */
    @Override
    public void getComicByPage (int page, String order) {
        mSubscription.add(mComicApi.getRecentByPage("list", page, "0", "1", order)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(Schedulers.computation())
                                   .map(new Function<String, List<ComicCover>>() {
                                       @Override
                                       public List<ComicCover> apply (String s) throws Exception {
                                           return parseComicByPageData(s);
                                       }
                                   })
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Consumer<List<ComicCover>>() {
                                       @Override
                                       public void accept (List<ComicCover> comicCovers)
                                           throws Exception {
                                           mView.onGetDataByPageSuccess(comicCovers);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           mView.onGetDataByPageFailure(throwable);
                                       }
                                   }, new Action() {
                                       @Override
                                       public void run () throws Exception {
                                           mView.onGetDataByPageFinish();
                                       }
                                   }));
    }

    /**
     * 解析html
     *
     * @param html html
     * @return comics
     */
    protected List<ComicCover> parseData (String html) {
        List<ComicCover> comicCovers = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element body = document.body();
        Element detail = body.getElementById("detail");
        Elements comics = detail.children();
        for(Element element : comics) {
            comicCovers.add(getComic(element.getElementsByTag("a").first()));
        }
        return comicCovers;
    }

    protected ComicCover getComic (Element element) {
        ComicCover comicCover = new ComicCover();
        comicCover.setUrl(element.attr("href"));
        comicCover.setCoverImg(element.child(0).child(0).attr("data-src"));
        comicCover.setState(element.child(0).child(1).text());
        comicCover.setTitle(element.child(1).text());
        comicCover.setAuthor(element.child(2).child(1).text());
        comicCover.setType(element.child(3).child(1).text());
        comicCover.setLatestChapter(element.child(4).child(1).text());
        comicCover.setUpdateTime(element.child(5).child(1).text());
        return comicCover;
    }

    protected List<ComicCover> parseComicByPageData (String html) {
        List<ComicCover> comicCovers = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element body = document.body();
        Elements comics = body.children();
        for(Element element : comics) {
            comicCovers.add(getComic(element.getElementsByTag("a").first()));
        }
        return comicCovers;
    }
}
