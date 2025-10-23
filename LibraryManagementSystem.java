import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    static class Book {
        int id;
        String title;
        String author;
        boolean issued;

        Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.issued = false;
        }

        @Override
        public String toString() {
            return String.format("[%d] %s by %s %s", id, title, author, issued ? "(issued)" : "");
        }
    }

    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public static void main(String[] args) {
        new LibraryManagementSystem().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add book");
            System.out.println("2. List books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> addBook(sc);
                case "2" -> listBooks();
                case "3" -> issueBook(sc);
                case "4" -> returnBook(sc);
                case "5" -> {
                    System.out.println("Goodbye.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addBook(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Author: ");
        String author = sc.nextLine().trim();
        Book b = new Book(nextId++, title, author);
        books.add(b);
        System.out.println("Book added: " + b);
    }

    private void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        books.forEach(System.out::println);
    }

    private void issueBook(Scanner sc) {
        System.out.print("Enter book id to issue: ");
        int id = readInt(sc);
        Book b = find(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        if (b.issued) {
            System.out.println("Book already issued.");
            return;
        }
        b.issued = true;
        System.out.println("Issued: " + b.title);
    }

    private void returnBook(Scanner sc) {
        System.out.print("Enter book id to return: ");
        int id = readInt(sc);
        Book b = find(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!b.issued) {
            System.out.println("Book is not issued.");
            return;
        }
        b.issued = false;
        System.out.println("Returned: " + b.title);
    }

    private int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private Book find(int id) {
        for (Book b : books) if (b.id == id) return b;
        return null;
    }
}
