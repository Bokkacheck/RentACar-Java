package Klase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ponuda {

    static Scanner ulaz = new Scanner(System.in);
    private int idPonuda;
    private int idAuta;
    private Date datumOd;
    private Date datumDo;
    private double cenaDan;

    public Ponuda(int idPonuda, int idAuta, String datumOd, String datumDo, double cenaDan) {

        this.idPonuda = idPonuda;
        this.idAuta = idAuta;
        try {
            this.datumOd = new SimpleDateFormat("dd.MM.yyyy").parse(datumOd);
            this.datumDo = new SimpleDateFormat("dd.MM.yyyy").parse(datumDo);
        } catch (ParseException ex) {
            System.out.println("Greska! Neispravan format datuma.");
        }
        this.cenaDan = cenaDan;
    }

    public Ponuda(int idPonuda, int idAuta, Date datumOd, Date datumDo, double cenaDan) {
        this.idPonuda = idPonuda;
        this.idAuta = idAuta;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.cenaDan = cenaDan;
    }

    public int getIdAuta() {
        return idAuta;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public double getCenaDan() {
        return cenaDan;
    }

    @Override
    public String toString() {
        return "ID Ponude: " + idPonuda + " ID Auta: " + idAuta + " " + new SimpleDateFormat("dd.MM.yyyy").format(datumOd) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(datumDo) + " cena po danu:" + cenaDan;
    }

    public static void prikaziPonude() {
        System.out.println("Prikaz ponuda:");
        for (int i = 0; i < Podaci.ponude.size(); i++) {
            System.out.println(Podaci.ponude.get(i));
        }
    }

    public static void prikaziPonudeZaAuto(int idAuta) {
        System.out.println("Ponude za odabrani auto");
        for (int i = 0; i < Podaci.ponude.size(); i++) {
            if (idAuta == Podaci.ponude.get(i).idAuta) {
                System.out.println(Podaci.ponude.get(i));
            }
        }
    }

    public static void dodajPonudu() {
        int brojac = 0;
        String idAuta = "";
        String datumOd = "";
        String datumDo = "";
        String cenaDan = "";
        while (true) {
            switch (brojac) {
                case 0:
                    System.out.println("Unesite ID automobila ili < da se vratite nazad");
                    idAuta = ulaz.nextLine();
                    if (idAuta.equals("<")) {
                        return;
                    }
                    if (ValidacijaPodataka.validacijaIdAuta(idAuta)) {
                        brojac++;
                    }
                    break;
                case 1:
                    prikaziPonudeZaAuto(Integer.parseInt(idAuta));
                    System.out.println("Unesite datum pocetka ponude (primer: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ") ili < da se vratite nazad");
                    datumOd = ulaz.nextLine();
                    if (datumOd.equals("<")) {
                        return;
                    }
                    System.out.println("Unesite datum kraja ponude (primer: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ") ili < da se vratite nazad");
                    datumDo = ulaz.nextLine();
                    if (datumDo.equals("<")) {
                        return;
                    }
                    if (ValidacijaPodataka.validacijaDatumaPonuda(datumOd, datumDo, idAuta)) {
                        brojac++;
                    }
                    break;
                case 2:
                    System.out.println("Unesite cenu po danu ili < da se vratite nazad");
                    cenaDan = ulaz.nextLine();
                    if (cenaDan.equals("<")) {
                        return;
                    }
                    if (ValidacijaPodataka.validacijaCeneDan(cenaDan)) {
                        brojac++;
                    }
                    break;
                default:
                    int idPonuda = Podaci.ponude.size() == 0 ? 1 : Podaci.ponude.get(Podaci.ponude.size() - 1).idPonuda + 1;
                    Podaci.ponude.add(new Ponuda(idPonuda, Integer.parseInt(idAuta), datumOd, datumDo, Double.parseDouble(cenaDan)));
                    System.out.println("Ponuda uspesno dodata.");
                    Meni.nastaviDalje();
                    return;
            }
        }
    }

    public static void obrisiPonudu() {
        while (true) {
            prikaziPonude();
            System.out.println("Unesite ID ponude koju zelite da obrisete ili < da se vratite nazad");
            String id = ulaz.nextLine();
            if (id.equals("<")) {
                return;
            }
            for (int i = 0; i < Podaci.ponude.size(); i++) {
                if ((Podaci.ponude.get(i).idPonuda + "").equals(id)) {
                    Podaci.ponude.remove(i);
                    System.out.println("Ponuda obrisana");
                    break;
                }
                if (i == Podaci.ponude.size() - 1) {
                    System.out.println("Unet ID ponude ne postoji");
                }
            }
        }
    }

    public static void obrisiPonuduZaAuto(int id) {
        for (int i = 0; i < Podaci.ponude.size(); i++) {
            if (Podaci.ponude.get(i).idAuta == id) {
                Podaci.ponude.remove(i--);
            }
        }
    }

    public static void sortirajPonude() {
        for (int i = 0; i < Podaci.ponude.size() - 1; i++) {
            for (int j = i + 1; j < Podaci.ponude.size(); j++) {
                if (Podaci.ponude.get(i).idAuta > Podaci.ponude.get(j).idAuta || (Podaci.ponude.get(i).idAuta == Podaci.ponude.get(j).idAuta && Podaci.ponude.get(i).datumOd.after(Podaci.ponude.get(j).datumOd))) {
                    Ponuda pom = Podaci.ponude.get(i);
                    Podaci.ponude.set(i, Podaci.ponude.get(j));
                    Podaci.ponude.set(j, pom);
                }
            }
        }
    }

    public static ArrayList<Ponuda> ponudeZaAuto(int idAuta) {
        ArrayList<Ponuda> oPonude = new ArrayList<Ponuda>();
        for (int i = 0; i < Podaci.ponude.size(); i++) {
            if (Podaci.ponude.get(i).idAuta == idAuta) {
                oPonude.add(Podaci.ponude.get(i));
            }
        }
        return oPonude;
    }

    

    private static Date dodajDan(Date d, int broj) {
        try {
            Date d2 = new SimpleDateFormat("dd.MM.yyyy").parse(new SimpleDateFormat("dd.MM.yyyy").format(d));
            d2.setDate(d2.getDate() + broj);
            return d2;
        } catch (ParseException ex) {
            return null;
        }
    }

    public static void iseciPonudee(int idAuta) {
        ArrayList<Rezervacija> oRez = Rezervacija.rezervacijeZaAuto(idAuta);
        ArrayList<Ponuda> oPonude = Ponuda.ponudeZaAuto(idAuta);
        Date ponPoc, ponKraj, rezPoc, rezKraj;
        Podaci.iPonude.clear();
        for (int i = 0; i < oPonude.size(); i++) {
            boolean cela = true;
            for (int j = 0; j < oRez.size(); j++) {
                ponPoc = oPonude.get(i).datumOd;
                ponKraj = oPonude.get(i).datumDo;
                rezPoc = oRez.get(j).getPocetak();
                rezKraj = oRez.get(j).getKraj();
                if (!ponPoc.before(rezPoc) && !ponKraj.after(rezKraj)) {
                    cela = false;
                    break;
                } else if (!ponPoc.before(rezPoc) && !ponPoc.after(rezKraj) && ponKraj.after(rezKraj)) {
                    cela = false;
                    oPonude.set(i, new Ponuda(oPonude.get(i).idPonuda, oPonude.get(i).idAuta, dodajDan(rezKraj, 1), ponKraj, oPonude.get(i).cenaDan));
                    if (j == oRez.size() - 1 || ponKraj.before(oRez.get(j + 1).getPocetak())) {
                        Podaci.iPonude.add(oPonude.get(i));
                        break;
                    }
                } else if (!ponPoc.after(rezPoc) && !ponKraj.before(rezKraj)) {
                    cela = false;
                    oPonude.set(i, new Ponuda(oPonude.get(i).idPonuda, oPonude.get(i).idAuta, dodajDan(rezKraj, 1), ponKraj, oPonude.get(i).cenaDan));
                    Podaci.iPonude.add(new Ponuda(oPonude.get(i).idPonuda, oPonude.get(i).idAuta, ponPoc, dodajDan(rezPoc, -1), oPonude.get(i).cenaDan));
                    if (j == oRez.size() - 1 || ponKraj.before(oRez.get(j + 1).getPocetak())) {
                        Podaci.iPonude.add(oPonude.get(i));
                        break;
                    }
                } else if (!ponPoc.after(rezPoc) && !ponKraj.after(rezKraj) && ponKraj.after(rezPoc)) {
                    cela = false;
                    Podaci.iPonude.add(new Ponuda(oPonude.get(i).idPonuda, oPonude.get(i).idAuta, ponPoc, dodajDan(rezPoc, -1), oPonude.get(i).cenaDan));
                    break;
                }
            }
            if (cela) {
                Podaci.iPonude.add(oPonude.get(i));
            }
        }
        for (int i = 0; i < Podaci.iPonude.size(); i++) {
            if (Podaci.iPonude.get(i).datumOd.after(Podaci.iPonude.get(i).datumDo)) {
                Podaci.iPonude.remove(i--);
            }
        }
        System.out.println("Prikaz ponuda za odabrani Automobil");
        if (Podaci.iPonude.size() == 0) {
            System.out.println("Trenutno nema ponuda za ovaj automobil.");
        } else {            
            for (int i = 0; i < Podaci.iPonude.size(); i++) {
                System.out.println(Podaci.iPonude.get(i));
            }
        }
    }
}
