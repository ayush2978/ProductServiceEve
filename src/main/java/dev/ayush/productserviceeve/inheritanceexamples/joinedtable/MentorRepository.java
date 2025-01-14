package dev.ayush.productserviceeve.inheritanceexamples.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);

    Mentor findMentorById(Long id);
}
