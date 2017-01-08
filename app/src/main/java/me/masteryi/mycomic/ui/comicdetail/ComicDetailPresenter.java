package me.masteryi.mycomic.ui.comicdetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.masteryi.mycomic.base.BasePresenter;
import me.masteryi.mycomic.beans.NextChapterInfo;
import me.masteryi.mycomic.beans.ComicContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */

public class ComicDetailPresenter extends BasePresenter<ComicDetailContract.IView>
    implements ComicDetailContract.IPresenter {
    public ComicDetailPresenter (ComicDetailContract.IView view) {
        super(view);
    }

    @Override
    public void getComicDetail (String comicId, String chapterId, final boolean isLoadNext) {
        mSubscription.add(mComicApi.getComicDetail(comicId, chapterId)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(Schedulers.computation())
                                   .map(new Function<String, ComicContent>() {
                                       @Override
                                       public ComicContent apply (String s) throws Exception {
                                           return getComicDetail(s);
                                       }
                                   })
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Consumer<ComicContent>() {
                                       @Override
                                       public void accept (ComicContent comicContent)
                                           throws Exception {
                                           mView.getComicDetailSuccess(comicContent, isLoadNext);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           mView.getComicDetailFailure(throwable);
                                       }
                                   }, new Action() {
                                       @Override
                                       public void run () throws Exception {
                                           mView.getComicDetailFinish();
                                       }
                                   }));
    }

    @Override
    public void getNextChapter (String comicId, String chapterId, final boolean isNext) {
        mSubscription.add(mComicApi.getNextChapter("jsonp1", comicId, chapterId)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(Schedulers.computation())
                                   .map(new Function<String, NextChapterInfo>() {
                                       @Override
                                       public NextChapterInfo apply (String s) throws Exception {
                                           String json = s.substring(7, s.length() - 1);
                                           ObjectMapper objectMapper = new ObjectMapper();
                                           return objectMapper.readValue(json, NextChapterInfo.class);
                                       }
                                   })
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Consumer<NextChapterInfo>() {
                                       @Override
                                       public void accept (NextChapterInfo comicNextChapter)
                                           throws Exception {
                                           mView.getNextChapterSuccess(comicNextChapter, isNext);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           mView.getNextChapterFailure(throwable);
                                       }
                                   }, new Action() {
                                       @Override
                                       public void run () throws Exception {
                                           mView.getNextChapterFinish();
                                       }
                                   }));
    }

    private ComicContent getComicDetail (String html) throws IOException {
        Document document = Jsoup.parse(html);
        Element body = document.body();
        //获取章节标题
        Element mangaTitle = body.getElementById("mangaTitle");
        String title = mangaTitle.ownText();
        Elements scripts = body.getElementsByTag("script");
        // TODO: 2016/12/24  重写正则
        for(Element element : scripts) {
            String text = element.html();
            if(text.contains("IMH.reader")) {
                ComicContent comicContent = parseScriptText(text);
                comicContent.setTitle(title);
                return comicContent;
            }
        }

        return null;
    }

    /**
     * @param text
     * @return
     */
    private ComicContent parseScriptText (String text) throws IOException {
        ComicContent comicContent = new ComicContent();
        // TODO: 2016/12/24  重写正则
        String p1 = "count: parseInt\\('(\\d+)'\\)";
        String p2 = "images: \\$\\.parseJSON\\('(.+)'\\)";

        Pattern pattern1 = Pattern.compile(p1);
        Matcher matcher1 = pattern1.matcher(text);
        if(matcher1.find()) {
            comicContent.setPageCount(Integer.valueOf(matcher1.group(1)));
        }

        Pattern pattern2 = Pattern.compile(p2);
        Matcher matcher2 = pattern2.matcher(text);
        if(matcher2.find()) {
            ObjectMapper mapper = new ObjectMapper();
            comicContent.setImages(mapper.readValue(matcher2.group(1), String[].class));
        }
        return comicContent;
    }
}
