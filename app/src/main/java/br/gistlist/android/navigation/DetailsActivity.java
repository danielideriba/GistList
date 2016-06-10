package br.gistlist.android.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.gistlist.android.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by danielideriba on 6/10/16.
 */
public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @Nullable
    @Bind(R.id.owner)
    TextView owner;

    @Nullable
    @Bind(R.id.thumb)
    ImageView thumb;

    @Nullable
    @Bind(R.id.gistFull)
    WebView gistFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String extraOwner = i.getStringExtra("EXTRA_OWNER");
        String extraThumb = i.getStringExtra("EXTRA_THUMB");
        String extraURL   = i.getStringExtra("EXTRA_URL");

        owner.setText(extraOwner);
        Picasso.with(this).load(extraThumb).into(thumb);


        WebSettings webSettings = gistFull.getSettings();
        webSettings.setBuiltInZoomControls(true);

        gistFull.loadUrl(extraURL);
    }

}
