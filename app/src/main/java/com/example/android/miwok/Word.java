package com.example.android.miwok;

/*
    English and miwok translation
 */
public class Word {
    private String english;
    private String miwok;

    // constructor of class
    //@params english and miwok words
    Word(String english, String miwok)
    {
        this.english=english;
        this.miwok=miwok;
    }
        //Get english translation
    public String getEnglish()
    {
     return english;
    }
    // Get miwok translation
    public String getMiwok()
    {
        return miwok;
    }
}
