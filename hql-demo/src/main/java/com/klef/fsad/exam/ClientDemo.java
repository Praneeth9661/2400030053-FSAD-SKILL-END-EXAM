package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.Date;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Restaurant r = new Restaurant();
        r.setName("ABC Hotel");
        r.setStatus("Open");
        r.setDate(new Date());

        session.save(r);
        tx.commit();

        System.out.println("Inserted Successfully!");

        session.beginTransaction();

        Query q = session.createQuery(
                "update Restaurant set name=:n, status=:s where id=:i");

        q.setParameter("n", "Updated Hotel");
        q.setParameter("s", "Closed");
        q.setParameter("i", 1);

        q.executeUpdate();
        session.getTransaction().commit();

        System.out.println("Updated!");

        session.close();
        sf.close();
    }
}