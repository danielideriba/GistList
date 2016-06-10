package br.gistlist.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.gistlist.android.R;
import br.gistlist.android.controller.AppControllerGistList;
import br.gistlist.android.model.ListGist;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by danielideriba on 6/9/16.
 */
public class GistListAdapter extends BaseAdapter {

    private static final String TAG = GistListAdapter.class.getSimpleName();

    private Context mContext;
    private List<ListGist> listGistItens;

    public GistListAdapter(Context mContext, List<ListGist> ListGistItens) {
        this.mContext = mContext;
        this.listGistItens = ListGistItens;
    }

    @Override
    public int getCount() {
        return listGistItens.size();
    }

    @Override
    public Object getItem(int position) {
        return listGistItens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
            convertView = View.inflate(mContext, R.layout.list_row, null);

            holder = new ViewHolder(convertView);

        ListGist ls = listGistItens.get(position);

        holder.owner.setText(ls.getOwner());
        Picasso.with(mContext).load(ls.getThumb()).into(holder.thumb);
        holder.type.setText(ls.getFileType());
        holder.lang.setText(ls.getFileLang());


        return convertView;
    }

    static class ViewHolder {
        @Nullable
        @Bind(R.id.owner)
        TextView owner;

        @Nullable
        @Bind(R.id.thumb)
        ImageView thumb;

        @Nullable
        @Bind(R.id.type)
        TextView type;

        @Nullable
        @Bind(R.id.lang)
        TextView lang;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
