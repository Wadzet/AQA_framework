package task_7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//Implement OneToOne, OneToMany, and ManyToMany relations in your models from the previous task.(Task_6)
//Test it by CRUD.

public class Task_7 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Phone.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();

        try (factory) {
            // Create
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();

                Address address = new Address();
                address.setStreet("123 Main St");
                address.setCity("Portland");
                address.setState("OR");

                Person person = new Person();
                person.setName("John Doe");
                person.setAge(30);
                person.setAddress(address);

                Phone phone1 = new Phone();
                phone1.setNumber("123-456-7890");
                phone1.setPerson(person);

                Phone phone2 = new Phone();
                phone2.setNumber("098-765-4321");
                phone2.setPerson(person);

                person.getPhones().add(phone1);
                person.getPhones().add(phone2);

                Project project1 = new Project();
                project1.setName("Project A");

                Project project2 = new Project();
                project2.setName("Project B");

                person.getProjects().add(project1);
                person.getProjects().add(project2);

                project1.getPersons().add(person);
                project2.getPersons().add(person);

                session.persist(person);

                session.getTransaction().commit();
                System.out.println("CREATE: Data successfully saved!");
            }

            // Read
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();

                Person retrievedPerson = session.get(Person.class, 1);
                System.out.println("READ: Retrieved person: " + retrievedPerson);
                System.out.println("READ: Associated address: " + retrievedPerson.getAddress());
                System.out.println("READ: Associated phones: " + retrievedPerson.getPhones());
                System.out.println("READ: Associated projects: " + retrievedPerson.getProjects());

                session.getTransaction().commit();
            }

            // Update
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();

                Person retrievedPerson = session.get(Person.class, 1);
                retrievedPerson.setAge(35); // Update age
                retrievedPerson.getAddress().setStreet("456 Elm St"); // Update address

                session.getTransaction().commit();
                System.out.println("UPDATE: Updated person: " + retrievedPerson);
            }

            // Delete
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();

                Person retrievedPerson = session.get(Person.class, 1);
                session.remove(retrievedPerson);

                session.getTransaction().commit();
                System.out.println("DELETE: Deleted person with ID: " + retrievedPerson.getId());
            }
        }
    }
}
