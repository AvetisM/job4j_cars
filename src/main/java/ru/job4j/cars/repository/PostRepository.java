package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.util.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {

    public static final Logger LOG = LoggerFactory.getLogger(PostRepository.class.getName());

    private static final String FIND_POST_FOR_THE_LAST_DAY =
            "FROM Post WHERE created between :startDate and :endDate";

    private static final String FIND_POST_BY_CAR =
            "FROM Post Fetch Car as car WHERE car = :car";

    private static final String FIND_POST_WITH_PHOTO =
            "FROM Post WHERE photo IS NOT NULL and octet_length(photo) > 0";

    private final CrudRepository crudRepository;

    public Optional<Post> add(Post post) {
        try {
            crudRepository.run(session -> session.persist(post));
            return Optional.of(post);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public boolean update(Post post) {
        try {
            crudRepository.run(session -> session.merge(post));
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public List<Post> findPostsByCar(Car car) {
        return crudRepository.query(FIND_POST_BY_CAR, Post.class,
                Map.of("car", car));
    }

    public List<Post> findPostsWithPhoto() {
        return crudRepository.query(FIND_POST_WITH_PHOTO, Post.class);
    }

    public List<Post> findPostsForLastDay() {
        LocalDateTime startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return crudRepository.query(FIND_POST_FOR_THE_LAST_DAY, Post.class,
                Map.of("startDate", startDate, "endDate", endDate));
    }
}
