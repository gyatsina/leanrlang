package com.project.gyatsina.learnlang.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.project.gyatsina.learnlang.LearnLangApplication;
import com.project.gyatsina.learnlang.R;
import com.project.gyatsina.learnlang.view.adapter.CourseAdapter;
import com.project.gyatsina.learnlang.viewmodel.FeedViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.post_list)
    RecyclerView courseList;

    @OptionsMenuItem(R.id.progress)
    MenuItem loadingMenuItem;

    @Inject
    FeedViewModel viewModel;

    private CourseAdapter courseAdapter;
    private LinearLayoutManager courseListLayoutManager;

    private CompositeSubscription subscriptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((LearnLangApplication) getApplication()).component().inject(this);

        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        subscriptions.unsubscribe();
    }

    @AfterViews
    void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                toolbar.setTitle(getTitle());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle(getTitle());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        courseListLayoutManager = new LinearLayoutManager(this);
        courseList.setLayoutManager(courseListLayoutManager);

        courseAdapter = new CourseAdapter();
        courseList.setAdapter(courseAdapter);

        courseList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, courseList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("initViews ", "onItemClick " );
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Log.d("initViews ", "onLongItemClick " );
                    }
                })
        );

        fab.setOnClickListener((View view) ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        initBindings();
        loadNextPage();
    }

    private void initBindings() {
        // Observable that emits when the RecyclerView is scrolled to the bottom
        Observable<Void> infiniteScrollObservable = Observable.create(subscriber -> {
            courseList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int totalItemCount = courseListLayoutManager.getItemCount();
                    int visibleItemCount = courseListLayoutManager.getChildCount();
                    int firstVisibleItem = courseListLayoutManager.findFirstVisibleItemPosition();

                    int sum = visibleItemCount + firstVisibleItem;
                    if ((sum) >= totalItemCount) {
                        Log.d("initBindings  sum="+sum, "totalItemCount ="+totalItemCount );
                        subscriber.onNext(null);
                    }
                }
            });
        });

        subscriptions.addAll(
                // Bind list of posts to the RecyclerView
                viewModel.postsObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(courseAdapter::setItems),

                // Bind loading status to show/hide loading spinner
                viewModel.isLoadingObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(this::setIsLoading)

                // Trigger next page load when RecyclerView is scrolled to the bottom
//                infiniteScrollObservable.subscribe(x -> loadNextPage())
        );
    }

    private void loadNextPage() {
        System.out.println("loadNextPage");
        subscriptions.add(
                viewModel.loadMorePosts().subscribe()
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setIsLoading(boolean isLoading) {
        if (loadingMenuItem != null) {
            loadingMenuItem.setVisible(isLoading);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
