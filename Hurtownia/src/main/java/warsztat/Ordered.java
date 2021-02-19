package warsztat;

public class Ordered {
    private int id_czesci;
    private int ilosc;
    private String nazwa;
    private int warsztat_id;

    public int getId_czesci() {
        return id_czesci;
    }

    public void setId_czesci(int id_czesci) {
        this.id_czesci = id_czesci;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getWarsztat_id() {
        return warsztat_id;
    }

    public void setWarsztat_id(int warsztat_id) {
        this.warsztat_id = warsztat_id;
    }

    public Ordered() {

    }

    public Ordered(int id_czesci, int ilosc, String nazwa, int warsztat_id) {
        this.id_czesci = id_czesci;
        this.ilosc = ilosc;
        this.nazwa = nazwa;
        this.warsztat_id = warsztat_id;
    }
}