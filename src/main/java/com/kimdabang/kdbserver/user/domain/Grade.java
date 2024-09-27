package com.kimdabang.kdbserver.user.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {

    GRADE_WELCOME("Welcome"),
    GRADE_GREEN("Green"),
    GRADE_GOLD("Gold");

    private final String grade;
    @JsonValue
    public String getGrade() {
        return grade;
    }

    public static Grade fromString(String value) {
        for (Grade grade: Grade.values()) {
            if (grade.grade.equals(value)) {
                return grade;
            }
        }
        throw new IllegalArgumentException("UnKnown value: " + value);
    }
}
