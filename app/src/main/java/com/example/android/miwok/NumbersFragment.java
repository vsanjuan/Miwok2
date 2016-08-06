package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.miwok.R.color.category_numbers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    /**
     * Clean up the media player by releasing its resources.
     */



    //Set up of the FocusChange method to handle different scenarios when AudioFocus change
    private AudioManager.OnAudioFocusChangeListener afChangeListener =
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
                        mAudioManager.abandonAudioFocus(afChangeListener);
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

    // Method to execute when the recording is finished

    private MediaPlayer.OnCompletionListener mCompletionListener  = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
            mAudioManager.abandonAudioFocus(afChangeListener);
            Toast.makeText(getActivity().getApplicationContext() , "Hola", Toast.LENGTH_SHORT).show();

        }

    };


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;*/
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /** TODO: Insert all the code from the Number's Activity onCreate() method
         * after teh setContentView method call
         */


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

        WordAdapter adapter = new WordAdapter(getActivity(), words, category_numbers);

        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);

        // Create Audio Manager variable

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Play the word

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (mMediaPlayer != null) releaseMediaPlayer();
                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Sets the media play with the appropiate sound
                    mMediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), words.get(i).getmSoundResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources we will
        // not be play any more sounds.
        releaseMediaPlayer();
    }

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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(afChangeListener);
        }

    }


}
