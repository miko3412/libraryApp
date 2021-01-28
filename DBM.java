package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DBM {

    private ArrayList<Book> books;
    private ArrayList<Section> sections;

    /**
     * Konstruktor próbuje odczytać zserializowane dane z domyślnych plików
     */
    public DBM() {
        books = new ArrayList<Book>();
        sections = new ArrayList<Section>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("database/books.ser")))) {
            Object obj = ois.readObject();

            //System.out.println(obj);

            if (obj != null) {
                @SuppressWarnings("unchecked")
                ArrayList<Book> tmp = (ArrayList<Book>) obj;
                books = tmp;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("database/sections.ser")))) {
            Object obj = ois.readObject();
            //System.out.println(obj);

            if (obj != null) {
                @SuppressWarnings("unchecked")
                ArrayList<Section> tmp = (ArrayList<Section>) obj;
                sections = tmp;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Konstruktor próbuje odczytać zserializowane dane z podanego pliku
     */
    public DBM(String fileName) {
        books = new ArrayList<Book>();
        sections = new ArrayList<Section>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            Object obj = ois.readObject();

            if (obj != null) {
                @SuppressWarnings("unchecked")
                ArrayList<Book> tmp = (ArrayList<Book>) obj;
                books = tmp;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            Object obj = ois.readObject();

            if (obj != null) {
                @SuppressWarnings("unchecked")
                ArrayList<Section> tmp = (ArrayList<Section>) obj;
                sections = tmp;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @return Lista wszystkich książek
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * 
     * @return Lista wszystkich sekcji
     */
    public ArrayList<Section> getSections() {
        return sections;
    }

    /**
     * Dodaj książkę do bazy danych, weryfikując czy ISBN jest unikalny,
     * 
     * @param book Książka do dodania
     * @return true - dodanie powiodło się, false - ISBN nie jest unikalny
     */
    public boolean addBook(Book book) {
        for (Book b : books) {
            if (b.getISBN().equals(book.getISBN())) {
                return false;
            }
        }
        return books.add(book);
    }

    /**
     * Dodaj książkę do bazy danych, implementuje addBook(Book book)
     * 
     * @param ISBN      ISNB książki
     * @param title     Tytuł ksiażki
     * @param authors   Lista autorów książki
     * @param sectionID Sekcja do której należy ksiązka
     * @return Dodaną książkę jeśli dodanie powiodło się, null w innym wypadku
     */
    public Book addBook(String ISBN, String title, ArrayList<String> authors, int sectionID) {
        Book book = new Book(ISBN, title, authors, sectionID);
        if (addBook(book))
            return book;
        else
            return null;
    }

    /**
     * Modyfikuj książkę, znajdując po instancji obiektu Book, implementuje
     * findBook(Book book) do wyszukania książki
     * 
     * @param book    Instancja Book znajdująca się w bazie danych
     * @param newBook Instancja Book na którą zastąpić tą w bazie danych
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(Book book, Book newBook) {
        Book tmp = findBook(book);
        if (tmp != null) {
            for (Book b : books) {
                if (b.getISBN().equals(newBook.getISBN()))
                    return false;
            }
            tmp.setAll(newBook);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po instancji obiektu Book, implementuje
     * findBook(Book book) do wyszukania książki
     * 
     * @param book     Instancja Book znajdująca się w bazie danych
     * @param newTitle Tytuł na który zmienić obecny tytuł książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(Book book, String newTitle) {
        Book tmp = findBook(book);
        if (tmp != null) {
            tmp.setTitle(newTitle);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po instancji obiektu Book, implementuje
     * findBook(Book book) do wyszukania książki
     * 
     * @param book       Instancja Book znajdująca się w bazie danych
     * @param newAuthors Lista autorów na którą zmienić obecnych autorów książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(Book book, ArrayList<String> newAuthors) {
        Book tmp = findBook(book);
        if (tmp != null) {
            tmp.setAuthors(newAuthors);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po instancji obiektu Book, implementuje
     * findBook(Book book) do wyszukania książki
     * 
     * @param book         Instancja Book znajdująca się w bazie danych
     * @param newSectionID Sekcja na którą zmienić obecną sekcję książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(Book book, int newSectionID) {
        Book tmp = findBook(book);
        if (tmp != null) {
            tmp.setParentID(newSectionID);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po instancji obiektu Book, implementuje
     * findBook(Book book) do wyszukania książki
     * 
     * @param book    Instancja Book znajdująca się w bazie danych
     * @param newISBN ISBN na który zmienić obecny ISBM książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBookISBN(Book book, String newISBN) {
        Book tmp = findBook(book);
        if (tmp != null) {
            for (Book b : books) {
                if (b.getISBN().equals(newISBN))
                    return false;
            }
            tmp.setISBN(newISBN);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po jej ISBN, implementuje findBook(String ISBN)
     * do wyszukania książki
     * 
     * @param ISBN    ISBN książki do wyszukania
     * @param newBook Instancja Book na którą zastąpić tą w bazie danych
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(String ISBN, Book newBook) {
        Book tmp = findBook(ISBN);
        if (tmp != null) {
            for (Book b : books) {
                if ((b.getISBN().equals(newBook.getISBN())))
                    return false;
            }
            findBook(ISBN).setAll(newBook);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po jej ISBN, implementuje findBook(String ISBN)
     * do wyszukania książki
     * 
     * @param ISBN     ISBN książki do wyszukania
     * @param newTitle Tytuł na który zmienić obecny tytuł książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(String ISBN, String newTitle) {
        Book tmp = findBook(ISBN);
        if (tmp != null) {
            tmp.setTitle(newTitle);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po jej ISBN, implementuje findBook(String ISBN)
     * do wyszukania książki
     * 
     * @param ISBN       ISBN książki do wyszukania
     * @param newAuthors Lista autorów na którą zmienić obecnych autorów książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(String ISBN, ArrayList<String> newAuthors) {
        Book tmp = findBook(ISBN);
        if (tmp != null) {
            tmp.setAuthors(newAuthors);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po jej ISBN, implementuje findBook(String ISBN)
     * do wyszukania książki
     * 
     * @param ISBN         ISBN książki do wyszukania
     * @param newSectionID Sekcja na którą zmienić obecną sekcję książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBook(String ISBN, int newSectionID) {
        Book tmp = findBook(ISBN);
        if (tmp != null) {
            tmp.setParentID(newSectionID);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj książkę, znajdując po jej ISBN, implementuje findBook(String ISBN)
     * do wyszukania książki
     * 
     * @param ISBN    ISBN książki do wyszukania
     * @param newISBN ISBN na który zmienić obecny ISBM książki
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         książki
     */
    public boolean modifyBookISBN(String ISBN, String newISBN) {
        Book tmp = findBook(ISBN);
        if (tmp != null) {
            for (Book b : books) {
                if (b.getISBN().equals(newISBN))
                    return false;
            }
            tmp.setISBN(newISBN);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj sekcję, znajdując po instancji sekcji Section, implementuje
     * findSection(Section section) do znalezienia sekcji
     * 
     * @param section    Instancja Section znajdująca się w bazie danych
     * @param newSection Instancja Section na którą zamienić obecną sekcję
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         sekcji
     */
    public boolean modifySection(Section section, Section newSection) {
        Section sec = findSection(section);
        if (sec != null) {
            sec.setAll(newSection);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj sekcję, znajdując po instancji sekcji Section, implementuje
     * findSection(Section section) do znalezienia sekcji
     * 
     * @param section Instancja Section znajdująca się w bazie danych
     * @param newName Nazwa na którą zmienić obecną nazwę sekcji
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         sekcji
     */
    public boolean modifySection(Section section, String newName) {
        Section sec = findSection(section);
        if (sec != null) {
            sec.setName(newName);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj sekcję, znajdując po instancji sekcji Section, implementuje
     * findSection(Section section) do znalezienia sekcji
     * 
     * @param section     Instancja Section znajdująca się w bazie danych
     * @param newParentID ID rodzica na który zmienić obecne ID rodzica
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         sekcji
     */
    public boolean modifySection(Section section, int newParentID) {
        Section sec = findSection(section);
        if (sec != null) {
            sec.setParentID(newParentID);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj sekcję, znajdując po jej id w bazie danych, implementuje
     * findSection(Section section) do znalezienia sekcji
     * 
     * @param id         ID po którym znaleźć sekcję
     * @param newSection Instancja Section na którą zamienić obecną sekcję
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         sekcji
     */
    public boolean modifySection(int id, Section newSection) {
        Section sec = findSection(id);
        if (sec != null) {
            ArrayList<Section> secs = findSections(sec.getParentID());
            for (Section s : secs) {
                if (s.getName().equals(newSection.getName()))
                    return false;
            }
            sec.setAll(newSection);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj sekcję, znajdując po jej id w bazie danych, implementuje
     * findSection(Section section) do znalezienia sekcji
     * 
     * @param id      ID po którym znaleźć sekcję
     * @param newName Nazwa na którą zmienić obecną nazwę sekcji
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         sekcji
     */
    public boolean modifySection(int id, String newName) {
        Section sec = findSection(id);
        if (sec != null) {
            ArrayList<Section> secs = findSections(sec.getParentID());
            for (Section s : secs) {
                if (s.getName().equals(newName))
                    return false;
            }
            sec.setName(newName);
            return true;
        }
        return false;
    }

    /**
     * Modyfikuj sekcję, znajdując po jej id w bazie danych, implementuje
     * findSection(Section section) do znalezienia sekcji
     * 
     * @param id          ID po którym znaleźć sekcję
     * @param newParentID ID rodzica na który zmienić obecne ID rodzica
     * @return true - modyfikacja powiodła się, false - nie znaleziono podanej
     *         sekcji lub nazwa nie jest unikalna
     */
    public boolean modifySection(int id, int newParentID) {
        Section sec = findSection(id);
        if (sec != null) {
            ArrayList<Section> secs = findSections(newParentID);
            for (Section s : secs) {
                if (s.getName().equals(sec.getName()))
                    return false;
            }
            sec.setParentID(newParentID);
            return true;
        }
        return false;
    }

    /**
     * Usuń książkę z bazy danych przez podanie instancji Book
     * 
     * @param book Książka do usunięcia
     * @return wynik funkcji remove obiektu typu ArrayList<>
     */
    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    /**
     * Usuń książkę z bazy danych przez podanie ISBN
     * 
     * @param ISBN ISBN książki do usunięcia
     * @return wynik funkcji remove obiektu typu ArrayList<> lub false w wypadku gdy
     *         nie ma książki o podanym ISBN
     */
    public boolean removeBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN))
                return books.remove(book);
        }
        return false;
    }

    /**
     * Usuń z bazy danych wszystkie książki podanych autorów
     * 
     * @param authors Lista autorów których książki usunąć
     * @return true
     */
    public boolean removeBook(ArrayList<String> authors) {
        for (Book book : books) {
            for (String author : authors) {
                if (book.getAuthors().contains(author))
                    books.remove(book);
            }
        }
        return true;
    }

    /**
     * Usuń wszystkie książki danego autora z bazy danych
     * 
     * @param author Autor którego książki usunąć
     * @return
     */
    public boolean removeBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthors().contains(author))
                books.remove(book);
        }
        return true;
    }

    /**
     * Dodaj nową sekcję do bazy danych
     * 
     * @param section Sekcja do dodania
     * @return Wynik fukcji add wykonananej na obiekcie typu ArrayList<>
     */
    public boolean addSection(Section section) {
        if (sections.size() != 0) {
            ArrayList<Section> secs = findSections(section.getParentID());
            for (int i = 0; i < secs.size(); i++) {
                if (secs.get(i).getName().equals(section.getName()))
                    return false;
            }
            int max = 0;
            for (int i = 0; i < sections.size(); i++) {
                if (sections.get(i).getID() >= max) {
                    max = sections.get(i).getID() + 1;
                }
            }
            section.setID(max);
        } else
            section.setID(0);
        return sections.add(section);
    }

    /**
     * Dodaj nową sekcję do bazy danych
     * 
     * @param name     Nazwa sekcji
     * @param parentID ID rodzica
     * @return Sekcja jeśli dodanie się powiodło, null w innym wypadku
     */
    public Section addSection(String name, int parentID) {
        Section section = new Section(name, parentID);
        if (addSection(section))
            return section;
        else
            return null;
    }

    /**
     * Usuwa sekcję z bazy danych
     * 
     * @param section Sekcja do usunięcia
     * @return Wynik funkcji remove na obiekcie typu ArrayList<>
     */
    public boolean removeSection(Section section) {
        return sections.remove(section);
    }

    /**
     * Usuwa sekcję z bazy danych
     * 
     * @param id ID sekcji do usunięcia
     * @return Wynik funkcji remove na obiekcie typu ArrayList<>
     */
    public boolean removeSection(int id) {
        for (Section section : sections) {
            if (section.getID() == id)
                return sections.remove(section);
        }
        return false;
    }

    /**
     * Usuwa sekcję z bazy danych
     * 
     * @param parentID
     * @return true
     */
    public boolean removeSectionByParentID(int parentID) {
        for (Section section : sections) {
            if (section.getParentID() == parentID)
                sections.remove(section);
        }
        return true;
    }

    /**
     * Zapisuje obecny stan książek do domyślnego pliku
     */
    public void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("database/books.ser")))) {
            oos.writeObject(books);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Zapisuje obecny stan książek do podanego pliku
     * 
     * @param fileName Nazwa pliku
     */
    public void saveBooks(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(books);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Zapisuje obecny stan sekcji do domyślnego pliku
     */
    public void saveSections() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("database/sections.ser")))) {
            oos.writeObject(sections);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Zapisuje obecny stan sekcji do podanego pliku
     * 
     * @param fileName
     */
    public void saveSections(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(sections);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Zapisuje książki i sekcje do domyślnych plików
     */
    public void saveAll() {
        saveBooks();
        saveSections();
    }

    /**
     * Zapisuje książki i sekcje do podanych plików
     * 
     * @param fileName1 Nazwa pliku książek
     * @param fileName2 Nazwa pliku sekcji
     */
    public void saveAll(String fileName1, String fileName2) {
        saveBooks(fileName1);
        saveSections(fileName2);
    }

    /**
     * Wyszukuje instancję Book w bazie danych
     * 
     * @param book Książka do wyszukania
     * @return Znaleziona książka
     */
    public Book findBook(Book book) {
        for (Book b : books) {
            if (book.equals(b))
                return b;
        }
        return null;
    }

    /**
     * Wyszukuje instancję Book w bazie danych
     * 
     * @param ISBN ISBN książki do wyszukania
     * @return Znaleziona książka
     */
    public Book findBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN))
                return book;
        }
        return null;
    }

    /**
     * Wyszykuje książki poprzez tytuł (contains)
     * 
     * @param name Tytuł ksiażki
     * @return Lista znalezionych książek
     */
    public ArrayList<Book> findBooksByTitle(String name) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().contains(name)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Wyszykuje książki poprzez tytuł (startsWith)
     * 
     * @param name Tytuł ksiażki
     * @return Lista znalezionych książek
     */
    public ArrayList<Book> findBooksByTitleS(String name) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().startsWith(name)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Wyszykuje książki poprzez tytuł (equals)
     * 
     * @param name Tytuł ksiażki
     * @return Lista znalezionych książek
     */
    public ArrayList<Book> findBooksByTitleE(String name) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().equals(name)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Wyszykuje książki poprzez autora
     * 
     * @param author Autor
     * @return Lista znalezionych książek
     */
    public ArrayList<Book> findBooksByAuthor(String author) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthors().contains(author)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Wyszykuje książki poprzez autorów
     * 
     * @param authors Autorzy książki
     * @return Lista znalezionych książek
     */
    public ArrayList<Book> findBooksByAuthors(ArrayList<String> authors) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {
            for (String author : authors) {
                if (book.getAuthors().contains(author)) {
                    foundBooks.add(book);
                    break;
                }
            }
        }
        return foundBooks;
    }

    /**
     * Wyszykuje książki poprzez sekcję w której się znajduje
     * 
     * @param parentID ID sekcji
     * @return Lista znalezionych książek
     */
    public ArrayList<Book> findBooksByParentID(int parentID) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getParentID() == parentID) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Wyszukuje sekcję w bazie danych
     * 
     * @param section Sekcja do wyszukania
     * @return Znaleziona sekcja
     */
    public Section findSection(Section section) {
        return sections.get(sections.indexOf(section));
    }

    /**
     * Wyszukuje sekcję w bazie danych przez id
     * 
     * @param id ID sekcji do wyszukania
     * @return Znaleziona sekcja
     */
    public Section findSection(int id) {
        return sections.get(id);
    }

    /**
     * Wyszukuje sekcje w bazie danych przez nazwę
     * 
     * @param name Nazwa sekcji do szukania
     * @return Znalezione sekcje
     */
    public ArrayList<Section> findSections(String name) {
        ArrayList<Section> foundSections = new ArrayList<Section>();
        for (Section section : sections) {
            if (section.getName().contains(name))
                foundSections.add(section);
        }

        return foundSections;
    }

    /**
     * Wyszukuje sekcje w bazie danych przez sekcję w której się znajdują
     * 
     * @param parentId ID sekcji w której znajdują się szukane sekcje
     * @return Znalezione sekcje
     */
    public ArrayList<Section> findSections(int parentId) {
        ArrayList<Section> foundSections = new ArrayList<Section>();
        for (Section section : sections) {
            if (section.getParentID() == parentId)
                foundSections.add(section);
        }
        return foundSections;
    }

}