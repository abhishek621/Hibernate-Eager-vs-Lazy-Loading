package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

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

			// get the course from db
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			System.out.println("Course : " + tempCourse);

			// delete courses from db
			System.out.println("Deleting Course : " + tempCourse);
			session.delete(tempCourse);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
