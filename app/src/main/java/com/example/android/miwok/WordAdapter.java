package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by salva on 23/07/2016.
 */
public class WordAdapter extends ArrayAdapter<Word>{

    private int mBackgroundColor;

    private String TAG = "Word Adapter: ";

    public WordAdapter(Context context, ArrayList<Word> Words, int BackgroundColor ){

        super(context, 0, Words);

        mBackgroundColor = BackgroundColor;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ){


        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_translation);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
//        miwokTextView.setBackgroundColor(this.mBackgroundColor);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

   /*     Log.i(TAG, "Background parameter: " + Integer.toString(this.mBackgroundColor));
        Log.i(TAG, "Background value. " + miwokTextView.getBackground().toString());*/

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_translation);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());
//        defaultTextView.setBackgroundColor(this.mBackgroundColor);

        // Find the ImageView in the list_item.xml laoyout with the ID version_number
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.word_image);

        // Check if there are images associated to the word
        if (currentWord.ismImage()) {


            // Get version image from the current Word object and
            // set this image on the image ImageView
            imageView.setImageResource(currentWord.getmImageResourceId());


        } else {

            // Hide the ImageView
            imageView.setVisibility(View.GONE);


        }

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // View wordBox = listItemView.findViewById(R.id.word_box);
        View blankView = listItemView.findViewById(R.id.blank_view);
        View playArrow = listItemView.findViewById(R.id.play_arrow);
        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor((getContext()),mBackgroundColor);
        // Set teh background color of the text container View
//      // textContainer.setBackgroundColor(color);
        // imageView.setBackgroundColor(color);

        textContainer.setBackgroundColor(color);
        // wordBox.setBackgroundColor(color);
        blankView.setBackgroundColor(color);
        playArrow.setBackgroundColor(color);

        Log.v(TAG, "Background parameter: " + Integer.toString(this.mBackgroundColor));
        Log.v(TAG, "Color value: " + Integer.toString(color));


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
