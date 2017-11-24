package zzj.product.com.memorynotes.models;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import zzj.product.com.memorynotes.provider.database.NoteContract;

/**
 * Created by zzj on 17-11-24.
 */

public class BookListModelImpl implements BookListModel {
    private Context context;

    public BookListModelImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void addBook(String name) {
        //TODO:这里需要异步添加，在添加完成的回调要跳转到笔记本内部界面。
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(NoteContract.Book.BOOK_NAME, name);
        contentResolver.insert(NoteContract.Book.CONTENT_URI, values);
    }
}
