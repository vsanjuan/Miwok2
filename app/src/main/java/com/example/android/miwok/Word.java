package com.example.android.miwok;

/**
 * Created by Salvador on 21/07/2016.
 */
public class Word {


    // State variables
    private String miwok_translation;
    private String default_translation;

    // Constructor
    public  Word (String miwok, String default_t ) {

        setMiwok_translation(miwok);
        setDefault_translation(default_t);

    }

    // Setter methods
    public void setMiwok_translation(String miwok){

        miwok_translation = miwok;

    }

    public void setDefault_translation(String default_t){

        default_translation = default_t;
    }

    // Getter method

    public String getMiwok_translation(){

        return miwok_translation;
    }

    public String getDefault_translation() {

        return default_translation;
    }

}
