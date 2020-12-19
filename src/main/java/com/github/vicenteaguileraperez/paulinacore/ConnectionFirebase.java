package com.github.vicenteaguileraperez.paulinacore;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Vicente Aguilera Pérez
 * @version 1.0.0
 * Date December 18th, 2020
 */

public class ConnectionFirebase
{
    /**
     * Creates a new <code>File</code> instance from a called jsonName with 
     * data (JsonData)
     *
     * @param jsonName
     * @param jsonData
     * @return <code>true</code> if and only if the file denoted is created
     *      in the path where the jar is .<code>false</code> otherwise
     */
    public static boolean createJson(String jsonName,String jsonData)
    {
        File file= new File(System.getProperty("user.dir"), "firestore-java-tablescoder-firebase-adminsdk-jq2wf-53f2e4b4b8.json");
        try
         {
          
            if(!file.exists()){
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(jsonData);
                bw.close();
                return true;
            }
            else return file.exists();
         }
         catch(IOException e)
         {
             return false;
         }
         catch(Exception e)
         {
             return false;
         }
    }
    /**
     * Creates a default <code>FirebaseApp</code> instance with the information 
     * of the jsonName (don't put the extension only the name of the file) 
     * and  the url of the database nameProject
     *
     * 
     * @param jsonName Name of the file json getting in firebase 
     * @param nameProject Name of the project to access to the Realtime database
     */
    public static void iniciarFirebase(String jsonName, String nameProject)
    {
        FileInputStream serviceAccount = null;
        try {

            serviceAccount = new FileInputStream(jsonName+".json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://"+nameProject+".firebaseio.com")
                    .setStorageBucket(nameProject+".appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
           
        }
        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

        } catch (IOException ex) {

        }
    }
    /**
     * Creates a default <code>FirebaseApp</code> instance with the information 
     * of the File instances where the information of the jsonFile is.
     * and  the url of the database nameProject
     *
     * 
     * @param file instance where the information  ot he jsonFile is
     * @param nameProject Name of the project to access to the Realtime database
     */
    public static void iniciarFirebase(File file, String nameProject)
    {
        FileInputStream serviceAccount = null;
        try {

            serviceAccount = new FileInputStream(file);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://"+nameProject+".firebaseio.com")
                    .setStorageBucket(nameProject+".appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println(FirestoreClient.getFirestore());
        }
        catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

        } catch (IOException ex) {

        }
    }
    /**
     * 
     * @return Returns the Firestore instance associated with the default Firebase app.
     */
    public static Firestore getInstance()
    {
        return FirestoreClient.getFirestore();
    }
}
