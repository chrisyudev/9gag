package app.android.nineninegag.com.a99gag.CustomRunnable;

import com.squareup.picasso.Picasso;

import app.android.nineninegag.com.a99gag.Adapter.ListView.HorizontalListViewAdapter;
import app.android.nineninegag.com.a99gag.Entity.Data;
import app.android.nineninegag.com.a99gag.Entity.Gag;
import app.android.nineninegag.com.a99gag.HttpController.CustomRunnable;

/**
 * Created by chrisyu on 15/12/13.
 */
public class HorizontalLoadMoreRunnable extends CustomRunnable {
    HorizontalListViewAdapter adapter;

    public HorizontalLoadMoreRunnable (HorizontalListViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void run() {
        Data newData = (Data)data;
        adapter.setData(newData);

        for(Gag gag: newData.getData())
            Picasso.with(adapter.getContext()).load((gag).getImages().get("normal")).fetch();

        adapter.getGagList().addAll(newData.getData());
        adapter.notifyDataSetChanged();
    }
}