package br.gistlist.android.rest;

/**
 * Created by danielideriba on 6/8/16.
 */
public class Server {
    private static String uri = "https://api.github.com/gists/public?%s";
    public static String uriFor(String value) {
        return String.format(uri, value);
    }
}
