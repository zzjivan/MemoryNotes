package zzj.product.com.memorynotes.views.fragment;

import android.database.Cursor;

/**
 * Created by zzj on 17-11-23.
 */

public interface NoteListView {
    void onNotesLoaded(Cursor cursor);
}
