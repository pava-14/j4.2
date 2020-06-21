package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Offer;
import ru.netology.domain.OfferByPriceAscComparator;
import ru.netology.repository.OfferRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OfferManagerMockitoTest {
    @Mock
    private OfferRepository repository;
    @InjectMocks
    private OfferManager manager;

    private Offer offer1 = new Offer(2, 10_000, "OVB", "AER", 310);
    private Offer offer2 = new Offer(1, 7_000, "OVB", "AER", 480);
    private Offer offer3 = new Offer(3, 9_000, "AER", "OVB", 360);
    private Offer offer4 = new Offer(4, 7_100, "OVB", "UUDD", 240);
    private Offer offer5 = new Offer(5, 8_000, "OVB", "UUDD", 300);

    @BeforeEach
    public void setUp() {
        doNothing().when(repository).save(offer1);
        doNothing().when(repository).save(offer2);
        doNothing().when(repository).save(offer3);
        doNothing().when(repository).save(offer4);
        doNothing().when(repository).save(offer5);

        manager.add(offer1);
        manager.add(offer2);
        manager.add(offer3);
        manager.add(offer4);
        manager.add(offer5);
    }

    @Test
    void shouldlistOffersByPriceAsc() {
        Offer[] returned = new Offer[]{offer1, offer2, offer3, offer4, offer5};
        doReturn(returned).when(repository).findAll();

        Offer[] expected = new Offer[]{offer2, offer4, offer5, offer3, offer1};
        assertArrayEquals(expected, manager.listOffersByPriceAsc());
    }

    @Test
    void shouldSearchByIfExixsts() {
        Offer[] returned = new Offer[]{offer1, offer2, offer3, offer4, offer5};
        doReturn(returned).when(repository).findAll();

        Offer[] expected = new Offer[]{offer2, offer1};
        assertArrayEquals(expected, manager.searchBy("OVB", "AER"));
    }

    @Test
    void shouldSearchByIfNotExixsts() {
        Offer[] returned = new Offer[]{offer1, offer2, offer3, offer4, offer5};
        doReturn(returned).when(repository).findAll();

        Offer[] expected = new Offer[0];
        assertArrayEquals(expected, manager.searchBy("AER", "UUDD"));
    }

    @Test
    void shouldlistOffersByTimeAsc() {
        Offer[] returned = new Offer[]{offer1, offer2, offer3, offer4, offer5};
        doReturn(returned).when(repository).findAll();

        Offer[] expected = new Offer[]{offer4, offer5, offer1, offer3, offer2};
        assertArrayEquals(expected, manager.listOffersByTimeAsc());
    }

    @Test
    void shouldFindAllSortByPriceAsc() {
        Offer[] returned = new Offer[]{offer1, offer2, offer3, offer4, offer5};
        doReturn(returned).when(repository).findAll();

        Offer[] expected = new Offer[]{offer2, offer1};
        assertArrayEquals(expected, manager.findAll("OVB", "AER", new OfferByPriceAscComparator()));
    }

    @Test
    void shouldFindAllSortByTimeDesc() {
        Offer[] returned = new Offer[]{offer1, offer2, offer3, offer4, offer5};
        doReturn(returned).when(repository).findAll();

        Offer[] expected = new Offer[]{offer5, offer4};
        assertArrayEquals(expected, manager.findAll("OVB", "UUDD", new OfferByPriceAscComparator().reversed()));
    }
}
