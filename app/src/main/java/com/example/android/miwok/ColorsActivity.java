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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private static MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //TODO: Add words here
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red","wetetti",R.mipmap.color_red,R.raw.color_red));
        words.add(new Word("green","chokokki",R.mipmap.color_green,R.raw.color_green));
        words.add(new Word("brown","takaakki",R.mipmap.color_brown,R.raw.color_brown));
        words.add(new Word("gray","topoppi",R.mipmap.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululli",R.mipmap.color_black,R.raw.color_black));
        words.add(new Word("white","kelelli",R.mipmap.color_white,R.raw.color_white));
        words.add(new Word("dusty yellow","topiisa",R.mipmap.color_dusty_yellow,R.raw.color_brown));
        words.add(new Word("mustard yellow","chiwiita",R.mipmap.color_mustard_yellow,R.raw.color_dusty_yellow));

        /*
        Method to save memory by reusing the view
        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        */

        WordAdapter adapter = new WordAdapter(this,words, R.color.category_colors);


        ListView listView = (ListView) findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mMediaPlayer= MediaPlayer.create(ColorsActivity.this, words.get(i).getmSoundResourceId());
                mMediaPlayer.start();

            }
        });


    }
}
