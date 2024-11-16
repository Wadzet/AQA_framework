package task_6;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load DB_PASSWORD from .env
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        // Hibernate setup
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        try (factory) {
            // Create
            Session session = factory.openSession();
            session.beginTransaction();

            Address address1 = new Address("123 Main St", "Portland", "OR");
            Address address2 = new Address("456 Elm St", "Seattle", "WA");
            session.persist(address1);
            session.persist(address2);

            Person person1 = new Person("John Doe", 30, address1);
            Person person2 = new Person("Jane Smith", 25, address2);
            session.persist(person1);
            session.persist(person2);

            session.getTransaction().commit();
            session.close();

            // Read
            session = factory.openSession();
            session.beginTransaction();

            List<Person> people = session.createQuery("from Person", Person.class).getResultList();
            System.out.println("// Read all people:");
            people.forEach(System.out::println);

            session.getTransaction().commit();
            session.close();

            // Update
            session = factory.openSession();
            session.beginTransaction();

            Person personToUpdate = session.get(Person.class, person1.getId());
            personToUpdate.setAge(35);
            session.persist(personToUpdate);

            session.getTransaction().commit();
            session.close();
            System.out.println("// Update person:");
            System.out.println("Updated person: " + personToUpdate);

            // Delete
            session = factory.openSession();
            session.beginTransaction();

            Person personToDelete = session.get(Person.class, person2.getId());
            session.delete(personToDelete);

            session.getTransaction().commit();
            session.close();
            System.out.println("// Delete person:");
            System.out.println("Deleted person: " + personToDelete);
        }
    }
}
