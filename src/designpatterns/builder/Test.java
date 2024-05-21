package designpatterns.builder;

public class Test {
    public static void main(String[] args) {
        try {
            Classe classe1 = new Classe.ClasseBuilder()
                    .setId(2)
                    //.setSigle("SIGLE")
                    .setSpecialite("SPECIALITE")
                    //.setAnnee(1)
                    .setNbreEleve(10)
                    .build();
            System.out.println(classe1);
        } catch (Exception e) {
            System.out.println("erreur :" + e.getMessage());

        }
    }
}
