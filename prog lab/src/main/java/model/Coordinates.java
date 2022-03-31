package model;

import java.util.Objects;

public class Coordinates {
    private double x;
    private Long y; //Максимальное значение поля: 311, Поле не может быть null

    public Coordinates() {}

    public Coordinates(double x, Long y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.getX(), getX()) == 0 && Objects.equals(getY(), that.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
