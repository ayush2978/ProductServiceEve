package dev.ayush.productserviceeve.inheritanceexamples.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TARepositorySingleClass extends JpaRepository<TA, Long> {
    TA save(TA ta);

    TA findTAById(Long id);
}
