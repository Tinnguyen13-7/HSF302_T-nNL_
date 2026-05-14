package com.fudn.slot2.jpa_demo.service;

import com.fudn.slot2.jpa_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String name, String email, int age) {
        Student s = new Student(name, email, age);
        em.persist(s); // Thực hiện lệnh INSERT xuống Database
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional(readOnly = true)
    public void printAll() {
        // Sử dụng JPQL để lấy danh sách sinh viên
        String jpql = "SELECT s FROM Student s";
        List<Student> students = em.createQuery(jpql, Student.class).getResultList();

        // In danh sách ra màn hình console
        students.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return em.find(Student.class, id);
    }

    @Transactional
    public void updateStudent(Long id, String newName, String newEmail, int newAge) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            student.setFullName(newName);
            student.setEmail(newEmail);
            student.setAge(newAge);
            System.out.println("Updated Student ID = " + id);
        } else {
            System.out.println("Không tìm thấy sinh viên với ID = " + id);
        }
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
            System.out.println("Deleted Student ID = " + id);
        } else {
            System.out.println("Không tìm thấy sinh viên với ID = " + id);
        }
    }
}