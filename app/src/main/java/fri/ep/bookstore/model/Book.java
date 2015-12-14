package fri.ep.bookstore.model;

import java.util.Locale;

public class Book {

    public String id;
    public String author;
    public String title;
    public double price;
    public String uri;
    public String description;

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s: %s (%.2f EUR)", author, title, price);
    }
}
