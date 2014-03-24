package me.knocks.generic.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The request data to be
 */
public class HttpRequestData {

    private HttpRequestType requestType;
    private RequestEncryptionLevel encryptionLevel;

    private Map<String, String> requestHeaders = new HashMap<String, String>();
    private Map<String, String> requestData = new HashMap<String, String>();

    public HttpRequestData() {
        this(HttpRequestType.GET, RequestEncryptionLevel.HTTP);
    }

    public HttpRequestData(HttpRequestType type) {
        this(type, RequestEncryptionLevel.HTTP);
    }

    public HttpRequestData(RequestEncryptionLevel level) {
        this(HttpRequestType.GET, level);
    }

    public HttpRequestData(HttpRequestType requestType, RequestEncryptionLevel level) {
        this.requestType = requestType;
        this.encryptionLevel = level;
    }

    public RequestEncryptionLevel getEncryptionLevel() {
        return encryptionLevel;
    }

    public void setEncryptionLevel(RequestEncryptionLevel encryptionLevel) {
        this.encryptionLevel = encryptionLevel;
    }

    public HttpRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(HttpRequestType requestType) {
        this.requestType = requestType;
    }

    public Map<String, String> getRequestHeaders() {
        return Collections.unmodifiableMap(requestHeaders);
    }

    public void setHeader(String header, String value) {
        requestHeaders.put(header, value);
    }

    public void removeHeader(String header) {
        requestHeaders.remove(header);
    }

    public void setRequestKey(String key, String value) {
        requestData.put(key, value);
    }

    public void removeRequestKey(String key) {
        requestData.remove(key);
    }

    public String getFullRequestData() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : requestData.entrySet()) {
            builder.append(entry.getKey() + "=" + entry.getValue());
            builder.append("&");
        }
        String result = builder.toString();
        if (result.endsWith("&"))
            result = result.substring(0, result.length() - 1);
        return result;
    }
}
