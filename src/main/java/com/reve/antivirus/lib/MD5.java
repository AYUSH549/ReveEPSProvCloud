
package com.reve.antivirus.lib;
import java.io.IOException;
import java.security.MessageDigest;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MD5
{
    String key="@d$f@ere42u^&v$#2)*!54";
    public String encode(String s, String key)
    {
        return base64Encode(xorWithKey(s.getBytes(), key.getBytes()));
    }

    public String decode(String s, String key)
    {
        return new String(xorWithKey(base64Decode(s), key.getBytes()));
    }

    private byte[] xorWithKey(byte[] a, byte[] key)
    {
        byte[] out = new byte[a.length];
        int l = a.length;
        for (int i = 0; i < l; i++)
        {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    private byte[] base64Decode(String s)
    {
        try
        {
            BASE64Decoder d = new BASE64Decoder();
            return d.decodeBuffer(s);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String base64Encode(byte[] bytes)
    {
        BASE64Encoder enc = new BASE64Encoder();
        return enc.encode(bytes).replaceAll("\\s", "");
    }

    private static String convertToHex(byte[] data)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++)
        {
            int halfOfByte = (data[i] >>> 4) & 0x0F;
            int twoHalfBytes = 0;

            do
            {
                if ((0 <= halfOfByte) && (halfOfByte <= 9))
                {
                    buf.append((char) ('0' + halfOfByte));
                }

                else
                {
                    buf.append((char) ('a' + (halfOfByte - 10)));
                }

                halfOfByte = data[i] & 0x0F;

            }
            while (twoHalfBytes++ < 1);
        }
        return buf.toString();
    }

    public static String getHash(String text)
    {
        MessageDigest md;
        MD5 m = new MD5();
        try
        {
            md = MessageDigest.getInstance("MD5");
            byte[] md5 = new byte[64];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            md5 = md.digest();
            String hash = convertToHex(md5);
            hash = m.encode(hash, m.key);
            return hash;

        }
        catch (Exception ex)
        {
            return null;
        }
    }
    
    public static String getHashOnly(String text)
    {
        MD5 m = new MD5();
        try
        {
            return m.decode(text, m.key);
        }
        catch (Exception ex)
        {
           
            return null;
        }
    }
}
