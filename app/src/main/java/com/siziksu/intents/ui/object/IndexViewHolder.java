package com.siziksu.intents.ui.object;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.siziksu.intents.R;

final class IndexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public View view;
    public TextView title;
    public TextView summary;

    private IndexAdapter.ClickListener listener;

    IndexViewHolder(View view, IndexAdapter.ClickListener listener) {
        super(view);
        this.listener = listener;
        this.view = view.findViewById(R.id.index_item);
        this.title = (TextView) view.findViewById(R.id.index_item_title);
        this.summary = (TextView) view.findViewById(R.id.index_item_summary);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
