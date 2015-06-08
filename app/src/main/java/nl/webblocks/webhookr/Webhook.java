package nl.webblocks.webhookr;

import java.io.Serializable;

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

    public String toString() {
        return name;
    }
}
