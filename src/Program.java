
import java.util.ArrayList;
import Klase.*;
import com.google.gson.reflect.TypeToken;

public class Program {

    public static void main(String[] args) {
//        ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
//        korisnici.add(new Administrator("Milovan", "Srejic", "milovan", "milovan", "31.01.1998", "Admin"));
//        korisnici.add(new Kupac("Bojan", "Stojkovic", "bojan", "bojan", "14.09.1997", "Kupac"));
//        korisnici.add(new Kupac("Nikola", "Jovicic", "nikola", "nikola", "14.09.1997", "Kupac"));
//        korisnici.add(new Kupac("Jelena", "Mi1tic", "jelena", "jelena", "14.09.1997", "Kupac"));
//        RadDatoteka.upisiKorisnike(korisnici, "korisnici.json");
//        ArrayList<Automobil> automobili = new ArrayList<Automobil>();
//        automobili.add(new Automobil(1,"Opel", "Astra", "manuelni", "benzin", "1800cm3", "karavan", 2001));
//        automobili.add(new Automobil(2,"Opel", "Corsa", "manuelni", "Benzin + Gas(TNG)", "1400cm3", "karavan", 2002));
//        automobili.add(new Automobil(3,"Audi", "A4", "automatski", "dizel", "3000cm3", "karavan", 2010));
//        automobili.add(new Automobil(4,"BMW", "X5", "automatski", "dizel", "3000cm3", "karavan", 2012));
//        RadDatoteka.<Automobil>upisiPodatke(automobili, "automobili.json");
//        ArrayList<Ponuda> ponude = new ArrayList<Ponuda>();
//        ponude.add(new Ponuda(1, 1, "16.06.2019", "30.06.2019", 1300));
//        ponude.add(new Ponuda(2, 2, "02.06.2019", "15.06.2019", 1800));
//        ponude.add(new Ponuda(3, 3, "02.06.2019", "15.06.2019", 2500));
//        ponude.add(new Ponuda(4, 1, "1.06.2019", "15.06.2019", 1300));
//        ponude.add(new Ponuda(5, 2, "02.07.2019", "20.07.2019", 1650));
//        RadDatoteka.<Ponuda>upisiPodatke(ponude, "ponude.json");
//        ArrayList<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
//        rezervacije.add(new Rezervacija(1, "rile", "05.05.2019", "12.06.2019", 0));
//        rezervacije.add(new Rezervacija(1, "rile", "14.06.2019", "25.06.2019", 0));
//        rezervacije.add(new Rezervacija(1, "rile", "30.06.2019", "30.06.2019", 0));
//        RadDatoteka.<Rezervacija>upisiPodatke(rezervacije, "rezervacije.json");
        RadDatoteka.napuniPodatke();
        (new Logovanje()).prikaziMeni();
    }
}
