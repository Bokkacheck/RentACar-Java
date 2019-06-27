
package Klase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Statistika {

    private int brDana;
    private String automobil;
    private static int ukupnoDana = 0;

    public Statistika(int brDana, String automobil) {
        this.brDana = brDana;
        this.automobil = automobil;
    }

    public static void prikazStatistike() {
        Scanner ulaz = new Scanner(System.in);
        while (true) {
            System.out.println("Unesite pocetni datum za prikaz statistike ili < da se vratite nazad");
            String datumOd = ulaz.nextLine();
            if (datumOd.equals("<")) {
                return;
            }
            System.out.println("Unesite datum kraj rezervacije (primer: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ") ili < da se vratite nazad");
            String datumDo = ulaz.nextLine();
            if (datumDo.equals("<")) {
                return;
            }
            if (ValidacijaPodataka.validacijaStatistika(datumOd, datumDo)) {
                Date pocetak = new Date();
                Date kraj = new Date();
                try {
                    pocetak = new SimpleDateFormat("dd.MM.yyyy").parse(datumOd);
                    kraj = new SimpleDateFormat("dd.MM.yyyy").parse(datumDo);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                ArrayList<Statistika> statistika = racunajDane(Podaci.rezervacije, Podaci.automobili, pocetak, kraj);
                if(statistika.size()!=0){
                    ispisSttistike(statistika);
                }
            }
        }
    }

    public static ArrayList<Statistika> racunajDane(ArrayList<Rezervacija> rezervacije, ArrayList<Automobil> automobili, Date pocetak, Date kraj) {
        ArrayList<Statistika> statistika = new ArrayList<Statistika>();
        ukupnoDana = 0;
        int ukupnoAuto = 0;
        Rezervacija.sortirajRezervacije();
        for (int i = 0; i < automobili.size(); i++) {
            ukupnoAuto = 0;
            for (int j = 0; j < rezervacije.size(); j++) {
                if (automobili.get(i).getIdAuta() == rezervacije.get(j).getIdAuta()) {
                    if (!rezervacije.get(j).getPocetak().before(pocetak) && !rezervacije.get(j).getPocetak().after(kraj)) {
                        if (!rezervacije.get(j).getKraj().after(kraj)) {
                            ukupnoAuto += (int) Rezervacija.brojDana(rezervacije.get(j).getKraj(), rezervacije.get(j).getPocetak());
                        } else {
                            ukupnoAuto += (int) Rezervacija.brojDana(kraj, rezervacije.get(j).getPocetak());
                        }
                    } else if (!rezervacije.get(j).getKraj().before(pocetak) && !pocetak.before(rezervacije.get(j).getPocetak())) {
                        if (!rezervacije.get(j).getKraj().after(kraj)) {
                            ukupnoAuto += (int) Rezervacija.brojDana(rezervacije.get(j).getKraj(), pocetak);
                        } else if (rezervacije.get(j).getKraj().after(kraj)) {
                            ukupnoAuto += (int) Rezervacija.brojDana(kraj, pocetak);
                        }
                    }
                }
            }
            if (ukupnoAuto != 0) {
                statistika.add(new Statistika(ukupnoAuto, automobili.get(i).getIdAuta() + " " + automobili.get(i).getMarka() + " " + automobili.get(i).getModel()));
                ukupnoDana += ukupnoAuto;
            }
        }
        return statistika;
    }

    private static void ispisSttistike(ArrayList<Statistika> statistika) {
        System.out.println("Statistika:");
        for(int i = 0;i<statistika.size();i++){
            System.out.println("Automobil: "+statistika.get(i).automobil+" Broj dana: "+statistika.get(i).brDana+" Procenat "+Math.round((((double)statistika.get(i).brDana/ukupnoDana)*100.0)*100.0)/100.0+"%");
        }
    }
}
