package co.infinum.academy.danijel_sokac.task2;

/**
 * Created by Danijel on 7.7.2015..
 */
public class UrlParser {
    public String parseUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        return url;
    }
}
