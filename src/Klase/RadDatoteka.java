package Klase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

public class RadDatoteka {

    public static <T> void upisiPodatke(ArrayList<T> lista, String datoteka) {
        try {
            Gson gs = new Gson();
            FileWriter fw = new FileWriter(datoteka);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gs.toJson(lista));
            bw.close();
            fw.close();
        } catch (Exception exp) {
            System.out.println("Došlo je do greške prilikom upisa podataka!");
        }
    }

    public static <T> ArrayList<T> citajPodatke(String datoteka, TypeToken token) {
        try {
            Gson gs = new Gson();
            FileReader fr = new FileReader(datoteka);
            BufferedReader br = new BufferedReader(fr);
            String tekst = "";
            String pomoc = "";
            while ((pomoc = br.readLine()) != null) {
                tekst += pomoc;
            }
            br.close();
            fr.close();
            ArrayList<T> lista = new ArrayList<T>();
            lista = gs.fromJson(tekst, token.getType());
            return lista;
        } catch (Exception exp) {
            System.out.println("Došlo je do greške prilikom čitanja podataka!");
            return new ArrayList<T>();
        }
    }

    public static ArrayList<Korisnik> citajKorisnike(String datoteka) {
        try {
            ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
            Gson gs = new Gson();
            FileReader fr = new FileReader(datoteka);
            BufferedReader br = new BufferedReader(fr);
            String tekst = "";
            while ((tekst = br.readLine()) != null) {
                Korisnik k = gs.fromJson(tekst, Kupac.class);
                if (k.tip.equals("admin")) {
                    korisnici.add(new Administrator(k.ime, k.prezime, k.korisnickoIme, k.lozinka, k.datRodjenja, k.tip));
                } else {
                    korisnici.add(new Kupac(k.ime, k.prezime, k.korisnickoIme, k.lozinka, k.datRodjenja, k.tip));
                }
            }
            br.close();
            fr.close();
            return korisnici;
        } catch (Exception exp) {
            System.out.println("Došlo je do greške prilikom čitanja podataka!");
            return new ArrayList<Korisnik>();
        }
    }

    public static void upisiKorisnike(ArrayList<Korisnik> korisnici, String datoteka) {
        try {
            Gson gs = new Gson();
            FileWriter fw = new FileWriter(datoteka);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Korisnik k : korisnici) {
                bw.write(gs.toJson(k));
                bw.write("\n");
            }
            bw.close();
            fw.close();
        } catch (Exception exp) {
            System.out.println("Došlo je do greške prilikom upisa podataka!");
        }
    }

    public static void sacuvajPromene() {
        RadDatoteka.<Automobil>upisiPodatke(Podaci.automobili, "automobili.json");
        RadDatoteka.<Ponuda>upisiPodatke(Podaci.ponude, "ponude.json");
        RadDatoteka.upisiKorisnike(Podaci.korisnici, "korisnici.json");
        RadDatoteka.<Rezervacija>upisiPodatke(Podaci.rezervacije, "rezervacije.json");
    }

    public static void napuniPodatke() {
        try {
            File f = new File("korisnici.json");
            if (!f.exists()) {
                Podaci.korisnici.add(new Administrator("Milovan", "Srejic", "milovan", "milovan", "31.01.1998", "Admin"));
                Podaci.korisnici.add(new Administrator("Bojan", "Stojkovic", "bojan", "bojan", "14.09.1997", "Admin"));
                RadDatoteka.upisiKorisnike(Podaci.korisnici, "korisnici.json");
            }
            f = new File("automobili.json");
            if (!f.exists()) {
                RadDatoteka.<Automobil>upisiPodatke(Podaci.automobili, "automobili.json");
            }
            f = new File("ponude.json");
            if (!f.exists()) {
                RadDatoteka.<Ponuda>upisiPodatke(Podaci.ponude, "ponude.json");
            }
            f = new File("rezervacije.json");
            if (!f.exists()) {
                RadDatoteka.<Rezervacija>upisiPodatke(Podaci.rezervacije, "rezervacije.json");
            }
        } catch (Exception ex) {
            System.out.println("Došlo je do greške prilikom rada sa datotekama");
        }
        Podaci.korisnici = RadDatoteka.citajKorisnike("korisnici.json");
        Podaci.automobili = RadDatoteka.citajPodatke("automobili.json", new TypeToken<ArrayList<Automobil>>() {
        });
        Podaci.ponude = RadDatoteka.citajPodatke("ponude.json", new TypeToken<ArrayList<Ponuda>>() {
        });
        Podaci.rezervacije = RadDatoteka.citajPodatke("rezervacije.json", new TypeToken<ArrayList<Rezervacija>>() {
        });
        Ponuda.sortirajPonude();
        Rezervacija.sortirajRezervacije();

    }

    public static void upisi() {

    }

}
