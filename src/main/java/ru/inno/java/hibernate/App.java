package ru.inno.java.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.dao.StudentDao;
import ru.inno.java.hibernate.entity.Course;
import ru.inno.java.hibernate.entity.Student;
import ru.inno.java.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    private static final Logger logger = LoggerFactory.getLogger("App");

    public static void main(String[] args) {

        // create some courses
        List<Course> courses     = new ArrayList<>();
        Course       tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        courses.add(tempCourse1);
        Course tempCourse2 = new Course("The Pinball Masterclass");
        courses.add(tempCourse2);

        //create some students
        StudentDao studentDao = new StudentDao();

        Student tempStudent1 = new Student("Ivan", "Bunin", "ivanbu@javaguides.com");
        tempStudent1.setCourses(Collections.singletonList(new Course("Personal Masterclass")));
        Integer id1 = studentDao.saveStudent(tempStudent1);


        Student tempStudent2 = new Student("Matvey", "Mashin", "mama@javaguides.com");
        tempStudent2.setCourses(courses);
        Integer id2 = studentDao.saveStudent(tempStudent2);

        studentDao.updateStudent(id1);
        studentDao.updateStudent(id2);

        studentDao.insertStudent();
        logger.info("\n\n\n\n\n\n\n\n\n\n\n\n");
        logger.info("===================JPQL=======================");
        studentDao.getStudents();
        logger.info("===================HIBC=======================");
        studentDao.getStudentsС();
        logger.info("===================JPAC=======================");
        studentDao.getStudentsСJPA();
        logger.info("==========================================");
        logger.info("\n\n\n\n\n\n\n\n\n\n\n\n");
        HibernateUtil.shutdown();
    }
}
