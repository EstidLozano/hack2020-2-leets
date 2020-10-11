
package com.leets.util;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.leets.model.Dane;
import com.leets.model.Familiar;
import com.leets.model.Inscrito;
import com.leets.model.Registraduria;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author estidlore
 */
public class FirebaseApi {
	
	private final static String
			DANE = "dane",
			FAMILIARES = "familiares",
			INSCRITOS = "inscritos",
			REGISTRADURIA = "registraduria";
	
	private static FirebaseApi instance = null;
	
	public static FirebaseApi getInstance() {
		if(instance == null) {
			instance = new FirebaseApi();
		}
		return instance;
	}
	
	private FirebaseApi() {
		init();
	}
    
    public void init() {
        InputStream serviceAccount = null;
        try {
            serviceAccount = this.getClass().getClassLoader()
            		.getResourceAsStream("./serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://hack2020-2-leets.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
        	System.out.println("Error " + e.toString());
        }
    }
    
    public String guardarFamiliar(Familiar familiar) {
    	Firestore store = FirestoreClient.getFirestore();
    	ApiFuture<WriteResult> future = store.collection(FAMILIARES)
    			.document(String.valueOf(familiar.getId())).set(familiar);
    	try {
			return future.get().getUpdateTime().toString();
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
			return null;
		}
    }
    
    public String guardarInscrito(Inscrito inscrito) {
    	Firestore store = FirestoreClient.getFirestore();
    	ApiFuture<WriteResult> future = store.collection(INSCRITOS)
    			.document(String.valueOf(inscrito.getId())).set(inscrito);
    	try {
			return future.get().getUpdateTime().toString();
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
			return null;
		}
    }
    
    public String actualizarFamiliar(String id, String field, String info) {
    	Firestore store = FirestoreClient.getFirestore();
    	ApiFuture<WriteResult> future = store.collection(FAMILIARES).document(id).update(field, info);
    	try {
			return future.get().getUpdateTime().toString();
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
    	return null;
    }
    
    public String actualizarInscrito(String id, String field, String info) {
    	Firestore store = FirestoreClient.getFirestore();
    	ApiFuture<WriteResult> future = store.collection(INSCRITOS).document(id).update(field, info);
    	try {
			return future.get().getUpdateTime().toString();
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
    	return null;
    }
    
    public Dane obtenerDane(String id) {
    	Firestore store = FirestoreClient.getFirestore();
    	DocumentReference docRef = store.collection(DANE).document(id);
    	ApiFuture<DocumentSnapshot> future = docRef.get();
    	Dane dane = null;
    	try {
			DocumentSnapshot doc = future.get();
			if(doc.exists()) {
				dane = doc.toObject(Dane.class);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
    	return dane;
    }
    
    public Familiar obtenerFamiliar(String id) {
    	Firestore store = FirestoreClient.getFirestore();
    	DocumentReference docRef = store.collection(FAMILIARES).document(id);
    	ApiFuture<DocumentSnapshot> future = docRef.get();
    	Familiar familiar = null;
    	try {
			DocumentSnapshot doc = future.get();
			if(doc.exists()) {
				familiar = doc.toObject(Familiar.class);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
    	return familiar;
    }
    
    public Inscrito obtenerInscrito(String id) {
    	Firestore store = FirestoreClient.getFirestore();
    	DocumentReference docRef = store.collection(INSCRITOS).document(id);
    	ApiFuture<DocumentSnapshot> future = docRef.get();
    	Inscrito inscrito = null;
    	try {
			DocumentSnapshot doc = future.get();
			if(doc.exists()) {
				inscrito = doc.toObject(Inscrito.class);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
    	return inscrito;
    }
    
    public Registraduria obtenerRegistraduria(String id) {
    	Firestore store = FirestoreClient.getFirestore();
    	DocumentReference docRef = store.collection(REGISTRADURIA).document(id);
    	ApiFuture<DocumentSnapshot> future = docRef.get();
    	Registraduria registraduria = null;
    	try {
			DocumentSnapshot doc = future.get();
			if(doc.exists()) {
				registraduria = doc.toObject(Registraduria.class);
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
    	return registraduria;
    }
 
}