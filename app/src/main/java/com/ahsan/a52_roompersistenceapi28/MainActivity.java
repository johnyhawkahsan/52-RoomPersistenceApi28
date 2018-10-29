//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
//https://www.youtube.com/watch?v=Lr62GxuChaM
//https://github.com/delaroy/RoomPersistentLibrary

package com.ahsan.a52_roompersistenceapi28;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ahsan.a52_roompersistenceapi28.adapter.WordListAdapter;
import com.ahsan.a52_roompersistenceapi28.data.Word;
import com.ahsan.a52_roompersistenceapi28.data.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private WordViewModel mWordViewModel; //To retrieve existing data from this model to our Adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up RecyclerView
        //recyclerView is located in content_main.xml. content_main.xml is included in activity_main.xml
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter wordListAdapter = new WordListAdapter(this);
        recyclerView.setAdapter(wordListAdapter); //set recyclerView adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(MainActivity.this).get(WordViewModel.class);


        // To display the current contents of the database, add an observer that observes the LiveData in the ViewModel.
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // Whenever the data changes, the onChanged() callback is invoked, which calls the adapter's setWord() method to update the adapter's cached data and refresh the displayed list.
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                // Update the cached copy of the words in the adapter.
               wordListAdapter.setWords(words);
           }
        });

        //When Floating button is clicked, we are redirected to NewWordActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

                Log.d(TAG, "onClick: launching NewWordActivity");
            }
        });
    }

    //When "Save" button is clicked on "NewWordActivity" we are taken back here along with "Intent data".
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK )
        {
            Log.d(TAG, "onActivityResult: resultCode == RESULT_OK = " + RESULT_OK);

            Word word = new Word(
                                data.getStringExtra(NewWordActivity.EMPLOYEE_ID),
                                data.getStringExtra(NewWordActivity.FIRST_NAME),
                                data.getStringExtra(NewWordActivity.LAST_NAME),
                                data.getStringExtra(NewWordActivity.TITLE),
                                data.getStringExtra(NewWordActivity.DEPARTMENT));

            Log.d(TAG, "onActivityResult: new added word, ID = "
                    + word.getId() + ", Name = " + word.getFirst() + " " + word.getLast() + ", title = " + word.getTitle() + ", department = " + word.getDepartment());

            mWordViewModel.insert(word);//Insert this new word to our Database

        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
