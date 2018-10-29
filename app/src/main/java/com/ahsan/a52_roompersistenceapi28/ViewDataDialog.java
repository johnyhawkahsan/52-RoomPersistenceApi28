package com.ahsan.a52_roompersistenceapi28;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ahsan.a52_roompersistenceapi28.data.Word;

public class ViewDataDialog extends DialogFragment {

    private static final String TAG = "ViewDataDialog";

    //widgets
    private EditText mID, mName, mTitle, mDepartment;
    private TextView mSave, mDelete;

    private Word mWord;
    private static Context mContext;

/*
    //Singleton pattern constructor of class is called from Main Activity.
    public static ViewDataDialog newInstance(Word word) {

        //Launch ViewDataDialog and send intent data to ViewDataDialog
        ViewDataDialog viewDataDialog = new ViewDataDialog();

        Bundle args = new Bundle();
        args.putSerializable("data", word);
        viewDataDialog.setArguments(args);
        viewDataDialog.show(((MainActivity)mContext).getSupportFragmentManager() ,  "Data Dialog");

        Log.d(TAG, "newInstance: send data object in args form. args = " + args);
        return viewDataDialog;
    }
*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWord = (Word) getArguments().getSerializable("data");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view_data, container, false);
        mID =  view.findViewById(R.id.note_id);
        mName =  view.findViewById(R.id.note_name);
        mTitle =  view.findViewById(R.id.note_title);
        mDepartment =  view.findViewById(R.id.note_department);

        mID.setText(mWord.getId());
        mName.setText(mWord.getFirst() + " " + mWord.getLast());
        mTitle.setText(mWord.getTitle());
        mDepartment.setText(mWord.getDepartment());

        mSave =  view.findViewById(R.id.save);
        mDelete = view.findViewById(R.id.delete);

        getDialog().setTitle("View Note");


        return view;

    }
}
