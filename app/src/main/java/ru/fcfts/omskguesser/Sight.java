package ru.fcfts.omskguesser;

import java.io.Serializable;

public class Sight implements Serializable {
    private int id;
    private double[] coords;
    private String name;
    private String fact;

    public Sight(int id,double[] coords, String name,String fact) {
        this.id = id;
        this.coords = coords;
        this.fact = fact;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public double[] getCoords() {
        return coords;
    }

    public String getFact() {
        return fact;
    }

    public String getName() {
        return name;
    }
}

