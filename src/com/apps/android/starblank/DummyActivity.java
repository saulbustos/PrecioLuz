package com.apps.android.starblank;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class DummyActivity extends Activity
{
    
    TextView tv;
    WebView webview;
    String url="http://www.trastosviejos.com/luz/listado.php";
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        try{
            //si es la primera vez, mostramos el dummy
            if (load().equals("1")){
                    inicia_app();
            }else{
                setContentView(R.layout.layout2);
                save("1");
                Button newButton = (Button)findViewById(R.id.new_button);
                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        inicia_app();
                    }});
            }
        }catch(Exception e){inicia_app();}
    }
    
      
    private void save(String valor){
        try{
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putString("first", valor);
            editor.commit();
        }catch(Exception e){}
    }
    
    private String load(){
        try{
            SharedPreferences prefs = getPreferences(MODE_PRIVATE); 
            String restoredText = prefs.getString("first", null);
            if (restoredText != null) 
            {
                return restoredText;
            }else{
                return "0";
            }
        }catch(Exception e){return "0";}
    }
    
    private void inicia_app(){
        try{
            Intent intent = new Intent(this, Web.class);
            startActivity(intent);
        }catch(Exception e){}
            
    }
}