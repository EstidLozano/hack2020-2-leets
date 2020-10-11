
package com.leets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.leets.util.InboundXML;
import static com.leets.util.InboundXML.ERROR;

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
            String options = "abcdefghijklmnopqrs",
            		digits = "amnpr",
                    option = request.getParameter("option");
            if(option != null && options.contains(option)) {
                String input = request.getParameter(digits
                		.contains(option)? "Digits" : "SpeechResult");
                if (input != null) {
                    System.out.println(option);
                    System.out.println(input);
                    new Flow(response, request).exec(option, input);
                }
            } else {
                InboundXML.redirectError(response, "Opción inválida");
            }
       } catch (Exception e) {
           System.out.println("Error " + e.toString());
           InboundXML.redirectError(response, "Error en el servidor");
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
