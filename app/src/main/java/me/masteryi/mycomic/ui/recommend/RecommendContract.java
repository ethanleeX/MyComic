package me.masteryi.mycomic.ui.recommend;

import java.util.List;
import me.masteryi.mycomic.base.IBasePresenter;
import me.masteryi.mycomic.base.IBaseView;
import me.masteryi.mycomic.beans.RecommendComic;

/**
 * @author master.yi
 * @date 2016/11/27
 * @blog masteryi.me
 */

public interface RecommendContract {
    interface IView extends IBaseView {
        void loadDataFailure (Throwable t);

        void loadDataSuccess (List<RecommendComic> recommendComics);

        void loadDataFinish ();
    }

    interface IPresenter extends IBasePresenter {
        /**
         * 换一批
         *
         * @param type
         */
        void changeData (int type);
    }
}
