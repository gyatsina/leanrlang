package com.project.gyatsina.learnlang.model;

import java.util.List;

public class LearnLangListing implements LearnLangObject
{
    private List<LearnLangObject> children;
    private String before;
    private String after;

    public LearnLangListing(List<LearnLangObject> children, String before, String after) {
        this.children = children;
        this.before = before;
        this.after = after;
    }

    public List<LearnLangObject> getChildren() {
        return children;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }
}


