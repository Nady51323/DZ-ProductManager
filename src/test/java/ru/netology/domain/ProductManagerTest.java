package ru.netology.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {


    Book book1 = new Book(30, "Hamlet", 6500, "Shakespeare");
    Book book2 = new Book(31, "Gadfly", 1500, "Voynich");
    Book book3 = new Book(32, "Theatre", 3200, "Maugham");
    Smartphone smartphone1 = new Smartphone(10, "iPhone", 80_000, "Apple");
    Smartphone smartphone2 = new Smartphone(11, "Honor", 40_000, "Honor");


    @Test

    public void saveTest() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);

        Product[] actual = repo.findAll();
        Product[] expected = {book1, book2, smartphone1};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void deleteTest() {
        ProductRepository repo = new ProductRepository();
        repo.save(book2);
        repo.save(book3);
        repo.save(smartphone2);

        repo.removeById(32);

        Product[] actual = repo.findAll();
        Product[] expected = {book2, smartphone2};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFind2Elements() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] actual = manager.searchBy("on");
        Product[] expected = {smartphone1, smartphone2};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindEmpty() {
        ProductManager manager = new ProductManager(new ProductRepository());

        Product[] actual = manager.searchBy("on");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldFind1ElementsMissing() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] actual = manager.searchBy("g");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFind1Elements() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("a");
        Product[] expected = {book1, book2, book3};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindMissing2Elements() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("as");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindNonexistentElement() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("вы");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindNumber() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("7");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void productMatchesRequest() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        boolean actual = manager.matches(book1, "Haml");
        boolean expected = true;

    }

    @Test
    public void productDoesNotMatchRequest() {
        ProductManager manager = new ProductManager(new ProductRepository());

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        boolean actual = manager.matches(book2, "Haml");
        boolean expected = false;

    }

}
