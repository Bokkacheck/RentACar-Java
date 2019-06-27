package Klase;

public class AdministratorMeni implements Meni {

    @Override
    public void prikaziMeni() {
        while (true) {
            System.out.println("Odaberi opciju:");
            System.out.println("1. Prikaz korisnika");
            System.out.println("2. Dodavanje korisnika");
            System.out.println("3. Brisanje korisnika");
            System.out.println("4. Prikaz automobila");
            System.out.println("5. Dodavanje automobila");
            System.out.println("6. Brisanje automobila");
            System.out.println("7. Prikaz ponuda");
            System.out.println("8. Dodavanje ponuda");
            System.out.println("9. Brisanje ponuda");
            System.out.println("10. Prikaz/Brisanje rezervacija");
            System.out.println("11. Statistika");
            System.out.println("12. Izloguj se");
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
                Korisnik.prikaziKorisnike();
                Meni.nastaviDalje();
                return false;
            case "2":
                Administrator.dodajKorisnika();
                return false;
            case "3":
                Administrator.obrisiKorisnika();
                return false;
            case "4":
                Automobil.prikaziAutomobile();
                Meni.nastaviDalje();
                return false;
            case "5":
                Automobil.dodajAutomobil();
                return false;
            case "6":
                Automobil.obrisiAutomobil();
                return false;
            case "7":
                Ponuda.prikaziPonude();
                Meni.nastaviDalje();
                return false;
            case "8":
                Ponuda.dodajPonudu();
                return false;
            case "9":
                Ponuda.obrisiPonudu();
                return false;
            case "10":
                Administrator.prikazBrisanjeRezervacija();
                return false;
            case "11":
                Statistika.prikazStatistike();
                return false;
            case "12":
                return true;
            default:
                System.out.println("Uneli ste nepostojeću opciju!");
                return false;
        }
    }

}
