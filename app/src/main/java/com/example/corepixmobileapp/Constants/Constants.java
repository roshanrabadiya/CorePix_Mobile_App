package com.example.corepixmobileapp.Constants;

import android.provider.BaseColumns;

public class Constants {

    private Constants() {
    }

    public static final class FileEntry implements BaseColumns {
        public static final String TABLE_NAME = "FileList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_ID = "ID";
    }
}
