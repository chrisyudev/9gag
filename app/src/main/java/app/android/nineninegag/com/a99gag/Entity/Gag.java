package app.android.nineninegag.com.a99gag.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chrisyu on 15/12/8.
 */
public class Gag {
    private String id;
    private String caption;
    private String link;
    private Map<String, String> images = new HashMap<>();
    private Map<String, Integer> votes = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }
}
