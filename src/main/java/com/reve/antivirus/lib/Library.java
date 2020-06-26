package com.reve.antivirus.lib;


import com.reve.antivirus.constants.Constants;
import com.reve.antivirus.constants.Messages;
import com.reve.antivirus.controller.ServerController;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Library {
	
	private static final Logger logger = LogManager.getLogger(ServerController.class.getName());
	
	private static char[] rgbDigits = "0123456789abcdef".toCharArray();
    private static final byte key[] = {'&', '7', 'a', 'S'};
    private static final String PATTERN = "^([0-9A-Fa-f]{2}[-]){5}([0-9A-Fa-f]{2})$";

    public static int twoByteToInt(byte data[], int index) {
        int t = data[index] & 0x00FF;
        return (t << 8) | (data[index + 1] & 0x00FF);
    }

    public static long fourByteToLong(byte[] data, int index) {
        long t = data[index] & 0x00FF;
        t = (t << 8) | (data[index + 1] & 0x00FF);
        t = (t << 8) | (data[index + 2] & 0x00FF);
        return (t << 8) | (data[index + 3] & 0x00FF);
    }

    public static long eightByteToLong(byte[] data, int index) {
        long t = data[index] & 0x00FF;
        t = (t << 8) | (data[index + 1] & 0x00FF);
        t = (t << 8) | (data[index + 2] & 0x00FF);
        t = (t << 8) | (data[index + 3] & 0x00FF);
        t = (t << 8) | (data[index + 4] & 0x00FF);
        t = (t << 8) | (data[index + 5] & 0x00FF);
        t = (t << 8) | (data[index + 6] & 0x00FF);
        return (t << 8) | (data[index + 7] & 0x00FF);
    }

    public static byte[] encrypt(byte[] message) {
        for (int i = 0; i < message.length; i++) {
            message[i] = (byte) (message[i] ^ key[i % key.length]);
        }
        return message;
    }

    public static byte[] decrypt(byte[] message) {
        for (int i = 0; i < message.length; i++) {
            message[i] = (byte) (message[i] ^ key[i % key.length]);
        }
        return message;
    }

    private static String convertToHex(byte[] rgbHash) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < rgbHash.length; i++) {
            buf.append(rgbDigits[(rgbHash[i] >>> 4) & 0x0F]);
            buf.append(rgbDigits[(rgbHash[i] & 0x0F)]);
        }
        return buf.toString();
    }

    public static String getMD5Hash(String text) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] md5 = new byte[64];
            md.update(text.getBytes(Constants.charset));
            md5 = md.digest();
            return convertToHex(md5);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex, ex);
            return null;
        }
    }

    public static boolean validateMac(String password) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public static void createErrorPacket(packet.DataPacket response, int errNum) {
        response.setMessageType(Messages._ERROR);
        response.setInt(Messages.ERROR_CODE, errNum);
    }

    public static void createErrorMessage(packet.DataPacket response, String err, int errNum) {
        response.setMessageType(Messages._ERROR);
        response.setInt(Messages.ERROR_CODE, errNum);
        response.setString(Messages.ERROR_MESSAGE_FROM_PUBLIC, err);
    }

