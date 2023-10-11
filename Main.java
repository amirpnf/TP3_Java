public class Main {
  public static void main(String[] args) {
  var book = new Book("Da Vinci Code", "Dan Brown");  
  System.out.println(book.title() + ' ' + book.author());
  //var weirdo = new Book(null, "oops");
  var first = new Book("The Fortune Cookies");
  System.out.println(first);
  Book second = first.withTitle("The Nasty World");
  System.out.println(second);
  }
}
