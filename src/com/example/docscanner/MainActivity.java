package com.example.myfirstapp;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private final static int CAP_IMG_CODE = 100;
	
	//Define File path for picture saving
	String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/name.png";
	File picFile = new File(imageFilePath);
	
	//take picture when button pressed
	public void takePicture(View view){
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(intent, CAP_IMG_CODE);    	
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	if(requestCode == CAP_IMG_CODE){
	    		if (resultCode == RESULT_OK){
	    				    			    		
	    			Bitmap photo = (Bitmap) data.getExtras().get("data");
	        		try {
	        			FileOutputStream out = new FileOutputStream(picFile);
	        			photo.compress(Bitmap.CompressFormat.JPEG, 70, out);
	        			Toast.makeText(this, "Picture saved!", Toast.LENGTH_LONG).show();
        			} 
        				catch (Exception e) {
        				e.printStackTrace();
	        		}     			
	    		} 
    	}
    }
    
    //onCreating the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
    }
    
    //Inflation of menu after creating it
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.f, menu);
        return true;
    }
    
    
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public void sendMessage(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }            
}
