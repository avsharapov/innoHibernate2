package ru.inno.java.hibernate.dao;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.inno.java.hibernate.entity.Student;
import ru.inno.java.hibernate.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class StudentDao {
    private static final Logger logger = LoggerFactory.getLogger("StudentDao");

    public Integer saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            int id = (Integer) session.save(student);
            // commit transaction
            transaction.commit();
            return id;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
        return null;
    }

    public void updateStudent(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Student student = session.get(Student.class, id);
            session.lock(student, LockMode.PESSIMISTIC_READ);
            student.setLastName("1");
            student.setFirstName("2");
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }

    public void getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createQuery("from Student", Student.class).list().forEach(o -> logger.info("{}", o));
        }
    }

    public void getStudentsСJPA() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder              builder = session.getCriteriaBuilder();
            final CriteriaQuery<Student> query   = builder.createQuery(Student.class);
            final Root<Student>          root    = query.from(Student.class);
            /*      root.fetch("courses", JoinType.INNER);*/
            session.createQuery(query).list().forEach(o -> logger.info("{}", o));
        }
    }

    public void getStudentsС() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createCriteria(Student.class).list().forEach(o -> logger.info("{}", o));
        }
    }


    public void insertStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String hql    = "INSERT INTO Student (firstName, lastName, email) SELECT firstName, lastName, email FROM Student";
            Query  query  = session.createQuery(hql);
            int    result = query.executeUpdate();
            logger.info("Rows affected: " + result);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }


    public void deleteStudent(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            final Query sqlQuery = session.createSQLQuery("DELETE FROM student WHERE id=" + id);
            int         result   = sqlQuery.executeUpdate();
            logger.info("Rows affected: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }

    public void removeStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.remove(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("", e);
        }
    }
}
