
package classes;

import java.util.ArrayList;

/**
 * Klasa reprezentująca obiekt Operator, która wykonuje polecenia użytkownika
 * @version 1.0
 */
public class Operator {
    private DBM data;
    public Section nowIn;
    private String path = "/";

    /**
     * Konstruktor klasy Operator
     * @param fileName cia znaków reprezentujący plik tekstowy do zapisu i odczytu
     */
    public Operator(String fileName) {
        this.data = new DBM(fileName);
    }

    /**
     * Bezparametrowy konstruktor klasy Operator
     */
    public Operator() {
        this.data = new DBM();
        nowIn = new Section();
    }

    /**
     * Funkcja zwracająca aktualną ścieżkę
     * @return ciąg znaków reprezentujący ścieżkę
     */
    public String getPath() {
        return path;
    }

    // --- Adding methods ---

    /**
     * Funkcja dodająca sekcję do bazy danych
     * @param name ciąg znaków reprezentujący nazwę Sekcji
     * @return zwraca wartość true false w zależności czy udało się dodać czy nie
     */
    public boolean addSection(String name) {
        Section section = new Section(name, nowIn.getID());
        return data.addSection(section);
    }

    /**
     * Funkcja dodająca książkę do bazy danych
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @param title ciąg znaków reprezentujący tytuł
     * @param author lista autorów
     * @return zwraca wartość true false w zależności czy udało się dodać czy nie
     */
    public boolean addBook(String ISBN, String title, ArrayList<String> author) {
        return data.addBook(new Book(ISBN, title, author, nowIn.getID()));
    }

    /**
     * Metoda zwracająca obecną sekcję
     * @return zwraca obiekt section
     */
    public Section getNowIn(){
        return this.nowIn;
    }

    // --- Removing methods ---

