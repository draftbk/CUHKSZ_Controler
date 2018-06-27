package com.example.lfs.cuhksz_controler.entity;

/**
 * Created by lfs on 2018/6/10.
 */

public class Temperature {
    private String id;
    private String temperature;
    private String time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Temperature(String id, String temperature, String time) {
        this.id = id;
        this.temperature = temperature;
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
