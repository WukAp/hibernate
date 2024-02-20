package hibernate.lab5.controller;

import hibernate.entity.Client;
import hibernate.entity.Room;
import hibernate.entity.RoomReserve;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hibernate.HibernateUtils.clientsByCity;
import static hibernate.HibernateUtils.clientsByRoom;

@RestController
@RequestMapping("/getData")
public class Controller {

    @GetMapping("/cleaners")
    public String getCleaners() {
        return "getCleaners";
    }

    @GetMapping("/clients")
    public String getClients() {
        return "getClients";
    }

    @GetMapping("/clientsByCity")
    public String getClientsByCity(String city) {
        List result;
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            result = clientsByCity(session, city);
            session.getTransaction().commit();
        }
        return result.toString();
    }

    @GetMapping("/clientsByCityUnsafe")

    public String getClientsByCityUnsafe(String city) {

        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            return session.createQuery("from Client where city='" + city + "'", Client.class).list().toString();
        }
    }

    @GetMapping("/clientsByRoom")
    public String getClientsByRoom(int room) {
        List result;
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            result = clientsByRoom(session, room);
            session.getTransaction().commit();
        }
        return result.toString();
    }

    @GetMapping("/clientsByRoomUnsafe")

    public String getClientsByRoomUnsafe(int room) {

        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            long passport = session.createQuery("from RoomReserve where checkOutDate=null and roomNumber=" + room,
                    RoomReserve.class).list().get(0).getPassport();
            return session.createQuery("from Client where passport=" + passport, Client.class).list().toString();
        }
    }

    @GetMapping("/RoomInfoUnsafe")

    public String getRoomByRoomNumberUnsafe(String room) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            return session.createQuery("from Room where roomNumber=" + room, Room.class).list().toString();
        }
    }

    @GetMapping("/RoomInfoUnsafe2")

    public String getRoomByRoomNumberUnsafe2(String room) {
        if (room.contains("=")) {
            return null;
        }
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            return session.createQuery("from Room where roomNumber=" + room, Room.class).list().toString();
        }
    }
}