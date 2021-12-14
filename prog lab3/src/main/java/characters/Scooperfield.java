package characters;

import java.util.Objects;

public class Scooperfield {
    private String placeOfWork = "Директор макаронной фабрики";
    private String name = "Скуперфильд";

    public void banishByWorkers() {
        placeOfWork = "безработный";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooperfield that = (Scooperfield) o;
        return Objects.equals(placeOfWork, that.placeOfWork) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeOfWork, name);
    }

    @Override
    public String toString() {
        return "Scooperfield{" +
                "placeOfWork='" + placeOfWork + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
