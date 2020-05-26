package com.example.recycleview;

public class ListItem {

    private String heading;
    private String desc;

    public ListItem(String heading, String desc) {
        this.heading = heading;
        this.desc = desc;
    }

    public String getHeading() {
        return heading;
    }

    public String getDesc() {
        return desc;
    }
}
