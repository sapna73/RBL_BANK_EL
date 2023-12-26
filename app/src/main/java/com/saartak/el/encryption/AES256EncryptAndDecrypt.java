
package com.saartak.el.encryption;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256EncryptAndDecrypt {
    //Maintain in Config
  /*  private static String PASSWORD = "PASSWORD_VALUE";
    private static String SALT = "SALT_VALUE";*/

    private static String PASSWORD = "02e7ceb30f774e8c98549f183ee18744";
    private static String SALT = "90c48d1af15849fcba37345052e433c6";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encryptAndEncode(Cipher first, String raw) {
        try {
            byte[] encryptedVal = first.doFinal(getBytes(raw));

            String s = getString(Base64.getEncoder().encode(encryptedVal));
            return s;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decodeAndDecrypt(Cipher first, String encrypted) {
        try {
            byte[] decodedValue = Base64.getDecoder().decode(getBytes(encrypted));
            byte[] decValue = first.doFinal(decodedValue);
            return new String(decValue);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static String getString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "UTF-8");
    }

    private static byte[] getBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static CipherTokenPair getCipher(int mode, String token) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv;
        if (mode == Cipher.ENCRYPT_MODE) {
            SecureRandom randomSecureRandom = new SecureRandom();
            System.out.println(c.getBlockSize());
            iv = new byte[c.getBlockSize()];
            randomSecureRandom.nextBytes(iv);
            Base64.Encoder encoder = Base64.getEncoder();
            token = encoder.encodeToString(iv);
            System.out.println("token = " + token);
        } else {
            if (token == null || token.isEmpty()) {
                throw new RuntimeException("IV token is required to decrypt");
            }
            Base64.Decoder decoder = Base64.getDecoder();
            iv = decoder.decode(token);


        }
        c.init(mode,
                generateKey(),
                new IvParameterSpec(iv));
        return new CipherTokenPair(c, token);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Key generateKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        System.out.println("PBKDF2WithHmacSHA256");
        char[] password = PASSWORD.toCharArray();
        byte[] salt = getBytes(SALT);
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        byte[] encoded = tmp.getEncoded();

        Base64.Encoder encoder = Base64.getEncoder();
        String token = encoder.encodeToString(encoded);
        System.out.println("key = " + token);

        return new SecretKeySpec(encoded, "AES");
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String args[]) throws Exception {

        //Encryption
        CipherTokenPair getEncryptToken = getCipher(Cipher.ENCRYPT_MODE, null);
        String encryptedValue = encryptAndEncode(getEncryptToken.getCipher(), "{\\\"ClientID\\\":\\\"351210621104217\\\",\\\"CreatedBy\\\":\\\"RVF\\\",\\\"CreatedByProject\\\":\\\"\\\",\\\"CreatedDate\\\":\\\"2021-06-25T17:32:02\\\",\\\"ExternalCustomerId\\\":\\\"\\\",\\\"KYCId\\\":\\\"878612243160\\\",\\\"RequestString\\\":{\\\"hunterVerificationRetail\\\":{\\\"ModuleType\\\":\\\"Applicant\\\",\\\"appDte\\\":\\\"2021-06-24\\\",\\\"appVal\\\":\\\"0\\\",\\\"assOrigVal\\\":\\\"0\\\",\\\"classification\\\":\\\"ACCEPTED\\\",\\\"count\\\":\\\"1\\\",\\\"date\\\":\\\"2021-06-24\\\",\\\"identifier\\\":\\\"\\\",\\\"Item\\\":{\\\"JointApplicant\\\":[{\\\"DLNo\\\":\\\"\\\",\\\"Passport_Number\\\":\\\"\\\",\\\"RationCard_Number\\\":\\\"\\\",\\\"UId\\\":\\\"\\\",\\\"VoterId\\\":\\\"\\\",\\\"firstName\\\":\\\"BATTHINI\\\",\\\"income\\\":\\\"0\\\",\\\"ModuleType\\\":\\\"\\\",\\\"nationality\\\":\\\"INDIAN\\\",\\\"pan\\\":\\\"EAMPK9643D\\\",\\\"residentialAddress\\\":{\\\"address\\\":\\\"1 7 175,musheerabad  near pochamma templ\\\",\\\"city\\\":\\\"Hyderabad\\\",\\\"country\\\":\\\"INDIA\\\",\\\"pincode\\\":\\\"500020\\\",\\\"state\\\":\\\"Andhra Pradesh\\\"}}],\\\"MainApplicant\\\":{\\\"bankAccount\\\":{\\\"accountInteger\\\":\\\"\\\",\\\"bankName\\\":\\\"\\\"},\\\"businessTelephone\\\":\\\"\\\",\\\"dateOfBirth\\\":\\\"1949-11-19\\\",\\\"email\\\":{\\\"emailAddress\\\":\\\"\\\"},\\\"employer\\\":{\\\"employerAddress\\\":{\\\"address\\\":\\\"\\\",\\\"city\\\":\\\"\\\",\\\"country\\\":\\\"\\\",\\\"pincode\\\":\\\"\\\",\\\"state\\\":\\\"\\\"},\\\"orgName\\\":\\\"\\\"},\\\"firstName\\\":\\\"BATTHINI\\\",\\\"idDocument\\\":{\\\"docNumber\\\":\\\"\\\",\\\"recDocCode\\\":\\\"\\\"},\\\"lastName\\\":\\\"BAI\\\",\\\"mobile\\\":\\\"8919736877\\\",\\\"residentialAddress\\\":{\\\"address\\\":\\\"1 7 175,Bakaram  near by pochamma templ\\\",\\\"city\\\":\\\"Musheerabad\\\",\\\"country\\\":\\\"INDIA\\\",\\\"pincode\\\":\\\"500020\\\",\\\"state\\\":\\\"Andhra Pradesh\\\"}}},\\\"originator\\\":\\\"RBL\\\",\\\"product\\\":\\\"PL_I_ST\\\",\\\"submissionNotificationRqd\\\":\\\"1\\\",\\\"term\\\":\\\"0\\\"}},\\\"ServiceType\\\":\\\"HunterVerificationRetail\\\",\\\"UniqueId\\\":\\\"1624535282355351210621104217\\\",\\\"AADHAR\\\":\\\"878612243160\\\"}");
        System.out.println("String To Encrypt Value = " + encryptedValue);

        //Decryption
        CipherTokenPair getDecryptToken = getCipher(Cipher.DECRYPT_MODE, getEncryptToken.getToken());
        System.out.println("String To Decrypt Value = " + decodeAndDecrypt(getDecryptToken.getCipher(), encryptedValue));
    }

     /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

        CipherTokenPair getEncryptToken,getDecryptToken = null;
        try {
            getEncryptToken = Encryption.getCipher(Cipher.ENCRYPT_MODE, null);
            String encryptedValue = Encryption.encryptAndEncode(getEncryptToken.getCipher(), "Naveen");
            System.out.println("String To Encrypt Value = " + encryptedValue);

            getDecryptToken = Encryption.getCipher(Cipher.DECRYPT_MODE, getEncryptToken.getToken());
            String decryptedValue = Encryption.decodeAndDecrypt(getDecryptToken.getCipher(), encryptedValue);
            System.out.println("String To Decrypt Value = " + decryptedValue);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/
}
