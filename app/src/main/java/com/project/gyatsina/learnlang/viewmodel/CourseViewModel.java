package com.project.gyatsina.learnlang.viewmodel;


import com.project.gyatsina.learnlang.model.LearnLangCourse;

public class CourseViewModel {
    private String id;
    private String lessonName;
    private int level;
    private int progress;
    private int total;
    private String thumb;

    public CourseViewModel(LearnLangCourse course) {
        this.id = course.getId();
        this.lessonName = course.getLessonName();
        this.level = course.getLevel();
        this.progress = course.getProgress();
        this.total = course.getTotal();
        this.thumb = course.getImage();
    }

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

    public String getThumb() {
        return thumb;
    }
}
