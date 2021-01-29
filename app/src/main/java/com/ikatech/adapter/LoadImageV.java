package com.ikatech.adapter;

import android.graphics.Bitmap;
import android.net.sip.SipSession;
import android.os.AsyncTask;

import okhttp3.internal.http2.Http2Connection;

public class LoadImageV  extends AsyncTask<String,Void, Bitmap> {


    private SipSession.Listener mListener;
    @Override
    protected Bitmap doInBackground(String... strings) {
        return null;
    }
}
