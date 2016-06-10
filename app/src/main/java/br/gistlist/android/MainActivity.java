package br.gistlist.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.gistlist.android.adapter.GistListAdapter;
import br.gistlist.android.adapter.InjectView;
import br.gistlist.android.controller.AppControllerGistList;
import br.gistlist.android.model.ListGist;
import br.gistlist.android.navigation.DetailsActivity;
import br.gistlist.android.rest.Connection;
import br.gistlist.android.rest.Server;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private GistListAdapter gistListAdapter;
    private List<ListGist> listGist = new ArrayList<ListGist>();

    @Nullable
    @Bind(R.id.publicacoes_list)
    ListView listView;

    @Nullable
    @Bind(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gistListAdapter = new GistListAdapter(this, listGist);
        listView.setAdapter(gistListAdapter);

        runTask();
    }

    @OnItemClick(R.id.publicacoes_list) void onItemClick(AdapterView<?> parent, View view,
                                                         int position, long id) {

        ListGist listExtra = (ListGist) parent.getItemAtPosition(position);

        String ownerEx  = listExtra.getOwner();
        String thumbEx  = listExtra.getThumb();
        String urlEx    = listExtra.getUrl();

        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("EXTRA_OWNER", ownerEx);
        intent.putExtra("EXTRA_THUMB", thumbEx);
        intent.putExtra("EXTRA_URL", urlEx);

        startActivity(intent);
    }


    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }


    private void runTask(){
        //showProgressDialog();

        String url = Server.uriFor("page=0");

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, "RESPORTA: "+response);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject js = response.getJSONObject(i);

                                ListGist list = new ListGist();

                                //get owner
                                if(js.has("owner")){
                                    JSONObject ownerObj = js.getJSONObject("owner");

                                    String url   = ownerObj.getString("url");
                                    String owner = ownerObj.getString("login");
                                    String thumb = ownerObj.getString("avatar_url");

                                    list.setUrl(url);
                                    list.setOwner("Owner: "+owner);
                                    list.setThumb(thumb);

                                    if(js.has("files")) {
                                        JSONObject filesOBJ = js.getJSONObject("files");

                                        Iterator keys = filesOBJ.keys();

                                        while(keys.hasNext()) {
                                            String currentDynamicKey = (String)keys.next();
                                            JSONObject currentDynamicValue = filesOBJ.getJSONObject(currentDynamicKey);

                                            String type = currentDynamicValue.getString("type");
                                            String lang = currentDynamicValue.getString("language");

                                            list.setFileType("Tipo: "+type);
                                            list.setFileLang("Linguagem: " + lang);
                                        }
                                    }

                                    listGist.add(list);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        gistListAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        AppControllerGistList.getInstance().addToRequestQueue(req);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
    }
}
