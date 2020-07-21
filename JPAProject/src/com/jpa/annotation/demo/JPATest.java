package com.jpa.annotation.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPATest
{

	private static final String PERSISTENCE_UNIT_NAME = "people";
	private static EntityManagerFactory factory;

	public static void main(String[] args)
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q = em.createQuery("select m from Person m");
		// Persons should be empty

		// do we have entries?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries)
		{
			Family family = new Family();
			family.setDescription("Family for the Knopfs");
			em.persist(family);
			for (int i = 0; i < 40; i++)
			{
				Person person = new Person();
				person.setFirstName("Ashish_" + i);
				person.setLastName("Tripathi_" + i);
				
				// now persists the family person relationship
				/*family.getMembers().add(person);
				em.persist(person);
				em.persist(family);*/
				
				person.setFamily(family);
				em.persist(person);
			}
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

		// check available people
		//checkAvailablePeople();

		// check family
		//checkFamily();

		// delete a person
		//deletePerson();

	}

	public static void checkAvailablePeople()
	{
		// now lets check the database and see if the created entries are
		// there
		// create a fresh, new EntityManager
		EntityManager em = factory.createEntityManager();

		// Perform a simple query for all the Message entities
		Query q = em.createQuery("select m from Person m");

		// We should have 40 Persons in the database
		if (q.getResultList().size() == 40)
		{
			System.out.println("Person count is 40");
		}

		em.close();
	}

	public static void checkFamily()
	{
		EntityManager em = factory.createEntityManager();
		// Go through each of the entities and print out each of their
		// messages, as well as the date on which it was created
		Query q = em.createQuery("select f from Family f");

		// We should have one family with 40 persons
		if (q.getResultList().size() == 1)
		{
			System.out.println("Familys is 1");
		}
		if (((Family) q.getSingleResult()).getMembers().size() == 40)
		{
			System.out.println("Person count is 40");
		}
		em.close();
	}

	public static void deletePerson()
	{
		EntityManager em = factory.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();
		Query q = em
				.createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName");
		q.setParameter("firstName", "Jim_1");
		q.setParameter("lastName", "Knopf_1");
		Person user = (Person) q.getSingleResult();
		em.remove(user);
		em.getTransaction().commit();
		//Person person = (Person) q.getSingleResult();
		// Begin a new local transaction so that we can persist a new entity

		em.close();
		
		System.out.println("A person is deleted");
	}
}
