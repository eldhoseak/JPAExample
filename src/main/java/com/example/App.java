package com.example;

import javax.persistence.*;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // Persist some objects
            em.persist(new Employee(1, "John"));
            em.persist(new Employee(2, "Paul"));
            em.persist(new Employee(3, "George"));
            em.persist(new Employee(4, "Ringo"));
            tx.commit();

            // Sample JPQL query to retrieve details.
            Query query =  em.createQuery("select e from Employee e where e.name=:name");
            query.setParameter("name", "George");
            Employee employee =  (Employee)query.getSingleResult();
            System.out.println(employee.getId());


            // Execute a named query
            Query namedQuery =  em.createNamedQuery("employeeCount");
            System.out.println("Employee count "+ namedQuery.getSingleResult());

            // Update an employee
            employee =  em.find(Employee.class, 2);
            employee.setName("Paulson");

            em.merge(employee);
            employee =  em.find(Employee.class, 2);
            System.out.println("Updated Employee name "+ employee.getName());

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
