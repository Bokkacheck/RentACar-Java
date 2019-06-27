package Klase;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Scanner;

public class Automobil {

    private static Scanner ulaz = new Scanner(System.in);
    private int idAuta;
    private String marka;
    private String model;
    private String menjac;
    private String gorivo;
    private String kubikaza;
    private String karoserija;
    private int godiste;

    public static void prikaziAutomobile() {
        System.out.println("Prikaz automobila: ");
        for (int i = 0; i < Podaci.automobili.size(); i++) {
            System.out.println(Podaci.automobili.get(i));
        }
    }

    public static void dodajAutomobil() {
        int brojac = 0;
        String marka = "";
        String model = "";
        String godiste = "";
        String karoserija = "";
        String kubikaza = "";
        String gorivo = "";
        String menjac = "";
        String tip = "";
        while (true) {
            switch (brojac) {
                case 0:
                    try {
                        System.out.println("Unesite marku automobila ili < da se vratite nazad");
                        marka = ulaz.nextLine();
                        if (marka.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaMarka(marka);
                        brojac++;
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 1:
                    try {
                        System.out.println("Unesite model automobila ili < da se vratite nazad");
                        model = ulaz.nextLine();
                        if (model.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaModel(model);
                        brojac++;
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Unesite godiste automobila < da se vratite nazad");
                    godiste = ulaz.nextLine();
                    if (godiste.equals("<")) {
                        return;
                    }
                    if (ValidacijaPodataka.validacijaGodiste(godiste)) {
                        brojac++;
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Unesite kubikazu ili < da se vratite nazad");
                        kubikaza = ulaz.nextLine();
                        if (kubikaza.equals("<")) {
                            return;
                        }
                        ValidacijaPodataka.validacijaKubikaze(kubikaza);
                        brojac++;
                    } catch (ValidacijaPodataka ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Odaberite karoseriju automobila ili < da se vratite nazad");
                    System.out.println("1. Hecbek");
                    System.out.println("2. Limuzina");
                    System.out.println("3. Karavan");
                    tip = ulaz.nextLine();
                    if (tip.equals("<")) {
                        return;
                    }
                    switch (tip) {
                        case "1":
                            karoserija = "Hecbek";
                            brojac++;
                            break;
                        case "2":
                            karoserija = "Limuzina";
                            brojac++;
                            break;
                        case "3":
                            karoserija = "Karavan";
                            brojac++;
                            break;
                        default:
                            System.out.println("Greska! Neispravna opcija");
                            break;
                    }
                    System.out.println(karoserija);
                    break;
                case 5:
                    System.out.println("Odaberite gorivo automobila ili < da se vratite nazad");
                    System.out.println("1. Dizel");
                    System.out.println("2. Benzin");
                    System.out.println("3. Benzin + Gas(TNG)");
                    tip = ulaz.nextLine();
                    if (tip.equals("<")) {
                        return;
                    }
                    switch (tip) {
                        case "1":
                            gorivo = "Dizel";
                            brojac++;
                            break;
                        case "2":
                            gorivo = "Benzin";
                            brojac++;
                            break;
                        case "3":
                            gorivo = "Benzin + Gas(TNG)";
                            brojac++;
                            break;
                        default:
                            System.out.println("Greska! Neispravna opcija");
                            break;
                    }
                    System.out.println(gorivo);
                    break;
                case 6:
                    System.out.println("Odaberite tip menjaca automobila ili < da se vratite nazad");
                    System.out.println("1. Automatski");
                    System.out.println("2. Manuelni");
                    tip = ulaz.nextLine();
                    if (tip.equals("<")) {
                        return;
                    }
                    switch (tip) {
                        case "1":
                            menjac = "Automatski";
                            brojac++;
                            break;
                        case "2":
                            menjac = "Manuelni";
                            brojac++;
                            break;
                        default:
                            System.out.println("Greska! Neispravna opcija");
                            break;
                    }
                    System.out.println(menjac);
                    break;
                default:
                    int idAuta = Podaci.automobili.size() == 0 ? 1 : Podaci.automobili.get(Podaci.automobili.size()-1).idAuta+1;
                    Podaci.automobili.add(new Automobil(idAuta,marka, model, menjac, gorivo, kubikaza.concat("cm3"), karoserija, Integer.parseInt(godiste)));
                    System.out.println("Automobil uspesno dodat.");
                    return;
            }
        }
    }

    public static void obrisiAutomobil() {
        while (true) {
            prikaziAutomobile();
            System.out.println("Unesite ID automobila koji zelite da obrisete ili < da se vratite nazad");
            String id = ulaz.nextLine();
            if (id.equals("<")) {
                return;
            }
            for (int i = 0; i < Podaci.automobili.size(); i++) {
                if ((Podaci.automobili.get(i).idAuta + "").equals(id)) {
                    Podaci.automobili.remove(i);
                    System.out.println("Automobil obrisan");
                    Ponuda.obrisiPonuduZaAuto(Integer.parseInt(id));
                    break;
                }
                if(i==Podaci.automobili.size()-1){
                    System.out.println("Unet ID automobila ne postoji");
                }
            }            
        }

    }

    public int getIdAuta() {
        return idAuta;
    }

    
    @Override
    public String toString() {
        return idAuta + " " + marka + " " + model + " " + godiste + " " + kubikaza + " " + karoserija + " " + gorivo + " " + menjac;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public String getMenjac() {
        return menjac;
    }

    public String getGorivo() {
        return gorivo;
    }

    public String getKubikaza() {
        return kubikaza;
    }

    public String getKaroserija() {
        return karoserija;
    }

    public Automobil(int idAuta,String marka, String model, String menjac, String gorivo, String kubikaza, String karoserija, int godiste) {
        this.idAuta = idAuta;
        this.marka = marka;
        this.model = model;
        this.menjac = menjac;
        this.gorivo = gorivo;
        this.kubikaza = kubikaza;
        this.karoserija = karoserija;
        this.godiste = godiste;
    }
}
