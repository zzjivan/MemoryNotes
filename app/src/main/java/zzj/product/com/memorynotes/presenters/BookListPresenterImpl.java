package zzj.product.com.memorynotes.presenters;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import zzj.product.com.memorynotes.models.BookListModel;
import zzj.product.com.memorynotes.models.BookListModelImpl;
import zzj.product.com.memorynotes.provider.database.NoteContract;
import zzj.product.com.memorynotes.views.BookListView;
import zzj.product.com.memorynotes.views.fragment.BookListFragment;

/**
 * Created by zzj on 17-11-23.
 */

public class BookListPresenterImpl extends BasePresenter implements BookListPresenter, LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = "BookListPresenterImpl";
    private BookListModel model;

    private Context context;

    private LoaderManager manager;
    private CursorLoader loader;

    public BookListPresenterImpl(Context context) {
        this.context = context.getApplicationContext();
        model = new BookListModelImpl(context);
    }

    @Override
    public void loadBook() {
        if (manager == null)
            manager = ((BookListFragment) getView()).getLoaderManager();
        manager.initLoader(0, null, this);
    }

    @Override
    public void addBook(String name) {
        model.addBook(name);
    }

    @Override
    public void deleteBook(int id) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        loader = new CursorLoader(context, NoteContract.Book.CONTENT_URI, null, null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished");
        ((BookListView) getView()).onBookListLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
