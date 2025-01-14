package dev.ayush.productserviceeve.inheritanceexamples.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepositoryTablePerClass extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);

    Mentor findMentorById(Long id);
}
