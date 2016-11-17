package com.project.gyatsina.learnlang.client;

import com.project.gyatsina.learnlang.model.LearnLangCourse;
import com.project.gyatsina.learnlang.model.LearnLangObject;

import java.util.ArrayList;

import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;

public class LearnLangClientMock implements LearnLangClient {
    public LearnLangClientMock() {

    }

    @Override
    public Observable<LearnLangObject> getTop(@Query("after") String after, @Query("limit") int limit) {
        final LearnLangCourse lo = new LearnLangCourse("id", "lessonName", 1, 10, 100,
                "http://cdn.pet360.com/pet360/Content/Images/CMS/Slideshows/cms_resized_large/cats-that-stay-small1.lg.jpg"
        );
        final LearnLangCourse[] array = {
                new LearnLangCourse("11", "lessonName", 1, 10, 100,
                        "http://cdn.pet360.com/pet360/Content/Images/CMS/Slideshows/cms_resized_large/cats-that-stay-small1.lg.jpg"),
                new LearnLangCourse("12", "lessonName", 1, 20, 100,
                        "http://cdn.pet360.com/pet360/Content/Images/CMS/Slideshows/cms_resized_large/cats-that-stay-small1.lg.jpg"),
                new LearnLangCourse("12", "lessonName", 2, 12, 100,
                        "http://cdn.pet360.com/pet360/Content/Images/CMS/Slideshows/cms_resized_large/cats-that-stay-small1.lg.jpg"),

        };

        Observable<LearnLangObject> mockLearnLangObservable = Observable.create(
                new Observable.OnSubscribe<LearnLangObject>() {
                    @Override
                    public void call(Subscriber<? super LearnLangObject> sub) {
                        sub.onNext(array[0]);
                        sub.onNext(array[1]);
                        sub.onNext(array[2]);
                        sub.onCompleted();
                    }
                }
        );

        return null;
    }
}
