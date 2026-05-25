package hsf302.lab02.services;

import hsf302.lab02.pojo.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void addStudent(Student student) {
        System.out.println("Added student: " + student.getName());
    }

    @Override
    public String getStudentInfo(Student student) {
        return student.toString();
    }
}
