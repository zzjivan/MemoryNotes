package zzj.product.com.memorynotes.provider.database;

import android.net.Uri;

/**
 * Created by zzj on 17-11-22.
 */

public class NoteContract {
    public static final String AUTHORITY = "com.content.notes";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);


    public static class User implements BaseColumns ,UserColumns {
        private User()  {}
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "users");
    }

    protected interface UserColumns {
        public static final String USER_ID = "user_id";
        public static final String ACCOUNT_PHONE_NUMBER = "account_phone_number";
        public static final String ACCOUNT_EMAIL = "account_email";
        public static final String ACCOUNT_PASSWORD = "account_password";
        public static final String ACCOUNT_PHOTO = "account_photo";
        public static final String ACCOUNT_NICK = "account_nick";
        public static final String REGIST_TIME = "regist_time";
    }

    public static class Book implements BaseColumns ,BookColumns {
        private Book()  {}
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "books");
    }

    protected interface BookColumns {
        public static final String BOOK_NAME = "book_name";
        public static final String BOOK_CREATE_TIME = "create_time";
        public static final String BOOK_USER_ID = "user_id";
    }

    public static class NoteDetail implements BaseColumns ,NoteDetailColumns {
        private NoteDetail()  {}
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "notes");
    }

    protected interface NoteDetailColumns {
        public static final String NOTE_TITLE = "title";
        public static final String NOTE_CONTENT = "content";
        public static final String NOTE_CREATE_TIME = "create_time";
        public static final String NOTE_LAST_SYNC_TIME = "sync_time";
        public static final String NOTE_LABEL = "label";
        public static final String BOOK = "book_id";
        public static final String DELETED = "is_deleted";
        public static final String DELETE_TIME = "deleted_time";
    }
}
