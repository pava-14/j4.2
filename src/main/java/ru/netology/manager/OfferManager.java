package ru.netology.manager;

import ru.netology.domain.Offer;
import ru.netology.domain.OfferByPriceAscComparator;
import ru.netology.domain.OfferByTimeAscComparator;
import ru.netology.repository.OfferRepository;

import java.util.Arrays;
import java.util.Comparator;

public class OfferManager {
    private OfferRepository repository;

    public OfferManager(OfferRepository repository) {
        this.repository = repository;
    }

    public boolean matches(Offer offer, String from, String to) {
        return offer.getFlightFrom().equalsIgnoreCase(from)
                && offer.getFlightTo().equalsIgnoreCase(to);
    }

    public void add(Offer offer) {
        repository.save(offer);
    }

    public Offer[] findAll(String from, String to, Comparator<Offer> comparator) {
        Offer[] result = new Offer[0];
        for (Offer offer : repository.findAll()) {
            if (matches(offer, from, to)) {
                int length = result.length + 1;
                Offer[] tmp = new Offer[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = offer;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public Offer[] searchBy(String from, String to) {
        Offer[] result = new Offer[0];
        for (Offer offer : repository.findAll()) {
            if (matches(offer, from, to)) {
                int length = result.length + 1;
                Offer[] tmp = new Offer[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = offer;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public Offer[] listOffersByPriceAsc() {
        Offer[] result = repository.findAll();
        Arrays.sort(result, new OfferByPriceAscComparator());
        return result;
    }

    public Offer[] listOffersByTimeAsc() {
        Offer[] result = repository.findAll();
        Arrays.sort(result, new OfferByTimeAscComparator());
        return result;
    }
}
