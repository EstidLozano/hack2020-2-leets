
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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
    	String key = "{\r\n"
    			+ "  \"type\": \"service_account\",\r\n"
    			+ "  \"project_id\": \"hack2020-2-leets\",\r\n"
    			+ "  \"private_key_id\": \"1221c660f1bc5492ece5e96c65cdbc4fd3dfbc6b\",\r\n"
    			+ "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDrFsPqzcePNqP4\\nwh70Whizv7KG0uqnvyiKH/8kawO9nx6hLlbQ2JpZi3UzaMOOXmP1iFPOYJSeum4K\\nJRygaXw/i08hSVY/OgAJfCg2WmPJwUBJQa0V6Dqjr2iswolIako9brR/Mju2APv8\\n+mFE+YHEiGQre6mWBUiA9kj2q+H6MT/9SYcvCnGzp/i99DmOgmD2qED6/pHg8zvv\\nPYNbC1t4NijmhhisIwNjQSYCrtZrl0T+alG3OuYS+Hef0vB1DmV+m0xV4MxEPJOk\\nqbF6zhe+YArSXXT5mgCmoc9CYaRP86Tc3BHjq9zhLMhbw7CRbYhqiFffPL7NPARb\\n9LsCvzeTAgMBAAECggEAJC7p0q7/gxxu2m/OYd+oI6piqPu3oD/81l2cYJeUCNB/\\n32EJfbWI9DVRwarjRntiuTzVYVNeLJwXKbqSkGIL/J/NexsVij+8F7mjWtJIv5nd\\n8iiIonGO7PinjMeYZ6vsZ04tu9IYfKfkIQjMhdg9T+mKOWMcM/n2Mds2WbkU2/QD\\nB48ufN6pcZ2iaUKvId9XQFx2qYv6qFAgN7fgvXT5GdE6HL/ed5lpsfkjtmbEpzdL\\n2LRx4jVmqBmarYH1GboGoK+frAMWwqJnV0LlrxwlY2+WY0QGCsgrytlLto9rWPYT\\n6V4qs841KU5RbMY+Ek/gCfXGorHvcYQx0DZITm8wgQKBgQD/lz08eHinKKuBvJKH\\n6/JNh51ETYKID8UReOdHDped2b92S2FXAOkKQxjA6/KUl+Jd03/vAB9b4fBmU/sP\\nw6zzIfvfvytfIE4OtFVfmaDfTVKmISBp1DEOcsnIJKNaKNNv0yRjqyE/db7LA/0e\\n/d7mtqwP3chMfttUFCuuBAKHwQKBgQDrdx9zswM6bsm0rfUwFd+2c7HNzVGzXj5R\\nptWGprU0cyIPURzoqjBfUV9MThNBclpzL0X9VYruuzBzlkorkc5DhOSmSO1xqjV6\\n7ClAspww6iMY1m8VYP6xtqVhzLrDqKaEYy1ctB11tqq0GJ2CTzZamCAuT1rCXbdG\\nKN79mNo0UwKBgAUI6qjlSqMoVvCQUWaiDDzgvgVZbYfWn7AvbIbmz6/JWU8wPvOi\\nzfRoy/UI8NdigpAMmYmQvA1oTv8FCn4OAbssYTTQJD+UeFUr0j623QdpE4a4QlYz\\nWuAI/NaXeixNf5TFWQMpnNcOL7r6EiNpJtnuoUcSvF5dZV6rowyjgZVBAoGACi+O\\nAcBFrHpsaU95IspfchIRJ/jwxtSV14xjAj8l6Y3tdEIrAPmTvBvWBoP+Gz+qH/90\\nhJ/1sZwRXX6r2LSm+o6+IV9YPimBxL6AjlbroMYQHJvyFDes7N0czF1B2wLpqSJt\\nLOxxtczgF4hVD/UDQeoAwmAWagAJ1JQiCVBaaBcCgYEAlquQvx4niCdn6jZSY0uK\\nE+lQvEYk6zedxIXwoEKR5eENapvZwFYx0tcBEWraw8BXbFg31vtvsVi+uTLboPf7\\nNUgdQ846qkTqVQJWKQ5Rf4h9vAMDdld0bnKgHwaErF1vZZZP0BPU5X+T0fpGAgBD\\n2ubR8kqe9MrvoiecOHeS6XI=\\n-----END PRIVATE KEY-----\\n\",\r\n"
    			+ "  \"client_email\": \"firebase-adminsdk-4xm4l@hack2020-2-leets.iam.gserviceaccount.com\",\r\n"
    			+ "  \"client_id\": \"102133138429616208213\",\r\n"
    			+ "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n"
    			+ "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n"
    			+ "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n"
    			+ "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-4xm4l%40hack2020-2-leets.iam.gserviceaccount.com\"\r\n"
    			+ "}\r\n"
    			+ "";
        InputStream serviceAccount = null;
        try {
            serviceAccount = new ByteArrayInputStream(key.getBytes());
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