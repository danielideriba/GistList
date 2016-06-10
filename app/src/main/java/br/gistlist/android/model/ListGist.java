package br.gistlist.android.model;

import android.util.Log;

/**
 * Created by danielideriba on 6/9/16.
 */
public class ListGist {
    private static final String TAG = ListGist.class.getSimpleName();

    private String url;
    private String owner;
    private String thumb;
    private String fileType;
    private String fileLang;

    public ListGist(){
    }

    public void setUrl(String mUrl){
        this.url = mUrl;
    }

    public void setOwner(String mOwner){
        this.owner = mOwner;
    }

    public void setThumb(String mThumb){
        this.thumb = mThumb;
    }

    public void setFileType(String mFileType){
        this.fileType = mFileType;
    }

    public void setFileLang(String mFileLang){
        this.fileLang = mFileLang;
    }

    public String getOwner() {
        return owner;
    }

    public String getThumb() {
        return thumb;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileLang() {
        return fileLang;
    }

    public String getUrl() {
        return url;
    }
}
