package com.fudn.slot2.jpa_demo;

import com.fudn.slot2.jpa_demo.entity.Student;
import com.fudn.slot2.jpa_demo.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateAndRetrieveStudentFromDatabase() {

        studentService.createStudent("Test Student", "test@fpt.edu.vn", 25);

        entityManager.flush();
        entityManager.clear();


        String jpql = "SELECT s FROM Student s WHERE s.email = :email";
        Student retrievedStudent = entityManager.createQuery(jpql, Student.class)
                .setParameter("email", "test@fpt.edu.vn")
                .getSingleResult();


        assertNotNull(retrievedStudent);
        assertEquals("Test Student", retrievedStudent.getFullName());
        assertEquals("test@fpt.edu.vn", retrievedStudent.getEmail());
    }

    @Test
    public void testGetStudentById() {

        studentService.createStudent("Student Get", "get@fpt.edu.vn", 20);
        entityManager.flush();
        entityManager.clear();

        Student s = entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", "get@fpt.edu.vn")
                .getSingleResult();

        Student found = studentService.getStudentById(s.getId());
        assertNotNull(found);
        assertEquals("Student Get", found.getFullName());
    }

    @Test
    public void testUpdateStudent() {

        studentService.createStudent("Student Update", "update@fpt.edu.vn", 21);
        entityManager.flush();
        entityManager.clear();

        Student s = entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", "update@fpt.edu.vn")
                .getSingleResult();

        studentService.updateStudent(s.getId(), "Student Update - Edited", "updated_email@fpt.edu.vn", 26);
        entityManager.flush();
        entityManager.clear();

        Student updatedStudent = studentService.getStudentById(s.getId());
        assertNotNull(updatedStudent);
        assertEquals("Student Update - Edited", updatedStudent.getFullName());
        assertEquals("updated_email@fpt.edu.vn", updatedStudent.getEmail());
        assertEquals(26, updatedStudent.getAge());
    }

    @Test
    public void testDeleteStudent() {

        studentService.createStudent("Student Delete", "delete@fpt.edu.vn", 22);
        entityManager.flush();
        entityManager.clear();

        Student s = entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", "delete@fpt.edu.vn")
                .getSingleResult();
        studentService.deleteStudent(s.getId());
        entityManager.flush();
        entityManager.clear();

        Student deletedStudent = studentService.getStudentById(s.getId());
        assertNull(deletedStudent);
    }
}