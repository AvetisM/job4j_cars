package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {

    private static final String UPDATE_NAME_BY_ID =
            "UPDATE auto_user SET login = :fLogin, password = :fPassword WHERE id = :fId";
    private static final String DELETE_BY_ID = "DELETE User WHERE id = :fId";
    private static final String FIND_ALL_ORDER_ID = "FROM User as u ORDER BY u.id";
    private static final String FIND_BY_ID = "FROM User as u WHERE u.id = :fId";
    private static final String FIND_BY_LIKE_LOGIN = "FROM User as u WHERE u.login like :fLogin";
    private static final String FIND_BY_LOGIN = "FROM User as u WHERE u.login = :fLogin";
    private final SessionFactory sf;

    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(UPDATE_NAME_BY_ID)
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(DELETE_BY_ID)
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        try {
            Query<User> query = session.createQuery(FIND_ALL_ORDER_ID, User.class);
            return query.list();
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        try {
            Query<User> query = session.createQuery(FIND_BY_ID, User.class);
            query.setParameter("fId", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        try {
            Query<User> query = session.createQuery(FIND_BY_LIKE_LOGIN, User.class);
            query.setParameter("fLogin", "%" + key + "%");
            return query.list();
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }

    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        try {
            Query<User> query = session.createQuery(FIND_BY_LOGIN, User.class);
            query.setParameter("fLogin", login);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
