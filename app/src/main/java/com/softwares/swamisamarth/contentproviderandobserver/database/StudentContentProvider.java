package com.softwares.swamisamarth.contentproviderandobserver.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;


/**
 * Created by Narendra on 8/27/2016.
 */
public class StudentContentProvider extends ContentProvider {

    private static final int STUDENT_LIST = 1;
    private static final int STUDENT_ID = 2;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(StudentsContract.AUTHORITY,
                "students",
                STUDENT_LIST);
        URI_MATCHER.addURI(StudentsContract.AUTHORITY,
                "students/#",
                STUDENT_ID);
    }

    private DBHelper dbHelper;
    private boolean isInBatchMode;


    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());

        return true;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (URI_MATCHER.match(uri)) {
            case STUDENT_LIST:
                return StudentsContract.CONTENT_TYPE;
            case STUDENT_ID:
                return StudentsContract.CONTENT_STUDENT_TYPE;
            default:
                return null;
        }
    }



    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (URI_MATCHER.match(uri)) {
            case STUDENT_LIST:
                builder.setTables(StudentTable.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = StudentTable.SORT_ORDER_DEFAULT;
                }
                break;
            case STUDENT_ID:
                builder.setTables(StudentTable.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(StudentTable.COLUMN_ID + " = " +
                        uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException(
                        "Unsupported URI: " + uri);
        }
        Cursor cursor =
                builder.query(
                        db,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

        return cursor;
    }



    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (URI_MATCHER.match(uri) != STUDENT_LIST) {
            throw new IllegalArgumentException(
                    "Unsupported URI for insertion: " + uri);
        } else if (URI_MATCHER.match(uri) == STUDENT_LIST) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id =
                        db.insert(
                                StudentTable.TABLE_NAME,
                                null,
                                values);
                return getUriForId(id, uri);

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int delCount = 0;
        switch (URI_MATCHER.match(uri)) {
            case STUDENT_LIST:
                delCount = db.delete(StudentTable.TABLE_NAME, selection, selectionArgs);
                break;
            case STUDENT_ID:
                String idStr = uri.getLastPathSegment();
                String where = StudentTable.COLUMN_ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                delCount = db.delete(StudentTable.TABLE_NAME, where, selectionArgs);
                break;
            default:
                // no support for deleting photos or entities -
                // photos are deleted by a trigger when the item is deleted
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        // notify all listeners of changes:
        if (delCount > 0 && !isInBatchMode) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateCount = 0;
        switch (URI_MATCHER.match(uri)) {
            case STUDENT_LIST:
                updateCount = db.update(StudentTable.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case STUDENT_ID:
                String idStr = uri.getLastPathSegment();
                String where = StudentTable.COLUMN_ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = db.update(StudentTable.TABLE_NAME, values, where,
                        selectionArgs);
                break;
            default:
                // no support for updating photos!
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        // notify all listeners of changes:
        if (updateCount > 0 && !isInBatchMode) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updateCount;
    }


    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            if (!isInBatchMode) {
                // notify all listeners of changes and return itemUri:
                getContext().
                        getContentResolver().
                        notifyChange(itemUri, null);
            }
            return itemUri;
        }
        // s.th. went wrong:
        throw new SQLException("Problem while inserting into uri: " + uri);
    }
}

