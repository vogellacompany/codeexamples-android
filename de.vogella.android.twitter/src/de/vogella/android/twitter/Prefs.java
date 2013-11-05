package de.vogella.android.twitter;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Prefs extends PreferenceActivity{

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    
    addPreferencesFromResource(R.xml.prefs);
  }

}