    /**
     * Funkcja usuwająca Sekcję
     * @param section przyjmuje obiekt który ma usunąć
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeSection(Section section) {
        ArrayList<Section> list = data.findSections(section.getID());
        for (Section child : list) {
            child.setParentID(section.getParentID());
        }
        return data.removeSection(section);
    }

    /**
     * Funkcja usuwająca sekcję
     * @param id integer reprezentujący id książki
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeSection(int id) {
        ArrayList<Section> list = data.findSections(id);
        for (Section child : list) {
            child.setParentID(id);
        }
        return data.removeSection(id);
    }

    /**
     * Funkcja usuwająca sekcję która zawiera w swojej nazwie name
     * @param path ciąg znaków reprezentujący ścieżkę do sekcji
     * @param name ciąg znaków reprezentujący nazwę sekcji
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeSection(String path, String name) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Section> list = data.findSections(nowIn.getID());
        for (Section section : list) {
            if (section.getName().contains(name)) {
                ArrayList<Section> listChild = data.findSections(section.getID());
                ArrayList<Book> listBooks = data.findBooksByParentID(section.getID());
                for (Section child : listChild) {
                    child.setParentID(section.getParentID());
                }
                for(Book book : listBooks){
                    book.setParentID(section.getParentID());
                }
                data.removeSection(section);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }

    /**
     * Funkcja usuwająca Sekcję która zaczyna się na name
     * @param path ciąg znaków reprezentujący scieżkę
     * @param name ciąg znaków reprezentujący nazwę
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeSectionS(String path, String name) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Section> list = data.findSections(nowIn.getID());
        for (Section section : list) {
            if (section.getName().startsWith(name)) {
                ArrayList<Section> listChild = data.findSections(section.getID());
                ArrayList<Book> listBooks = data.findBooksByParentID(section.getID());
                for (Section child : listChild) {
                    child.setParentID(section.getParentID());
                }
                for(Book book : listBooks){
                    book.setParentID(section.getParentID());
                }
                data.removeSection(section);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }
    /**
     * Funkcja usuwająca Sekcję która równa się name
     * @param path ciąg znaków reprezentujący scieżkę
     * @param name ciąg znaków reprezentujący nazwę
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeSectionE(String path, String name) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Section> list = data.findSections(nowIn.getID());
        for (Section section : list) {
            if (section.getName().equals(name)) {
                ArrayList<Section> listChild = data.findSections(section.getID());
                ArrayList<Book> listBooks = data.findBooksByParentID(section.getID());
                for (Section child : listChild) {
                    child.setParentID(section.getParentID());
                }
                for(Book book : listBooks){
                    book.setParentID(section.getParentID());
                }
                data.removeSection(section);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }

    /**
     * Funkcja usuwająca książkę z danym ISBN
     * @param path ciąg znaków reprezentujący ścieżkę
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookISBN(String path, String ISBN) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        for (Book book : list) {
            if (book.getISBN().equals(ISBN)) {
                data.removeBook(book);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }

    /**
     * Funkcja usuwająca książkę, która zawiera name w nazwie
     * @param path ciąg znaków reprezentujący ścieżkę
     * @param name ciąg znaków reprezentujący name
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBook(String path, String name) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        for (Book book : list) {
            if (book.getTitle().contains(name)) {
                data.removeBook(book);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }
    /**
     * Funkcja usuwająca książkę, której nazwa zaczyna się na name
     * @param path ciąg znaków reprezentujący ścieżkę
     * @param name ciąg znaków reprezentujący name
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookS(String path, String name) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        for (Book book : list) {
            if (book.getTitle().startsWith(name)) {
                data.removeBook(book);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }
    /**
     * Funkcja usuwająca książkę, której nazwa równa się name
     * @param path ciąg znaków reprezentujący ścieżkę
     * @param name ciąg znaków reprezentujący name
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookE(String path, String name) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        for (Book book : list) {
            if (book.getTitle().equals(name)) {
                data.removeBook(book);
            }
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;
    }

    /**
     * Funkcja usuwająca wszystkie książki z danym tytułem
     * @param name cią znaków reprezentujący tytuł
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookByTitle(String name) {
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        for (Book book : list) {
            if (book.getTitle().equals(name)) {
                data.removeBook(book);
            }
        }
        return true;
    }

    /**
     * Funkcja usuwająca podany obiekt książki
     * @param book obiekt reprezentujący książkę
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookByBook(Book book) {
        return data.removeBook(book);
    }

    /**
     * Funkcja usuwająca książkę po danym ISBN
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookByISBN(String ISBN) {
        return data.removeBook(ISBN);
    }

    /**
     * Funkcja usuwająca książki danego autora
     * @param author ciąg znaków reprezentujący autora
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookByAuthor(String author) {
        return data.removeBookByAuthor(author);
    }

    /**
     * funkcja usuwająca książki o podanych autorach
     * @param authors lista autorów
     * @return zwraca wartość true false w zależności czy udało się usunąć czy nie
     */
    public boolean removeBookByAuthors(ArrayList<String> authors) {
        return data.removeBook(authors);
    }

    // --- Modifying methods ---

