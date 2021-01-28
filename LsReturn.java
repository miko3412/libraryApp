package classes;

import java.util.ArrayList;

/**
 * Klasa pomocnicza, mająca pozwalać na łatwy transport list książek i list
 * sekcji (działa podobnie do struct w C++)
 */
public class LsReturn {
    public ArrayList<Section> sections;
    public ArrayList<Book> books;

    public LsReturn(ArrayList<Section> sections, ArrayList<Book> books) {
        this.sections = sections;
        this.books = books;
    }
}
