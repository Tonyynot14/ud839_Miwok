package com.example.android.miwok;

import android.app.Activity;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


//custom adapter for displaying word custom class
public class WordAdapter extends ArrayAdapter<Word>
{
         // needs activity and list of words as variables to take in.
        public WordAdapter(Activity context, ArrayList<Word> words)
        {
            super(context,0,words);
        }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word word = getItem(position);

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        //Text view with miwok words contained in it
        TextView miwok = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        //Text view with miwok translated to english
        TextView english = (TextView) listItemView.findViewById(R.id.default_text_view);

        // Image view with image to correspond with what the word means.
        ImageView resource =(ImageView) listItemView.findViewById(R.id.thumbNail);


        /*
        Sets sets of both textviews to miwok and english words
         */
        miwok.setText(word.getMiwok());
        english.setText(word.getEnglish());

        //If word has image then set it to the correct result and make it visible to see
        if (word.hasImage())
        {
            resource.setImageResource(word.getResource());
            resource.setVisibility(View.VISIBLE);

        }
        // If the word does not have a image make the imageview disappear
        else{
           resource.setVisibility(View.GONE);
        }







        return listItemView;
    }
}
