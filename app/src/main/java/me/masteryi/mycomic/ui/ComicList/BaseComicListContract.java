package me.masteryi.mycomic.ui.ComicList;

import java.util.List;
import me.masteryi.mycomic.base.IBasePresenter;
import me.masteryi.mycomic.base.IBaseView;
import me.masteryi.mycomic.beans.ComicCover;

/**
 * @author master.yi
 * @date 2017/1/2
 * @blog masteryi.me
 */

public class BaseComicListContract {
    public interface IView extends IBaseView {
        void onGetDataSuccess (List<ComicCover> comicCovers);

        void onGetDataFailure (Throwable t);

        void onGetDataFinish ();

        void onGetDataByPageSuccess (List<ComicCover> comicCovers);

        void onGetDataByPageFailure (Throwable t);

        void onGetDataByPageFinish ();
    }

    public interface IPresenter extends IBasePresenter {
        void getComicByPage (int page, String order);

        void getComic ();

        void getComic (String url);
    }
}
