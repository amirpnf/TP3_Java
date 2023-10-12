import java.util.Objects;

public class Book2 {
  private final String title;
  private final String author;

  public Book2(String title, String author) {
    this.title = title;
    this.author = author;
  }
  
  @Override 
  public boolean equals(Object other) {
    return other instanceof Book2 o && 
      title.equals(o.title) && author.equals(o.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author); 
  }


  public static void main(String[] args) {
    var book1 = new Book2("Da Vinci Code", "Dan Brown");
    var book2 = new Book2("Da Vinci Code", "Dan Brown");
    System.out.println(book1.equals(book2));
    System.out.println(book1.hashCode() == book2.hashCode());
  }
}