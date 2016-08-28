package com.softwares.swamisamarth.contentproviderandobserver.database;

import android.app.Activity;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by Narendra on 8/28/2016.
 */
public class StudentContentObserver extends ContentObserver {

    private final StudentContentObserverCallback studentContentObserverCallback;

    public StudentContentObserver(Handler handler, StudentContentObserverCallback studentContentObserverCallback) {
        super(handler);
        this.studentContentObserverCallback = studentContentObserverCallback;
    }

    public void register(Activity activity){
        activity.getContentResolver().registerContentObserver(StudentsContract.STUDENTS_URI, true, this);
    }

    public void unRegister(Activity activity){
        activity.getContentResolver().unregisterContentObserver(this);
    }

    @Override
    public void onChange(boolean selfChange) {
        studentContentObserverCallback.update();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        studentContentObserverCallback.update();
    }

    public interface StudentContentObserverCallback {
        void update();
    }
}
