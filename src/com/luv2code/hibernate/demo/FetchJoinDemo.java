package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		// create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create Session

		Session session = factory.getCurrentSession();

		// use the session object to save the java object

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;

			// option 2 : Hibernate Query with HQL

			Query<Instructor> query = session.createQuery(
					"Select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId ", Instructor.class);

			// set parameter on query
			query.setParameter("theInstructorId", theId);

			// execute query and get instructor
			Instructor tempInstrutor = query.getSingleResult();
			System.out.println("luv2code: Instructor : " + tempInstrutor);

			// commit the transaction
			session.getTransaction().commit();

			// close the session for testing
			session.close();
			System.out.println("\nluv2code : The session is now closed!\n");

			// get courses for the instructor
			System.out.println("luv2code: Courses : " + tempInstrutor.getCourses());

			System.out.println("luv2code: Done");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
