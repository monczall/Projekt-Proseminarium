package warsztat;

import java.util.Date;

public class Naprawa {

    private int id_naprawy;
    private int id_uzytkownika;
    private int id_samochodu;
    private String data_przyjecia_naprawy;
    private String opis_usterki;
    private String opis_naprawy;
    private String stan_naprawy;
    private Boolean czy_potrzebne_czesci;


    private String marka;
    private String model;
    private int rok_produkcji;

    private int id_czas;
    private Date planowany_początek_naprawy;
    private Date planowany_koniec_naprawy;

    private int id_czesci;
    private int ilosc;
    private int id_czesci_warsztat;

    public int getId_naprawy() {
        return id_naprawy;
    }

    public int getId_uzytkownika() {
        return id_uzytkownika;
    }

    public int getId_samochodu() {
        return id_samochodu;
    }

    public String getData_przyjecia_naprawy() {
        return data_przyjecia_naprawy;
    }

    public String getOpis_usterki() {
        return opis_usterki;
    }

    public String getOpis_naprawy() {
        return opis_naprawy;
    }

    public String getStan_naprawy() {
        return stan_naprawy;
    }

    public Boolean getCzy_potrzebne_czesci() {
        return czy_potrzebne_czesci;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getRok_produkcji() {
        return rok_produkcji;
    }

    public int getId_czas() {
        return id_czas;
    }

    public Date getPlanowany_początek_naprawy() {
        return planowany_początek_naprawy;
    }

    public Date getPlanowany_koniec_naprawy() {
        return planowany_koniec_naprawy;
    }

    public int getId_czesci() {
        return id_czesci;
    }

    public int getIlosc() {
        return ilosc;
    }

    public int getId_czesci_warsztat() {
        return id_czesci_warsztat;
    }

    public void setId_naprawy(int id_naprawy) {
        this.id_naprawy = id_naprawy;
    }

    public void setId_uzytkownika(int id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public void setId_samochodu(int id_samochodu) {
        this.id_samochodu = id_samochodu;
    }

    public void setData_przyjecia_naprawy(String data_przyjecia_naprawy) {
        this.data_przyjecia_naprawy = data_przyjecia_naprawy;
    }

    public void setOpis_usterki(String opis_usterki) {
        this.opis_usterki = opis_usterki;
    }

    public void setOpis_naprawy(String opis_naprawy) {
        this.opis_naprawy = opis_naprawy;
    }

    public void setStan_naprawy(String stan_naprawy) {
        this.stan_naprawy = stan_naprawy;
    }

    public void setCzy_potrzebne_czesci(Boolean czy_potrzebne_czesci) {
        this.czy_potrzebne_czesci = czy_potrzebne_czesci;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRok_produkcji(int rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public void setId_czas(int id_czas) {
        this.id_czas = id_czas;
    }

    public void setPlanowany_początek_naprawy(Date planowany_początek_naprawy) {
        this.planowany_początek_naprawy = planowany_początek_naprawy;
    }

    public void setPlanowany_koniec_naprawy(Date planowany_koniec_naprawy) {
        this.planowany_koniec_naprawy = planowany_koniec_naprawy;
    }

    public void setId_czesci(int id_czesci) {
        this.id_czesci = id_czesci;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public void setId_czesci_warsztat(int id_czesci_warsztat) {
        this.id_czesci_warsztat = id_czesci_warsztat;
    }
}