    /**
     * Funkcja modyfikująca książkę
     * @param old obiekt książki do zmodyfikowania
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @param title ciąg znaków reprezentujący tytuł
     * @param author lista autorów
     * @param sectionID ID sekcji w której ma się znajdować
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyWholeBook(Book old, String ISBN, String title, ArrayList<String> author, int sectionID) {
        Book book = new Book(ISBN, title, author, sectionID);
        return data.modifyBook(old, book);
    }

    /**
     * Funkcja modyfikująca książkę po podanym ISBN
     * @param ISBN ciąg znaków reprezentujący ISBN książki
     * @param newISBN ciąg znaków reprezentujący nowy ISBN
     * @param title ciąg znaków reprezentujący tytuł książki
     * @param author lista autorów
     * @param sectionID ID sekcji w której ma się znajdować
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyWholeBookByISBN(String ISBN, String newISBN, String title, ArrayList<String> author,
            int sectionID) {
        Book book = new Book(newISBN, title, author, sectionID);
        return data.modifyBook(ISBN, book);
    }

    /**
     * Funkcja modyfikująca ISBN książki
     * @param book obiekt książka
     * @param newISBN nowy ISBN książki
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyBookISBN(Book book, String newISBN) {
        return data.modifyBookISBN(book, newISBN);
    }
    /**
     * Funkcja modyfikująca tytuł książki
     * @param book obiekt książka
     * @param newTitle nowy tytuł książki
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyBookTitle(Book book, String newTitle) {
        return data.modifyBook(book, newTitle);
    }
    /**
     * Funkcja modyfikująca autorów książki
     * @param book obiekt książka
     * @param newAuthors lista nowych autorów
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyBookAuthors(Book book, ArrayList<String> newAuthors) {
        return data.modifyBook(book, newAuthors);
    }
    /**
     * Funkcja modyfikująca ID sekcji w której znajduje się książka
     * @param book obiekt książka
     * @param newID nowe ID sekcji w której znajduje się książka
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyBookParentID(Book book, int newID) {
        return data.modifyBook(book, newID);
    }

    /**
     * Funkcja edytująca ISBN książki
     * @param ISBN ciąg znaków reprezentujący stary ISBN
     * @param newISBN ciąg znaków reprezentujący nowy ISBN
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyISBNBookNewISBN(String ISBN, String newISBN) {
        return data.modifyBookISBN(ISBN, newISBN);
    }
    /**
     * Funkcja edytująca tytuł książki
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @param newTitle ciąg znaków reprezentujący nowy tytuł
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyISBNBookTitle(String ISBN, String newTitle) {
        return data.modifyBook(ISBN, newTitle);
    }
    /**
     * Funkcja edytująca autorów książki
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @param newAuthors lista nowych autorów
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyISBNBookAuthor(String ISBN, ArrayList<String> newAuthors) {
        return data.modifyBook(ISBN, newAuthors);
    }
    /**
     * Funkcja edytująca ID sekcji w której znajduje się książka
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @param newID liczba reprezentująca nowe ID sekcji w której znajduje się książka
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyISBNBookParentID(String ISBN, int newID) {
        return data.modifyBook(ISBN, newID);
    }

    /**
     * Funkcja modyfikująca sekcję
     * @param section obiekt klasy sekction
     * @param parentID nowe ID sekcji w której się znajduje
     * @param newName nowa nazwa sekcji
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifyWholeSection(Section section, int parentID, String newName) {
        Section newSection = new Section(newName, parentID);
        return data.modifySection(section, newSection);
    }

    /**
     * Funkcja modyfikująca ID rodzica w sekcji
     * @param section obiekt klasy section
     * @param parentID noe ID rodzica
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionParentID(Section section, int parentID) {
        return data.modifySection(section, parentID);
    }

    /**
     * Funkcja modyfikująca nazwę książki
     * @param section obiekt klasy section
     * @param newName nowa nazwa sekcji
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionName(Section section, String newName) {
        return data.modifySection(section, newName);
    }

    /**
     * Funkcja modyfikująca sekcję poprzez ID
     * @param id liczba reprezentująca id sekcji
     * @param parentID liczba reprezentująca nowe id rodzica
     * @param newName ciąg znaków reprezentujący nowa nazwę
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionByID(int id, int parentID, String newName) {
        Section newSection = new Section(newName, parentID);
        return data.modifySection(id, newSection);
    }

    /**
     * Funkcja edytująca nazwe sekcji
     * @param id licba reprezentująca id sekcji
     * @param newName ciąg znaków reprezentujący nową nazwę
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionNameByID(int id, String newName) {
        return data.modifySection(id, newName);
    }

    /**
     * Funkcja modyfikująca ID rodzica w sekcji
     * @param id licba reprezentująca id sekcji
     * @param parentID liczba reprezentująca nowe ID rodzica
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionParentIDByID(int id, int parentID) {
        return data.modifySection(id, parentID);
    }

    /**
     * Funkcja modyfikująca nazwę sekcji
     * @param name nazwa sekcji
     * @param path ścieżka do danej sekcji
     * @param newName nowa nazwa sekcji
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionByName(String name, String path, String newName ){
        String oldPath = this.path;
        int f=0;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Section> list = data.findSections(nowIn.getID());
        for(Section section: list){
            if(section.getName().contains(name)){
                section.setName(newName);
                f=1;
            }
        }
        if(f==0){
            this.path = oldPath;
            nowIn.setAll(old);
            nowIn.setID(old.getID());
            return false;
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;

    }
    /**
     * Funkcja modyfikująca nazwę sekcji wyszukując sekcję której nazwa równa się name
     * @param name nazwa sekcji
     * @param path ścieżka do danej sekcji
     * @param newName nowa nazwa sekcji
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionByNameE(String name, String path, String newName ){
        String oldPath = this.path;
        int f=0;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Section> list = data.findSections(nowIn.getID());
        for(Section section: list){
            if(section.getName().equals(name)){
                section.setName(newName);
                f=1;
            }
        }
        if(f==0){
            this.path = oldPath;
            nowIn.setAll(old);
            nowIn.setID(old.getID());
            return false;
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;

    }
    /**
     * Funkcja modyfikująca nazwę sekcji której nazwa zaczyna się na name
     * @param name nazwa sekcji
     * @param path ścieżka do danej sekcji
     * @param newName nowa nazwa sekcji
     * @return zwraca wartość true false w zależności czy udało się zmodyfikować czy nie
     */
    public boolean modifySectionByNameS(String name, String path, String newName ){
        String oldPath = this.path;
        int f=0;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        if (goTo(path) != null) {
            String x = goTo(path);
        } else {
            return false;
        }
        ArrayList<Section> list = data.findSections(nowIn.getID());
        for(Section section: list){
            if(section.getName().startsWith(name)){
                section.setName(newName);
                f=1;
            }
        }
        if(f==0){
            this.path = oldPath;
            nowIn.setAll(old);
            nowIn.setID(old.getID());
            return false;
        }
        this.path = oldPath;
        nowIn.setAll(old);
        nowIn.setID(old.getID());
        return true;

    }
    // --- Moving methods ---

