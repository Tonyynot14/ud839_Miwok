package com.example.android.miwok;

/*
    English and miwok translation
 */
public class Word {
    private String english;
    private String miwok;
    private int resource = NO_IMAGE_PROVIDED;
    /* Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED=-1;

    // constructor of class
    //@params english and miwok words
    Word(String english, String miwok)
    {
        this.english=english;
        this.miwok=miwok;

    }

    /*@param String english word, miwok word, and image of word 
        */
    Word(String english, String miwok, int resource)
    {
        this.english=english;
        this.miwok=miwok;
        this.resource=resource;
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

    // returns int related to resource in app
    public int getResource(){return resource;}

    public boolean hasImage(){
        return resource!=NO_IMAGE_PROVIDED;
    }
}

