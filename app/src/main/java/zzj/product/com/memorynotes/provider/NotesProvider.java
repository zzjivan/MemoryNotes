package zzj.product.com.memorynotes.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import zzj.product.com.memorynotes.provider.database.NoteContract;
import zzj.product.com.memorynotes.provider.database.NotesDBHelper;

/**
 * Created by zzj on 17-11-17.
 */

public class NotesProvider extends ContentProvider {

    private static final String TAG = "NotesProvider";

    private static final int USERS = 1000;
    private static final int USERS_ID = 1001;

    private static final int BOOKS = 2000;

    private static final int NOTES = 3000;

    private NotesDBHelper notesDBHelper;
    private ContentResolver resolver;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(NoteContract.AUTHORITY, "users", USERS);
        uriMatcher.addURI(NoteContract.AUTHORITY, "books", BOOKS);
        uriMatcher.addURI(NoteContract.AUTHORITY, "notes", NOTES);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        notesDBHelper = NotesDBHelper.getInstance(context);
        resolver = context.getContentResolver();
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
//        final int match = uriMatcher.match(uri);
//        switch (match) {
//            case
//        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] columns, @Nullable String selection, @Nullable String[] strings1, @Nullable String s1) {
        final int match = uriMatcher.match(uri);
        if (match == UriMatcher.NO_MATCH)
            return null;
        SQLiteDatabase db = notesDBHelper.getDatabase(false);
        Cursor cursor = null;
        switch (match) {
            case USERS:
                break;
            case BOOKS:
                cursor = db.query(true, NotesDBHelper.Tables.NOTE_BOOK, columns, selection, strings1, null, null, null, null);
                break;
            case NOTES:
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = uriMatcher.match(uri);
        if (match == UriMatcher.NO_MATCH)
            return null;
        SQLiteDatabase db = notesDBHelper.getDatabase(true);
        switch (match) {
            case USERS:
                break;
            case BOOKS:
                db.insert(NotesDBHelper.Tables.NOTE_BOOK, null, contentValues);

                break;
            case NOTES:

                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        final int match = uriMatcher.match(uri);
        if (match == UriMatcher.NO_MATCH)
            return -1;
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        final int match = uriMatcher.match(uri);
        if (match == UriMatcher.NO_MATCH)
            return -1;
        return 0;
    }

}
