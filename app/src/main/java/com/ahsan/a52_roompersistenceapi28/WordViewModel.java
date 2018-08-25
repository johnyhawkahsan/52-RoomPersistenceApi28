package com.ahsan.a52_roompersistenceapi28;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */
public class WordViewModel extends AndroidViewModel{

    //Add a private member variable to hold a reference to the repository.
    private WordRepository mRepository;

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Word>> mAllWords;


    //Add a constructor that gets a reference to the repository and gets the list of words from the repository.
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //Getter method for getting all words.
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    //Insert method
    public void insert(Word word){
        mRepository.insert(word); //Inside WordRepository, data is inserted in AsyncTask
    }

}
