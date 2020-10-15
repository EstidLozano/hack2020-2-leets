package com.leets.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.leets.model.Dane;
import com.leets.model.Familiar;
import com.leets.model.Inscrito;
import com.leets.model.Registraduria;
import com.leets.util.FirebaseApi;
import com.leets.util.XML;

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

    public void exec(String xml, String input) throws IOException {
        if(xml.equals("inicio")) {
            if(input.equals("uno")) {
                XML.redirectXML(response, XML.REGISTRO);
            } else if(input.equals("dos")) {
                XML.redirectXML(response, XML.CAMPAÑAS);
            } else {
                XML.redirectXML(response, XML.ERROR, new String[][]{
                        {"causa", "Opción desconocida"}
                });
            }
        } else if(xml.equals("registro")) {
            XML.redirectXML(response, XML.DEPARTAMENTO, new String[][]{
                {Inscrito.ID, input}
            });
        } else if(xml.equals(Inscrito.DEPARTAMENTO)) {
            XML.redirectXML(response, XML.MUNICIPIO, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, input}
            });
        } else if(xml.equals(Inscrito.MUNICIPIO)) {
            XML.redirectXML(response, XML.DIRECCION, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, request.getParameter(Inscrito.DEPARTAMENTO)},
                {Inscrito.MUNICIPIO, input}
            });
        } else if(xml.equals(Inscrito.DIRECCION)) {
            XML.redirectXML(response, XML.ZONA, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, request.getParameter(Inscrito.DEPARTAMENTO)},
                {Inscrito.MUNICIPIO, request.getParameter(Inscrito.MUNICIPIO)},
                {Inscrito.DIRECCION, input}
            });
        } else if(xml.equals(Inscrito.ZONA)) {
            XML.redirectXML(response, XML.TELEFONO, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, request.getParameter(Inscrito.DEPARTAMENTO)},
                {Inscrito.MUNICIPIO, request.getParameter(Inscrito.MUNICIPIO)},
                {Inscrito.DIRECCION, request.getParameter(Inscrito.DIRECCION)},
                {Inscrito.ZONA, input}
            });
        } else if(xml.equals(Inscrito.TELEFONO)) {
            XML.redirectXML(response, XML.INGRESOS, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, request.getParameter(Inscrito.DEPARTAMENTO)},
                {Inscrito.MUNICIPIO, request.getParameter(Inscrito.MUNICIPIO)},
                {Inscrito.DIRECCION, request.getParameter(Inscrito.DIRECCION)},
                {Inscrito.ZONA, request.getParameter(Inscrito.ZONA)},
                {Inscrito.TELEFONO, input}
            });
        } else if(xml.equals(Inscrito.INGRESOS)) {
            XML.redirectXML(response, XML.INTEGRANTES, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, request.getParameter(Inscrito.DEPARTAMENTO)},
                {Inscrito.MUNICIPIO, request.getParameter(Inscrito.MUNICIPIO)},
                {Inscrito.DIRECCION, request.getParameter(Inscrito.DIRECCION)},
                {Inscrito.ZONA, request.getParameter(Inscrito.ZONA)},
                {Inscrito.TELEFONO, request.getParameter(Inscrito.TELEFONO)},
                {Inscrito.INGRESOS, input}
            });
        } else if(xml.equals(Inscrito.INTEGRANTES)) {
            XML.redirectXML(response, XML.TIPO_ID_INTEGRANTE, new String[][]{
                {Inscrito.ID, request.getParameter(Inscrito.ID)},
                {Inscrito.DEPARTAMENTO, request.getParameter(Inscrito.DEPARTAMENTO)},
                {Inscrito.MUNICIPIO, request.getParameter(Inscrito.MUNICIPIO)},
                {Inscrito.DIRECCION, request.getParameter(Inscrito.DIRECCION)},
                {Inscrito.ZONA, request.getParameter(Inscrito.ZONA)},
                {Inscrito.TELEFONO, request.getParameter(Inscrito.TELEFONO)},
                {Inscrito.INGRESOS, request.getParameter(Inscrito.INGRESOS)},
                {Inscrito.INTEGRANTES, input}
            });
        }
    }
}
