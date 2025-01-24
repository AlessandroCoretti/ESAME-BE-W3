package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_tessera")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_isbn")
    private Elemento elementoPrestato;

    private LocalDate dataInizio;
    private LocalDate dataRestituzionePrevista;
    private LocalDate getDataRestituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(long id, Utente utente, Elemento elementoPrestato, LocalDate dataInizio, LocalDate dataRestituzionePrevista, LocalDate getDataRestituzioneEffettiva) {
        this.id = id;
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizio = dataInizio;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.getDataRestituzioneEffettiva = getDataRestituzioneEffettiva;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Elemento getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Elemento elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getGetDataRestituzioneEffettiva() {
        return getDataRestituzioneEffettiva;
    }

    public void setGetDataRestituzioneEffettiva(LocalDate getDataRestituzioneEffettiva) {
        this.getDataRestituzioneEffettiva = getDataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizio=" + dataInizio +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", getDataRestituzioneEffettiva=" + getDataRestituzioneEffettiva +
                '}';
    }
}
