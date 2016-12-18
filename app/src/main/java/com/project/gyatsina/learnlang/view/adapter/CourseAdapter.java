package com.project.gyatsina.learnlang.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.gyatsina.learnlang.R;
import com.project.gyatsina.learnlang.view.CourseViewHolder;
import com.project.gyatsina.learnlang.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder>
{
    private List<CourseViewModel> items = new ArrayList<>();

    public CourseAdapter()
    {
        setHasStableIds(true);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lang_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position)
    {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount()
    {
        int count = items.size();
        return count;
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId().hashCode();
    }

    public CourseViewModel getItem(int position)
    {
        return items.get(position);
    }

    public void setItems(List<CourseViewModel> items)
    {
        if (items == null)
        {
            return;
        }

        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }
}
