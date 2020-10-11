
package com.leets.util;

/**
 *
 * @author umansilla
 */
public class InboundXML {
    public static String setRedirectInboundXML(String redirectInboundXML, String causa) {
        return "<Response>" + "<Redirect method=\"GET\">" + redirectInboundXML + "?causa=" + causa + "</Redirect>"
                + "</Response>";
    }

    public static String setRedirectInboundXML(String redirectInboundXML) {
        return "<Response>" + "<Redirect method=\"GET\">" + redirectInboundXML + "</Redirect>" + "</Response>";
    }
}
