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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //Contains english words for numbers activity

       ArrayList<Word> words =new ArrayList<Word>();
       words.add(new Word("One","lutti",R.drawable.number_one));
       words.add(new Word("Two","otiiko",R.drawable.number_two));
       words.add(new Word("Three","tolookosu",R.drawable.number_three));
       words.add(new Word("Four","oyyisa",R.drawable.number_four));
       words.add(new Word("Five","massokka",R.drawable.number_five));
       words.add(new Word("Six","temmokka",R.drawable.number_six));
       words.add(new Word("Seven","kenekaku",R.drawable.number_seven));
       words.add(new Word("Eight","kawinta",R.drawable.number_eight));
       words.add(new Word("Nine","wo'e",R.drawable.number_nine));
       words.add(new Word("Ten","na'aacha",R.drawable.number_ten));




    /*
    This adapter recylcles views based on list and how many should be displayed on the page. object that provides views for a list view
    */
        WordAdapter adapter = new WordAdapter (this,words);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
