/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apps.android.starblank;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class Web extends Activity
{
    
    TextView tv;
    WebView webview;
    String url="http://www.trastosviejos.com/luz/listado.php";
    String summary = "<html><body><div style='font-family:verdana; "
                    + "text-align:center; color:black;'><br>Cargando "
                    + "datos, por favor espera...</div></body></html>";
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        webview =(WebView)findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient());          
        webview .getSettings().setJavaScriptEnabled(true);
        webview .getSettings().setDomStorageEnabled(true); 

        webview.loadData(summary, "text/html", null);
        webview.loadUrl(url); 
    
        Button newButton = (Button)findViewById(R.id.new_button);
        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webview.loadData(summary, "text/html", null);
                webview.loadUrl(url);
            }});
    }
 
    
    @Override
    public void onBackPressed() {
     Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}