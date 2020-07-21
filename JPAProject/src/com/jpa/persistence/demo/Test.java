package com.jpa.persistence.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Test
{
	private static final String PERSISTENCE_UNIT_NAME = "employee";
	private static EntityManagerFactory factory;

	public static void main(String[] args)
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		
		EntityManager em = factory.createEntityManager();
		
		// read the existing entries and write to console
		Query q = em.createQuery("select e from Employee e");
		List<Employee> employeeList = q.getResultList();
		
		for (Employee employee : employeeList)
		{
			System.out.println(employee);
		}
		
		System.out.println("Size: " + employeeList.size());
		// create new todo
		em.getTransaction().begin();
		
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setName("Ashish");
		emp.setEmail("atripathi@gmail.com");
		emp.setDepartment("Finance");
		
		em.persist(emp);
		
		em.getTransaction().commit();
		em.close();
	}
}
