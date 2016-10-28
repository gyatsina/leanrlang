package com.project.gyatsina.learnlang.client;

import com.project.gyatsina.learnlang.model.LearnLangObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LearnLangClient
{
    @GET("/courses.json")
    Observable<LearnLangObject> getTop(@Query("after") String after, @Query("limit") int limit);
}

