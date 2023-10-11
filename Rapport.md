## TP3 Java - Amirhossein Pouyanfar - Group 2 Mr. Rémy Forax

# Exercise 1

1. Déclarer un record Book avec les composants `title` et `author`.
 -----
Here's a `Book` record in Java having two separate fields : `title` and `author`.
```java
public record Book(String title, String author) {
    
}
```
 -----

2. Puis essayer le code suivant dans une méthode main du record Book.
```java
  var book = new Book("Da Vinci Code", "Dan Brown");
  System.out.println(book.title + ' ' + book.author);
```     
Expliquer 

 ------   

Here wen can access `Book`'s fields using `.` followed by the name of the field, without parentheses, because we are inside the record, we don't need
to use accessors. As a consequence, this code runs without any problem.

 ------

3. If we use the tow lines above in another file called `Main.java`, containing a class named `main`, we will have a specific error :
```bash
Main.java:4: error: title has private access in Book
```

That's because, outside the `Book` record, the fields aren't accessible.
In order to correct this error, we ought to use parentheses after the 
fields' names.

 -----
```java
  var book = new Book("Da Vinci Code", "Dan Brown");
  System.out.println(book.title() + ' ' + book.author());
```  
 -----

4. On peut remarquer que le code permet de créer des livres ayant un titre ou un auteur null.
```java 
var weirdBook = new Book(null, "oops");
```       

Comment faire pour éviter ce problème sachant qu'il existe une méthode static requireNonNull dans la classe java.util.Objects.

 -----
First of all, we should import `java.util.Objects` Package.
Secondly, we have to create a constructor for this record.
In the constructor, we are going to be able to use this static method from
the class `Objects` : `Objects.requireNonNull()`
```java
import java.util.*;

public record  Book(String title, String author){

  public Book(String title, String author) {
    Objects.requireNonNull(title, "Title can't be empty");
    this.title = title;
    this.author = author;
  }

  public static void main(String[] args) {
    var book = new Book("Da Vinci Code", "Dan Brown");
    System.out.println(book.title + ' ' + book.author);
  }
}
```
 -----

5. In a compact constructor, you won't need to list all of the arguments.
Let's rewrite the code above using this type of constructor : 
 -----
```java
import java.util.*;

public record  Book(String title, String author){

  public Book() {
    Objects.requireNonNull(title, "Title can't be empty");
  }

  public static void main(String[] args) {
    var book = new Book("Da Vinci Code", "Dan Brown");
    System.out.println(book.title + ' ' + book.author);
  }
}
``` 

6. We've added another constructor to the `Book` record. Here's the code :
 -----
 ```java
 import java.util.*;

public record  Book(String title, String author){

  public Book(String title, String author) {
    Objects.requireNonNull(title, "Title can't be empty");
    this.title = title;
    this.author = author;
  } 
}
 ```
As you can see, the second constructor uses the keyword `this` to refer to the 
first one.

 ----- 
 
Comment faire alors ? (indice comme `String.toUpperCase`)
Écrire le code correspondant et ajouter un code de test dans le main. 


- In Java, when you define a record class, the fields are implicitly `private final`, that means, once created an instance of this class and initialized its 
fields, you won't be able to reassign a value to its fields (mostly due to `final`).
In order to have this object with the desired changed field, we must create a new instance of this class. We could proceed like this :
```java
public Book withTitle(String title) {
  return new Book(title, this.author);
}
```
So the return type of our method will change to `Book`, and actually we are creating a new instance of the book class, assigning the value given as the 
argument of our method, to the its first field.