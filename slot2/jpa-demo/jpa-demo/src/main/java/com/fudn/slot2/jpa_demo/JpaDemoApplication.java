package com.fudn.slot2.jpa_demo;

import com.fudn.slot2.jpa_demo.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentService service) {
		return args -> {

			System.out.println("=== 1. THÊM MỚI SINH VIÊN ===");
			service.createStudent("Nguyễn Văn A", "a@fpt.edu.vn", 20);
			service.createStudent("Trần Thị B", "b@fpt.edu.vn", 21);

			System.out.println("\n--- Danh sách sinh viên ban đầu ---");
			service.printAll();


			System.out.println("\n=== 2. TÌM SINH VIÊN THEO ID (ID = 1) ===");
			var student = service.getStudentById(1L);
			System.out.println("Tìm thấy: " + student);


			System.out.println("\n=== 3. CẬP NHẬT SINH VIÊN (ID = 1) ===");
			service.updateStudent(1L, "Nguyễn Văn A - Đã sửa", "a_updated@fpt.edu.vn", 25);
			System.out.println("--- Danh sách sau khi Cập nhật ---");
			service.printAll();


			System.out.println("\n=== 4. XÓA SINH VIÊN (ID = 2) ===");
			service.deleteStudent(2L);
			System.out.println("--- Danh sách sau khi Xóa ---");
			service.printAll();
		};
	}
}