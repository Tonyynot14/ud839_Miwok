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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        // Create objects to find each textview on the mainactivity
        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView colors= (TextView) findViewById(R.id.colors);
        TextView familyMembers = (TextView) findViewById(R.id.family);
        TextView phrases= (TextView) findViewById(R.id.phrases);


        // set up on clicklistener for each textview. This listens for a click creates a intent starts next activity with intent
            numbers.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent numbersIntent = new Intent(MainActivity.this,NumbersActivity.class);

                    startActivity(numbersIntent);
                }
            });

            familyMembers.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent familyIntent = new Intent (MainActivity.this,FamilyActivity.class);

                    startActivity(familyIntent);
                }
            });

            colors.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent colorsIntent = new Intent(MainActivity.this,ColorsActivity.class);

                    startActivity(colorsIntent);
                }
            });


            phrases.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phrasesIntent = new Intent(MainActivity.this,PhrasesActivity.class);

                    startActivity(phrasesIntent);
                }
            });

        }




    }










/*
old on click methods
    public void openNumbersList(View view)
    {
        Intent numbers = new Intent(this,NumbersActivity.class);
        startActivity(numbers);
    }
    public void openFamilyMembersList(View view)
    {
        Intent numbers = new Intent(this,FamilyActivity.class);
        startActivity(numbers);
    }
    public void openColorsList(View view)
    {
        Intent numbers = new Intent(this,ColorsActivity.class);
        startActivity(numbers);
    }
    public void openPhrasesList(View view)
    {
        Intent numbers = new Intent(this,PhrasesActivity.class);
        startActivity(numbers);
    }*/
