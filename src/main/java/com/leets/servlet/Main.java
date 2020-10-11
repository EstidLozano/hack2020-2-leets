
package com.leets.servlet;

/**
 *
 * @author estidlore
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.leets.http.CPaaSWorkShopApi;
import com.leets.util.HttpResponse;


/**
 *
 * @author estidlore
 */
public class Main extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * CREAMOS METODO POST PARA RECIBIR PETICIONES HTTP
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// INDICAMOS HEADERS DE LA RESPUESTA QUE SE ENTREGARÁ.
		setAccessControlHeaders(response);
		try {
			// VALIDAMOS QUE LA PETICIÓN HTTP RECIBIDA POR CPAAS TENGA LOS PARÁMETROS
			// MÍNIMOS QUE NECESITAMOS PARA PROCESAR LA PETICIÓN AL API
			// AAADEVCPaaSWorkShopAPI
			if (request.getParameter("From") != null && request.getParameter("CallSid") != null
					&& request.getParameter("status") != null) {
				// DE ACUERDO AL VALOR DE STATUS VAMOS A ESCRIBIR EN LA BD O EDITAR EL ESTATUS.
				switch (request.getParameter("status")) {
				// ESTE CASO CREARA UNA LLAMADA DE ENTRADA CADA QUE SE INICIA UNA LLAMADA EN
				// CPAAS
				case "initCall":
					System.out.println("Create ActiveCall " + request.getParameter("From"));
					// SE REALIZA PETICIÓN HTTP HACIA EL API DE LA BD PARA CREAR LA LLAMADA ACTIVA.
					new CPaaSWorkShopApi().crearllamadaActivaPorCallSidYTelefono(request.getParameter("CallSid"),
							request.getParameter("From"));
					// SE RESPONDE QUE LA LLAMDA ACTIVA FUE CREADA CORRECTAMENTE
					response.getWriter().println(HttpResponse.RESPONSE_200("Llamada activa creada"));
					break;
				// ESTE CASO MODIFICA UNA LLAMADA DE ACUERDO A EL CALL SID QUE RECIBE DESDE
				// CPAAS
				case "finishCall":
					// SE REALIZA PETICIÓN HTTP HACIA EL API DE LA BD PARA ACTUALIZAR EL ESTATUS DE
					// LA LLAMADA ACTIVA.
					new CPaaSWorkShopApi().editarEstatusLlamadaDeLlamadaActivaPorCallSid(
							request.getParameter("CallSid"), "TERMINADA");
					// SE RESPONDE QUE LA LLAMDA ACTIVA FUE MODIFICADA CORRECTAMENTE
					response.getWriter().println(HttpResponse.RESPONSE_200("Llamada activa terminada"));
					break;
				default:
					break;
				}
			} else {
				// RESPUESTA DE ERROR "BadRequest" EN CASO QUE LA PETICIÓN RECIBIDA NO CONTENGA
				// LOS PARÁMETROS MÍNIMOS REQUERIDOS.
				response.getWriter().println(HttpResponse.RESPONSE_400("BadRequest"));
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
			// RESPUESTA EN CASO DE EXISTIR ERRORES EN TIEMPO DE EJECUCION
			response.getWriter().println(HttpResponse.RESPONSE_500(e.toString()));
		}

	}

	private void setAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		response.setHeader("Content-Type", "application/json; charset=utf-8");
	}

}
