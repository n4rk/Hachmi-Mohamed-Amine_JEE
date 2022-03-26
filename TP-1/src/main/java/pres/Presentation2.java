package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("config.txt"));

        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.newInstance();
        metier.setDao(dao);
        System.out.println("Résultat : " + metier.calcul());
    }
}
