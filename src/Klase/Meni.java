
package Klase;

import java.util.Scanner;
public interface Meni {

    Scanner ulaz = new Scanner(System.in);

    public void prikaziMeni();

    public boolean obradiOpciju(String opcija);

    public static void nastaviDalje() {
        System.out.println("Unesi bilo sta da se vratis nazad");
        ulaz.nextLine();
    }
}
