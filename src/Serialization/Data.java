package Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialize and deserialize an object in a document .txt
 * 
 * @author Amir BEN SLIMANE
 */
abstract public class Data {
    static final long serialVersionUID = 2000200L;

    public static boolean store(Object data, String name) {
        FileOutputStream file = null;
        ObjectOutputStream out = null;

        if (data == null) {
            return false;
        }

        name = "Data/" + name + ".data";

        try {
            file = new FileOutputStream(name);
            out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Object load(String name) {
        FileInputStream file = openFile(name);
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            return in.readObject(); // TODO cette ligne plante !!!
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static FileInputStream openFile(String name) {
        StringBuilder str = new StringBuilder();
        str.append("Data/");
        str.append(name);
        str.append(".data");

        String fileName = str.toString();

        try {
            return new FileInputStream(fileName);
        } catch (Exception e1) {
            System.err.println("Unable to open file - try something else");

            str = new StringBuilder();
            str.append(name);
            str.append(".data");

            fileName = str.toString();
            try {
                return new FileInputStream(fileName);
            } catch (Exception e2) {
                System.err.println("Unable to open file");
                return null;
            }
        }
    }
}
