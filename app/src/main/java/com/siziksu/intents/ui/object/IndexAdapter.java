package com.siziksu.intents.ui.object;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siziksu.intents.R;

import java.util.ArrayList;
import java.util.List;

public final class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<Index> indices;
    private ClickListener listener;
    private LinearLayoutManager layoutManager;

    public IndexAdapter(Context context) {
        this.context = context;
    }

    public void init(ClickListener listener) {
        indices = new ArrayList<>();
        this.listener = listener;
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.index_item, parent, false);
        return new IndexViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IndexViewHolder) {
            IndexViewHolder localHolder = (IndexViewHolder) holder;
            Index index = indices.get(position);
            localHolder.title.setText(index.title);
            localHolder.summary.setText(index.summary);
        }
    }

    @Override
    public int getItemCount() {
        return indices.size();
    }

    public Index getItem(int position) {
        if (!indices.isEmpty() && position < indices.size()) {
            return indices.get(position);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return indices.isEmpty();
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void showItems(List<Index> list) {
        indices.clear();
        showAllItems(list);
    }

    private void showAllItems(List<Index> list) {
        indices.addAll(list);
        notifyDataSetChanged();
    }

    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    public interface ClickListener {

        void onClick(View view, int position);
    }
}
