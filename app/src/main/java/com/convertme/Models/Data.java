package com.convertme.Models;

import java.io.Serializable;

/**
 * Created by Dias on 2/28/2017.
 */
public class Data implements Serializable {
    private String format;
    private String quality;
    private String size;
    private String link;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
