package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        // Instanciation statique
        /*
        MetierImpl metier = new MetierImpl();
        System.out.println(metier.calcul());
        !! Ceci nous donnera une Exception de type NullPointerException vu que :
        le paramètre 'dao' déclaré dans la classe MetierImpl a 'null' comme valeur
         */

        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println("Résultat : " + metier.calcul());
    }
}
