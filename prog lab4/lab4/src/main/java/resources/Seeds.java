package resources;

import java.util.Objects;

public class Seeds extends Resource {
    private String name="семена";
    public Seeds() {
        super("семена");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seeds seeds = (Seeds) o;
        return Objects.equals(name, seeds.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Seeds{" +
                "name='" + name + '\'' +
                '}';
    }
}
