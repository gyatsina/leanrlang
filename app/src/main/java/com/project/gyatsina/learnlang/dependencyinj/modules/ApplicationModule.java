package com.project.gyatsina.learnlang.dependencyinj.modules;

import com.project.gyatsina.learnlang.LearnLangApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule
{
    private LearnLangApplication application;

    public ApplicationModule(LearnLangApplication application)
    {
        this.application = application;
    }

    @Provides
    public LearnLangApplication provideApplication() {
        return application;
    }
}