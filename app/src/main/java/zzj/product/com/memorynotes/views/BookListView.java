package zzj.product.com.memorynotes.views;

import android.database.Cursor;

/**
 * Created by zzj on 17-11-23.
 */

public interface BookListView {
    void onBookListLoaded(Cursor cursor);
}
