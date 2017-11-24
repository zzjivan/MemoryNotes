package zzj.product.com.memorynotes.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import zzj.product.com.memorynotes.presenters.BasePresenter;

/**
 * Created by zzj on 17-11-21.
 */

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        presenter.detatchView();
        super.onDestroy();
    }

    abstract protected P createPresenter();
    abstract public P getPresenter();
}
