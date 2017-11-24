package zzj.product.com.memorynotes.presenters;

import java.lang.ref.WeakReference;

/**
 * Created by zzj on 17-11-24.
 */

public abstract class BasePresenter<V> {
    protected WeakReference<V> mView;

    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    public void detatchView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    protected V getView() {
        return mView.get();
    }
}
