package Klase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ValidacijaPodataka extends Exception {

    public ValidacijaPodataka(String izuzetak) {
        super(izuzetak);
    }

    public static void validacijaImePrezime(String tekst) throws ValidacijaPodataka {
        if (!Pattern.matches("^[A-Z]{1}[a-z]{2,20}$", tekst)) {
            throw new ValidacijaPodataka("Greska! Ime i prezime moraju poceti velikim slovom i sadrzati samo slova (3-20).");
        }
    }

    public static void validacijaKorIme(String tekst) throws ValidacijaPodataka {
        if (!Pattern.matches("^[a-z0-9]{3,20}$", tekst)) {
            throw new ValidacijaPodataka("Greska! Korisnicko ime moze sadrzati samo mala slova i brojeve (3-20).");
        }
    }

    public static void validacijaLozinka(String tekst) throws ValidacijaPodataka {
        if (!Pattern.matches("^[a-z0-9A-Z\\s.,-/]{6,20}$", tekst)) {
            throw new ValidacijaPodataka("Greska! Lozinka moze sadrzati slova, brojeve i karaktere ,.-/ i razmak (6-20).");
        }
    }

    public static boolean validacijaDatumaRodjenja(String tekst) {
        try {
            Date datum = new SimpleDateFormat("dd.MM.yyyy").parse(tekst);
            if ((new Date().getYear() - datum.getYear()) < 21) {
                System.out.println("Greska! Korisnik mora imati minimum 21 godiunu.");
                return false;
            }
            return true;
        } catch (ParseException ex) {
            System.out.println("Greska! Neispravan format datuma");
            return false;
        }
    }

    public static void validacijaMarka(String tekst) throws ValidacijaPodataka {
        if (!Pattern.matches("^[A-Z]{1}[a-z]{1,20}$", tekst)) {
            throw new ValidacijaPodataka("Greska! Marka mora poceti velikim slovom i sadrzati samo slova (2-20).");
        }
    }

    public static void validacijaModel(String tekst) throws ValidacijaPodataka {
        if (!Pattern.matches("^[a-zA-Z0-9. -]{1,20}$", tekst)) {
            throw new ValidacijaPodataka("Greska! Model moze sadrzati slova,brojeve i karaktere .- i razmak (1-20).");
        }
    }

    public static boolean validacijaGodiste(String tekst) {
        try {
            int godiste = Integer.parseInt(tekst);
            Date datum = new SimpleDateFormat("yyyy").parse(tekst);
            if (((int) (new Date().getYear()) - datum.getYear()) > 10 || (datum.getYear() > new Date().getYear())) {
                System.out.println("Greska! Automobil ne moze biti stariji od 10 godina ili manji od trenutne godine.");
                return false;
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Greska! Neisparavn unos.");
            return false;
        }
    }

    public static void validacijaKubikaze(String tekst) throws ValidacijaPodataka {
        if (!Pattern.matches("^([6-9]{1}[0-9]{2})|([1-5]{1}[0-9]{3})|6000$", tekst)) {
            throw new ValidacijaPodataka("Greska! Kubikaza mora biti u opsegu od 600cm3 do 6000cm3.");
        }
    }

    public static boolean validacijaIdAuta(String tekst) {
        try {
            int idAuta = Integer.parseInt(tekst);
            for (int i = 0; i < Podaci.automobili.size(); i++) {
                if (idAuta == Podaci.automobili.get(i).getIdAuta()) {
                    return true;
                }
            }
            System.out.println("Nije pronadjen automobil sa unetim ID.");
            return false;
        } catch (Exception exp) {
            System.out.println("Greska! ID automobila mogu biti samo celi brojevi.");
            return false;
        }
    }

    public static boolean validacijaDatumaPonuda(String pocetak, String kraj, String idAuta) {
        try {
            Date datumOd = new SimpleDateFormat("dd.MM.yyyy").parse(pocetak);
            Date datumDo = new SimpleDateFormat("dd.MM.yyyy").parse(kraj);
            if (datumOd.before(new Date())) {
                System.out.println("Greska! Ponuda ne moze poceti pre danasnjeg dana.");
                return false;
            } else if (datumOd.after(datumDo)) {
                System.out.println("Greska! Kraj ponude mora biti nakon pocetka ponude.");
                return false;
            }
            ArrayList<Ponuda> ponude = Podaci.ponude;
            for (int i = 0; i < ponude.size(); i++) {
                if ((ponude.get(i).getIdAuta() + "").equals(idAuta)) {
                    Date datPoc = ponude.get(i).getDatumOd();
                    Date datKraj = ponude.get(i).getDatumDo();
                    if (!((datumOd.before(datPoc) && datumDo.before(datPoc)) || (datumOd.after(datKraj) && datumDo.after(datKraj)))) {
                        System.out.println("Automobil se vec nalazi u nekoj ponudi u ovom periodu");
                        return false;
                    }
                }
            }
            return true;
        } catch (ParseException exp) {
            System.out.println("Greska! Neispravan format datuma.");
            return false;
        }
    }

    public static boolean validacijaCeneDan(String cenaDan) {
        try {
            double cena = Double.parseDouble(cenaDan);
            if (cena < 1000 || cena > 100000) {
                System.out.println("Greska! Cena po danu nije u dozvoljenom opsegu(1000 - 100000).");
                return false;
            }
            return true;
        } catch (Exception exp) {
            System.out.println("Greska! Neispravan format cene.");
            return false;
        }
    }

    public static boolean validacijaIdAutaKupac(String tekst, String markaAuta) {
        try {
            int idAuta = Integer.parseInt(tekst);
            for (int i = 0; i < Podaci.automobili.size(); i++) {
                if (idAuta == Podaci.automobili.get(i).getIdAuta()
                        && markaAuta.toLowerCase().equals(Podaci.automobili.get(i).getMarka().toLowerCase())) {
                    return true;
                }
            }
            System.out.println("Nije pronadjen automobil sa unetim ID za odabranu marku.");
            return false;
        } catch (Exception exp) {
            System.out.println("Greska! ID automobila mogu biti samo celi brojevi.");
            return false;
        }
    }

    public static boolean validacijaRezervisanje(String pocetak, String kraj) {
        try {
            Date datumOd = new SimpleDateFormat("dd.MM.yyyy").parse(pocetak);
            Date datumDo = new SimpleDateFormat("dd.MM.yyyy").parse(kraj);
            if (datumOd.before(new Date())) {
                System.out.println("Greska! Rezervacija ne moze poceti pre danasnjeg dana.");
                return false;
            } else if (datumOd.after(datumDo)) {
                System.out.println("Greska! Kraj rezervacije mora biti nakon pocetka ponude.");
                return false;
            }
            return true;
        } catch (ParseException ex) {
            System.out.println("Greska! Neispravan format datuma.");
            return false;
        }
    }

    public static boolean validacijaStatistika(String pocetak, String kraj) {
        try {
            Date datumOd = new SimpleDateFormat("dd.MM.yyyy").parse(pocetak);
            Date datumDo = new SimpleDateFormat("dd.MM.yyyy").parse(kraj);
            if (datumOd.after(datumDo)) {
                System.out.println("Greska! Kraj rezervacije mora biti nakon pocetka ponude.");
                return false;
            }
            return true;
        } catch (ParseException ex) {
            System.out.println("Greska! Neispravan format datuma.");
            return false;
        }
    }

    public static boolean validacijaRednogBroja(String tekst, int max) {
        try {
            int broj = Integer.parseInt(tekst);
            if (broj < 1 || broj > max) {
                System.out.println("Greska! Broj mora biti u opsegu od 1 do " + max + ".");
                return false;
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Greska! Neispravan format rednog broja.");
            return false;
        }
    }
}
