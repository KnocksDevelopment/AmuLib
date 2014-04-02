package me.knocks.amulib.http;

/**
 * An enumeration of all used HTTP Request Types
 * TODO: Add options like PUT, OPTIONS, etc.
 */
public enum HttpRequestType {
    GET("GET"),
    POST("POST");

    private final String requestTypeStr;

    private HttpRequestType(String str) {
        this.requestTypeStr = str;
    }

    public String getTypeString() {
        return requestTypeStr;
    }
}
