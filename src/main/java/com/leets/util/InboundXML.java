
package com.leets.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author umansilla
 * 
 * @author estidlore
 */
public class InboundXML {
	
    public final static String
            /*a*/ID = "https://cloud.zang.io/data/inboundxml/a9cf5acff407463ff56b04bea16ec9df084cd486",
            /*b*/CAMPANAS = "https://cloud.zang.io/data/inboundxml/9bb44cf6c1e4dfc6730860783a950b42fa2ef8b4",
            /*c*/ACTUALIZAR = "https://cloud.zang.io/data/inboundxml/9e13b03d6c2dae9cb4e519e7b552737453158739",
            /*d*/ESTADO = "https://cloud.zang.io/data/inboundxml/1ac440a6b3d93fb7c9419517e8b8a4b706dd5642",
            /*e*/SATISFACION = "https://cloud.zang.io/data/inboundxml/910f300caeed28799b8cc0784d05d28626e50da7",
            /*f*/RETROALIMENTACION = "https://cloud.zang.io/data/inboundxml/377160a668026264c6c39bce21075d0e16ce831f",
            /*g*/SIN_BENEFICIO = "https://cloud.zang.io/data/inboundxml/7d276bd1d3332339cfe20aaf68ae4ea433dc58a6",
            /////
            /*h*/HACER_REGISTRO = "https://cloud.zang.io/data/inboundxml/1977b13449030e40540122546d7da5ecb7d3e5d8",
            /*i*/DEPARTAMENTO = "https://cloud.zang.io/data/inboundxml/3be2f08f2e00931e83b4afdb1b72350239aa57a9",
            /*j*/MUNICIPIO = "https://cloud.zang.io/data/inboundxml/19465b2f1a8596fca8e587207c837346509f7608",
            /*k*/DIRECCION = "https://cloud.zang.io/data/inboundxml/ee04e65cdd13f3d3aa1ff71e703499ea12bf7eb4",
            /*l*/ZONA = "https://cloud.zang.io/data/inboundxml/d04e9d218fc4ad33da1ad00550b0c9bc2dd0ea13",
            /*m*/INGRESOS = "https://cloud.zang.io/data/inboundxml/cd40e9205ff2aa5d1329e734bb32c1d6153ecbb4",
            /*n*/INTEGRANTES = "https://cloud.zang.io/data/inboundxml/765ad1c0fd5b623e835bd28592e4353fb36523b2",
            /*o*/TIPO_ID_INTEGRANTE = "http://cloud.zang.io/data/inboundxml/471b561f02812adffaced06731de5141e18c4bd4",
            /*p*/ID_INTEGRANTE = "http://cloud.zang.io/data/inboundxml/e9667bda8c7777fb138eef586c8a46b44b8e3e04",
            /*q*/PARENTESCO = "http://cloud.zang.io/data/inboundxml/18e92b51aefe99affbaae5b708a92a690556713d",
            /*r*/TELEFONO = "http://cloud.zang.io/data/inboundxml/8c01d55cf3a1553ffa02697824202e255f5de6e7",
            /*s*/ID_INVALIDO = "http://cloud.zang.io/data/inboundxml/f9dcddb4990f57e77c570e238af7e854a7ae0d7f",
            /*t*/ERROR = "https://cloud.zang.io/data/inboundxml/713432ea4a491a93ea4bad809f7e75336ff6ddd1";

    public static void redirectHacerRegistro(HttpServletResponse response,
            int id, String nombre) throws IOException {
        String xml = "<Response>" + "<Redirect method=\"GET\">" + HACER_REGISTRO
                    + "?id=" + id + "&amp;nombre=" + nombre + "</Redirect>" + "</Response>";
        response.getWriter().println(xml);
    }
    
    public static void redirectError(HttpServletResponse response,
            String causa) throws IOException {
        String xml = "<Response>" + "<Redirect method=\"GET\">" + ERROR
                    + "?causa=" + causa + "</Redirect>" + "</Response>";
        response.getWriter().println(xml);
    }

    public static void redirectXML(HttpServletResponse response,
            String xml) throws IOException {
        xml = "<Response>" + "<Redirect method=\"GET\">"
                + xml + "</Redirect>" + "</Response>";
        response.getWriter().println(xml);
    }

}
