import java.util.*;

abstract class Book {
    String isbn;
    String title;
    String author;
    int year;
    double price;

    public Book(String isbn, String title, String author, int year, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public abstract double purchase(int quantity, String email, String address) throws Exception;

    public String getDetails() {
        return "Title: " + title +
               ", ISBN: " + isbn +
               ", Author: " + author +
               ", Year: " + year +
               ", Price: " + price;
    }
}

class PaperBook extends Book {
    int stock;

    public PaperBook(String isbn, String title, String author, int year, double price, int stock) {
        super(isbn, title, author, year, price);
        this.stock = stock;
    }

    @Override
    public double purchase(int quantity, String email, String address) throws Exception {
        if (quantity > stock) {
            throw new Exception("Quantum book store: Not enough stock for " + title);
        }
        stock -= quantity;
        System.out.println("Quantum book store: ShippingService sent to " + address);
        return price * quantity;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Stock: " + stock;
    }
}

class EBook extends Book {
    String filetype;

    public EBook(String isbn, String title, String author, int year, double price, String filetype) {
        super(isbn, title, author, year, price);
        this.filetype = filetype;
    }

    @Override
    public double purchase(int quantity, String email, String address) {
        System.out.println("Quantum book store: MailService sent to " + email);
        return price * quantity;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Filetype: " + filetype;
    }
}

class ShowcaseBook extends Book {
    public ShowcaseBook(String isbn, String title, String author, int year) {
        super(isbn, title, author, year, 0.0);
    }

    @Override
    public double purchase(int quantity, String email, String address) throws Exception {
        throw new Exception("Quantum book store: '" + title + "' is not for sale");
    }
}

class QuantumBookstore {
    Map<String, Book> inventory = new HashMap<>();

    public void addBook(Book book) {
        inventory.put(book.isbn, book);
        System.out.println("Quantum book store: Book added -> " + book.getDetails());
    }

    public List<Book> removeOutdatedBooks(int years) {
        List<Book> removed = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<String> toRemove = new ArrayList<>();

        for (String isbn : inventory.keySet()) {
            Book book = inventory.get(isbn);
            if (currentYear - book.year > years) {
                removed.add(book);
                toRemove.add(isbn);
            }
        }

        for (String isbn : toRemove) {
            inventory.remove(isbn);
        }

        for (Book book : removed) {
            System.out.println("Quantum book store: Removed outdated book -> " + book.getDetails());
        }

        return removed;
    }

    public double buyBook(String isbn, int quantity, String email, String address) throws Exception {
        if (!inventory.containsKey(isbn)) {
            throw new Exception("Quantum book store: Book with ISBN " + isbn + " not found");
        }

        Book book = inventory.get(isbn);
        double amount = book.purchase(quantity, email, address);
        System.out.println("Quantum book store: Purchase complete -> " + book.getDetails());
        System.out.println("Quantum book store: Quantity bought: " + quantity + ", Total paid: " + amount);
        return amount;
    }

    public void showBooks() {
        System.out.println("Quantum book store: Current Inventory:");
        for (Book book : inventory.values()) {
            System.out.println(" - " + book.getDetails());
        }
    }
}

//Test case for the QuantumBookstore system
public class QuantumBookstoreFullTest {
    public static void main(String[] args) {
        QuantumBookstore store = new QuantumBookstore();
        // Adding books to the store
        Book b1 = new PaperBook("isbn1", "Python Book", "Author A", 2005, 100.0, 5);
        Book b2 = new PaperBook("isbn2", "C++ Book", "Author B", 2018, 150.0, 2);
        Book b3 = new EBook("isbn3", "Java Book", "Author C", 2010, 60.0, "PDF");
        Book b4 = new EBook("isbn4", "JS Book", "Author D", 2022, 80.0, "EPUB");
        Book b5 = new ShowcaseBook("isbn5", "Go Book", "Author E", 2010);

        store.addBook(b1);
        store.addBook(b2);
        store.addBook(b3);
        store.addBook(b4);
        store.addBook(b5);

        // Displaying the books in the store
        store.showBooks();

        // Buying books
        try {
            store.buyBook("isbn2", 1, "ahmed@example.com", "Giza");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            store.buyBook("isbn5", 1, "mohamed@example.com", "Alex");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Removing outdated books
        store.removeOutdatedBooks(10);

        // Displaying the remaining books in the store after removal and purchase
        store.showBooks();
    }
}
