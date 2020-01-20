/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appArk.classes;

import com.appArk.jersey.ParkingJerseyClient;
import com.appArk.jersey.VehicleJerseyClient;
import com.appArk.entity.Parkings;
import com.appArk.entity.Reviews;
import com.appArk.entity.Vehicles;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.URL;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;
import com.appArk.service.ParkingsFacadeREST;
import com.appArk.service.VehiclesFacadeREST;

/**
 *
 * @author kirek
 */
public class ParkingTools {
    
    private final String UBICACION_PARKINGS_COCHE ="https://datosabiertos.malaga.eu/recursos/aparcamientos/ubappublicosmun/da_aparcamientosPublicosMunicipales-25830.geojson";
    private final String OCUPACION_PARKINGS_COCHE = "https://datosabiertos.malaga.eu/recursos/aparcamientos/ocupappublicosmun/ocupappublicosmunfiware.json";
    private final String APARCAMIENTOS_PMR ="https://datosabiertos.malaga.eu/recursos/transporte/trafico/da_aparcamientosMovilidadReducida-25830.geojson";
    private final String UBICACION_PARKINGS_MOTOS="https://datosabiertos.malaga.eu/recursos/transporte/trafico/da_aparcamientosMoto-4326.geojson"
;
    private final String UBICACION_PARKINGS_BICI="https://datosabiertos.malaga.eu/recursos/transporte/trafico/da_aparcamientosBici-4326.geojson";
    private final String JSON_PUBLICPARKINGS_ID="ogc_fid";
    private final String JSON_PUBLICPARKINGS_NAME="name";
    private final String LATITUD = "lat";
    private final String LONGITUD = "lon";
    private final String FEATURES = "features";
    private final String PROPERTIES = "properties";
    private final String COORDINATES = "geometry";
    private final String PUNTOS = "coordinates";
    private final String CERO ="0";
    private final String UNO ="1";
    private final String IDPARKING = "ogc_fid";
    private final String CALLE_PARKING ="address";
    private final String DESCRIPCION = "description";
    private final String NOMBRE_PARKING="name";
    private final String ERROR_INSERCCION="ERROR AL INSERTAR PARKING EN LISTA";
    
    private final String VEHICULO_COCHE="car";
    private final String VEHICULO_MOTO="motorbike";
    private final String VEHICULO_BICI="bicicle";
    
    private VehicleJerseyClient vehiclesFacade;
    private ParkingJerseyClient parkingsFacade;

