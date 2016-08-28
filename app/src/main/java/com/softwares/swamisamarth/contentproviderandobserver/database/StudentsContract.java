package com.softwares.swamisamarth.contentproviderandobserver.database;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by Narendra on 8/27/2016.
 */
public final class StudentsContract {

    public static final String AUTHORITY =
            "com.softwares.swamisamarth.contentproviderandobserver.students";


    /**
     * The content URI for the top-level
     * students authority.
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public static final Uri STUDENTS_URI = Uri.withAppendedPath(CONTENT_URI, "students");

    /**
     * The mime type of a directory of items.
     */

    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE +
                    "/com.softwares.swamisamarth.contentproviderandobserver.students_items";
    /**
     * The mime type of a single item.
     */
    public static final String CONTENT_STUDENT_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE +
                    "/com.softwares.swamisamarth.contentproviderandobserver.students_items";
}
