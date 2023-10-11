public class Test {
  public static void main(String args[]) {
    var b1 = new Book("Da Java Code", "Duke Brown");
    var b2 = b1;
    var b3 = new Book("Da Java Code", "Duke Brown");

    System.out.println(b1 == b2);
    System.out.println(b1 == b3);
  }
}