package dev.ayush.productserviceeve.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.management.ObjectName;

@Getter
@Setter
@Entity(name = "tbc_mentor")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
