package sn.iage.isi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import sn.iage.isi.entities.Book;

import java.util.List;

public class BookRepository {
    EntityManager em = JpaUtil.getEntityManager();
    public Book createBook(Book book) {
        EntityTransaction tx = em.getTransaction();
        Book b = Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .countPages(book.getCountPages())
                .category(book.getCategory())
                .build();
        b.setUserCreated("Mrrvh");
        b.setUserUpdated("Mrrvh");
        try {
            tx.begin();
            em.persist(b);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return b;
    }

    public List<Book> ListAllBooks() {
        return em.createQuery("SELECT b FROM Book b ORDER BY b.title ASC", Book.class)
                .getResultList();
    }
    public Book findBookById(int id) {
        Book book = em.find(Book.class, id);
        if (book == null)
            throw new EntityNotFoundException("Book not found");
        return book;
    }
    //Trouver un livre par son ISBN

    public Book findBookByIsbn(String isbn) {
        return em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }
    //Modifier un livre

    public Book updateBook(int id, Book newBook) {
        EntityTransaction tx = em.getTransaction();
        Book b = findBookById(id);
        if (b != null) {
            b.setTitle(newBook.getTitle());
            b.setAuthor(newBook.getAuthor());
            b.setPublicationYear(newBook.getPublicationYear());
            b.setCountPages(newBook.getCountPages());
            b.setCategory(newBook.getCategory());
            b.setUserUpdated("Mrrvh");
            try {
                tx.begin();
                em.merge(b);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
        }
        return b;
    }
    //Supprimer un livre
    public void deleteBook(int id) {
        EntityTransaction tx = em.getTransaction();
        Book b = findBookById(id);
        try {
            tx.begin();
            em.remove(b);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
//liste des livres triés par categories
    public List<Book> ListeBooksByCategory(String categoryName) {
        return em.createQuery(
                        "SELECT b FROM Book b WHERE b.category.name = :name ORDER BY b.title", Book.class)
                .setParameter("name", categoryName)
                .getResultList();
    }
    //recherche de livres par titre
    public List<Book> searchBooksByTitle(String keyword) {
        return em.createQuery(
                        "SELECT b FROM Book b WHERE LOWER(b.title) LIKE :kw ORDER BY b.title", Book.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }
    //recherche de livres par auteur
    public List<Book> searchBooksByAuthor(String keyword) {
        return em.createQuery(
                        "SELECT b FROM Book b WHERE LOWER(b.author) LIKE :kw ORDER BY b.title", Book.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }
    //recherche des livres publiés aprés une année donnée
    public List<Book> searchBooksAfterYear(int year) {
        return em.createQuery(
                        "SELECT b FROM Book b WHERE b.publicationYear > :year ORDER BY b.publicationYear", Book.class)
                .setParameter("year", year)
                .getResultList();
    }
    //compter les livres par categories
    public List<Object[]> countBooksByCategory() {
        return em.createQuery(
                        "SELECT b.category.name, COUNT(b) FROM Book b GROUP BY b.category.name ORDER BY b.category.name", Object[].class)
                .getResultList();
    }
    //compter tous les livres
    public int countAllBooks() {
        return em.createQuery("SELECT COUNT(b.id) FROM Book b", Long.class)
                .getSingleResult().intValue();
    }
}
