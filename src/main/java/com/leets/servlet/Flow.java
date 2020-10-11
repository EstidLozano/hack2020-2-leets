package com.leets.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.leets.model.Inscrito;
import com.leets.model.Registraduria;
import com.leets.util.CPaaSApi;
import com.leets.util.CPaaSWorkshopApi;
import com.leets.util.FirebaseApi;
import com.leets.util.InboundXML;

/**
 *
 * @author estidlore
 */
public class Flow {
    
    private final HttpServletResponse response;
    private final HttpServletRequest request;

    public Flow(HttpServletResponse response, HttpServletRequest request) {
            this.response = response;
            this.request = request;
    }

    public void exec(String option, String input) throws IOException {
        String digits = "amnp";
        FirebaseApi fbApi = FirebaseApi.getInstance();
        if(option.contains("a")) {
        	Inscrito inscrito = fbApi.obtenerInscrito(input);
        	if(inscrito == null) {
        		Registraduria registraduria = fbApi.obtenerRegistraduria(input);
        		if(registraduria == null) {
        			InboundXML.redirectXML(response, InboundXML.ID);
        		} else {
        			InboundXML.redirectHacerRegistro(response,
        					registraduria.getId(), registraduria.getNombre());
        		}
        	}
        } else if(option.contains("b")) {
            
        } else if(option.contains("c")) {
            
        } else if(option.contains("d")) {
            
        } else if(option.contains("e")) {
            
        } else if(option.contains("f")) {
            
        } else if(option.contains("g")) {
            
        } else if(option.contains("h")) {
            
        } else if(option.contains("i")) {
            
        } else if(option.contains("j")) {
            
        } else if(option.contains("k")) {
            
        } else if(option.contains("l")) {
            
        } else if(option.contains("m")) {
            
        } else if(option.contains("n")) {
            
        } else if(option.contains("o")) {
            
        } else if(option.contains("p")) {
            
        } else if(option.contains("q")) {
            
        } else if(option.contains("r")) {
            
        } else if(option.contains("s")) {
            
        }
    }

	/*public void analizarYResponderNivelDosIVRExampleB() throws Exception {
		if (request.getParameter("Digits") != null) {
			String numeroDeCuenta = request.getParameter("Digits");
			// OBTENER USUARIO POR NUMERO DE CUENTA CPAAS WORKSHOP API
			JSONObject jsonObjectResponse = new CPaaSWorkshopApi().obtenerUsuarioPorNumeroDeCuenta(numeroDeCuenta);
			if (jsonObjectResponse.has("status") && jsonObjectResponse.getString("status").equals("ok")) {
				// SETEAMOS EL PIN DEL USUARIO POR NUMERO DE CUENTA
				JSONObject jsonObjectResponsePIN = new CPaaSWorkshopApi()
						.crearNuevoPinRandomAUsuarioPorNumeroDeCuenta(numeroDeCuenta);
				if (jsonObjectResponsePIN.has("status") && jsonObjectResponsePIN.getString("status").equals("ok")) {
					// DEBEMOS VALIDAR SI EL TELEFONO DEL USUARIO ES TELEFONO MOVIL
					JSONObject jsonResponseCarrierLookUp = new CPaaSApi().getCarrierLookUpPorTelefono(
							jsonObjectResponse.getJSONObject("usuario").getString("telefonomovil"));
					if (jsonResponseCarrierLookUp.has("carrier_lookups")) {
						if (jsonResponseCarrierLookUp.getJSONArray("carrier_lookups").getJSONObject(0)
								.getBoolean("mobile")) {
							// ENVIAMOS SMS AL TELEFONO REGISTRADO
							new CPaaSApi().sendSMSPinRandom(
									jsonObjectResponsePIN.getJSONObject("pinRandomModel").getString("pinRandom"),
									jsonObjectResponse.getJSONObject("usuario").getString("telefonomovil"));
							// RESPONSEMOS AGREGANDO COMO PARAMETRO EL NUMERO DE CUENTA
							response.getWriter().println(InboundXML.redirectXML(InboundXML
									.setNumeroDeCuentaEnXML(numeroDeCuenta)));
						} else {
							System.out.println("El telefono registado como movil no es realmente movil");
							response.getWriter()
									.println(InboundXML.redirectXML(InboundXML.ERROR,
											"El teléfono movil registrado no es un teléfono movil"));
						}

					} else {
						System.out.println("Error en la petición HTTP getCarrierLookUpPorTelefono");
						response.getWriter().println(InboundXML.redirectXML(InboundXML.ERROR,
								"Error en la aplicación"));
					}

				} else {
					System.out.println("Error en la petición HTTP crearNuevoPinRandomAUsuarioPorNumeroDeCuenta");
					response.getWriter().println(InboundXML
							.redirectXML(InboundXML.MENU));
				}
			} else {
				//CUANDO NO EXISTE EL NUMERO DE CUENTA
				System.out.println("Error en la petición HTTP obtenerUsuarioPorNumeroDeCuenta");
				response.getWriter().println(InboundXML
						.redirectXML(InboundXML.MENU));
			}

		} else {
			response.getWriter().println(InboundXML
					.redirectXML(InboundXML.MENU));
		}
	}

	public void analizarYResponderNivelTresIVRExampleB() throws IOException {
		if (request.getParameter("Digits") != null && request.getParameter("numerodecuenta") != null) {
			// OBTENER USUARIO POR NUMERO DE CUENTA CPAAS WORKSHOP API
			JSONObject jsonObjectResponse = new CPaaSWorkshopApi()
					.obtenerUsuarioPorNumeroDeCuenta(request.getParameter("numerodecuenta"));
			if (jsonObjectResponse.has("status") && jsonObjectResponse.getString("status").equals("ok")) {
				// String pinRandom = request.getParameter("Digits");
				// VALIDAMOS QUE EL PIN CORRESPONDA AL NUMERO DE CUENTA
				JSONObject jsonObjectResponsePinValidation = new CPaaSWorkshopApi().validarElPinRandomPorNumeroDeCuenta(
						request.getParameter("numerodecuenta"), request.getParameter("Digits"));
				if (jsonObjectResponsePinValidation.has("status")
						&& jsonObjectResponsePinValidation.getString("status").equals("ok")) {

					if (jsonObjectResponsePinValidation.getJSONObject("pinRandomModel").getString("estatusPin")
							.equals("VALID")) {
						response.getWriter().println(InboundXML.redirectXML(
								InboundXML.SALDO
										+ "?nombreUsuario="
										+ jsonObjectResponse.getJSONObject("usuario").getString("nombre")
										+ "&saldoActual="
										+ jsonObjectResponse.getJSONObject("usuario").getString("saldoactual")));
					} else {
						System.out.println("El telefono registado como movil no es realmente movil");
						response.getWriter().println(InboundXML.redirectXML(InboundXML.ERROR,
								"El Pin no corresponse con el pin enviado, favor de intentar nuevamente."));
					}
				} else {
					response.getWriter().println(InboundXML
							.redirectXML(InboundXML.MENU));
				}
			} else {
				System.out.println("El telefono registado como movil no es realmente movil");
				response.getWriter().println(InboundXML.redirectXML(InboundXML.ERROR,
						"No Ha dado su numero de cuenta."));
			}

		} else {
			response.getWriter().println(InboundXML
					.redirectXML(InboundXML.MENU));
		}
	}*/
}
