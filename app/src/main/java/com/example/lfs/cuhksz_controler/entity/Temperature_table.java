package com.example.lfs.cuhksz_controler.entity;

/**
 * Created by lfs on 2018/6/15.
 */

public class Temperature_table {
    private float time;
    private float temperature;

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Temperature_table(float time, float temperature) {
        this.time = time;
        this.temperature = temperature;
    }
}
