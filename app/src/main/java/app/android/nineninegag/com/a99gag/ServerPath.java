package app.android.nineninegag.com.a99gag;

/**
 * Created by chrisyu on 15/12/11.
 */
public class ServerPath {
    public static String rootDirectory = "http://infinigag.eu01.aws.af.cm/";
    public static String post_traumatic = "https://next.json-generator.com/api/json/get/VkF3Ip4LI";
    public static String hotListing = post_traumatic;// rootDirectory + "hot";
    public static String trendingListing =  post_traumatic;//rootDirectory + "trending";
    public static String freshListing = post_traumatic;//rootDirectory + "fresh";
    public static String voteListing = rootDirectory + "vote";

    public static String [] listing = {hotListing, trendingListing, freshListing
    };

    public static String loadFromIndexZero = "/0";

    public static String loadFromIndexN(String n) {
        return "/" + n;
    }

    public static String getAPIPath(int pageNum) {
        if(pageNum == 0) {
            return ServerPath.hotListing + ServerPath.loadFromIndexZero;
        } else if(pageNum == 1) {
            return ServerPath.trendingListing + ServerPath.loadFromIndexZero;
        } else if(pageNum == 2) {
            return ServerPath.freshListing + ServerPath.loadFromIndexZero;
        }
        return "";
    }
}
