
package com.leets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.leets.util.XML;

/**
*
* @author estidlore
*/
public class Main extends HttpServlet{
   private static final long serialVersionUID = 1L;
	
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        setAccessControlHeaders(response);
        try {
            String xml = request.getParameter("xml");
            if(xml != null) {
                // gets the user input
                String input = request.getParameter("SpeechResult");
                if(input == null) {
                    input = request.getParameter("Digits");
                }
                // redirect
                if(input == null) {
                    XML.redirectXML(response, XML.ERROR, new String[][]{
                            {"causa", "Entrada inválida"}
                    });
                } else {
                    System.out.println(xml);
                    System.out.println(input);
                    new Flow(response, request).exec(xml, input);
                }
            } else {
                XML.redirectXML(response, XML.ERROR, new String[][]{
                        {"causa", "Error desconocido"}
                });
            }
       } catch (IOException e) {
           System.out.println("Error " + e.toString());
           XML.redirectXML(response, XML.ERROR, new String[][]{
                    {"causa", "Error en el servidor"}
            });
       }
   }

   private void setAccessControlHeaders(HttpServletResponse response) {
       response.setHeader("Access-Control-Allow-Origin", "*");
       response.setHeader("Access-Control-Allow-Credentials", "true");
       response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
       response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
       response.setHeader("Content-Type", "application/xml; charset=utf-8");
   }
}