//    public static void createErrorPacketLong(packet.LongDataPacket response, int errNum) {
//        response.setMessageType(Messages._ERROR);
//        response.setInt(Messages.ERROR_CODE, errNum);
//    }

    //For communicating with Web
    private static final String ALGO = "AES";
    private static final byte[] keyValue = "@$@D!34~15xgaj4e".getBytes(Constants.charset);

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    private static final String allowedChars = "0123456789";
    private static Random r = new Random();

    public static String getRandomNumber(int length, boolean firstZeroAllowed) {
        char[] rand = new char[length];
        int i = 0;
        if (!firstZeroAllowed) {
            rand[i++] = allowedChars.charAt(1 + r.nextInt(allowedChars.length() - 1));
        }
        for (; i < rand.length; i++) {
            rand[i] = allowedChars.charAt(r.nextInt(allowedChars.length()));
        }
        return new String(rand);
    }

    public static byte[] do_encryption(long serverId, String uniqueKey) throws UnsupportedEncodingException {
        byte[] temp = new byte[44];
        byte[] serverInfo = new byte[48];
        String rand_no = "ashsd";
        byte[] r_key = rand_no.getBytes("ISO-8859-1");

        byte[] _uniqueKey = uniqueKey.getBytes();

        temp[0] = (byte) ((serverId >> 56) & 0x00FF);
        temp[1] = (byte) ((serverId >> 48) & 0x00FF);
        temp[2] = (byte) ((serverId >> 40) & 0x00FF);
        temp[3] = (byte) ((serverId >> 32) & 0x00FF);
        temp[4] = (byte) ((serverId >> 24) & 0x00FF);
        temp[5] = (byte) ((serverId >> 16) & 0x00FF);
        temp[6] = (byte) ((serverId >> 8) & 0x00FF);
        temp[7] = (byte) (serverId & 0x00FF);

        for (int i = 0; i < 36; i++) {
            temp[8 + i] = _uniqueKey[i];
        }

        for (int i = 0; i < 44; i++) {
            temp[i] = (byte) (temp[i] ^ r_key[i % 4]);
        }

        for (int i = 0; i < 4; i++) {
            serverInfo[i] = r_key[i];
        }

        for (int i = 0; i < 44; i++) {
            serverInfo[4 + i] = temp[i];
        }

        //encrypt with fixed key
        for (int i = 0; i < 48; i++) {
            serverInfo[i] = (byte) (serverInfo[i] ^ key[i % 4]);
        }

        return serverInfo;

    }

    public static char getRandomChar(int start, int end) {
        final String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        String newChars = allowedChars.substring(start, end);

        char c = newChars.charAt(r.nextInt(newChars.length() - 1));
        return c;
    }

    private static final SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
    private static final SimpleDateFormat shortForm = new SimpleDateFormat("dd MMM yyyy");

    static {
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        shortForm.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String getLongDate(long milli) {
        return df.format(new Date(milli));
    }

    public static String getShortDate(long milli) {
        return shortForm.format(new Date(milli));
    }

    public static long getTimeInMillis(String date) throws ParseException {
        return df.parse(date).getTime();
    }

    public static long getTimeInMillisForShortDate(String date) throws ParseException {
        return shortForm.parse(date).getTime();
    }

    public static byte[] resizeArray(byte[] oldArr, int size) {
        byte[] newArr = Arrays.copyOf(oldArr, oldArr.length + size);
        return newArr;
    }
    
    
    public static String getNextRandomKey() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().toUpperCase();
        return randomUUIDString.substring(0, 8) + randomUUIDString.substring(24, 32);
    }

    /**
     * Compares two version strings.
     *
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less
     * than str2. The result is a positive integer if str1 is _numerically_
     * greater than str2. The result is zero if the strings are _numerically_
     * equal.
     */
    public static int versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }

//    public static boolean isIpBlocked(String ip, long loginTime) {
//        try {
//            IpFilterDTO ipFilterDTO = null;
//            if (EPSProvisioning.ipFilter.containsKey(ip)) {
//                ipFilterDTO = EPSProvisioning.ipFilter.get(ip);
//                
////                if(loginTime - ipFilterDTO.getLastLoginTry() > ) {
////                    
////                }
//            } else {
//                ipFilterDTO = new IpFilterDTO();
//                
//                ipFilterDTO.setIsBlocked(false);
//                ipFilterDTO.setLastLoginTry(loginTime);
//                ipFilterDTO.setLoginCount(1);
//                
//                EPSProvisioning.ipFilter.put(ip, ipFilterDTO);
//                
//                return true;
//            }
//
//        } catch (Exception e) {
//            logger.info("Exception : " + e);
//        }
//        
//        return true;
//    }

}
