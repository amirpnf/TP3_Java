## TP3 Java - Amirhossein Pouyanfar - Group 2 - with Mr. Rémy Forax

# Exercise 1

1. Déclarer un record Book avec les composants `title` et `author`.

**Answer** : Here's a `Book` record in Java having two separate fields : `title` and `author`.
```java
public record Book(String title, String author) {
    
}
```

2. Puis essayer le code suivant dans une méthode main du record Book.
```java
  var book = new Book("Da Vinci Code", "Dan Brown");
  System.out.println(book.title + ' ' + book.author);
```     
Expliquer.  

**Answer** : Here we can access `Book`'s fields using `.` followed by the name of the field, without parentheses, because we are inside the record, we don't need
to use accessors. As a consequence, this code runs without any problem.


3. Créer une classe Main (dans un fichier Main.java) et déplacer le main de la classe Book dans la classe Main.
Quel est le problème ? Comment peut-on le corriger ? 

**Answer** : If we use the tow lines above in another file called `Main.java`, containing a class named `main`, we will have a specific error :
```bash
Main.java:4: error: title has private access in Book
```
That's because, outside the `Book` record, the fields aren't accessible.
In order to correct this error, we ought to use parentheses after the 
fields' names.
```java
  var book = new Book("Da Vinci Code", "Dan Brown");
  System.out.println(book.title() + ' ' + book.author());
```  
4. On peut remarquer que le code permet de créer des livres ayant un titre ou un auteur null.
```java 
var weirdBook = new Book(null, "oops");
```       
Comment faire pour éviter ce problème sachant qu'il existe une méthode static requireNonNull dans la classe java.util.Objects.

**Answer** : First of all, we should import `java.util.Objects` Package.
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

5. En fait, on peut simplifier le code que vous avez écrit à la question précédente en utilisant un constructeur compact (compact constructor). Commenter le code précédent et utiliser un constructor compact à la place. 

**Answer** : In a compact constructor, you won't need to list all of the arguments.
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

6. Écrire un autre constructeur qui prend juste un titre et pas d'auteur et ajouter un code de test dans le main.
On initialisera le champ author avec `<no author>` dans ce cas. 
 
**Answer** : We've added another constructor to the `Book` record. Here's the code :
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
first one. (the first constructor)

7. Comment le compilateur fait-il pour savoir quel constructeur appeler ?

**Answer** : The compiler analyzes the arguments you've provided and checks if they match the parameter lists of any of the constructors in the class. If there's an exact match in terms of the number and types of arguments, the compiler selects that constructor.

8. On souhaite maintenant pouvoir changer le titre d'un livre déjà existant en utilisant une méthode nommée withTitle qui prend en paramètre le nouveau titre.
Pourquoi le code suivant ne marche pas ?
```java
  public void withTitle(String title) {
    this.title = title;
  }
```      
Comment faire alors ? (indice comme `String.toUpperCase`)
Écrire le code correspondant et ajouter un code de test dans le main. 

**Answer** : 
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


# Exercise 2

1. Qu'affiche le code ci-dessous ?
```java
    var b1 = new Book("Da Java Code", "Duke Brown");
    var b2 = b1;
    var b3 = new Book("Da Java Code", "Duke Brown");

    System.out.println(b1 == b2);
    System.out.println(b1 == b3);
```
Pourquoi ?

**Answer** : The code above would have a result like this in the terminal :
```bash
true
false
```

that's because for the `==` operator compares the addresses, not values, when used with objects (instances of a class). When we write `var b2 = b1;`, that doesn't mean that we are creating a new instance of the `Book` class, but it signifies that we are assigning the address of the object `b1` to `b2`.
So when these two are compared using `==`, the result will always be `true`, because they have the same address.

But this wouldn't be true for `b3`, because this last one is a new instance of the `Book` class, and although it has the exact same values in its fields, when compared to `b1` using the `==` operator, the result will always be false, as it's a new instance and has a totally new address, different than that of `b1`.


2. Comment faire pour tester si deux objets ont le même contenu ?
Écrire le code qui affiche si b1 et b2, puis b1 et b3 ont le même contenu. 

 **Answer** : We can compare the contents of two different instances of a record by using `equals()` method, this method is automatically defind by Java in records. But in a class, this method should be defined manually.
 As mentionned before, `Book` is a record, so it contains automatically the `equals()` method, we are going to use that :
 ```java
 public class Test {
  public static void main(String args[]) {
    var b1 = new Book("Da Java Code", "Duke Brown");
    var b2 = b1;
    var b3 = new Book("Da Java Code", "Duke Brown");

    System.out.println(b1.equals(b2));
    System.out.println(b1.equals(b3));
  }
}
 ```

3. Écrire une méthode isFromTheSameAuthor() qui renvoie vrai si deux livres sont du même auteur.
Et vérifier avec les deux livres suivants :

```java
  var book1 = new Book("Da Vinci Code", "Dan Brown");
  var book2 = new Book("Angels & Demons", new String("Dan Brown"));
```

**Answer** : Here's the requested `isFromTheSameAuthor()` :
```java
public boolean isFromTheSameAuthor(Book other) {
  if(this.author.equals(other.author)) {
    return true;
  }
  return false;
}
```

