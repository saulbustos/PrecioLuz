package com.apps.android.starblank;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
 
public class Utility {
    
    static int hora=0;
    static boolean firstTime=true;
    static String json_antiguo="";
    
     private int getHour(){
               Date date = new Date();   // given date
                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                calendar.setTime(date);   // assigns calendar to given date 
                return calendar.get(Calendar.HOUR_OF_DAY);           
                  

     }
     
     private int getSecond(){
               Date date = new Date();  
                Calendar calendar = GregorianCalendar.getInstance(); 
                calendar.setTime(date);  
                return calendar.get(Calendar.SECOND);           
                  

     }     
    
  private String getData(){
                    URLConnection feedUrl;
                    String json="";
                    InputStream in;
                    try{
                       //feedUrl= new URL("http://www.starblank.com/luz/data.txt").openConnection();
                       feedUrl= new URL("Your server path to data.txt!").openConnection();

                      in = feedUrl.getInputStream();
                      json = Utility.convertStreamToString(in);
                      return json;
                    }catch(Exception e){
                        return "";
                    }
                    
  }
  
  public String[] parsea(){
       
        int horaActual=getHour();
        String json = null;
        
        if (horaActual!=hora || firstTime){
            try{
                hora=horaActual;
                json=getData();
                firstTime=false;
            }catch(Exception e){
                firstTime=true;
                json="";
            }
        }
        
        String[] salida=null;
        
                //Y ahora parseamos los datos. Esto se hace cada 30seg.
                    if (json!=null && !json.equals("") && json.contains("--")){
                        try {
                            salida=parsedata(json,horaActual);
                            json_antiguo=json;
                            return salida;
                       }catch(Exception e){
                            try{
                                salida=parsedata(json_antiguo,horaActual);
                                return salida;
                            }catch(Exception f){
                                return null;
                            }
                       }
                    }else{
                        try{
                            salida=parsedata(json_antiguo,horaActual);
                            return salida;
                        }catch(Exception f){
                            return null;
                        }
                    }
          
     }
  
     private String[] parsedata(String json,int horaActual){
         float media=0,max=0,precioActual=0;
         String[] salida=new String[5];
         String[] tmp=json.split("--");
         float[] precios=new float[tmp.length];


                    for (int i=0;i<tmp.length;i++){
                        precios[i]=Float.parseFloat(tmp[i].replace(",", "."));
                        //precios[i]=(float)Math.round(precios[i] * 10) / 10;
                        media=media+precios[i];
                        //if (precios[i]>max) max=(float)Math.round(precios[i] * 10) / 10;
                        if (precios[i]>max) max=(float)precios[i];
                    }
                    media=media/24;
                    media=(float)Math.round(media * 1000) / 1000;

                    precioActual=precios[horaActual];


                    if (precioActual>media+0.005){
                        salida[0]="red";
                    }else if (precioActual<media-0.005){
                        salida[0]="green";
                    }else{
                        salida[0]="yellow";
                    }


                    salida[1]=String.valueOf(precioActual);
                    salida[2]=String.valueOf(max);
                    salida[3]=String.valueOf(media);
                    salida[4]=String.valueOf(hora);

                    return salida;
     }
  
  public int getColor(String c){
      
      if (c.equalsIgnoreCase("red")){
          return R.drawable.rounded_corners_red;
      }else if (c.equalsIgnoreCase("green")){
          return R.drawable.rounded_corners_green;
      }else{
          return R.drawable.rounded_corners_yellow;
      }
      
  }
  
  private static String convertStreamToString(InputStream is) {
              try {
                  BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                  StringBuilder sb = new StringBuilder();
                  String line = null;
                  while ((line = reader.readLine()) != null) {
                      sb.append(line + "\n");
                    }
                    is.close();
                    return sb.toString();
                  } catch (Exception e) {
                    
                    return "";
                  }
            }
}
