package com.project.gyatsina.learnlang.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.gyatsina.learnlang.R;
import com.project.gyatsina.learnlang.viewmodel.CourseViewModel;

public class CourseViewHolder extends RecyclerView.ViewHolder
{
    private View view;
    private TextView titleTextView;
    private TextView levelTextView;
    private TextView resultTextView;
    private ImageView thumbnailImageView;

    public CourseViewHolder(View view)
    {
        super(view);

        this.view = view;
        this.titleTextView = (TextView) view.findViewById(R.id.lang_title);
        this.levelTextView = (TextView) view.findViewById(R.id.level);
        this.resultTextView = (TextView) view.findViewById(R.id.learned_result);
        this.thumbnailImageView = (ImageView)view.findViewById(R.id.lang_image);
    }

    public void bind(CourseViewModel viewModel)
    {
        titleTextView.setText(viewModel.getLessonName());
        levelTextView.setText(viewModel.getLevel());
        resultTextView.setText(String.format(getString(R.id.learned_result), viewModel.getProgress(), viewModel.getTotal()));
//        tv.setText(String.format(getString(R.id.example),name,age,city));

        UrlValidator urlValidator = new UrlValidator();
        boolean hasThumbnail = viewModel.getThumbnailUrl() != null && urlValidator.isValid(viewModel.getThumbnailUrl());

        // Show/hide the thumbnail if there is/isn't one
        thumbnailImageView.setVisibility(hasThumbnail ? View.VISIBLE : View.GONE);

        // Load the thumbnail if there is one
        if (hasThumbnail)
        {
            Picasso.with(view.getContext()).load(viewModel.getThumbnailUrl()).into(thumbnailImageView);
        }
    }
}
