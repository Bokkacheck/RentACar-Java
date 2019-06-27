package Klase;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;

public class Administrator extends Korisnik {

    public Administrator(String ime, String prezime, String korisnickoIme, String lozinka, String datRodjenja, String tip) {
        super(ime, prezime, korisnickoIme, lozinka, datRodjenja, tip);
    }

    public static void dodajKorisnika() {
        int brojac = 0;
        String ime = "";
        String prezime = "";
        String korisnickoIme = "";
        String lozinka = "";
        String tip = "";
        String datRodj = "";
        izlaz :
        while (true) {
            switch (brojac) {
                case 0:
                    try {
                        System.out.println("Unesite ime korisnika ili < da se vratite nazad");
                        ime = ulaz.nextLine();
                        if (ime.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaImePrezime(ime);
                        brojac++;
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 1:
                    try {
                        System.out.println("Unesite prezime korisnika ili < da se vratite nazad");
                        prezime = ulaz.nextLine();
                        if (prezime.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaImePrezime(prezime);
                        brojac++;
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Unesite korisnicko ime ili < da se vratite nazad");
                        korisnickoIme = ulaz.nextLine();
                        if (korisnickoIme.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaKorIme(korisnickoIme);
                        for(int i=0;i<Podaci.korisnici.size();i++){
                            if(Podaci.korisnici.get(i).getKorisnickoIme().equals(korisnickoIme)){
                                System.out.println("Greska! Vec postoji sa ovim korisnickim imenom.");
                                continue izlaz;
                            }
                        }
                        brojac++;
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Unesite lozinku ili < da se vratite nazad");
                        lozinka = ulaz.nextLine();
                        if (lozinka.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaLozinka(lozinka);
                        System.out.println("Potvrdite lozinku");
                        String lozinkaPom = ulaz.nextLine();
                        if (lozinka.equals(lozinkaPom)) {
                            brojac++;
                        } else {
                            System.out.println("Lozinke se ne poklapaju");
                        }
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Unesite datum rodjenja (primer: 01.01.1998) ili < da se vratite nazad");
                    datRodj = ulaz.nextLine();
                    if (datRodj.equals("<")) {
                        return;
                    }
                    if (ValidacijaPodataka.validacijaDatumaRodjenja(datRodj)) {
                        brojac++;
                    }
                    break;
                case 5:
                    System.out.println("Odaberite tip korisnika ili < da se vratite nazad");
                    System.out.println("1. Kupac");
                    System.out.println("2. Administrator");
                    tip = ulaz.nextLine();
                    if (tip.equals("<")) {
                        return;
                    } else if (tip.equals("1")) {
                        tip = "Kupac";
                        Podaci.korisnici.add(new Kupac(ime, prezime, korisnickoIme, lozinka, datRodj, tip));

                    } else if (tip.equals("2")) {
                        tip = "Admin";
                        Podaci.korisnici.add(new Administrator(ime, prezime, korisnickoIme, lozinka, datRodj, tip));
                    } else {
                        System.out.println("Greska! Neispravna opcija");
                        break;
                    }
                    brojac++;
                    break;
                default:
                    System.out.println("Korisnik uspesno dodat.");
                    Meni.nastaviDalje();
                    return;
            }
        }
    }

    public static void obrisiKorisnika() {
        while (true) {
            Korisnik.prikaziKorisnike();
            System.out.println("Unesite korisnicko ime korisnka kojeg zelite da obrisete ili < da se vratite nazad");
            String korisnickoIme = ulaz.nextLine();
            if (korisnickoIme.equals("<")) {
                return;
            }
            if (korisnickoIme.equals(ulogovanKorisnik)) {
                System.out.println("Ne možete izbrisati sami sebe");
                continue;
            }
            for (int i = 0; i < Podaci.korisnici.size(); i++) {
                if (korisnickoIme.equals(Podaci.korisnici.get(i).korisnickoIme)) {
                    Podaci.korisnici.remove(i);
                    System.out.println("Korisnik uspešno izbrisan");
                    break;
                }
                if(i==Podaci.korisnici.size()-1){
                    System.out.println("Uneto korisničko ime ne postoji");
                }
            }            
        }

    }
    public static void prikazBrisanjeRezervacija(){        
        izlaz :
        while(true){
            Korisnik.prikaziKupce();
            System.out.println("Unesite korisnicko ime kupca ili < da se vratite nazad");
            String korIme = ulaz.nextLine();
            if(korIme.equals("<")){
                return;
            }
            for(int i=0;i<Podaci.korisnici.size();i++){
                if(Podaci.korisnici.get(i) instanceof Kupac && Podaci.korisnici.get(i).getKorisnickoIme().equals(korIme)){
                    Kupac.prikazBrisanjeRezervacije(korIme);
                    continue izlaz;
                }
            }
            System.out.println("Nije pronadjen kupac sa unetim korisnickim imenom.");
        }
    }
}
