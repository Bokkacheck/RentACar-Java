package Klase;

import java.util.ArrayList;

public class Kupac extends Korisnik {

    public Kupac(String ime, String prezime, String korisnickoIme, String lozinka, String datRodjenja, String tip) {
        super(ime, prezime, korisnickoIme, lozinka, datRodjenja, tip);
    }

    public static void odabirAutomobila() {
        ArrayList<Ponuda> ponude = Podaci.ponude;
        ArrayList<Automobil> automobili = Podaci.automobili;
        while (true) {
            System.out.println("Marke Automobila koje postoje");
            izlaz:
            for (int i = 0; i < automobili.size(); i++) {
                for (int j = i + 1; j < automobili.size(); j++) {
                    if (automobili.get(i).getMarka().equals(automobili.get(j).getMarka())) {
                        continue izlaz;
                    }
                }
                System.out.println(automobili.get(i).getMarka());

            }
            System.out.println("Unesite marku automobila koju zelite ili < da se vratite nazad");
            String markaAuta = ulaz.nextLine();
            if (markaAuta.equals("<")) {
                return;
            }
            System.out.println("Automobili u ponudi za traznu marku");
            boolean provera = false;
            for (int i = 0; i < automobili.size(); i++) {
                if (automobili.get(i).getMarka().toLowerCase().equals(markaAuta.toLowerCase())) {
                    System.out.println(automobili.get(i));
                    provera = true;
                }
            }
            if (!provera) {
                System.out.println("Nema automobila za unetu marku.");
                continue;
            }
            while (true) {
                System.out.println("Unesite ID automobila koji zelite da rezervisete ili < da se vratite nazad");
                String idAuta = ulaz.nextLine();
                if (idAuta.equals("<")) {
                    break;
                }
                if (ValidacijaPodataka.validacijaIdAutaKupac(idAuta, markaAuta)) {
                    Ponuda.iseciPonudee(Integer.parseInt(idAuta));
                    Rezervacija.rezervisiAuto();
                    return;
                }
            }
        }
    }

    public static void prikazBrisanjeRezervacije(String korisnickoIme) {
        while (true) {
            System.out.println("Rezervacije kupca " + korisnickoIme + ":");
            ArrayList<Rezervacija> rezervacije = Podaci.rezervacije;
            int brojac = 0;
            for (int i = 0; i < rezervacije.size(); i++) {
                if (rezervacije.get(i).getKorisnickoIme().equals(korisnickoIme)) {
                    System.out.println(++brojac + "." + rezervacije.get(i));
                }
            }
            if (brojac == 0) {
                System.out.println("Nema rezervacija.");
                return;
            }
            System.out.println("Unesite redni broj rezervacije koju zelite da obrisete ili < da se vratite nazad");
            String tekst = ulaz.nextLine();
            if (tekst.equals("<")) {
                return;
            }
            if (ValidacijaPodataka.validacijaRednogBroja(tekst, brojac)) {
                int broj = Integer.parseInt(tekst);
                int odbrojano = 0;
                for (int i = 0; i < rezervacije.size(); i++) {
                    if (rezervacije.get(i).getKorisnickoIme().equals(korisnickoIme)) {
                        odbrojano++;
                        if (odbrojano == broj) {
                            rezervacije.remove(i);
                            System.out.println("Uspesno obrisana rezervacija");
                        }
                    }
                }
            }

        }
    }

}
