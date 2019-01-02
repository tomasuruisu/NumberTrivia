package com.thomas.numbertrivia;

import android.content.Context;
;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class TriviaNumberAdapter extends RecyclerView.Adapter<TriviaNumberViewHolder> {

    private Context context;
    public List<TriviaNumber> triviaNumbers;

    public TriviaNumberAdapter(Context context, List<TriviaNumber> listGeoObject) {
        this.context = context;
        this.triviaNumbers = listGeoObject;
    }

    public TriviaNumber getTriviaNumber(int position) {
        return triviaNumbers != null ? triviaNumbers.get(position) : null;
    }

    @Override
    public TriviaNumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int length = getItemCount();
        View view;
        if (checkEvenLength(length)) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trivia_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trivia_item_reverse, parent, false);
        }


        return new TriviaNumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TriviaNumberViewHolder holder, final int position) {

        // Gets a single item in the list from its position
        final TriviaNumber triviaNumber = triviaNumbers.get(position);

        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.number.setText(Integer.toString(triviaNumber.getNumber()));
        holder.triviaFact.setText(triviaNumber.getText());
    }

    public boolean checkEvenLength(int length) {
        return length % 2 == 0;
    }


    @Override
    public int getItemCount() {
        return triviaNumbers.size();
    }
}

