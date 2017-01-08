package me.masteryi.mycomic.ui.comicintroduction;

import me.masteryi.mycomic.base.IBasePresenter;
import me.masteryi.mycomic.base.IBaseView;
import me.masteryi.mycomic.beans.ComicIntroduction;

/**
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */

class ComicIntroductionContract {
    interface IView extends IBaseView {
        void loadDataSuccess (ComicIntroduction comicIntroduction);

        void loadDataFinish ();

        void loadDataFailure (Throwable t);
    }

    interface IPresenter extends IBasePresenter {
        void loadData (String url);
    }
}
