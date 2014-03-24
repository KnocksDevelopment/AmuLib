package me.knocks.generic.http;

/**
 * This is used for maximum configurability of the request.
 */
public enum RequestEncryptionLevel {
    HTTPS("https://"),
    HTTP("http://");

    private final String uriPrefix;

    private RequestEncryptionLevel(String prefix) {
        this.uriPrefix = prefix;
    }

    public String getURIPrefix() {
        return uriPrefix;
    }
}
