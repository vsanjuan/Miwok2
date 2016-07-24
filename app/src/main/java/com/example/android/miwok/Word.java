package com.example.android.miwok;

/**
 * Created by Salvador on 21/07/2016.
 */
public class Word {


    // State variables
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId;
    private boolean mImage;

    // Constructor
    public  Word ( String defaultTranslation, String miwokTranslation ) {

        setMiwokTranslation(miwokTranslation);
        setDefaultTranslation(defaultTranslation);
        mImage = false;

    }

    // Constructor
    public  Word ( String defaultTranslation, String miwokTranslation, int imageResourceId ) {

        setMiwokTranslation(miwokTranslation);
        setDefaultTranslation(defaultTranslation);
        mImageResourceId = imageResourceId;
        mImage = true;

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

    public int getmImageResourceId() {

        return mImageResourceId;

    }

    public boolean ismImage() {

        return mImage;
    }

}
