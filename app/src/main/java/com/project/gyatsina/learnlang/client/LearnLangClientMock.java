package com.project.gyatsina.learnlang.client;

import com.project.gyatsina.learnlang.model.LearnLangCourse;
import com.project.gyatsina.learnlang.model.LearnLangListing;
import com.project.gyatsina.learnlang.model.LearnLangObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

public class LearnLangClientMock implements LearnLangClient {
    public LearnLangClientMock() {

    }

    @Override
    public Observable<LearnLangObject> getTop(@Query("after") String after, @Query("limit") int limit) {
        List<LearnLangObject> langCourseList = new ArrayList<>();
        langCourseList.add(new LearnLangCourse("11", "languageName", "1", 10, 100,
                "https://images.template.net/wp-content/uploads/2016/03/15120347/Smiling-Cat-Drawing-Free-Download-PDF.jpg"));
        langCourseList.add(new LearnLangCourse("12", "languageName", "1", 20, 100,
                        "https://images.template.net/wp-content/uploads/2016/03/15120347/Smiling-Cat-Drawing-Free-Download-PDF.jpg"));
        langCourseList.add(new LearnLangCourse("12", "languageName", "2", 12, 100,
                        "https://images.template.net/wp-content/uploads/2016/03/15120347/Smiling-Cat-Drawing-Free-Download-PDF.jpg"));

        String beforeArray = null;
        String afterArray = "123_123";

        LearnLangListing listing = new LearnLangListing(langCourseList, beforeArray, afterArray);

        Observable<LearnLangObject> mockLearnLangObservable = Observable.just(listing);

        return mockLearnLangObservable;
    }
}
