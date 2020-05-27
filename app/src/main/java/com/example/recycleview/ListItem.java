package com.example.recycleview;

public class ListItem {

    private String imageURL;
    private String heading;
    private String desc;

    public ListItem(String imageURL, String heading, String desc) {
        this.imageURL = imageURL;
        this.heading = heading;
        this.desc = desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getHeading() {
        return heading;
    }

    public String getDesc() {
        return desc;
    }
}
