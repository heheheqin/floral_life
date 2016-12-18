package com.dream.will.floral_life.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.utils.MD5Utils;
import com.dream.will.floral_life.utils.SDUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author：Will on 2016/12/14 19:36
 * Mail：heheheqin.will@gmail.com
 */

public class MyAsyncTack extends AsyncTask<String, Integer, Bitmap> {

    private HttpURLConnection openConnection = null;
    private String path;
    private ProgressShow progressShow;

    public void setPrgressShow(ProgressShow progressShow){
        this.progressShow = progressShow;
    }

    public interface ProgressShow{
        void finish(String progress);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        URL url;
        try {
            path = params[0];
            url = new URL(path);
            openConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = openConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (openConnection != null) {
                openConnection.disconnect();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (result != null) {
            String s = MD5Utils.md5(path);
            String pathurl = SDUtils.saveFile(result, Conten.KEY_APP_NAME, s + ".png");
            progressShow.finish(pathurl);
        }
    }

}
