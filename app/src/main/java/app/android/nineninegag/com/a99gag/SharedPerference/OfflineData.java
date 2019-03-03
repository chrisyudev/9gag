package app.android.nineninegag.com.a99gag.SharedPerference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chrisyu on 15/12/13.
 */
public class OfflineData {
    public static void save(String key, String storage, Context mContext) {
        SharedPreferences.Editor editor = ((Activity)mContext).getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString(key, storage);
        editor.apply();
    }

    public static String get(String key, Context mContext) {
        SharedPreferences prefs = ((Activity)mContext).getPreferences(Context.MODE_PRIVATE);
        return prefs.getString(key , null);
    }
}
