package dev.ayush.productserviceeve.inheritanceexamples.mappedSuperClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TARepositoryMappedSuperClass extends JpaRepository<TA, Long> {
    TA save(TA ta);

    TA findTAById(Long id);
}
