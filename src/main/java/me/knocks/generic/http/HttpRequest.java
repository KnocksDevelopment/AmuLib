package me.knocks.generic.http;


import me.knocks.generic.manager.GenericManager;

/**
 * A better HTTP frontend for Java.
 */
public class HttpRequest extends GenericManager<RequestCompletedListener> implements RequestCompletedListener {

    private final String URL;
    private final HttpRequestData requestData;

    public HttpRequest(String url) {
        this(url, new HttpRequestData(HttpRequestType.GET, RequestEncryptionLevel.HTTP));
    }

    public HttpRequest(String url, HttpRequestData data) {
        super(RequestCompletedListener.class);

        // automatically strip out prefixes
        for (RequestEncryptionLevel level : RequestEncryptionLevel.values())
            if (url.startsWith(level.getURIPrefix()))
                url = url.replace(level.getURIPrefix(), "");

        this.URL = url;
        this.requestData = data;
    }

    public final String getURL() {
        return URL;
    }

    public final HttpRequestData getRequestData() {
        return requestData;
    }

    public void run() {
        HttpRequestRunnable runnable = new HttpRequestRunnable(this);
        new Thread(runnable).start();
    }

    @Override
    public void onHttpResponse(String data, int status) {
        for (RequestCompletedListener listener : getContents())
            listener.onHttpResponse(data, status);
    }

    @Override
    public void onHttpException(Exception e) {
        for (RequestCompletedListener listener : getContents())
            listener.onHttpException(e);
    }
}
