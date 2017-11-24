package zzj.product.com.memorynotes.views.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zzj.product.com.memorynotes.R;
import zzj.product.com.memorynotes.presenters.BookListPresenter;
import zzj.product.com.memorynotes.presenters.BookListPresenterImpl;
import zzj.product.com.memorynotes.provider.database.NoteContract;
import zzj.product.com.memorynotes.views.BookListView;
import zzj.product.com.memorynotes.views.adapters.BookAdapter;

/**
 * Created by zzj on 17-11-21.
 */

public class BookListFragment extends BaseFragment implements BookListView{
    private static final String TAG = "BookListFragment";

    private RecyclerView recyclerView;
    private BookAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BookAdapter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BookListPresenter) presenter).loadBook();
    }

    @Override
    protected BookListPresenterImpl createPresenter() {
        return new BookListPresenterImpl(getContext());
    }

    @Override
    public BookListPresenterImpl getPresenter() {
        return (BookListPresenterImpl) presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, null);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onBookListLoaded(Cursor cursor) {
        if (cursor != null && cursor.moveToNext())
            Log.d(TAG, "book list:"+cursor.getString(cursor.getColumnIndex(NoteContract.Book.BOOK_NAME)));
        adapter.setCursor(cursor);
        adapter.notifyDataSetChanged();
    }
}
