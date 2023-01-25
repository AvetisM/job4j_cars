package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

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
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
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
        }
        session.close();
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
        }
        session.close();
    }

    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(FIND_ALL_ORDER_ID);
        List<User> rls = query.list();
        session.close();
        return rls;
    }

    public Optional<User> findById(int id) {
        Optional<User> rls;
        Session session = sf.openSession();
        Query<User> query = session.createQuery(FIND_BY_ID, User.class);
        query.setParameter("fId", id);
        rls = query.uniqueResultOptional();
        session.close();
        return rls;
    }

    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(FIND_BY_LIKE_LOGIN);
        query.setParameter("fLogin", "%" + key + "%");
        List<User> rls = query.list();
        session.close();
        return rls;
    }

    public Optional<User> findByLogin(String login) {
        Optional<User> rls;
        Session session = sf.openSession();
        Query<User> query = session.createQuery(FIND_BY_LOGIN, User.class);
        query.setParameter("fLogin", login);
        rls = query.uniqueResultOptional();
        session.close();
        return rls;
    }
}
