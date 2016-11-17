package com.project.gyatsina.learnlang.dependencyinj;

import com.project.gyatsina.learnlang.dependencyinj.modules.ApplicationModule;
import com.project.gyatsina.learnlang.dependencyinj.modules.ClientModule;
import com.project.gyatsina.learnlang.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ClientModule.class})
public interface ApplicationComponent
{
    void inject(MainActivity activity);
}
