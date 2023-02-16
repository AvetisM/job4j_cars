package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.util.CrudRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository {

    public static final Logger LOG = LoggerFactory.getLogger(EngineRepository.class.getName());

    private final CrudRepository crudRepository;

    public Optional<Engine> add(Engine engine) {
        try {
            crudRepository.run(session -> session.persist(engine));
            return Optional.of(engine);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public boolean update(Engine engine) {
        try {
            crudRepository.run(session -> session.merge(engine));
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
