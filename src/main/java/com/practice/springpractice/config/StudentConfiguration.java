package com.practice.springpractice.config;

import com.practice.springpractice.model.Student;
import com.practice.springpractice.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student naman = new Student(
                    "Naman Kumar",
                    "naman@gmail.com",
                    LocalDate.of(2002, Month.JULY, 20));

            Student satish = new Student(
                    "Satish Yadav",
                    "satish@gmail.com",
                    LocalDate.of(2002, Month.MAY, 23));

            Student prabhakar = new Student(
                    "Prabhakar Mishra",
                    "prabhakar@gmail.com",
                    LocalDate.of(2000, Month.SEPTEMBER, 25));

            Student piyush = new Student(
                    "Piyush Shukla",
                    "piyush@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 29));

            repository.saveAll(
                    List.of(naman, satish, prabhakar, piyush)
            );
        };
    }

}
