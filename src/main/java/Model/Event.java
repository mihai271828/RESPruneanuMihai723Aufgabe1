package Model;

import java.time.LocalDate;

public class Event {
    private int id;
    private String Charaktername;
    private Stufe Stufe;
    private String Beschreibung;
    private LocalDate Datum;
    private double Kraftpunkte;
    public enum Stufe{
        Genin, Chunin, Jonin, Kage
    }

    public Event(int id, String charaktername, Event.Stufe stufe, String beschreibung, LocalDate datum, double kraftpunkte) {
        this.id = id;
        Charaktername = charaktername;
        Stufe = stufe;
        Beschreibung = beschreibung;
        Datum = datum;
        Kraftpunkte = kraftpunkte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharaktername() {
        return Charaktername;
    }

    public void setCharaktername(String charaktername) {
        Charaktername = charaktername;
    }

    public Stufe getStufe() {
        return Stufe;
    }

    public void setStufe(Stufe stufe) {
        Stufe = stufe;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }

    public LocalDate getDatum() {
        return Datum;
    }

    public void setDatum(LocalDate datum) {
        Datum = datum;
    }

    public double getKraftpunkte() {
        return Kraftpunkte;
    }

    public void setKraftpunkte(double kraftpunkte) {
        Kraftpunkte = kraftpunkte;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", Charaktername='" + Charaktername + '\'' +
                ", Stufe=" + Stufe +
                ", Beschreibung='" + Beschreibung + '\'' +
                ", Datum=" + Datum +
                ", Kraftpunkte=" + Kraftpunkte +
                '}';
    }
}
