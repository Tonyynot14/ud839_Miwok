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
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    //Create media player variable to be assigned and reused later in program
    MediaPlayer mMediaPlayer;

    private AudioManager audioManager;

    /**
     * Listener is triggeed whenever audio focus changes, gain or lose it
     **/
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT|| focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
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
        audioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        //Array list has to be final to work with setOnItemClickListener
        //Contains english miwok words and sound file of miwok words
        final ArrayList<Word> phrases =new ArrayList<Word>();
        phrases.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        phrases.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        phrases.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        phrases.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));





    /*
    This adapter recylcles views based on list and how many should be displayed on the page. object that provides views for a list view
    */
        WordAdapter adapter = new WordAdapter (this, phrases,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        // inline creation of anonymous class within program for setting the audio based on the position in the listview that is clicked.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = phrases.get(position);
                // Release media player when a user clicks on another audio file
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Release media player when a user clicks on another audio file, have audio focus now
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudio());
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
