package app.android.nineninegag.com.a99gag.Listener.OnScrollListener;

import android.widget.AbsListView;

/**
 * Created by chrisyu on 15/12/11.
 */
public abstract class ListViewLoadMoreOnScrollListener implements AbsListView.OnScrollListener {
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount >= totalItemCount - 2) {
            loadMore();
        }
    }

    public abstract void loadMore() ;
}
