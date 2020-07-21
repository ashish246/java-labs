package com.jta.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class WithLocalDatabase
{
	public static void main(String[] args)
	{
		EntityManagerFactory emf = null;
		EntityTransaction transaction = null;
		try
		{
			emf = Persistence.createEntityManagerFactory("Example1PU");
			EntityManager em = emf.createEntityManager();

			transaction = em.getTransaction();
			transaction.begin();

			Course course = new Course();
			course.setCode("C0");
			course.setName("Course 0");

			em.persist(course);
			em.flush();

			System.out.println("Before rollback : "
					+ em.createNamedQuery("findAll").getResultList());
			
			transaction.rollback();

			em.close();

			EntityManager emRead = emf.createEntityManager();
			System.out.println("After  rollback : "
					+ emRead.createNamedQuery("findAll").getResultList());
			emRead.close();
		} catch (Exception e)
		{
			rollback(transaction);
			e.printStackTrace();
		} finally
		{
			if (emf != null)
			{
				emf.close();
			}
		}
	}

	private static void rollback(EntityTransaction transaction)
	{
		if (transaction != null)
		{
			transaction.rollback();
		}
	}
}