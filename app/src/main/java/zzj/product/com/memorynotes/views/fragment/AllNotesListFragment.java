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
import zzj.product.com.memorynotes.presenters.NoteListPresenter;
import zzj.product.com.memorynotes.presenters.NoteListPresenterImpl;
import zzj.product.com.memorynotes.views.adapters.AllNotesAdapter;

/**
 * Created by zzj on 17-11-21.
 */

public class AllNotesListFragment extends BaseFragment implements NoteListView{
    private static final String TAG = "AllNotesListFragment";

    private RecyclerView recyclerView;
    private AllNotesAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AllNotesAdapter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NoteListPresenter) presenter).loadAllNoteList();
    }

    @Override
    protected NoteListPresenterImpl createPresenter() {
        return new NoteListPresenterImpl(getContext());
    }

    @Override
    public NoteListPresenterImpl getPresenter() {
        return (NoteListPresenterImpl) presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_note_lists, null);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onNotesLoaded(Cursor cursor) {
        mAdapter.setCursor(cursor);
        mAdapter.notifyDataSetChanged();
    }

}
