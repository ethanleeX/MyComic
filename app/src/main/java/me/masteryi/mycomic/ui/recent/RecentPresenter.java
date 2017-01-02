package me.masteryi.mycomic.ui.recent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.ui.BaseComicList.BaseComicListContract;
import me.masteryi.mycomic.ui.BaseComicList.BaseComicListPresenter;

/**
 * @author master.yi
 * @date 2017/1/1
 * @blog masteryi.me
 */

public class RecentPresenter extends BaseComicListPresenter {
    public RecentPresenter (BaseComicListContract.IView view) {
        super(view);
    }

    @Override
    public void getComic () {
        mSubscription.add(mComicApi.getRecent()
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(Schedulers.computation())
                                   .map(new Function<String, List<ComicCover>>() {
                                       @Override
                                       public List<ComicCover> apply (String s) throws Exception {
                                           return parseData(s);
                                       }
                                   })
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Consumer<List<ComicCover>>() {
                                       @Override
                                       public void accept (List<ComicCover> comicCovers)
                                           throws Exception {
                                           mView.onGetDataSuccess(comicCovers);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           mView.onGetDataFailure(throwable);
                                           mView.onGetDataFinish();
                                       }
                                   }, new Action() {
                                       @Override
                                       public void run () throws Exception {
                                           mView.onGetDataFinish();
                                       }
                                   }));
    }
}
