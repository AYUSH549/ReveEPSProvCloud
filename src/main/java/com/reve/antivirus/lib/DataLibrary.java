
package com.reve.antivirus.lib;
import java.security.Key;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DataLibrary {
    
 
    private static char[] rgbDigits = "0123456789abcdef".toCharArray();
    private static final byte key[] = {'&', '7', 'a', 'S'};
        
    public static int twoByteToInt(byte data[], int index) {
        int t = data[index] & 0x00FF;
        return (t << 8) | (data[index + 1] & 0x00FF);
    }
    
    public static long fourByteToLong(byte[] data,int index){
        long t = data[index] & 0x00FF;
        t = (t << 8) | (data[index + 1] & 0x00FF);
        t = (t << 8) | (data[index + 2] & 0x00FF);
        return (t << 8) | (data[index + 3] & 0x00FF);
    }
    
    public static long eightByteToLong(byte[] data,int index){
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
        for (int i = 0; i < rgbHash.length; i++)
        {
            buf.append(rgbDigits[(rgbHash[i] >>> 4) & 0x0F]);
            buf.append(rgbDigits[(rgbHash[i] & 0x0F)]);
        }
        return buf.toString();
    }
    
    public static String getMD5Hash(String text) {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("MD5");
            byte[] md5 = new byte[64];
            //md.update(text.getBytes(Constants.charset));
            md5 = md.digest();
            return convertToHex(md5);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
       //     logger.info(ex,ex);
            return null;
        }
    }
       
//    public static void createErrorPacket(DataPacket response,int errNum) {
//        response.setMessageType(Messages._ERROR);
//        response.setInt(Messages.ERROR_CODE, errNum);
//    }
    

    private static final String ALGO = "AES";
    //private static final byte[] keyValue = "@$@D!34~15xgaj4e".getBytes(Constants.charset);
    
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
    private static Key generateKey(){
        //Key key = new SecretKeySpec(keyValue, ALGO);
        return null;
    }
    
    public static String encrypt(String data) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
        
    private static final String allowedChars = "0123456789";
    private static Random r = new Random();
    public static String getRandomNumber(int length, boolean firstZeroAllowed)
    {
        char[] rand = new char[length];
        int i=0;
        if(!firstZeroAllowed)
        {
            rand[i++] = allowedChars.charAt(1+r.nextInt(allowedChars.length()-1));
        }
        for (; i < rand.length; i++)
        {
            rand[i] = allowedChars.charAt(r.nextInt(allowedChars.length()));
        }
        return new String(rand);
    }
    
    
    public static char getRandomChar(int start, int end)
    {     
        final String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        String newChars = allowedChars.substring(start, end);     
        
        char c = newChars.charAt(r.nextInt(newChars.length() -1 ));
        return c;
    }
    
    
    private static final SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
    private static final SimpleDateFormat shortForm = new SimpleDateFormat("dd MMM yyyy");
    
    static{
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        shortForm.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    public static String getLongDate(long milli){
        return df.format(new Date(milli));
    }
    
    public static String getShortDate(long milli){
        return shortForm.format(new Date(milli));
    }
    
    public static long getTimeInMillis(String date) throws ParseException{
        return df.parse(date).getTime();
    }
    
    public static long getTimeInMillisForShortDate(String date) throws ParseException{
        return shortForm.parse(date).getTime();
    }

    public static byte[] resizeArray(byte[] oldArr,int size){
        byte[] newArr = Arrays.copyOf(oldArr, oldArr.length + size);
        return newArr;
    }
    
 
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
    
}
