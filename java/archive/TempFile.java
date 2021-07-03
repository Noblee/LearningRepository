package archive;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TempFile {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥

    public static void main(String[] args) throws Exception {
        genKeyPair();
        String path = "/Users/noble/nobler.xyz.zip";
        String outpath = "/Users/noble/王宇131226607312.pdf";
        String out2path = "/Users/noble/王宇13122660731.pdf";
        final ArrayList<String> list = new ArrayList<>();
        Base64 base64 = new Base64();
        final FileInputStream fileInputStream = new FileInputStream(path);
        while (true) {
            byte[] bytes = new byte[64];
            if (fileInputStream.read(bytes) == -1) {
                break;
            }
            final String s = base64.encodeAsString(bytes);
            list.add(encrypt(s, keyMap.get(0)));
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(list);
        oos.flush();
        final FileOutputStream fileOutputStream = new FileOutputStream(outpath);
        final FileInputStream fileInputStream2 = new FileInputStream(out2path);

        final byte[] bytes = fileInputStream2.readAllBytes();
        final byte[] compress = GZIPUtils.compress(baos.toByteArray());
        System.out.println(bytes.length);
        fileOutputStream.write(bytes);
        fileOutputStream.write(compress);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {

        keyMap.put(0, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVHH9TIzItTW2HACP4HAW7I5iixwMI+kqVt8tyNWO6YggafRxfPf8/AUdKYwMg3wNjje7RXoW9MPNm6eU9IRG114YL15yNPsdCQqlo6hBJZxrh8IPjDascNaxu82s4MvV0MSeOYwZcWGnuWtYWteIsM1qMC45N96QOh45Dh3V2qQIDAQAB");  //

    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }


}
