package com.project.gyatsina.learnlang.view;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.gyatsina.learnlang.R;
import com.project.gyatsina.learnlang.dependencyinj.modules.ApplicationModule;
import com.project.gyatsina.learnlang.viewmodel.CourseViewModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.validator.routines.UrlValidator;

import javax.inject.Inject;

public class CourseViewHolder extends RecyclerView.ViewHolder
{
    private View view;
    private TextView titleTextView;
    private TextView levelTextView;
    private TextView resultTextView;
    private ImageView thumbnailImageView;

    @Inject
    ApplicationModule appM;

    public CourseViewHolder(View view)
    {
        super(view);

        this.view = view;
        this.titleTextView = (TextView) view.findViewById(R.id.lang_title);
        this.levelTextView = (TextView) view.findViewById(R.id.level);
        this.resultTextView = (TextView) view.findViewById(R.id.learn_result);
        this.thumbnailImageView = (ImageView)view.findViewById(R.id.lang_image);
    }

    public void bind(CourseViewModel viewModel)
    {
        titleTextView.setText(viewModel.getLessonName());
        levelTextView.setText(viewModel.getLevel());
        Resources res = appM.getApplication().getResources();
        String learnedResult = String.format(res.getString(R.string.learn_result), viewModel.getProgress(), viewModel.getTotal());
        resultTextView.setText(learnedResult);

        UrlValidator urlValidator = new UrlValidator();
        boolean hasThumbnail = viewModel.getThumb() != null && urlValidator.isValid(viewModel.getThumb());

        // Show/hide the thumbnail if there is/isn't one
        thumbnailImageView.setVisibility(hasThumbnail ? View.VISIBLE : View.GONE);

        // Load the thumbnail if there is one
        if (hasThumbnail)
        {
            Picasso.with(view.getContext()).load(viewModel.getThumb()).into(thumbnailImageView);
        }
    }
}
