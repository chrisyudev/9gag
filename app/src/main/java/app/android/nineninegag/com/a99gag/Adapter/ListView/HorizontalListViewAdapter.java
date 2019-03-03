package app.android.nineninegag.com.a99gag.Adapter.ListView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.android.nineninegag.com.a99gag.CustomRunnable.HorizontalLoadMoreRunnable;
import app.android.nineninegag.com.a99gag.Entity.Data;
import app.android.nineninegag.com.a99gag.Entity.Gag;
import app.android.nineninegag.com.a99gag.HttpController.CustomCallBack;
import app.android.nineninegag.com.a99gag.HttpController.HttpClient;
import app.android.nineninegag.com.a99gag.R;
import app.android.nineninegag.com.a99gag.ServerPath;

/**
 * Created by chrisyu on 15/12/13.
 */
public class HorizontalListViewAdapter extends RecyclerView.Adapter<HorizontalListViewAdapter.CustomViewHolder> {
    private Context mContext;
    private ArrayList<Gag> gagList = new ArrayList<Gag>();
    private Data data;

    CustomCallBack httpCallBack;

    public HorizontalListViewAdapter (Context context, Data data) {
        this.gagList = (ArrayList<Gag>)data.getData().clone();
        this.mContext = context;
        this.data = data;

        httpCallBack = new CustomCallBack(Data.class, new HorizontalLoadMoreRunnable(this), mContext);
        for(Gag gag: gagList)
            Picasso.with(mContext).load((gag).getImages().get("normal")).fetch();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if(position == gagList.size()) {
            holder.showProgressBar();
            try {
                HttpClient.makeRequest(ServerPath.hotListing + ServerPath.loadFromIndexN(data.getPaging().get("next")), httpCallBack);
            } catch (Exception e) {

            }
        } else  {
            holder.loadVisibleItemSetting();

            (holder).textView.setText(gagList.get(position).getCaption());
            Picasso.with(mContext).load(gagList.get(position).getImages().get("normal")).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return gagList.size() + 1;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected ProgressBar progressBar;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.imageview);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        }

        public void loadVisibleItemSetting() {
            this.progressBar.setVisibility(View.GONE);
            this.textView.setVisibility(View.VISIBLE);
            this.imageView.setVisibility(View.VISIBLE);
        }

        public void showProgressBar() {
            this.progressBar.setVisibility(View.VISIBLE);
            this.textView.setVisibility(View.GONE);
            this.imageView.setVisibility(View.INVISIBLE);
        }
    }

    public Context getContext() {
        return mContext;
    }

    public Data getData() {
        return getData();
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ArrayList<Gag> getGagList(){
        return this.gagList;
    }


}
