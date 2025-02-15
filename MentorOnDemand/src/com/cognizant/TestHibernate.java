package com.cognizant;

import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestHibernate {

	public static void main(String[] args) {

		Technologies technology1 = new Technologies("1mysql", "mysql", 30000);
		Trainings training2 = new Trainings("1", "ongoing", 64, technology1);
		Trainings training1 = new Trainings("2", "ongoing", 64, technology1);
//		Trainings training1 = new Trainings("2", "accepted", 55, technology1);

		Set<Trainings> trainingSet = new HashSet<Trainings>();
		trainingSet.add(training1);
		trainingSet.add(training2);

		Payments payment1 = new Payments(123, "25%fee", 133.2, "fee", "133", "a@a.com");
		Payments payment2 = new Payments(321, "25%fee", 133.2, "fee", "133", "a@a.com");
		Set<Payments> paymentSet = new HashSet<Payments>();
		Set<Payments> paymentSet2 = new HashSet<Payments>();
		paymentSet.add(payment1);
		paymentSet.add(payment2);
		
		MentorSkills skill1 = new MentorSkills(1, "skill1", "toc1", "pre1");
		Set<MentorSkills> skillSet = new HashSet<MentorSkills>();
		skillSet.add(skill1);

		Users user1 = new Users("b@b.com", "b", "b", "blocked", "trainer", trainingSet, paymentSet);
		Users user2 = new Users("a@a.com", "a", "a", "unblocked", "student", trainingSet, paymentSet2);
		TrainerDetails trainer1 = new TrainerDetails(user1, 5, "+5:30", "9-7", "ppts", "/trainer1",skillSet);

		MentorSkills mentorSkills = new MentorSkills(121, "core java", "java fundamentals", "programming fundamentals");

		Configuration cfg = new Configuration();
		cfg.configure("Configuration.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.getTransaction();
		t.begin();

		s.save(user1);
		s.save(user2);
		s.save(skill1);
		s.save(trainer1);
		s.save(training1);
		s.save(training2);

		s.save(technology1);
		s.save(payment1);
		s.save(payment2);
		s.save(mentorSkills);
		t.commit();
		s.close();
		System.out.println("Executed Code!!");

	}

}
