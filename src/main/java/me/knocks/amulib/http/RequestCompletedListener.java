package me.knocks.amulib.http;

/**
 * Created by shoebox on 2/7/14.
 */
public interface RequestCompletedListener {
    public void onHttpResponse(String data, int status);
    public void onHttpException(Exception e);
}
