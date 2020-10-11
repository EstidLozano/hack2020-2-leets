
package com.leets.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.leets.util.Constants;

/**
 *
 * @author umansilla
 */
public class CPaaSWorkShopApi {

    public JSONObject crearllamadaActivaPorCallSidYTelefono(String callSid, String from)
            throws ClientProtocolException, IOException {
        final String URI = "https://" + Constants.FQDN
                + "/services/AAADEVCPaaSWorkShopAPI/ws/llamadasactivas";
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost postMethod = new HttpPost(URI);
        JSONObject jsonPayLoad = new JSONObject().put("callSid", callSid).put("telefonoLlamante", from);
        postMethod.addHeader("Authorization", "Bearer " + Constants.TOKEN);
        postMethod.addHeader("Content-Type", "application/json");
        final StringEntity payload = new StringEntity(jsonPayLoad.toString(), StandardCharsets.ISO_8859_1);
        postMethod.setEntity(payload);
        final HttpResponse response = client.execute(postMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
        return new JSONObject(result.toString());

    }

    public JSONObject editarEstatusLlamadaDeLlamadaActivaPorCallSid(String callSid, String estatus) throws ClientProtocolException, IOException {
        final String URI = "https://" + Constants.FQDN
                + "/services/AAADEVCPaaSWorkShopAPI/ws/llamadasactivas/" + callSid + "/callsid/estatusdial?estatus=" + estatus;
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPut putMethod = new HttpPut(URI);
        putMethod.addHeader("Authorization", "Bearer " + Constants.TOKEN);
        putMethod.addHeader("Content-Type", "application/json");
        final HttpResponse response = client.execute(putMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
        return new JSONObject(result.toString());
    }
}
