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

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);



        ArrayList<Word> family =new ArrayList<Word>();
        family.add(new Word("father","әpә"));
        family.add(new Word("mother","әṭa"));
        family.add(new Word("son","angsi"));
        family.add(new Word("daughter","tune"));
        family.add(new Word("older brother","taachi"));
        family.add(new Word("younger brother","chalitti"));
        family.add(new Word("older sister","teṭe"));
        family.add(new Word("younger sister","kolliti"));
        family.add(new Word("grandmother","ama"));
        family.add(new Word("grandfather","paapa"));


        /*This adapter recylcles views based on list and how many should be displayed on the page. object that provides views for a list view
                */
        WordAdapter adapter = new WordAdapter (this,family);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
