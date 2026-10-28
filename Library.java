import java.util.ArrayList;
import java.util.List;

public class Library {
    private static final int MAX_BORROW = 3;

    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void addMember(Member member) { members.add(member); }

    public Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public boolean borrowBook(Member member, int bookId) {
        Book book = findBook(bookId);
        if (book == null || !book.isAvailable() || member.getBorrowed().size() >= MAX_BORROW) {
            return false;
        }
        book.setAvailable(false);
        member.getBorrowed().add(book);
        return true;
    }

    public boolean returnBook(Member member, int bookId) {
        Book book = findBook(bookId);
        if (book == null || !member.getBorrowed().remove(book)) {
            return false;
        }
        book.setAvailable(true);
        return true;
    }

    public void printCatalog() {
        System.out.println("--- Catalog ---");
        for (Book b : books) System.out.println(b);
    }

    public void printMembers() {
        System.out.println("--- Members ---");
        for (Member m : members) System.out.println(m);
    }
}
