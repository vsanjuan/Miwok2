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

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private static MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //TODO: Add words here
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("father","apa",R.mipmap.family_father,R.raw.family_father));
        words.add(new Word("mother","ata",R.mipmap.family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",R.mipmap.family_son,R.raw.family_son));
        words.add(new Word("daughter","tune",R.mipmap.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",R.mipmap.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.mipmap.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","tete",R.mipmap.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.mipmap.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",R.mipmap.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",R.mipmap.family_grandfather,R.raw.family_grandfather));


        /*
        Method to save memory by reusing the view
        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        */

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);


        assert listView != null;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mMediaPlayer= MediaPlayer.create(FamilyActivity.this, words.get(i).getmSoundResourceId());
                mMediaPlayer.start();

            }
        });

    }
}
