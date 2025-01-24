package org.example;

import org.example.dao.BibliotecaDAO;
import org.example.entities.Elemento;
import org.example.entities.Libro;
import org.example.entities.Prestito;
import org.example.entities.Utente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();

        try {
            // Creo Utente
            Utente utente = new Utente("AB123", "Alessandro", "Coretti", LocalDate.of(2002, 4, 6));

            // Aggiungo l'utente al database (puoi aggiungere un metodo in BibliotecaDAO per questo)
            bibliotecaDAO.aggiungiUtente(utente);

            // Creo Libro
            Libro libro = new Libro("ISBN0001", "Viaggio al centro della Terra", 2002, 208, "Jules Verne", "Avventura");

            // Aggiungo il libro al database
            bibliotecaDAO.aggiungiElemento(libro);

            // Eseguiamo una ricerca per ISBN
            Elemento trovato = bibliotecaDAO.ricercaPerIsbn("ISBN0001");
            System.out.println("Elemento trovato per ISBN: " + trovato);

            // Creiamo un prestito
            Prestito prestito = new Prestito();
            prestito.setUtente(utente);
            prestito.setElementoPrestato(libro);
            prestito.setDataInizio(LocalDate.now());
            prestito.setDataRestituzionePrevista(LocalDate.now().plusDays(30));

            // Aggiungiamo il prestito
            bibliotecaDAO.aggiungiPrestito(prestito);

            // Verifica i prestiti in base alla tessera utente
            List<Prestito> prestitiUtente = bibliotecaDAO.ricercaPrestitiPerTessera("AB123");
            System.out.println("Prestiti per l'utente con tessera AB123:");
            for (Prestito p : prestitiUtente) {
                System.out.println(p);
            }

            // Ricerca dei prestiti scaduti
            List<Prestito> prestitiScaduti = bibliotecaDAO.ricercaPrestitiScaduti();
            System.out.println("Prestiti scaduti non ancora restituiti:");
            for (Prestito p : prestitiScaduti) {
                System.out.println(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiudiamo la connessione al database
            bibliotecaDAO.showdown();
        }
    }
}
