package characters;

import java.util.Objects;

public class Police {
    private Gun gun = new Gun();

    public Gun getGun() {
        return gun;
    }


    private final String policeName = "полицейские";

    public void fearOfRocket() {
        System.out.println(policeName + " боялись теперь и близко " + goToRocket() + ", а не то что " + shootNearRocket() + ".");
    }

    public String goToRocket() {
        return "подходить к ракете";
    }

    public String shootNearRocket() {
        return gun.createShoot() + " возле неё";
    }

    public void preventEvent() {
    }

    public void banishFromFactory() {
    }

    public class Gun {
        public String createShoot() {
            return "стрелять";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Police police = (Police) o;
        return Objects.equals(policeName, police.policeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policeName);
    }

    @Override
    public String toString() {
        return "Police{" +
                "policeName='" + "полиция" + '\'' +
                '}';
    }
}
