package com.hcmus.app_list_student;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    String id;
    int avatarResId;
    String faculty;
    String major;
    int creditsCompleted;
    float gpa;
    int trainingScore;

    // Constructor cho dữ liệu đầy đủ
    public Student(String name, String id, int avatarResId, String faculty, String major, int creditsCompleted, float gpa, int trainingScore) {
        this.name = name;
        this.id = id;
        this.avatarResId = avatarResId;
        this.faculty = faculty;
        this.major = major;
        this.creditsCompleted = creditsCompleted;
        this.gpa = gpa;
        this.trainingScore = trainingScore;
    }

    // Constructor cho dữ liệu từ database (không cần nữa vì schema đã đầy đủ)
    // Nhưng để lại để tương thích nếu cần
    public Student(String name, String id, int avatarResId) {
        this.name = name;
        this.id = id;
        this.avatarResId = avatarResId;
        this.faculty = "";
        this.major = "";
        this.creditsCompleted = 0;
        this.gpa = 0.0f;
        this.trainingScore = 0;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAvatarResId() {
        return avatarResId;
    }
}