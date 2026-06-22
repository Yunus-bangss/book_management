package sn.iage.isi.main;

import sn.iage.isi.entities.Book;
import sn.iage.isi.entities.Category;
import sn.iage.isi.repository.BookRepository;
import sn.iage.isi.repository.CategoryRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //CategoryRepository
        CategoryRepository categoryRepository = new CategoryRepository();
        Category category = new Category();




       /*
        category.setName("Amour");
        categoryRepository.create(category);*/

        // Affichage de la liste des categories
       /* for (Category cat : categoryRepository.getAll()) {
            System.out.println(cat);
        }*/

        // Get By Id
        //Category c = categoryRepository.getById(1);

        // Update
       /* Category catUpdate = new Category();
        catUpdate.setName("Nouveau nom");
        catUpdate.setState(true);
        categoryRepository.update(1, catUpdate);*/

        // Supprimer
        //categoryRepository.delete(1);

        // recherche pas clé
       /* for (Category category1 : categoryRepository.search("roman")) {
            System.out.println(c);
        }*/
        // Compter

        //System.out.println(categoryRepository.countCategories());


       /* for (Category category1 : categoryRepository.searchActiveCategories()) {
            System.out.println(c);
        }*/

        //  BookRepository
        BookRepository bookRepository = new BookRepository();

        Book book = new Book();
       // Creer

       /* book.setTitle("Les contes D'Amadou Koumba");
        book.setAuthor("Yunus");
        book.setPublicationYear(1994);
        book.setCountPages(140);
        book.setCategory(categoryRepository.getById(2));

        bookRepository.createBook(book);*/

       // List all
        /*for (Book b : bookRepository.ListAllBooks()) {
            System.out.println(b);
        }*/

       // Find by id
       // Book b1 = bookRepository.findBookById(1);

       // Find by isbn
        //Book b2 = bookRepository.findBookByIsbn("978-0-1234-5678-9");

       // Update
       /* Book newBook = new Book();
        newBook.setTitle("Nouveau titre");
        newBook.setAuthor("Nouvel auteur");
        newBook.setPublicationYear(2000);
        newBook.setCountPages(300);
        newBook.setCategory(categoryRepository.getById(2));
        bookRepository.updateBook(1, newBook);*/

        // Delete
       // bookRepository.deleteBook(1);

       // List by category
       /* for (Book b : bookRepository.ListeBooksByCategory("Roman")) {
            System.out.println(b);
        }*/

       //Recherche par titre
       /* for (Book b : bookRepository.searchBooksByTitle("école")) {
            System.out.println(b);
        }*/

        // Recherche par auteur
       /* for (Book b : bookRepository.searchBooksByAuthor("Molière")) {
            System.out.println(b);
        }*/

       // Recherche
       /* for (Book b : bookRepository.searchBooksAfterYear(1900)) {
            System.out.println(b);
        }*/

       // Compter par category
       /* for (Object[] result : bookRepository.countBooksByCategory()) {
            System.out.println(result[0] + " : " + result[1]);
        }*/

        // tous les livres
        //System.out.println(bookRepository.countAllBooks());
    }
}