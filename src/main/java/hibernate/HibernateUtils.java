package hibernate;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    @Getter
    private static final SessionFactory sessionFactory = initSession();

    private static SessionFactory initSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            return sessionFactory;
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void closeSession() {
        sessionFactory.close();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
