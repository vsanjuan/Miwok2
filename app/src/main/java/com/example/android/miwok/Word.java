package com.example.android.miwok;

/**
 * Created by Salvador on 21/07/2016.
 */
public class Word {


    // State variables
    private String mMiwokTranslation;
    private String mDefaultTranslation;

    // Constructor
    public  Word ( String defaultTranslation, String miwokTranslation ) {

        setMiwokTranslation(miwokTranslation);
        setDefaultTranslation(defaultTranslation);

    }

    // Setter methods
    public void setMiwokTranslation(String miwokTranslation){

        mMiwokTranslation = miwokTranslation;

    }

    public void setDefaultTranslation(String defaultTranslation){

        mDefaultTranslation = defaultTranslation;
    }

    // Getter method

    public String getmMiwokTranslation(){

        return mMiwokTranslation;
    }

    public String getmDefaultTranslation() {

        return mDefaultTranslation;
    }

}
