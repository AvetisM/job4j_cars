package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.PostService;
import ru.job4j.cars.util.CrudRepository;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PostRepositoryTest {

    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private PostService postService;
    private PostRepository postRepository;
    private Post testPost;

    @BeforeEach
    void initServices() {
        CrudRepository crudRepository = new CrudRepository(sf);
        postRepository = new PostRepository(crudRepository);
        postService = new PostService(postRepository);

        User user = new User(0, "User1", "1");
        Engine engine = new Engine(0, "2.0 л / 184 л.с. / Бензин");
        Driver driver = new Driver(0, "Driver1", user);
        Car car = new Car(0, "BMW 520i", engine, Set.of(driver));
        PriceHistory priceHistory =
                new PriceHistory(0, 12200000, 1100000, LocalDateTime.now());

        testPost = Post.of()
                .id(0)
                .description("BMW 5 серии 520i VI (F10/F11/F07) Рестайлинг")
                .created(LocalDateTime.now())
                .photo(new byte[] {1, 2, 3})
                .user(user)
                .car(car)
                .priceHistories(Arrays.asList(priceHistory))
                .build();
    }

    @AfterClass
    public static void closeConnection() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @After
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from Post")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Test
    @Ignore
    void add() {
        Assertions.assertTrue(postService.add(testPost));
        Optional<Post> result = postService.findById(testPost.getId());
        Assertions.assertTrue(result.isPresent());
        assertThat(result.get().getDescription(), is(testPost.getDescription()));
    }

    @Test
    void update() {
    }

    @Test
    void findPostsByCar() {
    }

    @Test
    void findPostsWithPhoto() {
    }

    @Test
    void findPostsForLastDay() {
    }
}