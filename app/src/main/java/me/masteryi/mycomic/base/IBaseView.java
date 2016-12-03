package me.masteryi.mycomic.base;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public interface IBaseView<T extends IBasePresenter> {
    void showErrorMsg (String msg);

    void setPresenter (T presenter);
}
