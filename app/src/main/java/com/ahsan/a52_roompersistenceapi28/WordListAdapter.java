package com.ahsan.a52_roompersistenceapi28;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflator;
    private List<Word> mWords; // Caches copy of words

    //Constructor for WordListAdapter
    WordListAdapter(Context context){
        mInflator = LayoutInflater.from(context);
    }


    //WordViewHolder class
    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView name, designation, employeeDepartment;

        public WordViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            designation = (TextView) itemView.findViewById(R.id.designation);
            employeeDepartment = (TextView) itemView.findViewById(R.id.employeeDepartment);
        }
    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null){
            Word current = mWords.get(position); //get current item
            holder.name.setText(current.getFirst() + " " + current.getLast());
            holder.designation.setText(current.getTitle());
            holder.employeeDepartment.setText(current.getDepartment());
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No name");
            holder.designation.setText("No designation");
            holder.employeeDepartment.setText("No employeeDepartment");
        }

    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }


    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
         else return 0;
    }






}
