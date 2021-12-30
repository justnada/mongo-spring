package com.nadasproject.restspringmongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class RestSpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSpringMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			StudentRepository studentRepository,
			MongoTemplate mongoTemplate) {
		return args -> {

			Address address = new Address("England","London","NE43");

			String email = "robertpatt@gmail.com";

			Student student = new Student(
					"Robert",
					"Pattinson",
					email,
					Gender.MALE,
					address,
					List.of("Art", "Theater"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			studentRepository.findStudentByEmail(email)
					.ifPresentOrElse(student1 -> {
						System.out.println(student + "\n Already exists!");
					}, () -> {
						System.out.println("Inserting student...\n" + student);
						studentRepository.insert(student);
						System.out.println("\nSuccessfully inserted!");
					});

			// usingMongoTemplateAndQuery(studentRepository, mongoTemplate, email, student);

		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException(
					"found many students with email " + email);
		}

		if (students.isEmpty()) {
			System.out.println("Inserting student...\n" + student);
			studentRepository.insert(student);
			System.out.println("\nSuccessfully inserted!");
		} else {
			System.out.println(students + "\n Already exists!");
		}
	}

}
