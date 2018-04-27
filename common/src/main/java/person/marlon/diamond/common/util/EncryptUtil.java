package person.marlon.diamond.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    public static final String MD5 = "MD5";
    public static final String SHA256 = "SHA-256";

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     * @param strSrc   要加密的字符串
     * @param encName   加密类型
     * @return default sha-256
     */
    public static String encrypt(String strSrc, String encName) {//"SHA-256","MD5"
        MessageDigest md = null;

        try {
            if (encName == null || encName.equals("")) {
                encName = SHA256;
            }
            md = MessageDigest.getInstance(encName);
            md.update(strSrc.getBytes( "UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return null;
        }
        return bytes2Hex(md.digest()); // to HexString;
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
                sb.append("0").append(Integer.toHexString(0xFF & aByteArray));
            } else {
                sb.append(Integer.toHexString(0xFF & aByteArray));
            }
        }
        return sb.toString();
    }
}
