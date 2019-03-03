package app.android.nineninegag.com.a99gag.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import app.android.nineninegag.com.a99gag.Adapter.ListView.FrontPageListViewAdapter;
import app.android.nineninegag.com.a99gag.CustomRunnable.CustomFrontPageListViewRunnable;
import app.android.nineninegag.com.a99gag.Entity.Data;
import app.android.nineninegag.com.a99gag.HttpController.CustomCallBack;
import app.android.nineninegag.com.a99gag.HttpController.HttpClient;
import app.android.nineninegag.com.a99gag.Listener.SwipeRefreshLayout.FrontPageOnRefreshListener;
import app.android.nineninegag.com.a99gag.R;
import app.android.nineninegag.com.a99gag.ServerPath;
import butterknife.Bind;
import butterknife.ButterKnife;

;

/**
 * Created by chrisyu on 15/12/11.
 */
public class ListViewFragment extends Fragment {
    public int pageNum = 0;
    @Bind(R.id.listview) ListView listView;
    @Bind(R.id.swiperefresh)  SwipeRefreshLayout mySwipeRefreshLayout;

    private FrontPageListViewAdapter frontPageListViewAdapter;

    private ProgressBar progressBar;
    private CustomFrontPageListViewRunnable uiRunnable;
    private CustomCallBack httpCallBack;

    public ListViewFragment(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);

        progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyle);

        frontPageListViewAdapter = new FrontPageListViewAdapter(getActivity());
        mySwipeRefreshLayout.setOnRefreshListener(new FrontPageOnRefreshListener(ServerPath.getAPIPath(pageNum), frontPageListViewAdapter, httpCallBack));

        uiRunnable = new CustomFrontPageListViewRunnable(listView, frontPageListViewAdapter, mySwipeRefreshLayout, progressBar);
        httpCallBack =  new CustomCallBack(Data.class, uiRunnable, getActivity());

        HttpClient.makeRequest(ServerPath.getAPIPath(pageNum), httpCallBack);

        mySwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
