/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static com.example.android.miwok.R.color.category_numbers;

public class NumbersActivity extends AppCompatActivity {

    private static Context context;
    private static Context mContext = getAppContext();
    private static MediaPlayer mMediaPlayer;


    // Create an Audio Manager instance
    final AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client3;

    // Get application context method
    private static Context getAppContext() {
        return NumbersActivity.context;
    }


    private MediaPlayer.OnCompletionListener mCompletionListener  = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
            am.abandonAudioFocus(afChangeListener);
            Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();

        }

    };

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mMediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        mMediaPlayer.stop();
                        releaseMediaPlayer();
                        am.abandonAudioFocus(afChangeListener);
                    } else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, because something else is also
                        // playing audio over you.
                        // i.e. for notifications or navigation directions
                        // Depending on your audio playback, you may prefer to
                        // pause playback here instead. You do you.
                        mMediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        if (mMediaPlayer != null){ mMediaPlayer.start();}
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Helper class to obtain the application context
        NumbersActivity.context = getApplicationContext();

        // Function to manage the different state from the Audio Manager

        //TODO: Add words here
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.mipmap.number_one,R.raw.number_one));
        words.add(new Word("two", "otiiko", R.mipmap.number_two,R.raw.number_two));
        words.add(new Word("three", "tolooksu", R.mipmap.number_three,R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.mipmap.number_four,R.raw.number_four));
        words.add(new Word("five", "mossoka", R.mipmap.number_five,R.raw.number_five));
        words.add(new Word("six", "temmoka", R.mipmap.number_six,R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.mipmap.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.mipmap.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.mipmap.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.mipmap.number_ten,R.raw.number_ten));

        /*
        Method to save memory by reusing the view
        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        */

        WordAdapter adapter = new WordAdapter(this, words, category_numbers);

        final ListView listView = (ListView) findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);


        // Play the word

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (mMediaPlayer != null) releaseMediaPlayer();
                // Request audio focus for playback
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(i).getmSoundResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });

        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer m) {

                    releaseMediaPlayer();
                    Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
                }
            });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Numbers Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.miwok/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Numbers Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.miwok/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
