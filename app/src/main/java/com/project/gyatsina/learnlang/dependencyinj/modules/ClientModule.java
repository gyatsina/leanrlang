package com.project.gyatsina.learnlang.dependencyinj.modules;

import com.project.gyatsina.learnlang.client.LearnLangClientMock;

import dagger.Module;
import dagger.Provides;

@Module
public class ClientModule {
    @Provides
    public LearnLangClientMock provideLearnLangClient(){
        LearnLangClientMock mock = new LearnLangClientMock();

        return mock;
    }
}
