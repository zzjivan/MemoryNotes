package zzj.product.com.memorynotes.presenters;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import zzj.product.com.memorynotes.models.NoteListModel;
import zzj.product.com.memorynotes.provider.database.NoteContract;
import zzj.product.com.memorynotes.views.fragment.AllNotesListFragment;
import zzj.product.com.memorynotes.views.fragment.NoteListView;

/**
 * Created by zzj on 17-11-23.
 */

public class NoteListPresenterImpl extends BasePresenter implements NoteListPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "NoteListFragment";
    private NoteListModel noteListModel;

    private LoaderManager manager;
    private CursorLoader loader;
    private Context context;

    public NoteListPresenterImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void loadAllNoteList() {
        // 通过异步的方式加载数据
        // 第一个参数为id 第二个位Bundle数据 第三个为LoaderCallbacks
        if (manager == null)
            manager = ((AllNotesListFragment) getView()).getLoaderManager();
        manager.initLoader(0, null, this);
    }

    @Override
    public void addNote(String title, String content) {

    }

    @Override
    public void deleteNote(int id) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        loader = new CursorLoader(context, NoteContract.NoteDetail.CONTENT_URI, null, null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished");
        ((NoteListView) getView()).onNotesLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}
