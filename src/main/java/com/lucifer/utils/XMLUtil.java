package com.lucifer.utils;

/**
 * Created by liufx on 2017/12/1.
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtil {
    public XMLUtil() {
    }

    public static Map doXMLParse(String strxml) {
        Map map = new HashMap();
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        try {
            if(strxml == null || "".equals(strxml)) {
                return null;
            }

            Document document = DocumentHelper.parseText(strxml);
            Element root = document.getRootElement();
            List elementList = root.elements();

            String k;
            String v;
            for(Iterator it = elementList.iterator(); it.hasNext(); map.put(k, v)) {
                Element element = (Element)it.next();
                k = element.getName();
                v = "";
                List children = element.attributes();
                if(children.isEmpty()) {
                    v = element.getStringValue();
                } else {
                    v = getChildrenText(children);
                }
            }
        } catch (DocumentException var10) {
            var10.printStackTrace();
        }

        return map;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();

            while(it.hasNext()) {
                Element ele = (Element)it.next();
                String name = ele.getName();
                String value = ele.getStringValue();
                List list = ele.attributes();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }

                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }
}
