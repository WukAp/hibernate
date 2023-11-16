package hibernate;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.List;

public class HibernateRunner {
    // private static final List<> entities = List.of(Cleaner.class, Cleaning.class, Client.class)
    public static void printAll(Session session) {
        System.out.println(session.createQuery("from Cleaner", Cleaner.class).list());
        System.out.println(session.createQuery("from Cleaning", Cleaning.class).list());
        System.out.println(session.createQuery("from Client", Client.class).list());
        System.out.println(session.createQuery("from Room", Room.class).list());
        System.out.println(session.createQuery("from RoomReserve", RoomReserve.class).list());
    }

    public static void addData(Session session) {
        List<Cleaner> cleaners = List.of(
                Cleaner.builder().idCleaner(18).name("Саша").secondName("Васильева").patronymic("Александрована").build(),
                Cleaner.builder().idCleaner(19).name("Саша").secondName("Васильева").patronymic("Александрована").build(),
                Cleaner.builder().idCleaner(20).name("Саша").secondName("Васильева").patronymic("Александрована").build(),
                Cleaner.builder().idCleaner(21).name("Саша").secondName("Васильева").patronymic("Александрована").build(),
                Cleaner.builder().idCleaner(22).name("Саша").secondName("Васильева").patronymic("Александрована").build());
        cleaners.forEach(session::persist);
        List<Cleaning> cleanings = List.of(
                new Cleaning("Wed", 4, 15),
                new Cleaning("Mon", 4, 18),
                new Cleaning("Sat", 4, 19),
                new Cleaning("Mon", 5, 20),
                new Cleaning("Sun", 4, 21));
        cleanings.forEach(session::persist);
        List<Client> clients = List.of(
                new Client(2234123456L, "Саша", "Привет", "Викторовна", "Санкт-Петербург"),
                new Client(2234123457L, "Паша", "Добрый", "Викторовна", "Санкт-Петербург"),
                new Client(2234123458L, "Маша", "Добрый", "Сергеевна", "Екатеринбург"),
                new Client(2234123459L, "Даша", "Добрый", "Сергеевна", "Москва"),
                new Client(2234123460L, "Вадик", "Добрый", "Сергеевич", "Санкт-Петербург"));
        clients.forEach(session::persist);
        List<RoomType> roomTypes = List.of(
                new RoomType(6, 6000.00),
                new RoomType(7, 7000.00),
                new RoomType(8, 8000.00),
                new RoomType(9, 9000.00),
                new RoomType(10, 10000.00));
        roomTypes.forEach(session::persist);
        List<Room> rooms = List.of(
                new Room(30, 10, 8995647, 4),
                new Room(31, 10, 8995648, 4),
                new Room(32, 10, 8995650, 4),
                new Room(33, 10, 8995649, 4),
                new Room(34, 10, 5995649, 4)

        );
        rooms.forEach(session::persist);
        List<RoomReserve> roomReserves = List.of(
                new RoomReserve(30, 2234123456L, new Date(1999, 10, 20), null),
                new RoomReserve(30, 2234123457L, new Date(1999, 10, 20), null),
                new RoomReserve(30, 2234123458L, new Date(1999, 10, 20), null),
                new RoomReserve(30, 2234123459L, new Date(1999, 10, 20), null),
                new RoomReserve(30, 2234123460L, new Date(1999, 10, 20), null)
        );
        roomReserves.forEach(session::persist);
    }

    public static void createSelect(Session session) {
        System.out.println(session.get(Cleaner.class, 1));
    }

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession();) {
            session.beginTransaction();

            printAll(session);
            createSelect(session);
            session.getTransaction().commit();
        }
    }
}
