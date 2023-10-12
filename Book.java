import java.util.*;

public record  Book(String title, String author){

  @Override
  public String toString() {
    return this.title + " by " + this.author;
  }

  public Book(String title, String author) {
    Objects.requireNonNull(title, "Title can't be empty");
    Objects.requireNonNull(author, "Author can't be empty");
    this.title = title;
    this.author = author;
  }

  public Book(String title) {
    this(title, "<no author>");
  }

  public static void main(String[] args) {
    var book = new Book("Da Vinci Code", "Dan Brown");
    System.out.println(book.title + ' ' + book.author);
  }

  public Book withTitle(String title) {
    return new Book(title, this.author);
  }

  public boolean isFromTheSameAuthor(Book other) {
    return this.author.equals(other.author);
  }
}