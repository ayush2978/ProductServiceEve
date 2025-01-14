package dev.ayush.productserviceeve.inheritanceexamples;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User{
    private int numberOfSessions;
    private int numberOfMentees;
}
