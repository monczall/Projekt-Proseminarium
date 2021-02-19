package warsztat;

public class Part {

    private int id;
    private String nazwa;
    private int ilosc;
    private int id_naprawy;
    private int hurtownia_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getId_naprawy() {
        return id_naprawy;
    }

    public void setId_naprawy(int id_naprawy) {
        this.id_naprawy = id_naprawy;
    }

    public Part() {

    }

    public Part(int id, String nazwa, int ilosc, int id_naprawy, int hurtownia_id) {
        this.id = id;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.id_naprawy = id_naprawy;
        this.hurtownia_id = hurtownia_id;
    }

    public int getHurtownia_id() {
        return hurtownia_id;
    }

    public void setHurtownia_id(int hurtownia_id) {
        this.hurtownia_id = hurtownia_id;
    }
}