package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.util.CrudRepository;

@Repository
@AllArgsConstructor
public class PriceHistoryRepository {
    public static final Logger LOG =
            LoggerFactory.getLogger(PriceHistoryRepository.class.getName());

    private static final String FIND_BY_POST_ID = "FROM PriceHistory WHERE id = :id";
    private final CrudRepository crudRepository;

    public boolean add(PriceHistory priceHistory) {
        try {
            crudRepository.run(session -> session.persist(priceHistory));
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
