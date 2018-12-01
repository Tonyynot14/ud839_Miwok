package com.example.android.miwok;

import android.app.Activity;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        TextView miwok = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        TextView english = (TextView) listItemView.findViewById(R.id.default_text_view);

        miwok.setText(word.getMiwok());
        english.setText(word.getEnglish());






        return listItemView;
    }
}
