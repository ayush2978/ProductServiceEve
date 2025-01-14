package dev.ayush.productserviceeve;

import dev.ayush.productserviceeve.inheritanceexamples.mappedSuperClass.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceEveApplicationTests {

//    @Autowired
//    private UserRepositoryMappedSuperClass userRepository;
    @Autowired
    private MentorRepositoryMappedSuperClass mentorRepository;
    @Autowired
    private InstructorReporistoryMappedSuperClass instructorReporistorySingleClass;

    @Autowired
    private TARepositoryMappedSuperClass taRepositorySingleClass;
    @Test
    void contextLoads() {
    }

    @Test
    void testDifferentInheritances(){
//        User user = new User();
//        user.setEmail("ayushfzs54@gmail.com");
//        user.setPassword("nani12345");
//        userRepository.save(user);

//        Mentor mentor = new Mentor();
//        mentor.setEmail("naman@gmail.com");
//        mentor.setPassword("naman1234");
//        mentor.setNumberOfMentees(10);
//        mentor.setNumberOfSessions(20);
//        mentorRepository.save(mentor);
////
//        Instructor instructor = new Instructor();
//        instructor.setEmail("ashish@gmail.com");
//        instructor.setPassword("ashish1234");
//        instructor.setHandsome(true);
//        instructorReporistorySingleClass.save(instructor);
////
////
//        TA ta = new TA();
//        ta.setEmail("saurav@gmail.com");
//        ta.setPassword("saurav1234");
//        ta.setAverageRating(4.5);
//        taRepositorySingleClass.save(ta);

    }
}
