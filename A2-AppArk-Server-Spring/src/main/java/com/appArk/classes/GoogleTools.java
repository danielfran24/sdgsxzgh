/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appArk.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Alvaro
 */

public class GoogleTools {
    private final String DIRECCION = "https://oauth2.googleapis.com/tokeninfo?id_token=";
    private final String CLIENT_ID="689413569595-19p08rkfoqssceef8jkph4g44rs0kh3l.apps.googleusercontent.com";
    private final String JSON_GOOGLE_SUB="sub";
    private final String JSON_GOOGLE_EMAIL="email";
    private final String JSON_GOOGLE_AUD="aud";
    
    
    public String registroGoogle(String idGoogleToken){
        
        /*
        Este procedimiento se utilizará para comprobar si el id_token, está verificado
        En caso de que se produzca alguna exceción este método devolvera null;
        En caso de que no se haya recibido como respuesta un HTTP_OK (200) o 
        el aud del json que devuelve, no es igual que el Client_ID devolverá un string vacío
        En el caso de que todo haya ido correctamente devolverá el idGoogle 
        */
        try {
            String idGoogle="";
            URL url = new URL(DIRECCION+idGoogleToken);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            
            int responseCode= con.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_OK){
                //Leer el body de la respuesta 
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine = "";
                StringBuffer response = new StringBuffer();
                while((inputLine=in.readLine())!=null){
                    response.append(inputLine);
                }
                in.close();
            /*---------------------------------------------------------------------------------------------*/
                
                JSONObject myResponse = new JSONObject (response.toString());
                if(myResponse.getString(this.JSON_GOOGLE_AUD).equals(this.CLIENT_ID)){
                    idGoogle = myResponse.getString(this.JSON_GOOGLE_SUB);
                    
                }
                
                
            }
            
            
            con.disconnect();
            return idGoogle;
            
                   
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(GoogleTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoogleTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GoogleTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       return null;
    }
    
    
    
    public GoogleTools() {
    }
    
}