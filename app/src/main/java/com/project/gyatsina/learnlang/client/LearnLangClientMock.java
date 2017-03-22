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
        langCourseList.add(new LearnLangCourse("11", "languageName №1", "1", 10, 100,
                "https://images.template.net/wp-content/uploads/2016/03/15120347/Smiling-Cat-Drawing-Free-Download-PDF.jpg"));
        langCourseList.add(new LearnLangCourse("12", "languageName №2", "1", 20, 100,
                        "https://is2-ssl.mzstatic.com/image/thumb/Purple7/v4/1b/90/ce/1b90cea3-024b-ca90-ffa9-88554eda5c5e/source/1242x2208bb.jpeg"));
        langCourseList.add(new LearnLangCourse("12", "languageName №3", "2", 12, 100,
                        "https://is5-ssl.mzstatic.com/image/thumb/Purple7/v4/39/87/e4/3987e462-079c-67e1-5f50-181224cbdfba/source/1242x2208bb.jpeg"));

        String beforeArray = null;
        String afterArray = "123_123";

        LearnLangListing listing = new LearnLangListing(langCourseList, beforeArray, afterArray);

        Observable<LearnLangObject> mockLearnLangObservable = Observable.just(listing);

        return mockLearnLangObservable;
    }
}
