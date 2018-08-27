package com.ahsan.a52_roompersistenceapi28;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private static final String TAG = "WordListAdapter";
    private final LayoutInflater mInflator;
    private List<Word> mWords; // Caches copy of words
    private int mSelectedItemIndex;
    Context mContext;

    //Constructor for WordListAdapter
    WordListAdapter(Context context){
        mContext = context;
        mInflator = LayoutInflater.from(context);
    }


    //WordViewHolder class
    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView employeeId, name, designation, employeeDepartment;

        public WordViewHolder(View itemView) {
            super(itemView);
            employeeId = itemView.findViewById(R.id.employee_id);
            name = itemView.findViewById(R.id.name);
            designation = itemView.findViewById(R.id.designation);
            employeeDepartment = itemView.findViewById(R.id.employeeDepartment);

            //Setting on click method for our list item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItemIndex = getAdapterPosition();
                    Word word = mWords.get(mSelectedItemIndex);
                    Log.d(TAG, "onClick: Selected item name = " + word.getFirst());

                    //Launch ViewDataDialog and send intent data to ViewDataDialog
                    ViewDataDialog viewDataDialog = new ViewDataDialog();
                    Bundle args = new Bundle();
                    args.putSerializable("data", word);
                    viewDataDialog.setArguments(args);
                    viewDataDialog.show(((MainActivity)mContext).getSupportFragmentManager() ,  "Data Dialog");
                }
            });
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
            holder.employeeId.setText(current.getId());
            holder.name.setText(current.getFirst() + " " + current.getLast());
            holder.designation.setText(current.getTitle());
            holder.employeeDepartment.setText(current.getDepartment());
        } else {
            // Covers the case of data not being ready yet.
            holder.employeeId.setText("No id");
            holder.name.setText("No name");
            holder.designation.setText("No designation");
            holder.employeeDepartment.setText("No employeeDepartment");
        }

    }

    //This method is called when new data is added, so it updates our database.
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
