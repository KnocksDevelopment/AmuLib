package me.knocks.generic.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by shoebox on 2/7/14.
 */
public class HttpRequestRunnable implements Runnable {
    protected final HttpRequest parentRequest;

    public HttpRequestRunnable(HttpRequest parentRequest) {
        this.parentRequest = parentRequest;
    }

    @Override
    public void run() {
        HttpRequestData data = parentRequest.getRequestData();
        String prefix = data.getEncryptionLevel().getURIPrefix();
        String type = data.getRequestType().getTypeString();
        URLConnection connection = null;
        try {
            String surl = prefix + parentRequest.getURL();
            URL url = new URL(surl);
            connection = url.openConnection();
            if (data.getEncryptionLevel().equals(RequestEncryptionLevel.HTTP)) {
                HttpURLConnection conn = (HttpURLConnection)connection;
                String reqData = data.getFullRequestData();
                for (Map.Entry<String, String> entry : data.getRequestHeaders().entrySet())
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                conn.setRequestMethod(type);
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                DataOutputStream dso = new DataOutputStream(conn.getOutputStream());
                dso.writeBytes(reqData);
                dso.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    result.append("\r\n");
                }
                reader.close();
                parentRequest.onHttpResponse(result.toString(), conn.getResponseCode());
            } else {
                HttpsURLConnection conn = (HttpsURLConnection)connection;
                String reqData = data.getFullRequestData();
                for (Map.Entry<String, String> entry : data.getRequestHeaders().entrySet())
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                conn.setRequestMethod(type);
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                DataOutputStream dso = new DataOutputStream(conn.getOutputStream());
                dso.writeBytes(reqData);
                dso.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    result.append("\r\n");
                }
                reader.close();
                parentRequest.onHttpResponse(result.toString(), conn.getResponseCode());
            }
        } catch (Exception e) {
            parentRequest.onHttpException(e);
        } finally {
            if (connection != null)
                if (connection instanceof HttpURLConnection)
                    ((HttpURLConnection)connection).disconnect();
                else if (connection instanceof HttpsURLConnection)
                    ((HttpsURLConnection)connection).disconnect();
        }
    }
}
