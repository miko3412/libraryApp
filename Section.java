package classes;

import java.io.Serializable;

/**
 * Klasa reprezentująca obiekt Section
 * @version 1.0
 */
public class Section implements Serializable {
    private String name;
    private int parentID;
    private int id;
    private static final long serialVersionUID = 1L;

    /**
     * Konstruktor klasy Section
     * @param name nazwa sekcji
     * @param parentID ciąg znaków reprezentujący ID rodzica
     */
    public Section(String name, int parentID) {
        this.name = name;
        this.parentID = parentID;
        id = -1;
    }

    /**
     * Konstruktor Section z jednym parametrem
     * @param name nazwa sekcji
     */
    public Section(String name) {
        this.name = name;
        parentID = -1;
        id = -1;
    }

    /**
     * Pusty konstruktor sekcji
     */
    public Section() {
        this.name = "";
        parentID = -1;
        id = -1;
    }

    /**
     * Metoda zwracająca nazwę sekcji
     * @return ciąg znaków reprezentujący nazwę sekcji
     */
    public String getName() {
        return name;
    }

    /**
     * metoda zmieniająca nazwę sekcji
     * @param name ciąg znaków reprezentujących nową nazwę
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda zwracająca ID rodzica
     * @return liczba całkowita równa ID rodzica
     */
    public int getParentID() {
        return parentID;
    }

    /**
     * Metoda zmieniająca ID rodzica
     * @param parentID liczba całkowita reprezentująca ID rodzica
     */
    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    /**
     * Metoda zwracająca ID sekcji
     * @return liczba całkowita równa ID sekcji
     */
    public int getID() {
        return id;
    }

    /**
     * Metoda zmieniająca ID sekcji
     * @param id liczba całkowita reprezentująca nowe ID sekcji
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * metoda ustawiająca wszystkie pola na pola podanej sekcji
     * @param section obiekt klasy section
     */
    public void setAll(Section section) {
        name = section.name;
        parentID = section.parentID;
    }

    /**
     * Metoda ustawiająca nazwę i ID rodzica sekcji
     * @param name ciąg znaków reprezentujący nową nazwę sekcji
     * @param parentID liczba całkowita reprezentująca ID nowego rodzica sekcji
     */
    public void setAll(String name, int parentID) {
        this.name = name;
        this.parentID = parentID;
    }

    /**
     * Metoda porównująca sekcje
     * @param section obiekt klasy section
     * @return true lub false w zależności czy dane sekjce są identyczne czy się różnią
     */
    public boolean equals(Section section) {
        if (section != null && id == section.id && name == section.name && parentID == section.parentID)
            return true;

        return false;
    }

    /**
     * Metoda toString()
     * @return zwraca dane Secji w postaci ciągu znaków
     */
    @Override
    public String toString() {
        return "Section {" + "name='" + name + '\'' + ", parentId=" + parentID + ", id=" + id + '}';
    }
}
