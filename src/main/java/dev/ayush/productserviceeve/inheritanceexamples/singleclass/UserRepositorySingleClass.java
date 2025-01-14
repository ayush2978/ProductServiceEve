package dev.ayush.productserviceeve.inheritanceexamples.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorySingleClass extends JpaRepository<User, Long> {
    User save(User user);

    User findUserById(Long id);
}
