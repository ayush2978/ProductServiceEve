package dev.ayush.productserviceeve.inheritanceexamples.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryTablePerClass extends JpaRepository<User, Long> {
    User save(User user);

    User findUserById(Long id);
}
