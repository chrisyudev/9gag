package app.android.nineninegag.com.a99gag.Entity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chrisyu on 15/12/8.
 */
public class Data {
    private String video;
    private ArrayList<Gag> data = new ArrayList<Gag>();
    private Map<String, String> paging;

    public ArrayList<Gag> getData() {
        return data;
    }

    public void setData(ArrayList<Gag> data) {
        this.data = data;
    }

    public Map<String, String> getPaging() {
        return paging;
    }

    public void setPaging(Map<String, String> paging) {
        this.paging = paging;
    }
}
