package com.project.gyatsina.learnlang.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.gyatsina.learnlang.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by galynaiatsyna on 3/21/17.
 */

@EFragment(R.layout.word_page_layout)
public class WordPageFragment extends Fragment {
    private String mainText;
    private String mainImage;

    @ViewById(R.id.textView)
    TextView textView;

    @ViewById(R.id.imageView)
    ImageView imageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.word_page_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public void setData(Bundle data) {
        mainText = data.getString(BundleKeys.WORD_PAGE_TEXT);
        mainImage = data.getString(BundleKeys.WORD_PAGE_IMAGE);
    }

    public void showData() {
        textView.setText(mainText);
        if (mainImage != null) {
            Picasso.with(getActivity()).load(mainImage).into(imageView);
        }
    }


}