    /**
     * Funkcja przenosząca książkę, która zawiera w tytule name
     * @param name nazwa książki
     * @param path ścieżka do której ma zostać przeniesiona
     * @return zwraca ścieżkę do której została przeniesiona książka
     */
    public String moveBook(String name, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Book> database = new ArrayList<>(data.findBooksByParentID(nowIn.getID()));
        for (Book book : database) {
            if (book.getTitle().contains(name)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    book.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    book.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }
    /**
     * Funkcja przenosząca książkę, której tytuł zaczyna się od name
     * @param name nazwa książki
     * @param path ścieżka do której ma zostać przeniesiona
     * @return zwraca ścieżkę do której została przeniesiona książka
     */
    public String moveBookS(String name, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Book> database = new ArrayList<>(data.findBooksByParentID(nowIn.getID()));
        for (Book book : database) {
            if (book.getTitle().startsWith(name)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    book.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    book.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }
    /**
     * Funkcja przenosząca książkę, której tytuł równa się od name
     * @param name nazwa książki
     * @param path ścieżka do której ma zostać przeniesiona
     * @return zwraca ścieżkę do której została przeniesiona książka
     */
    public String moveBookE(String name, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Book> database = new ArrayList<>(data.findBooksByParentID(nowIn.getID()));
        for (Book book : database) {
            if (book.getTitle().contains(name)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    book.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    book.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }

    /**
     * Funkcja przenosząca sekcje która zawiera w swojej nazwie name
     * @param name nazwa sekcji
     * @param path ścieżka do której sekcja ma zostać przeniesiona
     * @return zwraca ścieżkę do której została przeniesiona sekcja
     */
    public String moveSection(String name, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Section> database = new ArrayList<>(data.findSections(nowIn.getID()));
        for (Section section : database) {
            if (section.getName().contains(name)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    section.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    section.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }
    /**
     * Funkcja przenosząca sekcje, której nazwa zaczyna się na name
     * @param name nazwa sekcji
     * @param path ścieżka do której sekcja ma zostać przeniesiona
     * @return zwraca ścieżkę do której została przeniesiona sekcja
     */
    public String moveSectionS(String name, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Section> database = new ArrayList<>(data.findSections(nowIn.getID()));
        for (Section section : database) {
            if (section.getName().startsWith(name)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    section.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    section.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }
    /**
     * Funkcja przenosząca sekcje o danej nazwie
     * @param name nazwa sekcji
     * @param path ścieżka do której sekcja ma zostać przeniesiona
     * @return zwraca ścieżkę do której została przeniesiona sekcja
     */
    public String moveSectionE(String name, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Section> database = new ArrayList<>(data.findSections(nowIn.getID()));
        for (Section section : database) {
            if (section.getName().equals(name)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    section.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    section.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }

    /**
     * Funkcja przenosząca skiążkę po jej ISBN
     * @param ISBN ISBN książki
     * @param path ścieżka do której ma zostać przeniesiona książka
     * @return zwraca ścieżkę do której została przeniesiona książka
     */
    public String moveBookByISBN(String ISBN, String path) {
        String oldPath = this.path;
        Section old = new Section();
        old.setAll(nowIn);
        old.setID(nowIn.getID());
        ArrayList<Book> database = new ArrayList<>(data.findBooksByParentID(nowIn.getID()));
        for (Book book : database) {
            if (book.getISBN().equals(ISBN)) {
                String x = goTo(path);
                if (x == null) {
                    return null;
                }
                if (!x.equals("")) {
                    book.setParentID(nowIn.getID());
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                } else {
                    this.path = oldPath;
                    nowIn.setAll(old);
                    nowIn.setID(old.getID());
                    book.setParentID(nowIn.getID());
                    return null;
                }
                this.path = oldPath;
                nowIn.setAll(old);
                nowIn.setID(old.getID());
                return x;
            }
        }
        return null;
    }

    // --- Going methods ---

    /**
     * Funkcja przechodząca po hierarchii
     * @param path ścieżka do której chcemy przejść
     * @return zwraca ścieżkę do której przeszliśmy lub null gdy nie przeszliśmy
     */
    public String goTo(String path) {
        String out = null;
        int c = 0;
        int b = 0;
        if(this.path.equals("/") && path.startsWith("..")){
            return null;
        }
        if (path.charAt(0) == '/') {
            if (path.length() > 1) {
                c = 1;
                path = path.substring(1);
            }
            if (path.charAt(path.length() - 1) == '/' && !path.equals("/"))
                path = path.substring(0, path.length() - 1);
            if (path.length() == 1) {
                b = 1;
            }
            if(this.path.equals("/") && path.startsWith("..")){
                return null;
            }
            int found = 0;
            out = "/";
            Section old = new Section();
            old.setAll(nowIn);
            old.setID(nowIn.getID());
            String[] sections = path.split("/");
            if (b == 1) {
                out = "/";
                nowIn.setName("");
                nowIn.setID(-1);
                nowIn.setParentID(-1);
            }
            for (String a : sections) {
                ArrayList<Section> list;
                if (!a.equals("..")) {
                    if (c == 1) {
                        list = data.findSections(-1);
                        c = 0;
                    } else
                        list = data.findSections(nowIn.getID());
                    for (Section section : list) {
                        if (a.equals(section.getName())) {
                            out = out + a + '/';
                            nowIn.setAll(section);
                            nowIn.setID(section.getID());
                            found = 1;
                            break;
                        }
                    }
                    if (found == 0) {
                        nowIn.setAll(old);
                        nowIn.setID(old.getID());
                        return null;
                    } else
                        found = 0;
                } else if (a.equals("..") && !out.equals("/")) {
                    if (nowIn.getParentID() != -1) {
                        Section fresh = data.findSection(nowIn.getParentID());
                        nowIn.setAll(fresh);
                        nowIn.setID(fresh.getID());
                    } else {
                        nowIn = new Section();
                    }
                    if (!out.equals("/") && b != 1)
                        out = out.substring(0, out.length() - (nowIn.getName().length() + 1));
                }
            }
        } else {
            if (path.charAt(path.length() - 1) == '/')
                path = path.substring(0, path.length() - 1);
            int found = 0;
            out = this.path;
            Section old = new Section();
            old.setAll(nowIn);
            old.setID(nowIn.getID());
            String[] sections = path.split("/");
            for (String a : sections) {
                if (!a.equals("..")) {
                    ArrayList<Section> list = data.findSections(nowIn.getID());
                    for (Section section : list) {
                        if (a.equals(section.getName())) {
                            out = out + a + '/';
                            nowIn.setAll(section);
                            nowIn.setID(section.getID());
                            found = 1;
                            break;
                        }
                    }
                    if (found == 0) {
                        nowIn.setAll(old);
                        nowIn.setID(old.getID());
                        return null;
                    } else
                        found = 0;
                } else if (a.equals("..") && !out.equals("")) {
                    String h;
                    if (nowIn.getParentID() != -1) {
                        h = nowIn.getName();
                        Section fresh = data.findSection(nowIn.getParentID());
                        nowIn.setAll(fresh);
                        nowIn.setID(fresh.getID());
                    } else {
                        h = nowIn.getName();
                        nowIn = new Section();

                    }
                    out = out.substring(0, out.length() - (h.length() + 1));

                }
            }

        }
        this.path = out;
        return out;
    }

    // --- Listing methods ---

    /**
     * Funkcja zwracająca wszystkie podsekcje i książki
     * @return zwraca obiekt klasy LsReturn, który zawiera książki i działy znajdujące się w dziale, w którym aktualnie jesteśmy
     */
    public LsReturn ls() {
        ArrayList<Section> sections = data.findSections(nowIn.getID());
        ArrayList<Book> books = data.findBooksByParentID(nowIn.getID());
        return new LsReturn(sections, books);
    }

    // --- Finding methods ---

    /**
     * Funckja wyszukująca po nazwie wszystkie książki i sekcje
     * @param names nazwy po których ma wyszukać bazę danych
     * @return zwraca obiekt klasy LsReturn, który zawiera książki i działy
     */
    public LsReturn findQuery(String[] names) {
        ArrayList<Section> foundSections = new ArrayList<Section>();
        ArrayList<Book> foundBooks = new ArrayList<Book>();

        for (String name : names) {
            foundSections.addAll(data.findSections(name));
            foundBooks.addAll(data.findBooksByTitle(name));
            foundBooks.addAll(data.findBooksByAuthor(name));
        }
        return new LsReturn(foundSections, foundBooks);
    }

    /**
     * Funkcja znajdująca książkę po nazwie w sekcji w której jesteśmy
     * @param name nazwa książki której szukamy
     * @return zwraca listę książek które zawierają w sobie podaną nazwę
     */
    public ArrayList<Book> findBookInThisSection(String name) {
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        ArrayList<Book> out = new ArrayList<>();
        for (Book book : list) {
            if (book.getTitle().contains(name)) {
                out.add(book);
            }
        }
        return out;
    }

    /**
     * Funkcja znajdująca książki w sekcji, w której jesteśmy po autorze
     * @param author autor książki której szukamy
     * @return zwraca książki których autor jest równy podanemu w parametrze
     */
    public ArrayList<Book> findBookInThisSectionByAuthor(String author) {
        ArrayList<Book> list = data.findBooksByAuthor(author);
        ArrayList<Book> out = new ArrayList<>();
        for (Book book : list) {
            if (book.getParentID() == nowIn.getID()) {
                out.add(book);
            }
        }
        return out;
    }

    /**
     * Funkcja wyszukująca książki w sekcji, w której jesteśmy po autorach
     * @param authors autorzy książki której szukamy
     * @return zwraca listę książek, których autorzy pasują do podanych w parametrze
     */
    public ArrayList<Book> findBookInThisSectionByAuthors(ArrayList<String> authors) {
        ArrayList<Book> list = data.findBooksByAuthors(authors);
        ArrayList<Book> out = new ArrayList<>();
        for (Book book : list) {
            if (book.getParentID() == nowIn.getID()) {
                out.add(book);
            }
        }
        return out;
    }

    /**
     * Funkcja wyszukująca książkę o podanym ISBN w sekcji w której się znajdujemy
     * @param ISBN ciąg znaków reprezentujący ISBN
     * @return zwraca książkę, której szukamy lub null gdy nie została znaleziona
     */
    public Book findBookInThisSectionByISBN(String ISBN) {
        ArrayList<Book> list = data.findBooksByParentID(nowIn.getID());
        for (Book book : list) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Funkcja znajdująca wszystkie książki zawarte w podsekcjach których nazwa zawiera podane name
     * @param name nazwa książki
     * @param now sekcja w której chcemy wyszukać
     * @return zwraca listę książek ze wszystkich podsekcji, których nazwa zawiera podaną w parametrze name
     */
    public ArrayList<Book> findBooks(String name, Section now) {
        ArrayList<Book> list = data.findBooksByParentID(now.getID());
        list.removeIf(book -> !book.getTitle().contains(name));
        ArrayList<Section> list2 = data.findSections(now.getID());
        for (Section section : list2) {
            list.addAll(findBooks(name, section));
        }
        return list;
    }
    /**
     * Funkcja znajdująca wszystkie książki zawarte w podsekcjach których nazwa zaczyna się na name
     * @param name nazwa książki
     * @param now sekcja w której chcemy wyszukać
     * @return zwraca listę książek ze wszystkich podsekcji, których nazwa zawiera podaną w parametrze name
     */
    public ArrayList<Book> findBooksS(String name, Section now) {
        ArrayList<Book> list = data.findBooksByParentID(now.getID());
        list.removeIf(book -> !book.getTitle().startsWith(name));
        ArrayList<Section> list2 = data.findSections(now.getID());
        for (Section section : list2) {
            list.addAll(findBooks(name, section));
        }
        return list;
    }
    /**
     * Funkcja znajdująca wszystkie książki zawarte w podsekcjach których nazwa równa się name
     * @param name nazwa książki
     * @param now sekcja w której chcemy wyszukać
     * @return zwraca listę książek ze wszystkich podsekcji, których nazwa równa się podanej w parametrze name
     */
    public ArrayList<Book> findBooksE(String name, Section now) {
        ArrayList<Book> list = data.findBooksByParentID(now.getID());
        list.removeIf(book -> !book.getTitle().equals(name));
        ArrayList<Section> list2 = data.findSections(now.getID());
        for (Section section : list2) {
            list.addAll(findBooks(name, section));
        }
        return list;
    }

    /**
     * Funkcja wyszukująca sekcję o podanej nazwie we wszystkich podsekcjach podanej sekcji
     * @param name nazwa sekcji której szukamy
     * @param now obiek klasy section
     * @return
     */
    public Section findSectionInThisSection(String name, Section now) {
        ArrayList<Section> list = data.findSections(now.getID());
        for (Section section : list) {
            if (section.getName().equals(name)) {
                return section;
            }
        }
        return null;
    }

    /**
     * Funkcja wyszukująca książki o podanej nazwie w całej bazie danych
     * @param name nazwa ksiązki
     * @return lista książek któreych nazwa zawiera podaną
     */
    public ArrayList<Book> findBooksByTitle(String name) {
        return data.findBooksByTitle(name);
    }

    /**
     * Funkcja wyszukująca książki których tytuł zaczyna się na name w całej bazie danych
     * @param name nazwa ksiązki
     * @return lista książek których tytuł zaczyna sie na name
     */
    public ArrayList<Book> findBooksByTitleS(String name) {
        return data.findBooksByTitleS(name);
    }
    /**
     * Funkcja wyszukująca książki o tytule równym name w całej bazie danych
     * @param name nazwa ksiązki
     * @return lista książek któreych nazwa równa się podanej
     */
    public ArrayList<Book> findBooksByTitleE(String name) {
        return data.findBooksByTitleE(name);
    }

    /**
     * Funkcja wyszukująca książki po danym autorze w całej bazie danych
     * @param author nazwa autora
     * @return lista książek, których autor jest równy podanemu
     */
    public ArrayList<Book> findBooksByAuthor(String author) {
        return data.findBooksByAuthor(author);
    }

    /**
     * Funkcja wyszukująca książki po podanych autorach
     * @param authors lista autorów, po których szukamy
     * @return lista książek, których autorzy zgadzają się z podanymi
     */
    public ArrayList<Book> findBooksByAuthors(ArrayList<String> authors) {
        return data.findBooksByAuthors(authors);
    }

    /**
     * Funkcja wyszukująca książkę po ISBN
     * @param ISBN ciąg znaków reprezentujący ISBN książki
     * @return zwraca obiekt Book. jest to znaleziona książka
     */
    public Book findBook(String ISBN) {
        return data.findBook(ISBN);
    }

    /**
     * Funkcja wyszukująca wszystkie sekcje, których nazwa zawiera name
     * @param name nazwa sekcji
     * @return list sekcji, których nazwa zawiera name
     */
    public ArrayList<Section> findSections(String name) {
        return data.findSections(name);
    }

    /**
     * Funkcja znajdująca sekcje po ID rodzica
     * @param parentId liczba reprezentująca ID rodzica
     * @return lista sekcji, których rodzica ma ID podane w parametrze
     */
    public ArrayList<Section> findSections(int parentId) {
        return data.findSections(parentId);
    }

    // --- Saving methods ---

    /**
     * Funkcja zapisująca do pliku podanego przez użytkownika
     * @param fileName1 nazwa pliku do którego chcemy zapisać książki
     * @param fileName2 nazwa pliku do którego chcemy zapisać sekcje
     */
    public void save(String fileName1,String fileName2) {
        data.saveAll(fileName1,fileName2);
    }

    /**
     * Funkcja zapisująca w domyślnym pliku tekstowym
     */
    public void save() {
        data.saveAll();
    }

}