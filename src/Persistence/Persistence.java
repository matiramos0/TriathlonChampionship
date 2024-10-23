package Persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.Championship;

public class Persistence {
	
	
		
	    public static void championshipPersistence(Championship championship) {
	        try {
	            FileOutputStream fos = new FileOutputStream("championship.file");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(championship);
	            oos.flush();
	            oos.close();
	        } catch (Exception e) {
	            System.out.println("Excepcion during the serialitation process: " + e);
	            System.exit(0);
	        }
	    }

	    public static Championship ChampionshipDeserialization() {
	        Championship championship = null;
	        try {
	            FileInputStream fis = new FileInputStream("championship.file");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            championship = (Championship) ois.readObject();
	            ois.close();
	        } catch (Exception e) {
	            System.out.println("Exception during deserialization: " + e);
	            System.exit(0);
	        }
	        return championship;
	    }
}
