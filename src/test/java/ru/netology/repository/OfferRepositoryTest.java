package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Offer;

import static org.junit.jupiter.api.Assertions.*;

class OfferRepositoryTest {
    private OfferRepository repository;
    private Offer offer1 = new Offer(1, 7_000, "OVB", "AER", 480);
    private Offer offer2 = new Offer(2, 10_000, "OVB", "AER", 300);
    private Offer offer3 = new Offer(3, 9_000, "AER", "OVB", 360);
    private Offer offer4 = new Offer(4, 7_000, "OVB", "UUDD", 240);
    private Offer offer5 = new Offer(5, 8_000, "OVB", "UUDD", 300);

    @BeforeEach
    public void setUp() {
        repository = new OfferRepository();
        repository.save(offer1);
        repository.save(offer2);
        repository.save(offer3);
        repository.save(offer4);
    }

    @Test
    void shouldFindByIdIfExists() {
        assertEquals(offer3, repository.findById(3));
    }

    @Test
    void shouldFindByIdIfNotExists() {
        assertNull(repository.findById(7));
    }

    @Test
    void shouldRemoveByExistsId() {
        Offer[] expected = new Offer[]{offer1, offer3, offer4};
        repository.removeById(2);
        assertArrayEquals(expected, repository.findAll());
    }

    @Test
    void shouldNotRemoveByNotExistsId() {
        Offer[] expected = new Offer[]{offer1, offer2, offer3, offer4};
        repository.removeById(7);
        assertArrayEquals(expected, repository.findAll());
    }
}