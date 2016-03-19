package org.xxz.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.Charsets;

/**
 * 加密工具类
 * @author xxzkid
 */
public final class EncyptUtil {
    
    private static final char[] DIGITS_LOWER =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    /**
     * md5加密
     * @param data
     * @return
     */
    public static String md5Hex(final String data) {
        return new String(encodeHex(md5(data), DIGITS_LOWER));
    }
    
    @SuppressWarnings("deprecation")
    private static byte[] md5(final String data) {
        return md5(data.getBytes(Charsets.UTF_8));
    }
    
    private static byte[] md5(final byte[] data) {
        return getDigest("md5").digest(data);
    }
    
    private static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
    
    public static void main(String[] args) {
        String str = "test";
        System.out.println(md5Hex(str));
    }

}
