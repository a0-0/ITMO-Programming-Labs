package resources;

import java.util.Objects;

public class Antilunite extends Resource {
    private String name="антилунит";
    public Antilunite() {
        super("антилунитом");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Antilunite that = (Antilunite) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Antilunite{" +
                "name='" + name + '\'' +
                '}';
    }
}
