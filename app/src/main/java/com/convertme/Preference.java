package com.convertme;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dias on 3/5/2017.
 */
public class Preference {

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "YoutubiousPref";

    // All Shared Preferences Keys

    private static final String isTutorial = "1";

    // Constructor
    public Preference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void hideTutorial()
    {
        editor.putString(isTutorial, "0");
        editor.commit();
    }

    public boolean checkTutorial()
    {
        if(pref.getString(isTutorial, null)==null)
            return true;
        else
            return false;
    }
}
