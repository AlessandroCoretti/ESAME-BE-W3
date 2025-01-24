package org.example.dao;

import org.example.entities.Elemento;
import org.example.entities.Libro;
import org.example.entities.Prestito;
import org.example.entities.Utente;

import javax.persistence.*;
import java.util.List;

public class BibliotecaDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public BibliotecaDAO(){
        emf = Persistence.createEntityManagerFactory("EsameJPA");
    }

    // Metodi per Elementi - Libri e Riviste
    public void aggiungiElemento(Elemento elemento){
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(elemento);
            transaction.commit();
        } catch(Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void rimuoviElemento(String isbn) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Elemento elemento = em.find(Elemento.class, isbn);
            if (elemento != null) {
                em.remove(elemento);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Aggiungo Utente
    public void aggiungiUtente(Utente utente) {
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Ricerca per ISBN
    public Elemento ricercaPerIsbn(String isbn) {
        em = emf.createEntityManager();
        try{
            return em.find(Elemento.class, isbn);
        } finally {
            em.close();
        }
    }

    // Ricerca per Anno di Pubblicazione
    public List<Elemento> ricercaPerAnnoPubblicazione(int anno) {
        em = emf.createEntityManager();
        try {
            TypedQuery<Elemento> query = em.createQuery(
                    "SELECT e FROM Elemento e WHERE e.annoPubblicazione = :anno", Elemento.class
            );
            query.setParameter("anno", anno);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca per Autore - Libri
    public List<Libro> ricercaPerAutore(String autore){
        em = emf.createEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery(
                    "SELECT l FROM Libro l WHERE LOWER(l.autore) LIKE LOWER(:autore)",
                    Libro.class
            );
            query.setParameter("autore", "%" + autore + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca per titolo
    public List<Elemento> ricercaPerTitolo(String titolo){
        em = emf.createEntityManager();
        try {
            TypedQuery<Elemento> query = em.createQuery(
                    "SELECT e FROM Elemento e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)",
                    Elemento.class
            );
            query.setParameter("Titolo", "%" + titolo + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Metodi per Prestiti
    public void aggiungiPrestito(Prestito prestito){
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(prestito);
            transaction.commit();
        } catch(Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Ricerca Prestiti per Numero Tessera Utente
    public List<Prestito> ricercaPrestitiPerTessera(String numeroTessera) {
        em = emf.createEntityManager();
        try {
            TypedQuery<Prestito> query = em.createQuery(
                    "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera",
                    Prestito.class
            );
            query.setParameter("tessera", numeroTessera);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Ricerca Prestiti Scaduti
    public List<Prestito> ricercaPrestitiScaduti(){
        em = emf.createEntityManager();
        try {
            TypedQuery<Prestito> query = em.createQuery(
                    "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE " +
                            "AND p.dataRestituzioneEffettiva IS NULL",
                    Prestito.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Chiusura delle risorse
    public void showdown(){
        if (emf != null){
            emf.close();
        }
    }




}
