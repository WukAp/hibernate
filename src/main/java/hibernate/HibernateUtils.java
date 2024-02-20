package hibernate;

import hibernate.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class HibernateUtils {
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
//        List<Cleaning> cleanings = List.of(
//                new Cleaning("Wed", 4, 15),
//                new Cleaning("Mon", 4, 18),
//                new Cleaning("Sat", 4, 19),
//                new Cleaning("Mon", 5, 20),
//                new Cleaning("Sun", 4, 21));
//        cleanings.forEach(session::persist);
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

    public static List clientsByRoom(Session session, int roomNumber) {
        Criteria criteria = session.createCriteria(RoomReserve.class);

        var clientsFromRoom = criteria.add(Restrictions.eq("roomNumber", roomNumber))
                .add(Restrictions.isNull("checkOutDate"))
                .list();

        List<Client> clients = new java.util.ArrayList<>(List.of());
        for (var roomReserve : clientsFromRoom) {
            clients.add(session.get(Client.class, ((RoomReserve) roomReserve).getPassport()));
        }
        return clients;
    }

    public static List clientsByCity(Session session, String city) {
        Criteria criteria = session.createCriteria(Client.class);
        return criteria.add(Restrictions.eq("city", city)).add(Restrictions.eq("city", city)).list();
        // System.out.println(session.get(Cleaner.class, 1));
    }

    public static Cleaner cleanerByClientAndDate(Session session, String date, long clientPassport) {
        //находим номер клиента
        Criteria criteria = session.createCriteria(RoomReserve.class);
        int roomNumber = ((RoomReserve) criteria.add(Restrictions.eq("passport", clientPassport))
                .add(Restrictions.isNull("checkOutDate")).uniqueResult()).getRoomNumber();
        int floor = (session.get(Room.class, roomNumber)).getFloor();

        criteria = session.createCriteria(Cleaning.class);
        Cleaner cleaner = ((Cleaning) criteria.add(Restrictions.eq("weekDay", date))
                .add(Restrictions.eq("floor", floor)).uniqueResult()).getCleaner();
        return cleaner;
    }

    public static List getEmptyRooms(Session session) {
        //находим занятые номера
        Criteria criteria = session.createCriteria(RoomReserve.class);
        var roomNumbers = criteria.add(Restrictions.isNull("checkOutDate"))
                .list().stream().mapToInt(rr -> ((RoomReserve) rr).getRoomNumber()).toArray();

        criteria = session.createCriteria(Room.class);
        var allRooms = criteria.list();

        return allRooms.stream()
                .filter(room -> Arrays.stream(roomNumbers).noneMatch(rr -> rr == ((Room) room).getRoomNumber()))
                .toList();
    }

    public static int addNewCleaner(Session session, String name, String secondName, String patronymic) {
        return (int) session.save(new Cleaner((int) Math.round(Math.random() * 100000),
                name, secondName, patronymic));
    }

    public static void dismissCleaner(Session session, int idCleaner) {
        session.remove(session.get(Cleaner.class, idCleaner));
    }

    public static void updateCleanerShed(Session session, int floor, String day, int oldCleanerId, int newCleanerId) {
        Criteria criteria = session.createCriteria(Cleaning.class);
        var cleaningDay = (Cleaning) criteria.add(Restrictions.eq("weekDay", day))
                .add(Restrictions.eq("floor", floor))
                .add(Restrictions.eq("idCleaner", oldCleanerId)).uniqueResult();
        cleaningDay.getCleaner().setIdCleaner(newCleanerId);
        System.out.println(

                session.merge(cleaningDay));
    }

    public static void checkIn(Session session, long passport, int roomNumber) {
        System.out.println(

                session.save(new RoomReserve(roomNumber,
                        passport, new Date(System.currentTimeMillis()), null)));
    }

    public static void checkOut(Session session, long passport, int roomNumber) {
        Criteria criteria = session.createCriteria(RoomReserve.class);
        var roomReserve = (RoomReserve) criteria.add(Restrictions.eq("passport", passport))
                .add(Restrictions.eq("roomNumber", roomNumber))
                .add(Restrictions.isNull("checkOutDate")).uniqueResult();
        roomReserve.setCheckOutDate(new Date(System.currentTimeMillis()));
        System.out.println(

                session.merge(roomReserve));
    }
}
