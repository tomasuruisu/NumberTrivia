package com.thomas.numbertrivia;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TriviaNumberViewHolder extends RecyclerView.ViewHolder {

    public View view;
    public TextView number;
    public TextView triviaFact;

    public TriviaNumberViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        number = itemView.findViewById(R.id.number);
        triviaFact = itemView.findViewById(R.id.trivia_fact);
    }
}
