package com.project.gyatsina.learnlang;

import android.app.Application;

import com.project.gyatsina.learnlang.dependencyinj.ApplicationComponent;
import com.project.gyatsina.learnlang.dependencyinj.DaggerApplicationComponent;
import com.project.gyatsina.learnlang.dependencyinj.modules.ApplicationModule;

import org.androidannotations.annotations.EApplication;

public class LearnLangApplication extends Application {

    private static LearnLangApplication instance;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent =
                DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        instance = this;
    }

    public static LearnLangApplication getInstance() {
        return instance;
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }

}
