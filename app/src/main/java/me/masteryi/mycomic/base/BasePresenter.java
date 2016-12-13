package me.masteryi.mycomic.base;

import io.reactivex.disposables.CompositeDisposable;
import me.masteryi.mycomic.utils.network.ComicApi;
import me.masteryi.mycomic.utils.network.ComicService;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {
    protected final V mView;
    protected CompositeDisposable mSubscription;
    protected ComicApi mComicApi;

    public BasePresenter (V view) {
        mView = view;
        mComicApi = ComicService.getApiInstance();
        mSubscription = new CompositeDisposable();
    }

    @Override
    public void onSubscribe () {
        loadData();
    }

    @Override
    public void unSubscribe () {
       if(mSubscription != null){
           mSubscription.clear();
       }
    }

    public abstract void loadData ();
}
