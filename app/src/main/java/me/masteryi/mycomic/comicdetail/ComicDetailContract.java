package me.masteryi.mycomic.comicdetail;

import me.masteryi.mycomic.base.IBasePresenter;
import me.masteryi.mycomic.base.IBaseView;
import me.masteryi.mycomic.model.beans.ComicChapter;
import me.masteryi.mycomic.model.beans.ComicDetail;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */

public class ComicDetailContract {
    interface IView extends IBaseView {
        void getComicDetailSuccess (ComicDetail comicDetail);

        void getComicDetailFinish ();

        void getComicDetailFailure (Throwable t);

        void getNextChapterSuccess (ComicChapter pageCount, boolean isNext);

        void getNextChapterFailure (Throwable t);

        void getNextChapterFinish ();
    }

    interface IPresenter extends IBasePresenter {
        void getComicDetail (String comicId, String chapterId);

        void getNextChapter (String comicId, String chapterId, boolean isNext);
    }
}
