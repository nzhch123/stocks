package invest.utils;

import java.io.*;

public class SerializeUtils {

    public static void serializeObject(Object obj, String path) throws IOException {
        ObjectOutputStream oout=null;
        try {
            File file = new File(path);
            oout = new ObjectOutputStream(new FileOutputStream(file));
            oout.writeObject(obj);
        } finally {
            oout.close();
        }

    }

    public static Object deserializeObject(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream oin=null;
        try {
            oin = new ObjectInputStream(new FileInputStream(path));
            Object object= oin.readObject();
            return object;
        }finally {
            oin.close();
        }
    }

}
