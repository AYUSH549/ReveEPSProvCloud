package com.reve.antivirus.deployment;

public class Deployment {
private static boolean isSandbox = true;
    
    private static final String PRODUCTION_WEB_APP_LINK = "188.138.33.38:57999/EPS/";
    private static final String SANDBOX_WEB_APP_LINK = "192.187.126.18:8080/EPSCloudProvisioning/";
    
    private static final String PRODUCTION_SOCKET_IP = "188.138.33.38";
    private static final String SANDBOX_SOCKET_IP = "192.187.126.18";
    
    private static final int PRODUCTION_PORT = 3033;
    private static final int SANDBOX_PORT = 3035;
    
    private static final String PRODUCTION_API_KEY = "58c53b90eff019b01c277ff329ed0bf8";
    private static final String SANDBOX_API_KEY = "ef3b2599694c9939564ab9fc24d6803e";
    
    private static final String PRODUCTION_VERSION = "1.0";
    private static final String SANDBOX_VERSION = "2.0";
    
    
    

    
    public static final String getWebAppLink(){
        if(isSandbox){
            return SANDBOX_WEB_APP_LINK;
        }
        else{
            return PRODUCTION_WEB_APP_LINK;
        }
    }
    
    public static final String getSocketIP(){
        if(isSandbox){
            return SANDBOX_SOCKET_IP;
        }
        else{
            return PRODUCTION_SOCKET_IP;
        }
    }
    
    public static final int getPort(){
        if(isSandbox){
            return SANDBOX_PORT;
        }
        else{
            return PRODUCTION_PORT;
        }
    }
    
    public static final String getVersion(){
        if(isSandbox){
            return SANDBOX_VERSION;
        }
        else{
            return PRODUCTION_VERSION;
        }
    }
    
    
    public static final String getAPIKey(){
        if(isSandbox){
            return SANDBOX_API_KEY;
        }
        else{
            return PRODUCTION_API_KEY;
        }
    }
}
