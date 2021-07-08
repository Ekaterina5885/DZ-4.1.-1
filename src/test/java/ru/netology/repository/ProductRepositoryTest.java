package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book firstBook = new Book(101, "Ангелы и демоны (2000)", 100, "Дэн Браун");
    private Book secondBook = new Book(202, "Код да Винчи (2003)", 200, "Дэн Браун");
    private Book thirdBook = new Book(303, "Затерянный мир", 300, "Артур Конан Дойл");
    private Smartphone firstSmartphone = new Smartphone(404, "10X Lite 4/128GB", 300, "Honor");
    private Smartphone secondSmartphone = new Smartphone(505, "9S 32GB", 400, "Honor");
    private Smartphone thirdSmartphone = new Smartphone(606, "Y8p 128GB", 400, "Huawei");

    @BeforeEach
    public void setup() {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(firstSmartphone);
        repository.save(secondSmartphone);
        repository.save(thirdSmartphone);
    }

    //    Удаление существующего элемента;
    @Test
    public void shouldRemoveByIdOneProduct() {

        repository.removeById(505);

        Product[] expected = new Product[]{firstBook, secondBook, thirdBook, firstSmartphone, thirdSmartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(actual, expected);
    }

    // Попытка удаления несуществующего элемента;
    @Test
    public void shouldRemoveByIdNonexistentElement() {

            repository.removeById(8);
      
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(8);
        });
    }
}


