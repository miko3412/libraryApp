package classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa reprezentująca obiekt Book
 * @version 1.0
 */
public class Book implements Serializable {
    private String ISBN;
    private String title;
    private ArrayList<String> authors;
    private int parentID;
    private static final long serialVersionUID = 2L;

    /**
     * Konstruktor klasy Book
     * @param ISBN ciąg znaków przedstawiający ISBN książki
     * @param title ciąg znaków reprezentujący tytuł książki
     * @param authors lista reprezentująca autorów książki
     * @param parentID liczba całkowita reprezentująca ID działu w którym się znajduje
     */
    public Book(String ISBN, String title, ArrayList<String> authors, int parentID) {
        this.ISBN = ISBN;
        this.title = title;
        this.authors = authors;
        this.parentID = parentID;
    }

    /**
     * Pusty konstruktor klasy Book
     */
    public Book(){

    }

    /**
     * Metoda zwracająca ISBN książki
     * @return cią znaków reprezentujący ISBN książki
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * metoda edytująca ISBN książki
     * @param ISBN ciąg znaków reprezentujący nowy ISBN książki
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Metoda zwracająca tytuł książki
     * @return ciąg znaków reprezentujący tytuł książki
     */
    public String getTitle() {
        return title;
    }

    /**
     * Metoda ustawiająca tytuł
     * @param title ciąg znaków reprezentujący nowy tytuł
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metoda zwracająca listę autorów książki
     * @return lista autorów
     */
    public ArrayList<String> getAuthors() {
        return authors;
    }

    /**
     * Metoda ustawiająca autorów książki
     * @param authors lista nowych autorów książki
     */
    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    /**
     * Metoda zwracająca ID rodzica książki
     * @return liczba całkowita reprezentująca ID działu w którym książka się znajduje
     */
    public int getParentID() {
        return parentID;
    }

    /**
     * Metoda ustawiająca parentID
     * @param parentID nowe ID działu w którym książka się znajduje
     */
    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    /**
     * Metoda ustawiająca wszystkie pola na pola innej książki
     * @param book obiekt klasy Book, na który chcemy zmienić naszą książkę
     */
    public void setAll(Book book) {
        ISBN = book.ISBN;
        authors = book.authors;
        parentID = book.parentID;
        title = book.title;
    }

    /**
     * Metoda ustawiająca wszystkie pola książki
     * @param ISBN ciąg znaków reprezentujący nowy ISBN skiążki
     * @param title ciąg znaków reprezentujący tytuł książki
     * @param authors lista reprezentująca autorów książki
     * @param sectionID liczba całkowita reprezentująca ID rodzica
     */
    public void setAll(String ISBN, String title, ArrayList<String> authors, int sectionID) {
        this.ISBN = ISBN;
        this.authors = authors;
        this.parentID = sectionID;
        this.title = title;
    }

    /**
     * Metoda porównująca dwa obiekty ze sobą
     * @param book obiekt klasy Book do porónania
     * @return
     */
    public boolean equals(Book book) {
        if (book != null && ISBN == book.ISBN && title == book.title && authors == book.authors
                && parentID == book.parentID)
            return true;

        return false;
    }

    /**
     * Metoda toString()
     * @return ciąg znaków reprezentujący dane książki
     */
    @Override
    public String toString() {
        return "Book {" + "title= '" + title + '\'' + ", authors= " + authors.toString() + ", ISBN= " + ISBN + '}';
    }

    public static void main(String[] args) {
    }
}
