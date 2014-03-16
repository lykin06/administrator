package Serialization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Serialize and deserialize an object in a document .txt
 * 
 * @author Amir BEN SLIMANE
 *
 */
abstract public class Text {
	static final long serialVersionUID = 2000000L;
	
	public static boolean store(String[] data, String name) {
		if (data == null) {
			return false;
		}

		BufferedReader in = null;
		PrintWriter printWrite = null;
		FileWriter fileWriter = null;
		BufferedWriter out = null;
		StringReader stringReader = null;

		String str;

		name = "Data/" + name + ".txt";

		try {
			fileWriter = new FileWriter(name, true);
			out = new BufferedWriter(fileWriter);
			printWrite = new PrintWriter(out);
		} catch (Exception e) {
		}

		printWrite.println();
		
		for (int i = 0; i < data.length; i++) {
			try {
				stringReader = new StringReader(data[i]);
				in = new BufferedReader(stringReader);
				str = in.readLine();
				if (i != data.length - 1) {
					printWrite.print(str + " ; ");
				} else {
					printWrite.print(str);
				}
			} catch (Exception e) {
			}
		}
		
		printWrite.close();
		return true;
	}

	public static String[] load(String name) {
		BufferedReader in = null;
		FileReader file = null;

		String[] res = null;
		int cpt = 0;

		name = "Data/" + name + ".txt";

		try {
			file = new FileReader(name);
			in = new BufferedReader(file);

			while (in.readLine() != null) {
				++cpt;
			}

			res = new String[cpt];

			file = new FileReader(name);
			in = new BufferedReader(file);

			for (int i = 0; i < cpt; ++i) {
				res[i] = in.readLine();
			}

			in.close();
		} catch (Exception e) {
		}

		return res;
	}
	
	public static boolean deleteLine(String fileName, int lineNumber) {
		fileName = "Data/" + fileName + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
 
            StringBuffer sb = new StringBuffer(); 
            String line;    
            int nbLinesRead = 0;       
            while ((line = reader.readLine()) != null) {
                if (nbLinesRead != lineNumber) {
                    sb.append(line + "\n");
                }
                nbLinesRead++;
            }
            reader.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(sb.toString());
            out.close();
 
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}