Notice that we compare two strings here using the `equals()` method, considering that we need to compare their content, not their location in memory.

4. Comment faire pour que le code suivant
```java
  var javaBook = new Book("Da Java Code", "Duke Brown");
  System.out.println(javaBook);
```

affiche

  Da Java Code by Duke Brown
        

**Answer** : To make sure that `System.out.println(book)` prints the string model given ("X by Y"), we must rewrite the `toString()` method.

Here's the mentioned method :
```java
public String toString() {
  return this.title + " by " + this.author;
}
```

5. Utiliser l'annotation @Override (java.lang.Override) sur la méthode ajoutée à Book.
**Answer** : 
 ```java
 @Override
  public String toString() {
    return this.title + " by " + this.author;
  }
 ```

6. A quoi sert l'annotation @Override ? 

**Answer** : `@Override` is a compiler directive, usually used in a subclass to indicate that a method in a subclass in intended to override a method with the same name and parameters defined in its superclass. (although in our case there's no superclass and we've used it just to indicate the fact of overriding).
Here we ought to use it because we have the intention to introduce a new definition of this method.

## Exercise 3

Au lieu d'utiliser un record, un étudiant qui aime bien ré-inventer la roue à écrit le code suivant
```java
public class Book2 {
  private final String title;
  private final String author;

  public Book2(String title, String author) {
    this.title = title;
    this.author = author;
  }
  
  public static void main(String[] args) {
    var book1 = new Book2("Da Vinci Code", "Dan Brown");
    var book2 = new Book2("Da Vinci Code", "Dan Brown");
    System.out.println(book1.equals(book2));
  }
}
```
Malheureusement, le main n'a pas le comportement attendu. 


1. Quel est le problème ? 

**Answer** : This student has decided to use a class instead of a record, to define `Book`.
The problem is, he's used the `equals()` method, without having defined it in the class. This method is automatically defined in records, but it should be overridden in a class, using the `@Override` directive.

2. Comment corriger le problème si on s'entête à utiliser une classe ?
Ne m'oubliez pas le @Override SVP ! 

**Answer** : He can correct this mistake and solve this problem by overriding the `toString()` method in his class, without forgetting `@Override` of course.
Here's an example of that:

```java
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
```

# Exercise 4

1. Écrire une méthode swap qui échange les valeurs de deux cases d'un tableau :
```java
void swap(int[] array,int index1,int index2)
```

**Answer** : Here's a `swap` method, doing the requested task :
```java
private static void swap(int[] array, int index1, int index2) {
  var temp = array[index1];
  array[index1] = array[index2];
  array[index2] = temp;
}
```

2. Écrire une méthode indexOfMin qui renvoie l'indice de la valeur minimale d'un tableau.

**Answer** : Here's a method named `indexOfMin`, returning the minimum of an array.
```java
private static int indexOfMin(int[] array) {
  var min = 0;
  for(var i = 0; i < array.length; i++) {
    if(array[i] < array[min]) {
      min = i;
    }
  }
  return min;
}
```

3. Modifier la méthode indexOfMin en ajoutant deux indices indiquant que l'on cherche l'indice du minimum, non pas sur tout le tableau, mais sur la partie de tableau entre ces deux indices (le premier inclus, le deuxième exclu). 

**Answer** : Here's the modified version of the mentioned method :
```java
private static int indexOfMinRange(int[] array, int start, int end) {
  var indexMin = start;
  for(var i = start; i < end; i++) {
    if(array[i] < array[indexMin]) {
      indexMin = i;
    }
  }
  return indexMin;
}   
```

4. Écrire la méthode sort qui prend un tableau d'entiers en paramètre et qui trie celui-ci en utilisant pour cela les méthodes indexOfMin et swap. 

**Answer** : Here's a `sort()` method, using the two mentioned methods above, to sort the values in a Java array.
```java
public static void sort(int[] array) {
  var indexMin = 0;
  for(int i = 0; i < array.length; i++) {
    indexMin = indexOfMinRange(array, i, array.length); 
    swap(array, indexMin, i);
  } 
}
```

Finally, here's the whole `Pebble` class, in which all of these methods are implemented and defined.
```java
import java.util.Arrays;

public class Pebble { 
  private static void swap(int[] array, int index1, int index2) {
    var temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  private static int indexOfMin(int[] array) {
    var min = 0;
    for(var i = 0; i < array.length; i++) {
      if(array[i] < array[min]) {
        min = i;
      }
    }
    return min;
  }

  private static int indexOfMinRange(int[] array, int start, int end) {
    var indexMin = start;
    for(var i = start; i < end; i++) {
      if(array[i] < array[indexMin]) {
        indexMin = i;
      }
    }
    return indexMin;
  }   

  public static void sort(int[] array) {
    var indexMin = 0;
    for(int i = 0; i < array.length; i++) {
      indexMin = indexOfMinRange(array, i, array.length); 
      swap(array, indexMin, i);
    } 
  }


  public static void main(String[] args) {
    int[] array = new int[5];
    for(var i = 5; i > 0 ; --i) {
      array[5 - i] = i;
    }
    swap(array, 2, 3);
    System.out.println(Arrays.toString(array));
    System.out.println(indexOfMinRange(array, 2, 4));
    sort(array);
    System.out.println(Arrays.toString(array));
  }
}  

```