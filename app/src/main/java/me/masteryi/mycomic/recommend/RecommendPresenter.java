package me.masteryi.mycomic.recommend;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.base.BasePresenter;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.beans.RecommendComic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author master.yi
 * @date 2016/11/27
 * @blog masteryi.me
 */

public class RecommendPresenter extends BasePresenter<RecommendContract.View>
    implements RecommendContract.Presenter {

    public RecommendPresenter (RecommendContract.View recommendView) {
        super(recommendView);
    }

    /**
     * 获取推荐数据
     *
     * @return
     */
    @Override
    public void initData () {
        mSubscription.add(mComicApi.getHome()
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(Schedulers.computation())
                                   .map(new Function<String, List<RecommendComic>>() {
                                       @Override
                                       public List<RecommendComic> apply (String s)
                                           throws Exception {
                                           return decoderHtml(s);
                                       }
                                   })
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Consumer<List<RecommendComic>>() {
                                       @Override
                                       public void accept (List<RecommendComic> recommendComics)
                                           throws Exception {
                                           mView.loadDataSuccess(recommendComics);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           {
                                               mView.loadDataFail(throwable);
                                               mView.loadDataFinish();
                                           }
                                       }
                                   }, new Action() {
                                       @Override
                                       public void run () throws Exception {
                                           mView.loadDataFinish();
                                       }
                                   }));
    }

    private List<RecommendComic> decoderHtml (String html) {
        Document document = Jsoup.parse(html);

        Element lianzai = document.getElementById("main-lianzai");//热门连载
        Element wanjie = document.getElementById("main-wanjie");//经典完结
        Element caise = document.getElementById("main-caise");//彩色
        Element shangjia = document.getElementById("main-shangjia");//上架

        //获得每种类型的推荐数据
        List<RecommendComic> list = new ArrayList<>(4);
        list.add(getRecommendComic(lianzai));
        list.add(getRecommendComic(wanjie));
        list.add(getRecommendComic(caise));
        list.add(getRecommendComic(shangjia));

        return list;
    }

    /**
     * 获得每种类型的推荐数据
     *
     * @param element
     * @return
     */
    private RecommendComic getRecommendComic (Element element) {
        List<ComicCover> list = new ArrayList<>(6);
        String title = element.child(0).child(0).text();
        Elements li = element.child(1).child(0).child(0).getElementsByTag("li");
        for(Element e : li) {
            list.add(getComicCover(e));
        }
        return new RecommendComic(title, list);
    }

    /**
     * 获得每本漫画封面
     *
     * @param element
     * @return
     */
    private ComicCover getComicCover (Element element) {
        Element contentElement = element.child(0);//a标签
        String coverImg = contentElement.child(0).attr("data-src");
        String name = contentElement.child(1).text();
        String latestChapter = contentElement.child(2).text();
        String url = contentElement.attr("href");

        return new ComicCover(coverImg, name, latestChapter, url);
    }

    @Override
    public void changeData (int type) {

    }
}
