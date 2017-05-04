package com.dev3.app.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dev3.app.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

public class WeChatMessageUtil {
    /**
     * convert xml to Map Collection
     *
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        StringBuilder builder = new StringBuilder();
        InputStream ins = null;

        try {
            ins = request.getInputStream();

            InputStreamReader isr = new InputStreamReader(ins, "utf8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(builder.toString());
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }

        Element root = doc.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        map.put("__raw__", builder.toString());

        try {
            ins.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return map;
    }


    public static Map<String, String> xmlToMap(String xml) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();

            @SuppressWarnings("unchecked")
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            map.put("__raw__", xml);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }

        return map;
    }

    /**
     * Serialize TextMessage Object To XML
     *
     * @param textMessage
     * @return
     */
    public static <M> String textMessageToXml(M textMessage, Class<?> clazz) {
        XStream xstream = new XStream();
        xstream.alias("xml", clazz);
        return xstream.toXML(textMessage);

    }

}
