package dev.ayush.productserviceeve.inheritanceexamples.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorReporistorySingleClass extends JpaRepository<Instructor, Long> {
    Instructor save(Instructor instructor);

    Instructor findInstructorById(Long id);
}
