package com.project.gyatsina.learnlang.viewmodel;

import com.project.gyatsina.learnlang.client.LearnLangClient;
import com.project.gyatsina.learnlang.client.LearnLangClientMock;
import com.project.gyatsina.learnlang.model.LearnLangCourse;
import com.project.gyatsina.learnlang.model.LearnLangListing;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class FeedViewModel
{
    private LearnLangClient redditClient;
    private LearnLangClientMock mockRedditClient;

    private int pageLimit;
    private String afterToken;
    private BehaviorSubject<List<CourseViewModel>> postSubject = BehaviorSubject.create(new ArrayList<>());
    private BehaviorSubject<Boolean> isLoadingSubject = BehaviorSubject.create(false);

    @Inject
//    public FeedViewModel(LearnLangClient redditClient)
    public FeedViewModel(LearnLangClientMock mockRedditClient)
    {
//        this.redditClient = redditClient;
        this.mockRedditClient = mockRedditClient;
        this.pageLimit = 25;
    }

    public Observable<List<CourseViewModel>> loadMorePosts()
    {
        // Don't try and load if we're already loading
        if (isLoadingSubject.getValue())
        {
            return Observable.empty();
        }

        isLoadingSubject.onNext(true);

//        return redditClient
        return mockRedditClient
            .getTop(afterToken, pageLimit)
            // Safe to cast to LearnLangListing, as this is always returned from top posts
            .cast(LearnLangListing.class)
            // Store the after token, so we can use it to get the next page of posts is a subsequent load
            .doOnNext(listing -> afterToken = listing.getAfter())
            // Flatten into observable of RedditLinks
            .map(LearnLangListing::getChildren)
            .flatMapIterable(list -> list)
            .filter(object -> object instanceof LearnLangCourse)
            // Transform model to viewmodel
            .map(link -> new CourseViewModel((LearnLangCourse) link))
            // Merge viewmodels into a single list to be emitted
            .toList()
            // Concatenate the new posts to the current posts list, then emit it via the post subject
            .doOnNext(list -> {
                List<CourseViewModel> fullList = new ArrayList<>(postSubject.getValue());
                fullList.addAll(list);
                postSubject.onNext(fullList);
            })
            .doOnTerminate(() -> isLoadingSubject.onNext(false));
    }

    public Observable<List<CourseViewModel>> postsObservable()
    {
        return postSubject.asObservable();
    }

    public Observable<Boolean> isLoadingObservable()
    {
        return isLoadingSubject.asObservable();
    }
}
