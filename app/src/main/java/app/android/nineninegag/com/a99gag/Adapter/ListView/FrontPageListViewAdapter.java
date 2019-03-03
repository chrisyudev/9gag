package app.android.nineninegag.com.a99gag.Adapter.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.android.nineninegag.com.a99gag.Entity.Data;
import app.android.nineninegag.com.a99gag.Entity.Gag;
import app.android.nineninegag.com.a99gag.PixelUtil;
import app.android.nineninegag.com.a99gag.R;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by chrisyu on 15/12/11.
 */
public class FrontPageListViewAdapter<T> extends BaseAdapter {
    private ArrayList<Gag> gagList = new ArrayList<Gag>();
    private Data data;
    private Context context;

    @Bind(R.id.content)
    ImageView imageView;

    public FrontPageListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if(gagList == null) return 0;
        return gagList.size();
    }

    @Override
    public Object getItem(int position) {
        return gagList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(gagList == null) return 0;
        return gagList.get(position).getId().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gag_item, (ViewGroup) convertView);
        } else {
            view = convertView;
        }
        ((TextView)view.findViewById(R.id.title)).setText(gagList.get(position).getCaption());

        ButterKnife.bind(this, view);

        Picasso.with(context).load(gagList.get(position).getImages().get("normal")).placeholder(R.drawable.ninethumb).resize(PixelUtil.getScreenWidth(context),0).into(imageView);
        return view;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        if(data == null || (data).getData() == null) return;
        this.data = data;
        if(this.gagList == null) {
            this.gagList = (data).getData();
        } else {
            this.gagList.addAll((data).getData());
        }
    }

    public Context getContext(){
        return context;
    }

    public ArrayList<Gag> getGagList() {
        if(gagList == null)
            return new ArrayList<Gag>();
        return gagList;
    }
}
