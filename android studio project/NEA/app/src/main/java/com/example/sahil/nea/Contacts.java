package com.example.sahil.nea;

/**
 * Created by Sahil on 30-04-2017.
 */
public class Contacts {

    private String name,location,time;

    public Contacts(String name,String location , String time)
    {
        this.setName(name);
        this.setLocation(location);
        this.setTime(time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
