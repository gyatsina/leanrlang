package com.project.gyatsina.learnlang.model;

public class LearnLangCourse implements LearnLangObject {
    private String id;
    private String lessonName;
    private int level;
    private int progress;
    private int total;
    private String image;

    public String getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getLevel() {
        return level;
    }

    public int getProgress() {
        return progress;
    }

    public int getTotal() {
        return total;
    }

    public String getImage() {
        return image;
    }
}
