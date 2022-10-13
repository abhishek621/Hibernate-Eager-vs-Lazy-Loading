package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCourseDemo {

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
			Instructor tempInstrutor = session.get(Instructor.class, theId);

			// create some courses
			Course tempCourse1 = new Course("Air Guitar - The Ultimate Guitar");
			Course tempCourse2 = new Course("The Pinball Masterclass");

			// add courses to instructor
			tempInstrutor.addCourse(tempCourse1);
			tempInstrutor.addCourse(tempCourse2);

			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);

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
