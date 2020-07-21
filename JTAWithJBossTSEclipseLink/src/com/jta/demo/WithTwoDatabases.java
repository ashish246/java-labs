package com.jta.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

import org.omg.CORBA.SystemException;

public class WithTwoDatabases
{
	private static UserTransaction userTransaction = null;

	public static void main(String[] args)
	{
		EntityManagerFactory emf1 = null;
		try// (JndiServer jndi = JndiServer.startServer())
		{
			userTransaction = com.arjuna.ats.jta.UserTransaction.userTransaction();

			/*jndi.initializeNamingContext()
					.andBind("java:/TransactionManager",
							com.arjuna.ats.jta.TransactionManager.transactionManager())
					.andBind("java:comp/UserTransaction", userTransaction)
					.andBind("java:/Example1DS", DatabaseUtil.createDatasource());*/

			emf1 = Persistence.createEntityManagerFactory("Example1PU");

			userTransaction.begin();

			Course course1 = new Course();
			course1.setCode("C1");
			course1.setName("Course 1");

			EntityManager em1 = emf1.createEntityManager();
			em1.persist(course1);
			em1.flush();

			System.out.println("Before rollback : "
					+ em1.createNamedQuery("findAll").getResultList());

			userTransaction.commit();
			em1.close();

			EntityManager em1Read = emf1.createEntityManager();
			System.out.println("After  commit : "
					+ em1Read.createNamedQuery("findAll").getResultList());
			em1Read.close();

		} catch (Exception e)
		{
			rollback(userTransaction);
			e.printStackTrace();
		} finally
		{
			if (emf1 != null)
			{
				emf1.close();
			}
		}
	}

	private static void rollback(UserTransaction userTransaction)
	{
		if (userTransaction != null)
		{
			try
			{
				userTransaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException
					| javax.transaction.SystemException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}