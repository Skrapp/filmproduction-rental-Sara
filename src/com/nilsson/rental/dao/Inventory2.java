package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory2 {
    private List<Accessory> accessories;
    private List<Camera> cameras;
    private List<Light> lights;
    private List<Microphone> microphones;

    public Inventory2() {
        accessories = new ArrayList<>();
        cameras = new ArrayList<>();
        lights = new ArrayList<>();
        microphones = new ArrayList<>();
    }

    public Inventory2(List<Accessory> accessories, List<Camera> cameras, List<Light> lights, List<Microphone> microphones) {
        this.accessories = accessories;
        this.cameras = cameras;
        this.lights = lights;
        this.microphones = microphones;
    }



    //TODO sortera enligt brand
}
