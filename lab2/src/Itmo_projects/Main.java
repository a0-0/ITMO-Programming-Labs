package Itmo_projects;

import Itmo_projects.pokemon.*;

import ru.ifmo.se.pokemon.Battle;


public class Main {

    public static void main(String[] args) {
        Battle b = new Battle();
        Slugma p1 = new Slugma("Player1", 1);
        Magcargo p2 = new Magcargo("Player2", 1);
        Dusclops p3 =new Dusclops("Player3", 1);
        b.addAlly(p1);
        b.addFoe(p2);
        b.addFoe(p3);
        b.go();

    }
}
