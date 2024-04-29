package designpatterns.composite;

import ecole.metier.Infos;

public class Test {
    public static void main(String[] args) {
        Section s1 = new Section(1, "SIO");
        Section s2 = new Section(2, "SLAM");
        Classe c1 = new Classe(1, 2020, "SIO", 30);
        Classe c2 = new Classe(2, 2020, "SLAM", 30);
        Classe c3 = new Classe(3, 2020, "SIO", 30);
        Classe c4 = new Classe(4, 2020, "SLAM", 30);
        s1.getElts().add(c1);
        s1.getElts().add(c2);
        s2.getElts().add(c3);
        s2.getElts().add(c4);
        s1.getElts().add(s2);
        System.out.println(s1);
    }
}
