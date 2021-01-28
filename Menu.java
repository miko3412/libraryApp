package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa łącząca w sobie wszystkie moduły;
 * odpowiedzialna za interakcję z użytkownikiem
 * Posługuje się interfejsem terminalowym
 * @version 1.0
 */
public class Menu {

    public static String spaceReplace(String fraza){
        if (fraza.contains(" ")) return fraza.replace(" ", "_");
        else return fraza;
    }

    /**
     * metoda główna klasy
     * @param args nieużywane
     */
    public static void main(String[] args) {

        Operator operator = new Operator();
        boolean testRun = false;
        System.out.println("_________________________________\n_________________________________"
                + "\n WITAJ W SYSTEMIE BIBLIOTECZNYM! "
                + "\n_________________________________\n_________________________________\n\n"
                + "Aby zobaczyc liste komend napisz help");

        boolean run = true;
        int testIterator = 0;
        String path = "/";
        Scanner scanner = new Scanner(System.in);

        try {
            while (run) {
                String[] test = {"ls", "adds testSec", "ls", "cd testSec", "add testBook test123 testBook", "adds testSec1",
                        "adds testSec1", "adds testSec2", "ls", "mod test123 testBook1", "ls", "mv testBook1 testSec1", "ls",
                        "cd testSec1", "ls", "cd /testSec/", "mods testSec2 testSec3", "ls", "mvs testSec1 testSec3", "search test", "ls",
                        "cd testSec3", "ls", "rms testSec1", "ls", "cd ..", "ls", "rms testSec3", "ls", "search test", "rm testBook1",
                        "ls", "cd ..", "ls", "rms testSec", "ls", "save"};
                if ( testIterator >= test.length) testRun = false;

                String komenda;
                String[] parametry = {};
                if (!testRun){
                    System.out.print(path + " : ");
                    komenda = scanner.nextLine();
                    parametry = komenda.split(" ");
                }
                else {

                    parametry = test[testIterator].split(" ");
                    System.out.println(path + " : " + test[testIterator]);
                    testIterator++;
                }

                switch (parametry[0]) {
                    case "help":
                        if (parametry.length == 1) {
                            System.out.println("----------------------------------\n"
                                    + "ls: wyswietl zawartosc sekcji\n" + "cd <dir>: zmien aktualna sekcje\n"
                                    + "mod <ksiazka>: zmodyfikuj ksiazke\n" + "mods <sekcja>: zmodyfikuj sekcje\n"
                                    + "search <String>: wyszukaj obiekt w bazie danych\n"
                                    + "add <ksiazka>: dodaj nowa ksiazke\n" + "adds <sekcja>: dodaj nowa sekcje\n"
                                    + "rm [po ISBN(-i)/po tytule]: usun ksiazke\n" + "rms <sekcja>: usun sekcje\n"
                                    + "mv [po ISBN(-i)/po tytule]: przemiesc ksiazke\n" + "mvs <sekcja>: przemiesc sekcje\n"
                                    + "q : wyjdz z programu\n" + "save : zapisz wykonane zmiany\n"
                                    + "Aby uzyskac szczegolowe informacje uzyj komendy help <komenda>  /np: help cd/\n"
                                    + "----------------------------------");
                        } else if (parametry.length == 2) {
                            switch (parametry[1]) {
                                case "ls":
                                    System.out.println("ls\n"
                                            + "Wyswietla wszystkie podsekcje i ksiazki\n" +
                                            " znajdujace sie w sekcji w ktorej zostanie uzyta komenda");
                                    break;
                                case "cd":
                                    System.out.println("cd <dir>\n" + "Zmienia aktualna sciezke do sekcji na podana.\n"
                                            + "<dir> - nowa sciezka do sekcji\n"
                                            + "Jest mozliwosc uzycia zapisu sciezki do sekcji w sposob wzgledny");
                                    break;
                                case "mod":
                                    System.out.println("mod <tytul ksiazki>\n" +
                                            "Modyfikuje jeden z trzech dostepnych atrybutow ksiazki\n" +
                                            "Nalezy wybrac opcje:\n" +
                                            "   \"1\" aby zmodyfikowac nr ISBN\n" +
                                            "   \"2\" aby zmodyfikowac autora(autorow)\n" +
                                            "   \"3\" aby zmodyfikowac tytul\n" +
                                            "Nastepnie wybrany parametr zostanie zczytany\n" +
                                            "i zamieniony w bazie danych");
                                    break;

                                case "mods":
                                    System.out.println("mods <nazwa sekcji>\n" +
                                            "Modyfikuje nazwe wybranej sekcji\n" +
                                            "(aby zmienic jej lokalizacje nalezy\n" +
                                            "uzyc komendy mvs, zob. help mvs)\n" +
                                            "Nastepnie zostanie zczytana nowa nazwa sekcji\n" +
                                            "Nie mozna modyfikawac sekcji\n" +
                                            "w ktorej zostanie uzyta komenda");
                                    break;

                                case "search":
                                    System.out.println("search <String>\n" +
                                            "Wyszukuje i wyswietla wszystkie sekcje i ksiazki\n" +
                                            "znajdujace sie w bazie danych\n" +
                                            "zawierajce w nazwie wprowadzony ciag znakow\n" +
                                            "wraz z parametrami:\n" +
                                            "w przypadku sekcji jest to jej unikalny nr ID\n" +
                                            "dla ksiazki sa to: nr ISBN oraz autorzy");
                                    break;
                                case "add":
                                    System.out.println("add <tytul ksiazki>\n" +
                                            "dokonywana jest proba dodania ksiazki o zadanym tytule\n" +
                                            "Nastepnie zczytywane sa dane:\n" +
                                            "autor/autorzy - jesli ksiazka ma wiecej niz jednego autora\n" +
                                            "nalezy wymienic ich rozdzielajac znakiem ','\n" +
                                            "numer ISBN - nalezy podac unikalny numer nowej ksiazki\n" +
                                            "Po wprowadzeniu poprawnych danych ksiazka zostanie dodana\n" +
                                            "w sekcji w ktorej zostanie uzyta komenda");
                                    break;
                                case "adds":
                                    System.out.println("adds <nazwa sekcji>\n" +
                                            "Dodaje nowa sekcje o zadanej nazwie\n" +
                                            "w sekcji w ktorej zostanie uzyta komenda");
                                    break;
                                case "rm":
                                    System.out.println("rm [po ISBN(-i)/po tytule] <ISBN(-i)/tytul>\n" +
                                            "W przypadku zadeklarowania flagi (-i) nalezy podac kolejny argument - ISBN ksiazki\n" +
                                            "Ksiazka zostanie usunieta po tym parametrze\n" +
                                            "W drugim przypadku nalezy podac argument - tytul ksiazki\n" +
                                            "Jesli jest on unikalny w sekcji w ktorej zostanie uzyta komenda\n" +
                                            "ksiazka zostanie usunieta po tym parametrze");
                                    break;

                                case "rms":
                                    System.out.println("rms <nazwa sekcji>\n" +
                                            "Usuwa sekcje o nazwie podanej w argumencie znajdujaca sie\n" +
                                            "w sekcji w ktorej zostanie uzyta komenda\n" +
                                            "Opcjonalnie moze przyjac dodatkowy argument\n" +
                                            "bedacy inna lokalizacja w ktorej ma byc usunieta sekcja" +
                                            "(dozwolone sa trzy postaci wprowadzanej lokalizacji:\n" +
                                            "   1. sciezka bezwzgledna np. /a/b/c/\n" +
                                            "   2. sciezka wzgledna do sekcji nadrzednej \"..\"\n" +
                                            "   3. nazwa podsekcji znajdujacej sie\n" +
                                            "   w sekcji w ktorej zostanie uzyta komenda\n" +
                                            "(zawartosc sekcji zostaje przyporzadkowana do sekcji nadrzednej)\n" +
                                            "(nie mozna usunac sekcji w ktorej zostanie uzyta komenda)");
                                    break;
                                case "mv":
                                    System.out.println("mv [po ISBN(-i)/po tytule] <ISBN(-i)/tytul>\n" +
                                            "Przemieszcza ksiazke do innej sekcji\n" +
                                            "W przypadku zadeklarowania flagi (-i) nalezy podac kolejny argument - ISBN ksiazki\n" +
                                            "   Nastepnie zczytywana jest lokalizacja docelowa (dozwolone sa trzy postaci wprowadzanej lokalizacji:\n" +
                                            "   1. sciezka bezwzgledna np. /a/b/c/\n" +
                                            "   2. sciezka wzgledna do sekcji nadrzednej \"..\"\n" +
                                            "   3. nazwa podsekcji znajdujacej sie\n" +
                                            "   w sekcji w ktorej zostanie uzyta komenda\n" +
                                            "W drugim przypadku nalezy podac argument - tytul ksiazki\n" +
                                            "   Jesli jest on unikalny w sekcji w ktorej zostanie uzyta komenda\n" +
                                            "   zczytywana jest lokalizacja docelowa (dalej jak wyzej)");
                                    break;
                                case "mvs":
                                    System.out.println("mvs <nazwa sekcji> <sciezka>\n" +
                                            "Przemieszcza sekcje do innej sekcji\n" +
                                            "<nazwa sekcji> - ktora ma zastac przemieszczona\n" +
                                            "<sciezka> lokalizacja do ktorej ma byc przemieszczona sekcja\n" +
                                            "(dozwolone sa trzy postaci wprowadzanej lokalizacji:\n" +
                                            "   1. sciezka bezwzgledna np. /a/b/c/\n" +
                                            "   2. sciezka wzgledna do sekcji nadrzednej \"..\"\n" +
                                            "   3. nazwa podsekcji znajdujacej sie\n" +
                                            "   w sekcji w ktorej zostanie uzyta komenda\n" +
                                            "(nie mozna przemiescic sekcji w ktorej zostanie uzyta komenda)");
                                    break;
                                case "save":
                                    System.out.println("save\n" +
                                            "zapisuje dokonane zmiany do bazy danych");
                                    break;
                                case "q":
                                    System.out.println("q\n" +
                                            "zapisuje dokonane zmiany do bazy danych\n" +
                                            "i konczy dzialanie programu");
                                    break;
                                default:
                                    System.out.println("Nierozpoznana komenda");
                            }
                        } else
                            System.out.println("Za duzo parametrow");
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "ls":
                        if (testRun) System.out.println(" *** Uzycie komendy ls ***");
                        LsReturn lsReturn = operator.ls();
                        for (Section section : lsReturn.sections) {
                            System.out.println("<sec> " + section.getName());
                        }
                        for (Book book : lsReturn.books) {
                            System.out.println("<book> title: " + book.getTitle() + "\t ISBN: " + book.getISBN());
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "cd":
                        if (testRun) System.out.println(" *** Uzycie komendy cd z parametrem <" + parametry[1] + "> ***");
                        if (parametry.length == 1)
                            System.out.println("Brak potrzebnych parametrow");
                        else {
                            String s = operator.goTo(parametry[1]);
                            if (s == null)
                                System.out.println("Nie ma takiej sciezki");
                            else
                                path = s;
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "mod":
                        if(!testRun){
                            if (parametry.length == 1)
                                System.out.println("Brak potrzebnych parametrow");

                            else if(operator.findBookInThisSectionByISBN(parametry[1]) != null){
                                Scanner sc = new Scanner(System.in);
                                System.out.print("Jaki parametr chcesz zmodyfikowac?\n" +
                                        "   (1) ISBN\n" +
                                        "   (2) autorzy\n" +
                                        "   (3) tytul\n" +
                                        "(podaj liczbe): ");
                                String param = sc.nextLine();
                                switch (param){
                                    case "1":
                                        boolean loop = true;
                                        while (loop){
                                            System.out.print("Podaj nowy numer ISBN: ");
                                            String newINBN = sc.nextLine();
                                            if (!newINBN.equals("")) {
                                                if(!operator.modifyISBNBookNewISBN(parametry[1], newINBN)) System.out.println("Akcja nie powiodla sie");
                                                else {
                                                    System.out.println("ISBN zostal zmodyfikowany!");
                                                    loop = false;
                                                }
                                            }
                                            else System.out.println("To pole nie moze byc puste!");
                                        }
                                        break;

                                    case "2":
                                        String[] array;
                                        ArrayList<String> autors = new ArrayList<>();
                                        System.out.print("Podaj nowego autora/autorow (wedlug standardowych zasad, zob. <help add>): ");
                                        String newAut = sc.nextLine();
                                        if (!newAut.equals("")) {
                                            array = newAut.split(",");
                                            for (String s : array) {
                                                autors.add(s.trim());
                                            }
                                        }
                                        if(!operator.modifyISBNBookAuthor(parametry[1], autors)) System.out.println("Akcja nie powiodla sie");
                                        else {
                                            System.out.println("Autor(autorzy) zostal zmodyfikowany!");
                                        }

                                        break;

                                    case "3":
                                        boolean loop1 = true;
                                        while (loop1){
                                            System.out.print("Podaj nowy tytul: ");
                                            String newTitle = sc.nextLine().replace(" ", "_");
                                            if (!newTitle.equals("")) {
                                                if(!operator.modifyISBNBookTitle(parametry[1], newTitle)) System.out.println("Akcja nie powiodla sie");
                                                else {
                                                    System.out.println("Tytul zostal zmodyfikowany!");
                                                    loop1 = false;
                                                }
                                            }
                                            else System.out.println("To pole nie moze byc puste!");
                                        }
                                        break;

                                    default:
                                        System.out.println("Nie ma takiej opcji");
                                }
                            }
                            else System.out.println("W tej sekcji nie ma ksiazki o podanym ISBN\n" +
                                        "Akcja nie powiodla sie...");
                        }
                        else{
                            System.out.println(" *** Uzycie komendy mod z parametrem <" + parametry[1] + "> - zmiana tytulu ksiazki na <" + parametry[2] + "> ***");
                            operator.modifyISBNBookTitle(parametry[1], parametry[2]);
                        }

                        break;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "mods":
                        if(!testRun){
                            if (parametry.length == 1)
                                System.out.println("Brak potrzebnych parametrow");

                            else if (operator.findSectionInThisSection(parametry[1], operator.getNowIn()) !=null){
                                Scanner sc = new Scanner(System.in);

                                System.out.print("Podaj nowa nazwe sekcji: ");
                                String newName = sc.nextLine().replace(" ", "_");
                                if (!newName.equals("")) {
                                    if(!operator.modifySectionByName(parametry[1], path, newName)) System.out.println("Akcja nie powiodla sie");
                                    else {
                                        System.out.println("Nazwa sekcji zostala zmodyfikowana!");

                                    }
                                }
                                else System.out.println("Nie podales nowej nazwy!");
                            }
                            else System.out.println("Nie mozesz modyfikowac sekcji w ktorej sie znajdujesz!\n" +
                                        "Akcja nie powiodla sie...");
                        }
                        else{
                            System.out.println("Uzycie komendy mods z parametrem <" + parametry[1] + "> - zmiana nazwy sekcji na <" + parametry[2] + "> ***");
                            operator.modifySectionByName(parametry[1], path, parametry[2]);
                        }

                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "search":
                        if(testRun) System.out.println(" *** Uzycie komendy serch z parametrem <" + parametry[1] + "> ***");
                        if (parametry.length == 1)
                            System.out.println("Brak potrzebnych parametrow");
                        else {
                            LsReturn tmp = operator.findQuery(parametry);
                            for (Section section : tmp.sections) {
                                System.out.println("<sec> " + section.getName() + "\n   id: " + section.getID());
                            }
                            for (Book book : tmp.books) {
                                System.out.println("<book> " + book.getTitle() + "\n   ISBN: " + book.getISBN() + "\n   autorzy: " + book.getAuthors());
                            }
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "add":
                        if(!testRun){
                            if (parametry.length == 1)
                                System.out.println("Brak potrzebnych parametrow");
                            else {
                                ArrayList<String> autors = new ArrayList<>();
                                String[] array;
                                boolean run1 = true;
                                while (run1){
                                    System.out.print("Podaj autorow (w przypadku wielu rozdziel znakiem \",\"): ");
                                    String scan = scanner.nextLine();

                                    if (!scan.equals("")) {
                                        array = scan.split(",");
                                        for (String s : array) {
                                            autors.add(s.trim());
                                        }
                                        run1 = false;
                                    }
                                    else System.out.println("To pole nie moze byc puste!");
                                }

                                while (!run1){
                                    System.out.print("Podaj ISBN: ");
                                    String isbn = scanner.nextLine();
                                    if (!isbn.equals("")){
                                        if (operator.addBook(isbn, String.join(" ", Arrays.copyOfRange(parametry, 1, parametry.length)).replace(" ", "_"), autors)){
                                            System.out.println("Ksiazka zostala dodana pomyslnie!");
                                            run1 = true;
                                        }
                                        else System.out.println("Podany nr ISBN nie jest unikalny");
                                    }
                                    else System.out.println("To pole nie moze byc puste!");
                                }
                            }
                        }
                        else{
                            System.out.println(" *** Uzycie komendy add z parametrem <" + parametry[1] + "> - dodanie ksiazki o tytule testBook,\n" +
                                    "autorach: [Maksim Szaszkow, Mikolaj Blinowski, Adam Rogowski],\n" +
                                    "oraz numerze ISBN: test123 ***");
                            ArrayList<String> testAutors= new ArrayList(List.of("Maksim Szaszkow", "Mikolaj Blinowski", "Adam Rogowski"));
                            operator.addBook(parametry[2].replace(" ", "_"), parametry[3], testAutors);
                        }

                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "adds":
                        if(testRun) System.out.println(" *** Uzycie komendy adds z parametrem <" + parametry[1] + "> ***");
                        if (parametry.length == 1) {
                            System.out.println("Brak potrzebnych parametrow");
                        } else {
                            if(!operator.addSection(String.join(" ", Arrays.copyOfRange(parametry, 1, parametry.length)).replace(" ", "_")))
                                System.out.println("Nie udalo sie dodac sekcji...");
                            else System.out.println("Akcja powiodla sie!");
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "rm":
                        if(testRun){
                            System.out.println(" *** Uzycie komendy rm z parametrem <" + parametry[1] + "> ***");
                        }
                        if (parametry.length == 1)
                            System.out.println("Brak potrzebnych parametrow");
                        else {
                            if(parametry[1].equals("-i")){
                                if(operator.findBookInThisSectionByISBN(parametry[2]) != null){
                                    if (operator.removeBookByISBN(parametry[2])) System.out.println("Ksiazka zostala usunieta pomyslnie!");
                                    else System.out.println("Niestety akcja sie nie powiodla");
                                }
                                else System.out.println("W tej sekcji nie ma ksiazki o podanym ISBN\n" +
                                        "Akcja nie powiodla sie...");
                            }
                            else {
                                if (operator.findBookInThisSection(parametry[1]).size() == 0){
                                    System.out.println("W tej sekcji nie ma ksiazki o podanym tytule\n" +
                                            "Akcja nie powiodla sie...");
                                }
                                else if (operator.findBookInThisSection(parametry[1]).size() == 1){
                                    if(operator.removeBookByTitle(parametry[1])) System.out.println("Ksiazka zostala usunieta pomyslnie!");
                                    else System.out.println("Niestety akcja sie nie powiodla");
                                }
                                else System.out.println("W tej sekcji znajduje sie wiecej ksiazek o podanym tytule\n" +
                                            "Sprobuj komendy rm z flaga (-i) aby wybrac unikalna ksiazke po jej numerze ISBN");
                            }
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "rms":
                        if(testRun){
                            System.out.println(" *** Uzycie komendy rms z parametrem <" + parametry[1] + "> ***");
                        }
                        if (parametry.length == 1)
                            System.out.println("Brak potrzebnych parametrow");
                        else if (parametry.length==2)
                        {
                            if(!path.equals("/")) {
                                String[] loc = path.split("/");
                                if (loc[loc.length - 1].equals(parametry[1]))
                                    System.out.println("Nie mozesz usunac sekcji w ktrej sie znajdujesz!");
                                else {
                                    if (!operator.removeSection(path, parametry[1]))
                                        System.out.println("Nie udalo sie usunac sekcji...");
                                    else System.out.println("Akcja powiodla sie!");
                                }
                            }
                            else{
                                if (!operator.removeSection(path, parametry[1]))
                                    System.out.println("Nie udalo sie usunac sekcji...");
                                else System.out.println("Akcja powiodla sie!");
                            }
                        }
                        else{
                            if(path.equals(parametry[2])) System.out.println("Nie mozesz usunac sekcji w ktrej sie znajdujesz!");
                            else{
                                if(!operator.removeSection(parametry[2], parametry[1])) System.out.println("Nie udalo sie usunac sekcji w zadalej lokalizacji...");
                                else System.out.println("Akcja powiodla sie!");
                            }
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "mv":
                        if(!testRun){
                            if (parametry.length == 1)
                                System.out.println("Brak potrzebnych parametrow");
                            else{
                                Scanner scanner1 = new Scanner(System.in);
                                if(parametry[1].equals("-i")){
                                    if(operator.findBookInThisSectionByISBN(parametry[2]) != null){
                                        System.out.print("Podaj sciezke docelowa: ");
                                        if(operator.moveBookByISBN(parametry[2], scanner1.nextLine().trim())!=null){
                                            System.out.println("Akcja przemieszczenia powiodla sie!");
                                        }
                                        else System.out.println("Bledna sciezka\n" +
                                                "Akcja nie powiodla sie...");
                                    }
                                    else System.out.println("W tej sekcji nie ma ksiazki o podanym ISBN\n" +
                                            "Akcja nie powiodla sie...");
                                }
                                else {
                                    if (operator.findBookInThisSection(parametry[1]).size() == 0){
                                        System.out.println("W tej sekcji nie ma ksiazki o podanym tytule\n" +
                                                "Akcja nie powiodla sie...");
                                    }
                                    else if (operator.findBookInThisSection(parametry[1]).size() == 1){
                                        System.out.print("Podaj sciezke docelowa: ");
                                        if (operator.moveBook(parametry[1], scanner1.nextLine().trim())!=null){
                                            System.out.println("Akcja przemieszczenia powiodla sie!");
                                        }
                                        else System.out.println("Bledna sciezka\n" +
                                                "Akcja nie powiodla sie...");
                                    }
                                    else System.out.println("W tej sekcji znajduje sie wiecej ksiazek o podanym tytule\n" +
                                                "Sprobuj komendy mv z flaga (-i) aby wybrac unikalna ksiazke po jej numerze ISBN");
                                }
                            }
                        }
                        else{
                            System.out.println(" *** Uzycie komendy mv z parametrem <" + parametry[1] + "> - przemieszczenie ksiazki do lokalizacji <" + parametry[2] + "> ***");
                            operator.moveBook(parametry[1], parametry[2]);

                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "mvs":
                        if(testRun){
                            System.out.println(" *** Uzycie komendy mvs z parametrami <" + parametry[1] + "> <" + parametry[1] + "> ***");
                        }
                        if (parametry.length == 1)
                            System.out.println("Brak potrzebnych parametrow");
                        else if(parametry.length == 2) System.out.println("Za malo argumentow");
                        else
                        {
                            if(!path.equals("/")) {
                                String[] loc = path.split("/");
                                if (loc[loc.length - 1].equals(parametry[1]))
                                    System.out.println("Nie mozesz przemiescic sekcji w ktrej sie znajdujesz!");
                                else {
                                    String result = operator.moveSection(parametry[1], parametry[2]);
                                    if (result == null) System.out.println("Nie udalo sie przeniesc sekcji...");
                                    else{
                                        System.out.println("Akcja powiodla sie!");
                                    }
                                }
                            }
                            else{
                                String result = operator.moveSection(parametry[1], parametry[2]);
                                if (result == null) System.out.println("Nie udalo sie usunac sekcji...");
                                else{
                                    System.out.println("Akcja powiodla sie!");
                                }
                            }
                        }
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    case "save":
                        operator.save();
                        System.out.println("Dane zapiane pomyslnie!");
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    case "q":
                        run = false;
                        break;

                    case "test":
                        testRun = true;
                        break;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    default:
                        System.out.println("Nierozpoznana komenda");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        operator.save();
        scanner.close();
    }
}
