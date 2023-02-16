package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.util.CrudRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DriverRepository {

    public static final Logger LOG = LoggerFactory.getLogger(DriverRepository.class.getName());

    private final CrudRepository crudRepository;

    public Optional<Driver> add(Driver driver) {
        try {
            crudRepository.run(session -> session.persist(driver));
            return Optional.of(driver);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public boolean update(Driver driver) {
        try {
            crudRepository.run(session -> session.merge(driver));
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
