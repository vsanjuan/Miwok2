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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //TODO: Add words here
        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red","wetetti"));
        words.add(new Word("green","chokokki"));
        words.add(new Word("brown","takaakki"));
        words.add(new Word("gray","topoppi"));
        words.add(new Word("black","kululli"));
        words.add(new Word("white","kelelli"));
        words.add(new Word("dusty yellow","topiisa"));
        words.add(new Word("mustard yellow","chiwiita"));

        /*
        Method to save memory by reusing the view
        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        */

        WordAdapter adapter = new WordAdapter(this,words);

        ListView listView = (ListView) findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);


    }
}
