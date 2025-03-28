package dev.ayush.productserviceeve.inheritanceexamples.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_mentor")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