    public ParkingTools() {
        parkingsFacade= new ParkingJerseyClient();
        vehiclesFacade= new VehicleJerseyClient();
    }
    
    
    public String lecturaJson(String url){
        try{
            doTrustToCertificates();
            URL enlace = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection)enlace.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            
            int responseCode = connection.getResponseCode();
            
            if(responseCode == HttpsURLConnection.HTTP_OK){
                //Leer el body de la respuesta 
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine = "";
                StringBuffer response = new StringBuffer();
                while((inputLine=in.readLine())!=null){
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
        }
        } catch (IOException ex) {
            Logger.getLogger(ParkingTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ParkingTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    
   public List<Parkings> parkingList(){
          
        try {
            List<Parkings> parkingsBD=new ArrayList<>();
            List<Vehicles> tiposVehiculos=new ArrayList<>();
            
            Response response=this.parkingsFacade.findAll_XML(Response.class);
            if (response.getStatus() == 200) {
               GenericType<List<Parkings>> genericType = new GenericType<List<Parkings>>(){};
                parkingsBD =(response.readEntity(genericType));
               
            }
            response=this.vehiclesFacade.findAll_XML(Response.class);
            if (response.getStatus() == 200) {
               GenericType<List<Vehicles>> genericType = new GenericType<List<Vehicles>>(){};
                tiposVehiculos =(response.readEntity(genericType));
               
            }
           
          
            List<Parkings> conjuntoParkings = new ArrayList<>();
            
            for(Vehicles vehiculo : tiposVehiculos){
                 
                iteracionParkings(parkingsBD, conjuntoParkings, vehiculo);
         
            }
            
           
           
            return conjuntoParkings;

            } catch (NumberFormatException ex) {
            Logger.getLogger(ParkingTools.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("");
            } catch (JSONException ex) { 
            Logger.getLogger(ParkingTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    } 

   private List<JSONObject> lecturaParkingPMR() throws JSONException {
        String json = lecturaJson(APARCAMIENTOS_PMR);
         
        if(json==null){
            throw new JSONException("Error al leer json");
        } else {
            JSONObject myResponse = new JSONObject (json);
            JSONArray array = myResponse.getJSONArray(this.FEATURES); 
            List<JSONObject> plazasPMR = new ArrayList<>();
            for(int i=0;i < array.length();i++){
                plazasPMR.add(array.getJSONObject(i));
            }
            return plazasPMR;
        }
    }
   
   
    private void iteracionParkings(List<Parkings> parkingsBD, List<Parkings> conjuntoParkings, Vehicles vehiculoAdmitido) throws NumberFormatException, JSONException {
      
         String json="";
         List<JSONObject> plazasPMR = null;
        if(vehiculoAdmitido.getType().equalsIgnoreCase(this.VEHICULO_COCHE)){
           json = lecturaJson(UBICACION_PARKINGS_COCHE);
           plazasPMR = lecturaParkingPMR();
        }else if (vehiculoAdmitido.getType().equalsIgnoreCase(this.VEHICULO_MOTO)){
            json = lecturaJson(UBICACION_PARKINGS_MOTOS);
        }else{
            json = lecturaJson(UBICACION_PARKINGS_BICI);
        }
         
        if(json==null){
            throw new JSONException("Error al leer json");
        } else {
            
            JSONObject myResponse = new JSONObject (json);
            JSONArray array = myResponse.getJSONArray(this.FEATURES); 
            List<JSONObject> parkings = new ArrayList<>();
            for(int i=0;i < array.length();i++){
                parkings.add(array.getJSONObject(i));
            }
                   
            
            
            for(JSONObject parking : parkings){
                
                JSONObject datosParking = parking.getJSONObject(this.PROPERTIES);
                JSONObject coordenadas = parking.getJSONObject(this.COORDINATES);
                addParking(coordenadas,datosParking, parkingsBD, conjuntoParkings,plazasPMR,vehiculoAdmitido);
                
            }
            
            
        }
    }

    private void addParking(JSONObject coordenadas,JSONObject datosParking, List<Parkings> parkingsBD, List<Parkings> conjuntoParkings, List<JSONObject> plazasPMR, Vehicles vehiculo) throws NumberFormatException, JSONException {
        int ogc_fid = datosParking.getInt(this.IDPARKING);
        Parkings park = parkingEnBD(ogc_fid,parkingsBD);
        if(park==null){
            
            park = new Parkings();
            park.setId(ogc_fid);
            park.setReviewsCollection(new ArrayList<>());
            //List<Vehicles> tiposVehiculos = new ArrayList<>();
            //tiposVehiculos.add(vehiculo);
            park.setTipoVehiculoParking(vehiculo);
            //parkingsFacade.create_JSON(park);
            park.setAccesibility(false);
            ponerParametros(coordenadas, park, datosParking, vehiculo, plazasPMR, conjuntoParkings);
            
        }else{
            //Parkings parkingEnLista = parkingEnLista(park,conjuntoParkings);
            
           // if(parkingEnLista==null){
                ponerParametros(coordenadas, park, datosParking, vehiculo, plazasPMR, conjuntoParkings);
            //}else{

                  // parkingEnLista.getVehiclesCollection().add(vehiculo);  
            
           // }
            
            
            
            
           
        }
        
        
        
    }

    private void ponerParametros(JSONObject coordenadas, Parkings park, JSONObject datosParking, Vehicles vehiculo, List<JSONObject> plazasPMR, List<Parkings> conjuntoParkings) throws JSONException, NumberFormatException {
    
        
        
            
        park.setName(datosParking.getString(this.NOMBRE_PARKING));
        park.setScore(calcularPuntuacion(park.getReviewsCollection()));
        
        if(vehiculo.getType().equalsIgnoreCase(this.VEHICULO_COCHE)){
       
            park.setAddress(datosParking.getString(this.CALLE_PARKING));
            
            double lat;
            double lon;
            
            lat=datosParking.getDouble(this.LATITUD);
            lon = datosParking.getDouble(this.LONGITUD);
            park.setLatitude(lat);
            park.setLongitude(lon);
            
            if(!park.isAccesibility()){
                
                if(parkingAccesible(park,plazasPMR)){
                    park.setAccesibility(true);
                }
            }
        }else{
                JSONArray array = coordenadas.getJSONArray(this.PUNTOS); 

            double lat;

            double lon;

             lat = (Double)array.get(0);

           try{

               lon = (Double)array.get(1);

           }catch(ClassCastException e){

               lon = 0.0; 

           }
        park.setLatitude(lon);
        park.setLongitude(lat);
            
            
            park.setAddress(datosParking.getString(this.DESCRIPCION));
            
        }
        
        
        conjuntoParkings.add(park);
    }
    
    
    
    
    
    
    //método para que no salte error de seguridad al pedir el json al ayuntamiento
    public void doTrustToCertificates() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts;
        trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }
                
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                }

                
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = (String urlHostName, SSLSession session) -> {
            if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
            }
            return true;
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private Parkings parkingEnBD(int ogc_fid, List<Parkings> parkingsBD) {
        Parkings parking = null;
       
        for(Parkings park : parkingsBD){
            if(park.getId()==ogc_fid){
                parking = park;
                break;
            }
        }
        
        return parking;
    }

    private Double calcularPuntuacion(Collection<Reviews> reviewsCollection) {
        Double media = 0.0;
        if(reviewsCollection.size()>0){
           for(Reviews review : reviewsCollection){
               media = media + review.getScore();
            }
            media = media/reviewsCollection.size(); 
        }
        
        
        return media;
    }

    

    private Parkings parkingEnLista(Parkings park, List<Parkings> conjuntoParkings) {
       Parkings esta = null;
       
       for(Parkings pk : conjuntoParkings){
           if(park.getId().equals(pk.getId())){
               esta = pk;
               break;
           }
       }
       
       
       return esta;
    }

    private boolean parkingAccesible(Parkings park, List<JSONObject> plazasPMR) {
        boolean accesible = false;
       try{
           
           for(JSONObject plaza : plazasPMR){
                
                JSONObject datosPlaza = plaza.getJSONObject(this.PROPERTIES);
                int idPlazaPMR =datosPlaza.getInt(this.IDPARKING);              
                if(idPlazaPMR==park.getId()){
                    accesible = true;
                    break;
                }
                
            }
            
        
        return accesible;
       } catch (JSONException ex) {
            Logger.getLogger(ParkingTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return accesible;
            
    }

    
}
