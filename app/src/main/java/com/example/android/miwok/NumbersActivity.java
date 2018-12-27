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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    /** Handles playback of sound files, is reset for every sound file **/
    MediaPlayer mMediaPlayer;
    /**
     * Handles audio focus, request focus
     */
    private AudioManager audioManager;

    /**
     * Listener is triggeed whenever audio focus changes, gain or lose it
     **/
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT|| focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                //Pause and restart media player point
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                // AUDIO_GAIN means we regain focus and can resume playback
                mMediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener= new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Contains english words for numbers activity


      final  ArrayList<Word> words =new ArrayList<Word>();
       words.add(new Word("One","lutti",R.drawable.number_one,R.raw.number_one));
       words.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
       words.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
       words.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
       words.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
       words.add(new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
       words.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
       words.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
       words.add(new Word("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
       words.add(new Word("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));




    /*
    This adapter recylcles views based on list and how many should be displayed on the page. object that provides views for a list view
    */
        WordAdapter adapter = new WordAdapter (this,words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Release media player when a user clicks on another audio file, have audio focus now
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudio());
                    mMediaPlayer.start();
                    // Listener to release  media player when done
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });



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
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}


