package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{
    static {
        System.out.println("MyClassLoader");
    }
    public static final String driver = "src/main/others/";
    public static final String fileTyep = ".class";

    @Override
    public Class findClass(String name) {
        byte[] data = loadClassData(name);
        return defineClass(data, 0, data.length);
    }

    public byte[] loadClassData(String name) {
        FileInputStream fis = null;
        byte[] data = null;
        try {
            File file = new File(driver + name + fileTyep);
            System.out.println(file.getAbsolutePath());
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("loadClassData-IOException");
        }
        return data;
    }
}