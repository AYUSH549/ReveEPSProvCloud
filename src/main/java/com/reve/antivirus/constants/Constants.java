package com.reve.antivirus.constants;

import java.nio.charset.Charset;

public class Constants {
	
private static boolean isSandbox = false;
    
    private static final String PRODUCTION_REPORTING_MAIL_PATH = "/usr/local/reveantivirus/EPS/customReport.html";
    private static final String SANDBOX_REPORTING_MAIL_PATH = "/usr/local/reveantivirus/EPS/customReport.html";
    
    private static final String PRODUCTION_SUB_USER_MAIL_PATH = "/usr/local/reveantivirus/EPS/passwordCustomMail.html";
    private static final String SANDBOX_SUB_USER_MAIL_PATH = "/usr/local/reveantivirus/EPS/passwordCustomMail.html";
    
    public static final String getReportingMailPath(){
        if(isSandbox){
            return SANDBOX_REPORTING_MAIL_PATH;
        }
        else{
            return PRODUCTION_REPORTING_MAIL_PATH;
        }
    }
    
    public static final String getSubUserMailPath(){
        if(isSandbox){
            return SANDBOX_SUB_USER_MAIL_PATH;
        }
        else{
            return PRODUCTION_SUB_USER_MAIL_PATH;
        }
    }

    //UserType
    public static final int TYPE_PROMO_USER = 0;
    public static final int TYPE_FULL_USER = 1;
    public static final int TYPE_TRIAL_USER = 2;
    public static final int TYPE_INTERNAL_USER = 3;
    public static final int TYPE_ENTERPRISE_USER = 4;
    
    //rights wise user type
    public static final int SUPER_ADMIN = 1;
    public static final int ADMIN = 0;
    public static final int SUB_ADMIN_VIEW_ONLY = 2;
    public static final int SUB_ADMIN_ALL_RIGHTS = 3;

    public static final Charset charset = Charset.forName("ISO-8859-1");
    public static final String[] PRODUCT_TYPE_RES = {
        "UNDEFINED",
        "SDKJFSKDNFSIDBBFSDK",
        "SDKFHSDFNIUWFNSDKNC",
        "SDHFSLMSIBCQDDSKAAC",
        "AHTEIPMTWQHYRSPUBCI",
        "YTQINZPIJASLMYETLPC",
        "YHJDRWPLCGAZXEPUTFN"
    };
    public static final int setValue = 1;
    public static final int MAX_PRODUCT_TYPE = 5;
    public static final long MAXIMUM_TIMEOUT_TIME = 0;              //in milliseconds
    public static final long MAXIMUM_INACTIVE_TIME_ALLOWED = 30 * 60000;     //30 minute(in milliseconds)
    public static final long SESSION_CLEANER_PERIOD = 60 * 60000;  // 1 hr(in milliseconds)
    public static final int TYPE_SERVER = 1;
    public static final int TYPE_CLIENT = 0;

    public static final int TOTAL_RULE_VIOLATED = 1;
    public static final int TOTAL_MD5_CHANGE = 2;
    public static final int TOTAL_PORT_SCAN = 3;

    public static final int STOP_ACTIVATION = 0;

    public static final long ONE_DAY_IN_MILLIS = 86400000;
    public static final long SEVEN_DAYS_IN_MILLIS = 604800000;

    public static final int NOTIFICATION_ON = 0;
    public static final int NOTIFY_POLICY_CREATION = 1;
    public static final int NOTIFY_VIRUS_FOUND = 2;
    public static final int NOTIFY_SPAM_MAIL = 3;
    public static final int NOTIFY_WEB_REPORT = 4;
    public static final int NOTIFY_APPLICATION_REPORT = 6;
    public static final int NOTIFY_UNINSTALL_REPORT = 7;
    public static final int NOTIFY_FIREWALL_VIOLATION = 8;
    public static final int NOTIFY_GROUP_CREATION = 13;
    public static final int NOTIFY_GROUP_CHANGE = 14;
    public static final int NOTIFY_POLICY_CHANGE = 15;
    public static final int NOTIFY_90_PERCENT_LICENSE_USED = 16;

    public static final int REPORTING_ON = 0;
    public static final int REPORTING_ANTIMALWARE = 1;
    public static final int REPORTING_EMAIL_SECURITY = 2;
    public static final int REPORTING_WEB_CONTROL = 3;
    public static final int REPORTING_DEVICE_CONTROL = 4;
    public static final int REPORTING_APP_CONTROL = 5;
    public static final int REPORTING_UNINSTALL_PROTECTION = 6;
    public static final int REPORTING_FIREWALL_CONTROL = 7;
    public static final int REPORTING_FILE_MONITORING = 8;
    public static final int REPORTING_DATALOSS = 9;
    public static final int REPORTING_ACTIVITY_MONITORING = 10;
    public static final int REPORTING_DATA_BACKUP = 11;

}
