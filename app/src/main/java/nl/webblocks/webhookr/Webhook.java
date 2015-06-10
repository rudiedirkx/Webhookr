package nl.webblocks.webhookr;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webhook implements Serializable {
    public int id;
    public String name;
    public String url;

    public String paramTitle = "title";
    public String paramUrl = "url";

    public Webhook(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getUrlDomain() {
        Pattern p = Pattern.compile("//(.+)/");
        Matcher m = p.matcher(this.url);

        if (m.find()) {
            return m.group(1);
        }

        return "";
    }

    public String toString() {
        return name;
    }
}
