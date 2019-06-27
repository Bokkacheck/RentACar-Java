
package Klase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rezervacija {

    private int idAuta;
    private String korisnickoIme;
    private Date pocetak;
    private Date kraj;
    private double cena;

    public Rezervacija(int idAuta, String korisnickoIme, String pocetak, String kraj, double cena) {
        this.idAuta = idAuta;
        this.korisnickoIme = korisnickoIme;
        try {
            this.pocetak = new SimpleDateFormat("dd.MM.yyyy").parse(pocetak);
            this.kraj = new SimpleDateFormat("dd.MM.yyyy").parse(kraj);
        } catch (ParseException ex) {
            System.out.println("Greske! Neispravan format datuma.");
        }
        this.cena = cena;
    }

    public int getIdAuta() {
        return idAuta;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public double getCena() {
        return cena;
    }

    @Override
    public String toString() {
        String auto = "";
        for (int i = 0; i < Podaci.automobili.size(); i++) {
            if (Podaci.automobili.get(i).getIdAuta() == this.idAuta) {
                auto += Podaci.automobili.get(i).getMarka() + " " + Podaci.automobili.get(i).getModel();
            }
            }
            return auto +  " " + new SimpleDateFormat("dd.MM.yyyy").format(pocetak) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(kraj) + " cena:" + cena;
        }

    

    public static ArrayList<Rezervacija> rezervacijeZaAuto(int idAuta) {
        ArrayList<Rezervacija> oRezeravacije = new ArrayList<Rezervacija>();
        for (int i = 0; i < Podaci.rezervacije.size(); i++) {
            if (Podaci.rezervacije.get(i).idAuta == idAuta) {
                oRezeravacije.add(Podaci.rezervacije.get(i));
            }
        }
        return oRezeravacije;
    }

    public static void sortirajRezervacije() {
        for (int i = 0; i < Podaci.rezervacije.size() - 1; i++) {
            for (int j = i + 1; j < Podaci.rezervacije.size(); j++) {
                if (Podaci.rezervacije.get(i).idAuta > Podaci.rezervacije.get(j).idAuta || (Podaci.rezervacije.get(i).idAuta == Podaci.rezervacije.get(j).idAuta && Podaci.rezervacije.get(i).pocetak.after(Podaci.rezervacije.get(j).pocetak))) {
                    Rezervacija pom = Podaci.rezervacije.get(i);
                    Podaci.rezervacije.set(i, Podaci.rezervacije.get(j));
                    Podaci.rezervacije.set(j, pom);
                }
            }
        }
    }

    public static void rezervisiAuto() {
        Scanner ulaz = new Scanner(System.in);
        ArrayList<Ponuda> iPonude = Podaci.iPonude;
        if (iPonude.size() != 0) {
            while (true) {
                System.out.println("Unesite datum pocetka rezervacije (primer: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ") ili < da se vratite nazad");
                String datumOd = ulaz.nextLine();
                if (datumOd.equals("<")) {
                    return;
                }
                System.out.println("Unesite datum kraj rezervacije (primer: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ") ili < da se vratite nazad");
                String datumDo = ulaz.nextLine();
                if (datumDo.equals("<")) {
                    return;
                }
                if (ValidacijaPodataka.validacijaRezervisanje(datumOd, datumDo)) {
                    Date pocetak = new Date();
                    Date kraj = new Date();
                    try {
                        pocetak = new SimpleDateFormat("dd.MM.yyyy").parse(datumOd);
                        kraj = new SimpleDateFormat("dd.MM.yyyy").parse(datumDo);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    double ukupnaCena = 0.0;
                    for (int i = 0; i < iPonude.size(); i++) {
                        ukupnaCena = 0.0;
                        if (!pocetak.before(iPonude.get(i).getDatumOd()) && !pocetak.after(iPonude.get(i).getDatumDo())) {
                            int brojac = 0;
                            for (int j = i; j < iPonude.size(); j++) {
                                if (iPonude.get(i).getIdAuta() == iPonude.get(j).getIdAuta()) {
                                    if (i == j || brojDana(iPonude.get(j).getDatumOd(), iPonude.get(i + brojac++).getDatumDo()) == 1) {
                                        ukupnaCena = ukupnaCena + iPonude.get(j).getCenaDan() * ((brojDana(iPonude.get(j).getDatumDo(), iPonude.get(j).getDatumOd()) + 1));
                                        if (!iPonude.get(j).getDatumDo().before(kraj)) {
                                            ukupnaCena = ukupnaCena - iPonude.get(i).getCenaDan() * (brojDana(pocetak, iPonude.get(i).getDatumOd()));
                                            ukupnaCena = ukupnaCena - iPonude.get(j).getCenaDan() * (brojDana(iPonude.get(j).getDatumDo(), kraj));
                                            ubaciRezervaciju(iPonude.get(i).getIdAuta(), pocetak, kraj, datumOd, datumDo, ukupnaCena);
                                            return;
                                        }
                                    } else {
                                        i = j;
                                        break;
                                    }
                                } else {
                                    i = j;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("Nije moguce rezrevisati automobil u trazenom terminu.");
                }
            }
        }
    }

    public static int brojDana(Date prvi, Date drugi) {
        return (int) ((prvi.getTime() - drugi.getTime()) / 86400000);
    }

    private static void ubaciRezervaciju(int idbrAuta, Date pocetak, Date kraj, String datumOd, String datumDo, double ukupnaCena) {
        boolean nadjen = false;
        for (int k = 0; k < Podaci.rezervacije.size(); k++) {
            if (Podaci.rezervacije.get(k).idAuta == idbrAuta && pocetak.before(Podaci.rezervacije.get(k).pocetak) && !nadjen) {
                nadjen = true;
                Podaci.rezervacije.add(Podaci.rezervacije.get(Podaci.rezervacije.size() - 1));
                for (int m = Podaci.rezervacije.size() - 2; m >= k; m--) {
                    System.out.println("1");
                    Podaci.rezervacije.set(m + 1, Podaci.rezervacije.get(m));
                }
                System.out.println("2");
                Podaci.rezervacije.set(k, new Rezervacija(idbrAuta, Korisnik.ulogovanKorisnik, datumOd, datumDo, ukupnaCena));
            }
        }
        if (!nadjen) {
            Podaci.rezervacije.add(new Rezervacija(idbrAuta, Korisnik.ulogovanKorisnik, datumOd, datumDo, ukupnaCena));
        }
        System.out.println("Rezervacija uspesno dodata.");
        System.out.println("Ukupna cena rezervacije je " + ukupnaCena + "din.");
    }
}
