package designpatterns.builder;

public class Test {
    public static void main(String[] args) {
        try {
            Classe classe = new Classe.ClasseBuilder()
                    .setId(1)
                    .setSigle("SIGLE")
                    .setAnnee(2021)
                    .setSpecialite("SPECIALITE")
                    .setNbreEleve(10)
                    .build();
            System.out.println(classe);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        try {
            Classe classe1 = new Classe.ClasseBuilder()
                    .setId(2)

                    .setSpecialite("SPECIALITE")
                    .setNbreEleve(10)
                    .build();
            System.out.println(classe1);
        } catch (Exception e) {
            System.out.println("e :" +e.getMessage());

        }
    }
}
