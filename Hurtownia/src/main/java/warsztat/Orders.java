package warsztat;

public class Orders {
    private int idSprzedazy;
    private int idCzesci;
    private String nazwa;
    private int ilosc;

    public int getIdSprzedazy() {
        return idSprzedazy;
    }

    public void setIdSprzedazy(int idSprzedazy) {
        this.idSprzedazy = idSprzedazy;
    }

    public int getIdCzesci() {
        return idCzesci;
    }

    public void setIdCzesci(int idCzesci) {
        this.idCzesci = idCzesci;
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
}
