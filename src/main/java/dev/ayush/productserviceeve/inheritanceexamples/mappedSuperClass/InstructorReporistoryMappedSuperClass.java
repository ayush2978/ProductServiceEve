package dev.ayush.productserviceeve.inheritanceexamples.mappedSuperClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorReporistoryMappedSuperClass extends JpaRepository<Instructor, Long> {
    Instructor save(Instructor instructor);

    Instructor findInstructorById(Long id);
}
