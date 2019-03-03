package app.android.nineninegag.com.a99gag.Listener.SwipeRefreshLayout;

import android.support.v4.widget.SwipeRefreshLayout;

import app.android.nineninegag.com.a99gag.Adapter.ListView.FrontPageListViewAdapter;
import app.android.nineninegag.com.a99gag.HttpController.CustomCallBack;
import app.android.nineninegag.com.a99gag.HttpController.HttpClient;

/**
 * Created by chrisyu on 15/12/13.
 */
public class FrontPageOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
    private FrontPageListViewAdapter frontPageListViewAdapter;
    private CustomCallBack httpCallBack;
    private String url;

    public FrontPageOnRefreshListener(String url, FrontPageListViewAdapter frontPageListViewAdapter, CustomCallBack httpCallBack) {
        this.frontPageListViewAdapter = frontPageListViewAdapter;
        this.httpCallBack = httpCallBack;
        this.url = url;
    }

    @Override
    public void onRefresh() {
        frontPageListViewAdapter.setData(null);
        HttpClient.makeRequest(url, httpCallBack);
    }
}
