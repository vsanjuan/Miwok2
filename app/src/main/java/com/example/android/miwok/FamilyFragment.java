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

import static com.example.android.miwok.R.color.category_family;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager am;

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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            am.abandonAudioFocus(afChangeListener);
        }

    }


    //Set up of the FocusChange method to handle different scenarios when AudioFocus change
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

    // Method to execute when the recording is finished

    private MediaPlayer.OnCompletionListener mCompletionListener  = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
            am.abandonAudioFocus(afChangeListener);
            Toast.makeText(getActivity().getApplicationContext() , "Hola", Toast.LENGTH_SHORT).show();

        }

    };


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;*/
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /**
         */

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("father", "apa", R.mipmap.family_father, R.raw.family_father));
        words.add(new Word("mother", "ata", R.mipmap.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.mipmap.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.mipmap.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.mipmap.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.mipmap.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "tete", R.mipmap.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.mipmap.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.mipmap.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.mipmap.family_grandfather, R.raw.family_grandfather));

        /*
        Method to save memory by reusing the view
        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        */

        WordAdapter adapter = new WordAdapter(getActivity(), words, category_family);

        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);

        // Create Audio Manager variable

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Play the word

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (mMediaPlayer != null) releaseMediaPlayer();
                // Request audio focus for playback
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
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
}
