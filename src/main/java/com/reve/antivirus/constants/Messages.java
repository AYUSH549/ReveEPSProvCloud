package com.reve.antivirus.constants;

public class Messages {

/**
 * *************Request Messages ******************
 */
public static final int REQUEST_CLIENT_ACTIVATION = 0x0001;
public static final int REQUEST_MAP_VIEW = 0x5810;    //7
public static final int REQUEST_SERVER_REGISTRATION = 0x0201;
public static final int REQUEST_SERVER_ACTIVATION = 0x0202;
public static final int REQUEST_SERVER_LOGIN = 0x0203;
public static final int REQUEST_FIXED_CONNECTION = 0x0204;
public static final int REQUEST_PRODUCT_UPDATE = 0x0014;
public static final int REQUEST_CHECK_STATUS = 0x0025;
public static final int REQUEST_DEVICE_CONTROL_SETTING = 0x0060;
public static final int REQUEST_UNINSTALL_PASSWORD = 0x0061;
public static final int REQUEST_SAVE_APPLICATION_CONTROL = 0x8001;
public static final int REQUEST_ASSET = 0x5005;
public static final int REQUEST_SOFTWARE_INFO = 0x5006;
public static final int REQUEST_SOFTWARE_CLIENT_MAP = 0x5007;
public static final int REQUEST_SERVER_SOFTWARE_INFO = 0x5008;
public static final int REQUEST_NETWORK_DISCOVERY = 0x5301;
public static final int REQUEST_GET_ALL_PARENTAL_CONTROL_BY_SERVER_ID = 0x0205;
public static final int REQUEST_GET_ALL_APPLICATION_CONTROL_BY_SERVER_ID = 0x0206;
public static final int REQUEST_GET_ALL_APPLICATION_CONTROL_BY_CLIENT_ID = 0x0207;
public static final int REQUEST_GET_ALL_DEVICE_CONTROL_BY_SERVER_ID = 0x0208;
public static final int REQUEST_GET_ALL_DEVICE_CONTROL_BY_CLIENT_ID = 0x0209;
public static final int REQUEST_GET_ALL_UNINSTALL_PASSWORD_BY_SERVER_ID = 0x020A;
public static final int REQUEST_GET_ALL_UNINSTALL_PASSWORD_BY_CLIENT_ID = 0x020B;
public static final int REQUEST_GET_ALL_BY_CLIENT = 0x020C;

public static final int REQUEST_UPDATE_FIREWALL_SETTINGS = 0x8020;
public static final int REQUEST_TOGGLE_FIREWALL_SETTINGS = 0x8021;

public static final int REQUEST_GET_ALL_FIREWALL_BY_SERVER_ID = 0x020D;

public static final int REQUEST_PARENTAL_CONTROL = 0x5814;

public static final int REQUEST_DELETE_APPLICATION_CONTROL = 0x020E;
public static final int REQUEST_DELETE_FIREWALL_SETTINGS = 0x020F;

public static final int REQUEST_INSTALLER_EMAIL = 0x0210;
public static final int REQUEST_PRODUCT_KEY_CHANGE = 0x0211;

public static final int REQUEST_ANTIVIRUS_VERSION_DETAILS = 0x0048;

public static final int REQUEST_CHANGE_PASSWORD = 0x0212;
public static final int REQUEST_FORGOT_PASSWORD = 0x0213;

public static final int REQUEST_CLIENT_ACTIVATION_DETAILS = 0x0215;

public static final int REQUEST_WEB_LOGIN = 0x0216;

public static final int REQUEST_CREATE_GROUP = 0x0217;
public static final int REQUEST_CHANGE_GROUP = 0x0218;
public static final int REQUEST_DELETE_GROUP = 0x0219;

public static final int REQUEST_CREATE_POLICY = 0x021A;
public static final int REQUEST_UPDATE_POLICY = 0x021B;
public static final int REQUEST_DELETE_POLICY = 0x021C;
public static final int REQUEST_POLICY_GROUP_MAPPING = 0x021D;

public static final int REQUEST_REPORT_WEB = 0x021E;
public static final int REQUEST_REPORT_SCAN = 0x021F;
public static final int REQUEST_REPORT_APP_CONTROL = 0x0220;
public static final int REQUEST_REPORT_FIREWALL = 0x0221;
public static final int REQUEST_REPORT_UNINSTALL_PASSWORD = 0x0222;
public static final int REQUEST_REPORT_DEVICE_CONTROL = 0x0223;

public static final int REQUEST_TOGGLE_ALLOW_ACTIVATION = 0x0224;

public static final int REQUEST_SYNC_GROUP_DETAILS = 0x0225;
public static final int REQUEST_SYNC_POLICY_DETAILS = 0x0226;
public static final int REQUEST_SYNC_POLICY_BY_POLICY_ID = 0x0227;
public static final int REQUEST_SYNC_GROUP_MAP = 0x0228;

public static final int REQUEST_FEEDBACK = 0x0229;

public static final int REQUEST_GET_USERS_BY_SERVER_ID = 0x022A;

public static final int REQUEST_GROUP_SCAN = 0x022B;

public static final int REQUEST_GENERATE_UNIQUE_CODE = 0x022C;

public static final int REQUEST_DIRECT_CLIENT_ACTIVATION = 0x022D;

public static final int REQUEST_SERVER_STATUS_CHECK = 0x022E;
public static final int REQUEST_UNINSTALL_CLIENT = 0x022F;
public static final int REQUEST_SYNC_UNIQUE_CODE = 0x0230;

public static final int REQUEST_SYNC_CLIENTS_UPDATE = 0x0231;

public static final int REQUEST_FILE_ACCESS_MONITORING = 0x0232;

public static final int REQUEST_DELETE_FILE_ACCESS_MONITORING = 0x0233;

public static final int REQUEST_DLP_SETTINGS = 0x0234;

public static final int REQUEST_REPORT_FILE_MONITORING = 0x8046;

//public static final int REQUEST_SYNC_DELETED_ENTITIES = 0x0236;
public static final int REQUEST_USB_DETAILS = 0x0237;

public static final int REQUEST_UPDATE_USB_POLICY = 0x0238;

public static final int REQUEST_REMOVE_CLIENT = 0x0239;

public static final int REQUEST_EDIT_USER_NAME = 0x023A;

public static final int REQUEST_CREATE_USB_SPECIFIC_POLICY = 0x023B;
//0x023D used
public static final int REQUEST_DECLINE_USB_VENDOR_SETTINGS = 0x023C;

public static final int REQUEST_SYNC_USB_REQUEST = 0x023F;

public static final int REQUEST_SYNC_PUSH_SETTINGS = 0x0240;

public static final int REQUEST_USB_DETECTED = 0x023D;

public static final int REQUEST_REPORT_DLP = 0x0241;

public static final int REQUEST_SYNC_APP_CONTROL_REPORT = 0x0242;
public static final int REQUEST_SYNC_DLP_MONITORING_REPORT = 0x0243;
public static final int REQUEST_SYNC_DEVICE_CONTROL_REPORT = 0x0244;
public static final int REQUEST_SYNC_FILE_ACCESS_MONITORING_REPORT = 0x0245;
public static final int REQUEST_SYNC_FIREWALL_REPORT = 0x0246;
public static final int REQUEST_SYNC_UNINSTALL_PASSWORD_REPORT = 0x0247;
public static final int REQUEST_SYNC_WEB_REPORT = 0x0248;
public static final int REQUEST_SYNC_SCAN_REPORT = 0x0249;
public static final int REQUEST_SYNC_THREAT_INFO = 0x024A;

public static final int REQUEST_LAST_ACTIVITY = 0x024B;

public static final int REQUEST_DELETE_DLP = 0x024C;

public static final int REQUEST_ANTIMALWARE_SETTINGS = 0x0235;
public static final int REQUEST_ANTISPAM_SETTINGS = 0x0236;

public static final int REQUEST_SYNC_ACTIVITY_TIME = 0x024D;

public static final int REQUEST_USER_ACTIVITY_MONITORING = 0x024E;

//0x024F used
public static final int REQUEST_SERVER_RELOCATION = 0x0250;

public static final int REQUEST_ISOLATED_CLIENT_REACTIVATION = 0x0251;

public static final int REQUEST_CHECK_NETWORK_MANAGER = 0x0252;

public static final int REQUEST_DATA_BACKUP_POLICY = 0x0254;

public static final int REQUEST_USER_DATA_BACKUP = 0x0255;

public static final int REQUEST_SYNC_USER_DATA_BACKUP = 0x0258;
//0x0259 0x025A used

public static final int REQUEST_RELOCATE_USERS = 0x025B;

public static final int REQUEST_DELETE_BACKUP_FILES = 0x025C;

public static final int REQUEST_MANAGE_NOTIFICATION = 0x025D;

public static final int REQUEST_ALL_CLIENT_ACTIVATION_DETAILS = 0x025E;

public static final int REQUEST_ALL_CLIENT_ASSET_DETAILS = 0x025F;

public static final int REQUEST_GET_USER_ASSETS_BY_SERVER_ID = 0x0260;

public static final int REQUEST_INCREASE_KEY = 0x0261;

public static final int REQUEST_THREAD_MONITOR_CONTROL = 0x0262;

public static final int REQUEST_NETWORK_INTRUSION = 0x0263;

public static final int REPORT_NETWORK_INTRUSION = 0x0264;

public static final int REQUEST_SYNC_NETWORK_INTRUSION_REPORT = 0x0265;

public static final int REQUEST_CREATE_SUB_ADMIN = 0x0266;
public static final int REQUEST_UPDATE_MANAGER_IP = 0x0267;
public static final int REQUEST_LID = 0x0269;
public static final int REQUEST_SERVER_DETAILS = 0x0270;

public static final int RESPONSE_DOWNLOAD_FILES = 0x0271;
public static final int REQUEST_CHANGE_EMAIL = 0x0272;
public static final int REQUEST_DOWNLOAD_FILES = 0x0273;
public static final int SUCCESS_CHANGE_EMAIL =0x0274;


/**
 * ****************Message Attributes******************
 */
public static final int ERROR_CODE = 0x0000;
public static final int API_KEY = 0x6003;
public static final int API_VERSION = 0x6002;
public static final int SESSION_KEY = 0x0001;
public static final int RANDOM_NO = 0x0103;
public static final int IP = 0x0905;
public static final int PORT = 0x0015;

//for Register
public static final int ATTRIBUTE_LOGIN_NAME = 0x0922;
public static final int LOGIN_ID = 0x0902;
public static final int MOBILE = 0x0109;
public static final int PASSWORD = 0x0907;
public static final int ATTRIBUTE_LID = 0x0908;
public static final int TIMEZONE = 0x0909;

//for ProductKey
public static final int PRODUCT_KEY = 0x0100;
public static final int MAC = 0x0101;
public static final int PRODUCT_TYPE = 0x0102;
public static final int SERIAL_KEY = 0x0919;
public static final int PRODUCT_VERSION = 0x0776;
public static final int LATEST_PRODUCT_VERSIONS = 0x0777;
//for LANServerDetails
public static final int SERVER_ID = 0x6004;
public static final int UNIQUE_KEY = 0x6005;
//for UserActivation
public static final int HASH_NEW_INSTALL = 0x0104;
public static final int COMPUTER_NAME = 0x0106;
public static final int EMAIL_ID = 0x0108;
public static final int INSTALL_DATE = 0x0200;
public static final int VALIDITY = 0x0201;
public static final int HASH_VALIDITY = 0x0105;     //261
public static final int HASH_LICENSE = 0x0202;
public static final int SERVICES = 0x0203;
//for ProductUpdate
public static final int SERVER_HASH = 0x6006;
public static final int CURRENT_VERSION = 0x0800;
public static final int OS = 0x0772;
public static final int UPDATE_URL = 0x0801;
public static final int BIT = 0x0802;
public static final int PRODUCT_SUBTYPE = 0x0113;
//for CheckStatus
public static final int USER_ID = 0x0107;
public static final int TIMESTAMP = 0x0504;
//for DEVICE_CONTROL
public static final int DEVICE_CONTROL_SETTING = 0x1600;
//for UninstallPassword
public static final int CURRENT_PASSWORD = 0x0903;
//for Application Control
public static final int EXE_NAME = 0x8002;
public static final int PRODUCT_NAME = 0x8003;
public static final int PRODUCT_DESCRIPTION = 0x8004;
public static final int WINDOW_NAME = 0x8005;
public static final int APPLICATION_MODE = 0x8006;
public static final int APPLICATION_CATEGORY = 0x8007;

//for Asset
public static final int IS64BIT = 0x5100;
public static final int OS_NAME = 0x5101;
public static final int OS_VERSION = 0x5102;
public static final int NO_OF_VERSION = 0x5103;
public static final int IP_ADDRESS = 0x5105;
public static final int USER_NAME = 0x5106;
public static final int TOTAL_DISK_SIZE = 0x5107;
public static final int AVAILABLE_DISK_SIZE = 0x5108;

public static final int PROCESSOR_TYPE = 0x0770;
public static final int PROCESSOR_NO = 0x0771;
public static final int OS_MAJOR = 0x0772;
public static final int OS_MINOR = 0x0773;
public static final int TOTAL_MEMORY = 0x0774;
public static final int AVAILABLE_MEMORY = 0x0775;

public static final int SOFTWARE_ID_LOCAL = 0x0777;
public static final int SOFTWARE_ID_GLOBAL = 0x0797;
public static final int OS_ARCHITECTURE = 0x0778;
public static final int BIOS_MANUFACTURER = 0x0779;
public static final int CPU_MANUFACTURER = 0x0780;
public static final int MOTHER_BOARD_MANUFACTURER = 0x0781;
public static final int BIOS_SERIAL = 0x0782;
public static final int PROCESSOR_INFO = 0x0783;
public static final int PROCESSOR_ID = 0x0784;
public static final int NO_OF_RAM_SLOT = 0x0785;
public static final int NO_OF_CORES = 0x0786;
public static final int LAST_BOOT_TIME = 0x0787;
public static final int OS_INSTALL_DATE = 0x0788;
public static final int OS_SERIAL_NUMBER = 0x0789;
public static final int DOMAIN_NAME = 0x0790;
public static final int LIST_OF_DRIVES =  0x0791;
public static final int SIZES_OF_DRIVES = 0x0792;
public static final int FREE_SIZES_OF_DRIVES = 0x0793;
public static final int PRIMARY_OWNER_NAME = 0x0794;
public static final int RAM_MANUFACTURER = 0x0795;
public static final int RAM_SERIAL = 0x0796;

// application info

public static final int SOFTWARE_NAME = 0x0750;
public static final int SOFTWARE_VERSION = 0x0751;
public static final int SOFTWARE_PUBLISHER = 0x0752;
public static final int SOFTWARE_INSTALL_DATE= 0x0753;
public static final int SOFTWARE_SIZE = 0x0754;

//for Network Discovery
public static final int USER_ACTIVITY = 0x5200;
//for GET_ALL_BY_SERVER_ID
public static final int LAST_MODIFIED_TIME = 0x0704;
//public static final int BLACKLIST = 0x0009;     //9
public static final int WHITELIST = 0x0010;     //16  
public static final int WEB_CONTROL_SERVICES = 0x0701;            //1793
public static final int CATEGORY_VALUE = 0x0705;
public static final int DEFAULT_CATEGORY = 0x0008;

//Firewal
public static final int ATTRIBUTE_DIRECTION = 0x8022;
public static final int ATTRIBUTE_TRAFFIC_ACTION = 0x8023;
public static final int ATTRIBUTE_PORT_SCAN = 0x8024;
public static final int ATTRIBUTE_ID = 0x8025;
public static final int ATTRIBUTE_ADD_PROCESS = 0x8026;
public static final int ATTRIBUTE_RULE_CHECK_PROTOCOL = 0x8027;
public static final int ATTRIBUTE_CHECK_REMOTE_PORT = 0x8028;
public static final int ATTRIBUTE_RULE_CHECK_REMOTE_NETWORK = 0x8029;
public static final int ATTRIBUTE_RULE_CHECK_SID = 0x8030;
public static final int ATTRIBUTE_ADD_CATEGORY = 0x8031;
public static final int ATTRIBUTE_STEALTH_MODE = 0x8032;
public static final int ATTRIBUTE_STEALTH_MODE_ADAPTER = 0x8033;
public static final int ATTRIBUTE_ADD_SID = 0x8034;

//for CHANGE_SERVICE
public static final int SERVICE_TYPE = 0x0700;      //1792

public static final int TOTAL_LICENSE = 0x8035;
public static final int NEW_PASSWORD = 0x8036;

public static final int IS_WEB = 0x8037;

public static final int REPORT_WEB = 0x0506;
public static final int REPORT_SCAN = 0x0505;
public static final int CATEGORY = 0x0601;

//Group
public static final int GROUP_NAME = 0x8038;
public static final int GROUP_DESCRIPTION = 0x8039;
public static final int GROUP_ID = 0x803A;

public static final int MYSQL_PASSWORD = 0x803B;

public static final int ATTRIBUTE_FIREWALL_SETTINGS = 0x8101;
public static final int ATTRIBUTE_APPLICATION_CONTROL_SETTINGS = 0x8102;
public static final int ATTRIBUTE_DEVICE_CONTROL_SETTINGS = 0x8103;
public static final int ATTRIBUTE_PARENTAL_CONTROL_SETTINGS = 0x8104;
public static final int ATTRIBUTE_PASSWORD_SETTINGS = 0x8105;
public static final int ATTRIBUTE_FILE_ACCESS_MONITORING = 0x8106;
public static final int ATTRIBUTE_POLICY_ID = 0x8107;
public static final int ATTRIBUTE_POLICY_NAME = 0x8108;
public static final int ATTRIBUTE_POLICY_DESCRIPTION = 0x8109;
public static final int DESCRIPTION = 0x8043;

public static final int ATTRIBUTE_DATA_LOSS_PREVENTION = 0x810A;
public static final int DESCRIPTION_TYPE = 0x810B;
public static final int APP_CONTROL_REPORT_DESCRIPTION = 0x810E;

public static final int ATTRIBUTE_TOTAL = 0x090E;
public static final int INFECTED = 0x0910;
public static final int DELETED = 0x0911;
public static final int QUARANTINED = 0x0912;
public static final int FILE_NAME = 0x0913;
public static final int THREAD_INFO = 0x0914;

//public static final int UNINSTALL_PASSWORD_REPORT_DESCRIPTION = 0x810F;
//public static final int DEVICE_CONTROL_REPORT_DESCRIPTION = 0x8110;
public static final int ALLOW_ACTIVATION_TOGGLE_VALUE = 0x8111;
public static final int AVAILABLE_LICENCE = 0x8112;

public static final int ATTRIBUTE_RATINGS = 0x8113;
public static final int ATTRIBUTE_COMMENTS = 0x8114;

public static final int ATTRIBUTE_PUSH_SETTING = 0x6412;

public static final int UNIQUE_CODE = 0x8115;
public static final int NO_OF_USERS = 0x8116;

public static final int IS_ROAMING_CLIENT = 0x8117;

public static final int ATTRIBUTE_EXTENSION_NAME = 0x8044;
public static final int ATTRIBUTE_DRIVE_TYPE = 0x8045;

public static final int ATTRIBUTE_PRINTER = 0x8118;
public static final int ATTRIBUTE_CLIP_BOARD = 0x8119;
public static final int ATTRIBUTE_DLP_SETTINGS = 0x811A;
public static final int ATTRIBUTE_REMOVABLE_DISK_EXTENSION = 0x811B;
//public static final int ATTRIBUTE_COUNTRY_SETTINGS = 0x811C;
public static final int ATTRIBUTE_USER_DEFINED_DICTIONARY = 0x811D;
public static final int ATTRIBUTE_SENSITIVE_INFORMATION = 0x811C;
public static final int ATTRIBUTE_COUNTRY_ID = 0x811E;

public static final int ATTRIBUTE_USB_HASH = 0x8130;
public static final int ATTRIBUTE_USB_VENDOR = 0x8131;
public static final int ATTRIBUTE_USB_DESCRIPTION = 0x8132;
public static final int ATTRIBUTE_USB_SERIAL_NO = 0x8133;
public static final int ATTRIBUTE_USB_PRODUCT_ID = 0x8134;

public static final int ATTRIBUTE_USB_BLOCK_FLAG = 0x8135;
public static final int IS_DELETED = 0x8136;

public static final int ATTRIBUTE_OPERATION_TYPE = 0x8009;

public static final int CURRENT_STATUS = 0x800A;

public static final int DATE_CREATION = 0x800B;

public static final int FRAGMENT_BIT = 0x800C;

public static final int URL = 0x800D;
public static final int URL_ID = 0x800E;

public static final int ATTRIBUTE_SCAN_SETTINGS = 0x8137;
public static final int ATTRIBUTE_SCAN_ACTION_FIRST = 0x8138;
public static final int ATTRIBUTE_SCAN_ACTION_SECOND = 0x8139;
public static final int ATTRIBUTE_UPDATE_FREQUENCY = 0x813A;
public static final int ATTRIBUTE_EXCLUDE_PATH = 0x813B;
public static final int ATTRIBUTE_EXCLUDE_EXTENSION = 0x813C;

//public static final int ATTRIBUTE_EXCLUDE_PATH = 0x813B;
//public static final int ATTRIBUTE_EXCLUDE_EXTENSION = 0x813C;
public static final int ATTRIBUTE_ANTIMALWARE_SETTINGS = 0x810C;

public static final int ATTRIBUTE_ANTISPAM_SETTINGS = 0x813D;
public static final int ATTRIBUTE_AGGRESSION_LEVEL = 0x813E;
public static final int ATTRIBUTE_EMAIL_HASH = 0x813F;
public static final int ATTRIBUTE_SENSITIVE_PATTERN = 0x8140;
public static final int ATTRIBUTE_PATTERN_TYPE = 0x8141;

public static final int ATTRIBUTE_ANTISPAM = 0x810D;

public static final int LAST_ACTIVITY_TIME = 0x8142;
public static final int SIGNATURE_UPDATE_TIME = 0x8143;

public static final int ATTRIBUTE_SCRRENSHOT_FREQUENCY = 0x8144;
public static final int ATTRIBUTE_WORKING_HOUR_START = 0x8145;
public static final int ATTRIBUTE_WORKING_HOUR_END = 0x8146;
public static final int ATTRIBUTE_WORKING_DAYS = 0x8147;
public static final int ATTRIBUTE_IMAGE_QUALITY = 0x8148;
public static final int ATTRIBUTE_USER_ACTIVITY_FLAG = 0x8149;
public static final int ATTRIBUTE_USER_SCREEN_SHOT_ACTIVITY_FLAG = 0x814A;

public static final int ATTRIBUTE_USER_ACTIVITY_MONITORING = 0x810F;

public static final int ATTRIBUTE_WORKING_HOUR_FLAG = 0x814F;

public static final int ATTRIBUTR_BLACKLIST_URL = 0x0009;
public static final int ATTRIBUTR_WHITELIST_URL = 0x6407;

public static final int ATTRIBUTE_LAN_SERVER = 0x8150;

public static final int ERROR_MESSAGE_FROM_PUBLIC = 0x8151;

public static final int ATTRIBUTE_REACTIVATION_REQUIRED = 0x8152;

public static final int ATTRIBUTE_MAX_BACKUP_SIZE = 0x8153;
public static final int ATTRIBUTE_MAX_VERSION = 0x8154;
public static final int ATTRIBUTE_MAX_FILE_SIZE = 0x8155;
public static final int ATTIBUTE_BACKUP_FLAGS = 0x8156;
public static final int ATTIBUTE_BACKUP_MANAGER_ID = 0x8157;
public static final int ATTIBUTE_BACKUP_DIRECTORIES = 0x8158;
public static final int ATTRIBUTE_BACKUP_EXTENSIONS = 0x8159;

public static final int ATTRIBUTE_DATA_BACKUP_SETTINGS = 0x815A;

public static final int ATTRIBUTE_BACKUP_DRIVE_LOCATION = 0x815C;

public static final int ATTRIBUTE_BACKUP_MANAGER_NAME = 0x815B;

public static final int ATTRIBUTE_DRIVE_NAME = 0x815E;

public static final int ATTRIBUTE_BACKUP_MANAGER_MAX_BACKUP_SIZE = 0x815D;
public static final int ATTRIBUTE_FILE_STATUS = 0x8160;

public static final int ENCRYPTION_TYPE = 0x8147;
public static final int COMPRESSION_TYPE = 0x8148;
public static final int BACKUP_VERSION = 0x8149;
public static final int TOTAL_SIZE = 0x8145;
public static final int MD5HASH = 0x5013;
public static final int CONTENT = 0x5014;
public static final int MOREFILES = 0x5015;
public static final int DIRECTORY = 0x5016;
public static final int REQUEST_LOCAL_BACKUP = 0x0250;
public static final int ATTRIBUTE_BACKUP_ORIGINAL_HASH = 0x815E;
public static final int ATTRIBUTE_BACKUP_ENCRYPTED_HASH = 0x815F;

public static final int ATTRIBUTE_BACKUP_STATUS = 0x8161;

public static final int ATTRIBUTE_FILE_ID = 0x5010;

public static final int ATTRIBUTE_DELETED_USERS = 0x8162;

public static final int ATTRIBUTE_RESTORE_LOCATION = 0x8163;

public static final int ATTRIBUTE_NOTIFICATION_SERVICE = 0x8164;
public static final int ATTRIBUTE_REPORTING_SERVICE = 0x8165;

public static final int ATTRIBUTE_REPORTING_INTERVAL = 0x8166;
public static final int ATTRIBUTE_REPORTING_TIME = 0x8167;

public static final int ATTRIBUTE_IS_ENABLE = 0x8168;

public static final int ATTRIBUTE_IPS_TOGGLE = 0x8169;
public static final int ATTRIBUTE_IDS_TOGGLE = 0x8170;
public static final int ATTRIBUTE_NETWORK_INTRUSION_SETTINGS = 0x8171;
public static final int ATTRIBUTE_PATTERNS = 0x8172;
public static final int ATTRIBUTE_ENABLE_DEEP_PACKET_INSPECTION = 0x8173;

public static final int ATTRIBUTE_IDS_IPS_SETTINGS = 0x8174;

public static final int ATTRIBUTE_IPS_DURATION = 0x8175;

public static final int ATTRIBUTE_ACTION = 0x8176;

public static final int ATTRIBUTE_USER_TYPE = 0x8177;

public static final int ATTRIBUTE_LOGIN_ACCESS_TOKEN = 0x8178;
public static final int ATTRIBUTE_LOGIN_ID_LIST = 0x8179;
public static final int ATTRIBUTE_LOGIN_NAME_LIST = 0x8180;

public static final int ATTRIBUTE_UPDATE_MANAGER_ID = 0x8181;
public static final int ATTRIBUTE_UPDATE_MANAGER_NAME = 0x8182;
public static final int ATTRIBUTE_UPDATE_MANAGER_IP = 0x8183;
public static final int ATTRIBUTE_UPDATE_MANAGER_PORT = 0x8184;
public static final int ATTRIBUTE_UPDATE_MANAGER_CACHE_SIZE = 0x8185;
public static final int ATTRIBUTE_MAX_LOCATION_SUPPORTED = 0x8186;

/**
 * *************Response Messages ****************
 */
public static final int _ERROR = 0x1000;                    //4096
public static final int USER_EXIST_AND_VALID = 0x1001;
public static final int NEW_INSTALL_SUCCESS = 0x1002;
public static final int RESPONSE_LID = 0x1003;
public static final int RESPONSE_PORT = 0x1004;
public static final int RESPONSE_LAN_SERVER_DETAILS = 0x1005;
public static final int RESPONSE_LOGIN_DETAILS = 0x1006;

public static final int RESPONSE_PRODUCT_UPDATE = 0x1014;

public static final int RESPONSE_SUCCESS = 0x1018;

public static final int RESPONSE_CHECK_STATUS = 0x1025;

public static final int RESPONSE_UNINSTALL_PASSWORD = 0x1061;

public static final int RESPONSE_APPLICATION_CONTROL = 0x8008;

public static final int RESPONSE_GET_ALL_BY_SERVER_ID = 0x08009;

public static final int RESPONSE_GET_ALL_APPLICATION_CONTROL_BY_SERVER_ID = 0x1019;
public static final int RESPONSE_GET_ALL_APPLICATION_CONTROL_BY_CLIENT_ID = 0x101A;

public static final int RESPONSE_ALREADY_UPDATED = 0x101B;

public static final int RESPONSE_GET_ALL_DEVICE_CONTROL_BY_SERVER_ID = 0x101C;
public static final int RESPONSE_GET_ALL_DEVICE_CONTROL_BY_CLIENT_ID = 0x101D;

public static final int RESPONSE_GET_ALL_UNINSTALL_PASSWORD_BY_SERVER_ID = 0x101E;
public static final int RESPONSE_GET_ALL_UNINSTALL_PASSWORD_BY_CLIENT_ID = 0x101F;

public static final int RESPONSE_GET_ALL_BY_CLIENT = 0x5440;

public static final int RESPONSE_GET_ALL_FIREWALL_BY_SERVER_ID = 0x1020;

public static final int RESPONSE_WEB_CONTROL = 0x5040;

public static final int RESPONSE_KEEP_ALIVE = 0x1020;

public static final int RESPONSE_PRODUCT_KEY_CHANGE = 0x1021;
public static final int RESPONSE_ANTIVIRUS_VERSION_DETAILS = 0x1048;

public static final int RESPONSE_WEB_LOGIN_DETAILS = 0x1024;

public static final int RESPONSE_CREATE_GROUP = 0x1025;

public static final int RESPONSE_MYSQL_PASSWORD = 0x1026;
public static final int RESPONSE_SYNC_GROUP_DETAILS = 0x1027;
public static final int RESPONSE_SYNC_POLICY_DETAILS = 0x1028;
public static final int RESPONSE_SYNC_POLICY_BY_POLICY_ID = 0x1029;
public static final int RESPONSE_SYNC_GROUP_MAP = 0x102A;

public static final int RESPONSE_PRODUCT_UPDATE_CRITICAL = 0x102B;

//public static final int RESPONSE_GET_ALL_USERS_BY_SERVER_ID = 0x102C;
public static final int RESPONSE_CLIENT_ACTIVATION_DETAILS = 0x102D;

public static final int RESPONSE_UNIQUE_CODE = 0x102E;
public static final int RESPONSE_GET_ALL_USERS_BY_SERVER_ID = 0x102F;

public static final int RESPONSE_SERVER_STATUS_CHECK = 0x1030;

public static final int RESPONSE_SYNC_UNIQUE_CODE = 0x1031;

public static final int RESPONSE_SYNC_UNINSTALLED_CLIENTS = 0x1032;

public static final int RESPONSE_DELETED_ENTITIES = 0x1033;

public static final int RESPONSE_CREATE_USB_SPECIFIC_POLICY = 0x1034;

public static final int RESPONSE_SYNC_USB_REQUEST = 0x1035;

public static final int RESPONSE_SYNC_PUSH_SETTINGS = 0x1036;

public static final int RESPONSE_SYNC_APP_CONTROL_REPORT = 0x1037;
public static final int RESPONSE_SYNC_DLP_MONITORING_REPORT = 0x1038;
public static final int RESPONSE_SYNC_DEVICE_CONTROL_REPORT = 0x1039;
public static final int RESPONSE_SYNC_FILE_ACCESS_MONITORING_REPORT = 0x103A;
public static final int RESPONSE_SYNC_FIREWALL_REPORT = 0x103B;
public static final int RESPONSE_SYNC_UNINSTALL_PASSWORD_REPORT = 0x103C;
public static final int RESPONSE_SYNC_WEB_REPORT = 0x103D;
public static final int RESPONSE_SYNC_SCAN_REPORT = 0x103E;
public static final int RESPONSE_SYNC_THREAT_INFO = 0x103F;

public static final int RESPONSE_SYNC_ACTIVITY_TIME = 0x1040;

public static final int RESPONSE_USER_ACTIVITY_MONITORING = 0x1041;

public static final int RESPONSE_ISOLATED_CLIENT_REACTIVATION = 0x1042;

public static final int RESPONSE_CHECK_NETWORK_MANAGER = 0x1043;

public static final int RESPONSE_SYNC_USER_DATA_BACKUP = 0x1047;

public static final int RESPONSE_DELETE_BACKUP_FILES = 0x104A;

public static final int RESPONSE_ALL_CLIENT_ACTIVATION_DETAILS = 0x104B;

public static final int RESPONSE_GET_ALL_USERS_ASSETS_BY_SERVER_ID = 0x104C;

public static final int RESPONSE_GET_ALL_ASSETS = 0x104D;

public static final int RESPONSE_NETWORK_INTRUSION = 0x104E;

public static final int RESPONSE_SYNC_NETWORK_INTRUSION_REPORT = 0x104F;

public static final int RESPONSE_SUPER_ADMIN_LOGIN_DETAILS = 0x1050;

public static final int RESPONSE_UPDATE_MANAGER_IP = 0x1051;

public static final int RESPONSE_GET_LID = 0x1053;

public static final int RESPONSE_SERVER_DETAILS = 0x1054;

public static final int RESPONSE_SERVER_SOFTWARE_INFO = 0x1055;

public static final int PRO_ID = 0x0204;
public static final int DATE_EXPIRY = 0x0205;
public static final int ACTIVATION_TIME = 0x0206;
public static final int IP_RESTRICTION = 0x0207;
public static final int MAX_LOCATION_SUPPORTED = 0x0208;
public static final int AVAILABLE_SUPPORTING_LOCATIONS = 0x0209;
public static final int KEY_TYPE = 0x020A;
public static final int MODIFICATION_FLAG = 0x020B;

}
