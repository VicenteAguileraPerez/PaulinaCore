/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.vicenteaguileraperez.paulinacore;

import com.github.vicenteaguileraperez.paulinacore.enums.Options;
import com.github.vicenteaguileraperez.paulinacore.interfaces.Something;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import javax.annotation.Nonnull;


/**
 * @author Vicente Aguilera Pérez
 * @version 1.0.0
 * Date December 18th, 2020
 */
public class FirestoreFunctions 
{

    private Something something;

    public void setListenerAny(Something something)
    {
        this.something=something;
    }

    public  void addData(String collection, String document, Map<String, Object> data)
    {
        DocumentReference docRef = FirestoreClient.getFirestore().collection(collection).document(document);
       
        ApiFuture<WriteResult> result = docRef.set(data);
       
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            
            something.status(Options.ADD,true);
        }
        catch (InterruptedException e)
        {
            something.status(Options.ADD,false);
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            something.status(Options.ADD,false);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            something.status(Options.ADD,false);
        }
    }
    public void updateData(String collection, String document, Map<String, Object> data)
    {

        DocumentReference docRef = FirestoreClient.getFirestore().collection(collection).document(document);

        ApiFuture<WriteResult> future = docRef.update(data);

        try {
            
            WriteResult result = future.get();
            System.out.println("Write result: " + result);
            something.status(Options.UPDATE,true);
        }
        catch (InterruptedException e)
        {
            something.status(Options.UPDATE,true);
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            something.status(Options.UPDATE,true);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            something.status(Options.UPDATE,true);
        }

    }
    public void deleteData(String collection,String document) {

        ApiFuture<WriteResult> result = FirestoreClient.getFirestore().collection(collection).document(document).delete();
       
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            something.status(Options.DELETE,true);
        } catch (InterruptedException e) {
           something.status(Options.DELETE,false);
            e.printStackTrace();
        } catch (ExecutionException e) {
           something.status(Options.DELETE,false);
            e.printStackTrace();
        } catch (Exception e) {
           something.status(Options.DELETE,false);
        }
    }
    public  void getData(String collection,String document,@Nonnull Class<?> valueType,String idField) throws ExecutionException, InterruptedException {

        DocumentReference docRef = FirestoreClient.getFirestore().collection(collection).document(document);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot documentSnapshot = future.get();
        if (documentSnapshot.exists()) 
        {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Map<String,Object> mapData =documentSnapshot.getData();
            mapData.put(idField,documentSnapshot.getId());
            String jsonString = gson.toJson(mapData);
            something.getSomething(gson.fromJson(jsonString, valueType));
            
            System.out.println("Document data: " +mapData);
        } else {
            System.out.println("No such document!");
            something.getSomething(null);
        }

    }
    public void getDocuments(String collection, ArrayList<Object> values,Class<?> valueType,String idField) throws ExecutionException, InterruptedException
    {
        
        ApiFuture<QuerySnapshot> future = FirestoreClient.getFirestore().collection(collection).get();
       
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        
            
        documents.forEach(new Consumer<QueryDocumentSnapshot>() {
            @Override
            public void accept(QueryDocumentSnapshot document) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Map<String,Object> mapData =document.getData();
                mapData.put(idField,document.getId());
                String jsonString = gson.toJson(mapData);
                values.add(gson.fromJson(jsonString,valueType));
            }
        });
        something.getAll(values);

    }
}
