package app.android.nineninegag.com.a99gag.CustomRunnable;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.lucasr.twowayview.widget.ListLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

import app.android.nineninegag.com.a99gag.Adapter.ListView.FrontPageListViewAdapter;
import app.android.nineninegag.com.a99gag.Adapter.ListView.HorizontalListViewAdapter;
import app.android.nineninegag.com.a99gag.Entity.Data;
import app.android.nineninegag.com.a99gag.HttpController.CustomCallBack;
import app.android.nineninegag.com.a99gag.HttpController.CustomRunnable;
import app.android.nineninegag.com.a99gag.HttpController.HttpClient;
import app.android.nineninegag.com.a99gag.Listener.OnScrollListener.ListViewLoadMoreOnScrollListener;
import app.android.nineninegag.com.a99gag.PixelUtil;
import app.android.nineninegag.com.a99gag.R;
import app.android.nineninegag.com.a99gag.ServerPath;

/**
 * Created by chrisyu on 15/12/11.
 */
public class CustomFrontPageListViewRunnable<T> extends CustomRunnable {
    ListView listView;
    BaseAdapter adapter;
    ProgressBar progressBar;
    SwipeRefreshLayout mySwipeRefreshLayout;

    LoadMoreRunnable uiRunnable;
    CustomCallBack httpCallBack;

    String next = "";
    String tempNext = "dummy";

    public CustomFrontPageListViewRunnable(ListView listView, BaseAdapter adapter, SwipeRefreshLayout mySwipeRefreshLayout, ProgressBar progressBar) {
        this.listView = listView;
        this.adapter = adapter;
        this.progressBar = progressBar;
        this.mySwipeRefreshLayout = mySwipeRefreshLayout;

        listView.addFooterView(progressBar);
        uiRunnable = new LoadMoreRunnable(listView,adapter);
    }

    @Override
    public void run() {
        Data downloadedData = ((Data)data);

        ((FrontPageListViewAdapter) adapter).setData(downloadedData);


        //add Horizontal Header if needed
        //TODO rooms for improvement here
        if(url.contains(ServerPath.hotListing) && listView.getHeaderViewsCount() == 0) {
            TwoWayView twoWayView = (TwoWayView) ((LayoutInflater)listView.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE )).inflate(R.layout.two_wey_view, null);
            twoWayView.setAdapter(new HorizontalListViewAdapter(listView.getContext(), (Data)data));

            twoWayView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, PixelUtil.getPixelFromDp(220, listView.getContext())));
            //TODO rooms for improvement here

            twoWayView.setLayoutManager(new ListLayoutManager(listView.getContext(), ListLayoutManager.Orientation.HORIZONTAL));
            listView.addHeaderView(twoWayView);
        }

        listView.setAdapter(adapter);
        //onscrolllistener for loading more
        try {
            next = downloadedData.getPaging().get("next");
        } catch (Exception e ) {

        }
        listView.setOnScrollListener(new ListViewLoadMoreOnScrollListener() {
            @Override
            public void loadMore() {
                for(String path : ServerPath.listing){
                    if(url.contains(path) && data instanceof Data ) {
                        httpCallBack =  new CustomCallBack(Data.class, uiRunnable, ((FrontPageListViewAdapter)adapter).getContext() );

                        if(!CustomCallBack.httpRequestOnGoing) { //avoid muliple calling
                            CustomCallBack.httpRequestOnGoing = true;
                            HttpClient.makeRequest(path + "/" + next, httpCallBack);
                        }
                    }
                }
            }
        });

        if(mySwipeRefreshLayout != null)
            mySwipeRefreshLayout.setRefreshing(false);
    }

    //a little bit violating rules ~_~
    // a runnable class in another runnable class
    class LoadMoreRunnable extends CustomRunnable {
        ListView listView;
        BaseAdapter adapter;

        public LoadMoreRunnable(ListView listView, BaseAdapter adapter) {
            this.listView = listView;
            this.adapter = adapter;
        }

        @Override
        public void run() {
            try {
                tempNext = ((Data) data).getPaging().get("next");
            } catch (Exception e) {

            }
            if(!tempNext.equals(next)) { //avoid muliple adding data
                next = tempNext;
                if (adapter != null && adapter instanceof FrontPageListViewAdapter) {
                    ((FrontPageListViewAdapter) adapter).getGagList().addAll(((Data) data).getData());
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
