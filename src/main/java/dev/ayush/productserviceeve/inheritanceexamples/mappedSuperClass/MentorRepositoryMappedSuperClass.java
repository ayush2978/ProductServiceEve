package dev.ayush.productserviceeve.inheritanceexamples.mappedSuperClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepositoryMappedSuperClass extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);

    Mentor findMentorById(Long id);
}
