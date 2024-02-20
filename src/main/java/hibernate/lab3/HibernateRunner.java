package hibernate.lab3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static hibernate.HibernateUtils.cleanerByClientAndDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            //printAll(session);
            //createSelect(session);

            // Клиенты, проживающие в заданном номере
            //System.out.println(clientsByRoom(session, 12));

            // Клиенты, прибывшие из заданного города
            //System.out.println(clientsByCity(session, "Тихвин"));

            // Кто убирал номер клиента в заданный день
            System.out.println(cleanerByClientAndDate(session, "Wed", 1234123461));

            // Получаем список пустых номеров
            //System.out.println(getEmptyRooms(session));

            // Добавляем нового служащего
            //int id =  addNewCleaner(session, "Николай", "Витальевич", "Снег");
            //printAll(session);

            // Увольняем служащего
             //dismissCleaner(session, id);
            //printAll(session);

            //Меняем расписание служащего
            //updateCleanerShed(session, 4, "Sat", 1, 3);
            // printAll(session);

            //Заселяем клиента
            // checkIn(session, 1234123460, 13);
           // printAll(session);

            //Выселяем клиента
           // checkOut(session, 1234123460, 13);
           // printAll(session);

            session.getTransaction().commit();
        }
    }
}
