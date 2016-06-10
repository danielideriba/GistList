package br.gistlist.android.rest;



import java.util.ArrayList;
import java.util.List;


import br.gistlist.android.model.ListGist;

/**
 * Created by danielideriba on 6/8/16.
 */
public class Connection {
    private static final String TAG = Connection.class.getSimpleName();
    private final String url;


    public Connection(String json) {
        this.url = Server.uriFor(json);
    }
}
