package dev.ayush.productserviceeve.inheritanceexamples.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepositorySingleClass extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);

    Mentor findMentorById(Long id);
}
