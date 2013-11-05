package de.vogella.android.twitter;


import winterwell.jtwitter.Twitter;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyTwitter extends Activity implements OnClickListener{

    static final String TAG = "MyTwitter";
    
    Twitter twitter;
    SharedPreferences prefs;
    
    Button buttonUpdate;
    Button buttonPrefs;
    EditText textStatus;
    
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // find views by id
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        textStatus = (EditText) findViewById(R.id.textStatus);
        
        // Add listener
        buttonUpdate.setOnClickListener(this);

        //Initialize twitter
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("username", "n/a");
        String password = prefs.getString("password", "n/a");
        if (username != null && password != null){
          twitter = new Twitter(username, password);
        }
        Log.e(TAG, "Username " + username);
        
        
    }

    @Override
    public void onClick(View src) {
      
      String status = textStatus.getText().toString();
      
      Log.d(TAG, "Clicked on "+ status);
      
      // Toast
      Toast.makeText(this, textStatus.getText(), Toast.LENGTH_LONG).show();
      
      // set twitter status 
      twitter.setStatus(status);
      
      //reset status string
      textStatus.setText("");
    }
   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return true;
    }
    
    // Called when menu item is selected //
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
      
      switch(item.getItemId()){
      
      case R.id.menuPrefs:
        // Launch Prefs activity
        Intent i = new Intent(MyTwitter.this, Prefs.class);
        startActivity(i);
        Log.d(TAG, "MenuPrefs starting Prefs");
        Toast.makeText(MyTwitter.this, textStatus.getText(), Toast.LENGTH_LONG).show();
        break;
        
      }    
      return true;
    }
}