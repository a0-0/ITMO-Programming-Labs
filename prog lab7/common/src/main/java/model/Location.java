package model;

import java.io.Serializable;

public class Location implements Serializable {
    private long x;
    private int y;
    private Integer z; //Поле не может быть null

    public Location() {}

    public Location(long x, int y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
