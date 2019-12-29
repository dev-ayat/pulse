package com.moh.departments.constants;

import android.provider.BaseColumns;

public class DBContract {

    public static class Icd implements BaseColumns {
        // Inner class that defines the table contents
        public static final String TABLE_NAME = "C_ICD_CODE_TB";
        public static final String COLUMN_NAME_EN = "ICDNAMEEN";
        public static final String COLUMN_CD = "ICDCD";
        public static final String COLUMN_CLASS = "ICDCLASS";
        public static final String COLUMN_TYPE = "ICDTYPE";
        public static final String COLUMN_INCUBATION_CD = "ICDINCUBATIONCD";


    }
}

