package zzj.product.com.memorynotes.provider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by zzj on 17-11-22.
 */

public class NotesDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "NotesDBHelper";

    private static NotesDBHelper instance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "MemoryNote.db";

    public interface Tables {
        public static final String USER_INFO = "users";
        public static final String NOTE_BOOK = "books";
        public static final String NOTE_DETAIL = "note_detail";
    }

    private NotesDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Bootstrapping database version: " + DB_VERSION);

        //创建各表
        db.execSQL("CREATE TABLE " + Tables.USER_INFO + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NoteContract.User.USER_ID + " INTEGER," +
                NoteContract.User.ACCOUNT_PHONE_NUMBER + " TEXT," +
                NoteContract.User.ACCOUNT_EMAIL + " TEXT," +
                NoteContract.User.ACCOUNT_PASSWORD + " TEXT," +
                NoteContract.User.ACCOUNT_PHOTO + " BLOB," +
                NoteContract.User.ACCOUNT_NICK + " TEXT," +
                NoteContract.User.REGIST_TIME + " INTEGER" +
                ");");

        db.execSQL("CREATE TABLE " + Tables.NOTE_BOOK + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NoteContract.Book.BOOK_NAME + " TEXT," +
                NoteContract.Book.BOOK_CREATE_TIME + " INTEGER," +
                NoteContract.Book.BOOK_USER_ID +" INTEGER REFERENCES users(_id)" +
                ");");

        db.execSQL("CREATE TABLE " + Tables.NOTE_DETAIL + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NoteContract.NoteDetail.NOTE_TITLE + " TEXT," +
                NoteContract.NoteDetail.NOTE_CONTENT + " TEXT," +
                NoteContract.NoteDetail.NOTE_CREATE_TIME + " INTEGER," +
                NoteContract.NoteDetail.NOTE_LAST_SYNC_TIME + " INTEGER," +
                NoteContract.NoteDetail.NOTE_LABEL + " TEXT," +
                NoteContract.NoteDetail.BOOK + " INTEGER REFERENCES books(_id)," +
                NoteContract.NoteDetail.DELETED + " INTEGER," +
                NoteContract.NoteDetail.DELETE_TIME + " INTEGER" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public static synchronized NotesDBHelper getInstance(Context context) {
        if (instance == null)
            instance = new NotesDBHelper(context, DB_NAME, null, DB_VERSION);
        return instance;
    }

    public SQLiteDatabase getDatabase(boolean writable) {
        return writable ? getWritableDatabase() : getReadableDatabase();
    }
}
