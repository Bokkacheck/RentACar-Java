
package Klase;

import java.util.ArrayList;

public class Logovanje implements Meni {

    private static ArrayList<Korisnik> korisnici = Podaci.korisnici;

    @Override
    public void prikaziMeni() {
        while (true) {
            System.out.println("Dobrodosli u Rent a Car agenciju");
            System.out.println("--------------------------------");
            System.out.println("Odaberi opciju:");
            System.out.println("1. Uloguj se");
            System.out.println("2. Izađi iz programa");
            System.out.println("Unesi opciju:");
            if (obradiOpciju(ulaz.nextLine())) {
                return;
            }
        }
    }

    @Override
    public boolean obradiOpciju(String opcija) {
        switch (opcija) {
            case "1":
                Logovanje.prijaviSe();
                return false;
            case "2":
                RadDatoteka.sacuvajPromene();
                return true;
            default:
                System.out.println("Uneli ste pogresnu opciju!");
                return false;
        }
    }

    public static void prijaviSe() {
        while (true) {
            System.out.println("Unesite Vaše korisničko ime ili < da se vratite nazad:");
            String korisnickoIme = ulaz.nextLine();
            if (korisnickoIme.equals("<")) {
                return;
            }
            System.out.println("Unesite Vašu lozinku ili < da se vratite nazad: ");
            String lozinka = ulaz.nextLine();
            if (lozinka.equals("<")) {
                return;
            }
            for (Korisnik k : korisnici) {
                if (k.korisnickoIme.equals(korisnickoIme) && k.lozinka.equals(lozinka)) {
                    Korisnik.ulogovanKorisnik = k.korisnickoIme;
                    if (k.tip.equals("Kupac")) {
                        (new KorisnikMeni()).prikaziMeni();
                        return;
                    } else if (k.tip.equals("Admin")) {
                        (new AdministratorMeni()).prikaziMeni();
                        return;
                    }
                }
            }
            System.out.println("Nije pronađen korisnik sa ovim korisničkim imenom i lozinkom!");
        }
    }
}
