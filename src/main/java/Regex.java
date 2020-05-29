import java.io.*;

public class Regex {

    public static void main(String[] args) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("/Users/Noble/1.tex"));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("/Users/Noble/2.tex"));
        byte[] bytes = new byte[2048];
        int n = -1;
        StringBuilder sb = new StringBuilder();
        while ((n = in.read(bytes, 0, bytes.length)) != -1) {
            String str = new String(bytes, 0, n, "UTF-16");
            sb.append(str);
        }
        String str = sb.toString();
        StringBuilder ssb = new StringBuilder();
        for (int i = 0; i < 20000000; i++) {
            try {
                ssb.append(str.charAt(i));
                int temp = str.codePointAt(i);
                //代表是汉字
                if ((temp & 0xffff00) != 0) {
                    if (str.charAt(i + 2) == ' ') {
                        if (str.charAt(i + 1) == ',') {
                            ssb.append('，');
                            i += 2;
                        }
                        if (str.charAt(i + 1) == '.') {
                            ssb.append('。');
                            i += 2;
                        }
                        if (str.charAt(i + 1) == ';') {
                            ssb.append('；');
                            i += 2;
                        }
                        if (str.charAt(i + 1) == ':') {
                            ssb.append('：');
                            i += 2;
                        }
                    } else {
                        if (str.charAt(i + 1) == ',') {
                            ssb.append('，');
                            i += 1;
                        }
                        if (str.charAt(i + 1) == '.') {
                            ssb.append('。');
                            i += 1;
                        }
                        if (str.charAt(i + 1) == ';') {
                            ssb.append('；');
                            i += 1;
                        }
                        if (str.charAt(i + 1) == ':') {
                            ssb.append('：');
                            i += 1;
                        }
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        out.write(ssb.toString().getBytes(), 0, ssb.toString().getBytes().length);
    }
}
