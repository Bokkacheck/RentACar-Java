
package Klase;

public class KorisnikMeni implements Meni {

    @Override
    public void prikaziMeni() {
        while (true) {
            System.out.println("Odaberi opciju:");
            System.out.println("1. Rezervisi automobil");
            System.out.println("2. Pogledaj/Obrisi svoje rezervacije");
            System.out.println("3. Izloguj se");
            System.out.println("Unesi opciju: ");
            if (obradiOpciju(ulaz.nextLine())) {
                return;
            }
        }
    }

    @Override
    public boolean obradiOpciju(String opcija) {
        switch (opcija) {
            case "1":
                Kupac.odabirAutomobila();
                return false;
            case "2":
                Kupac.prikazBrisanjeRezervacije(Korisnik.ulogovanKorisnik);
                return false;
            case "3":
                return true;
            default:
                System.out.println("Uneli ste pogresnu opciju!");
                return false;
        }
    }

}
