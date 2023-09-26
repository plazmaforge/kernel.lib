package plazma.lib.data.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import plazma.lib.AbstractTestCase;

public class DOMParserTest extends AbstractTestCase {

    @Test
    public void testDOMRead() {

        System.out.println("Test DOM Read");
        long time = System.currentTimeMillis();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // builderFactory.setNamespaceAware(true);
        DocumentBuilder builder = null;

        try {

            String fileName = getFileName("/test_ns.xml");

            builder = builderFactory.newDocumentBuilder();

            Document document = builder.parse(new File(fileName));
            document.normalize();

            // System.out.println(document.getBaseURI());
            // System.out.println(document.getDocumentURI());
            // System.out.println(document.getNamespaceURI());

            Element root = document.getDocumentElement();
            root.normalize();

            System.out.println(System.currentTimeMillis() - time);

            NodeList nodes = root.getChildNodes();

            // System.out.println(nodes);

            int len = Math.min(nodes.getLength(), 6); // nodes.getLength();

            for (int i = 0; i < len; i++) {
                Node node = nodes.item(i);
                if (!(node instanceof Element)) {
                    continue;
                }
                Element element = (Element) node;
                String nodeName = element.getNodeName();

                // if (!"parameter".equals(nodeName)) {
                // continue;
                // }
                // element = (Element)
                // element.getElementsByTagName("defaultValueExpression").item(0);

                System.out.println();
                dump(element);

                NamedNodeMap attrs = element.getAttributes();
                for (int j = 0; j < attrs.getLength(); j++) {
                    Node a = attrs.item(j);
                    System.out.println();
                    dump(a);

                }

            }

            // NodeList elements = parameter.getElementsByTagName("defaultValueExpression");
            // Element element = (Element) elements.item(0);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void dump(Node element) {

        // BuilderFactory.setNamespaceAware(false/true);

        /*
         * 
         * Element ==================================================================
         * 
         * NamespaceURI : null NodeName : f:table LocalName : null Prefix : null TagName
         * : f:table
         * 
         * 
         * NamespaceURI : http://www.w3.org/TR/html4/ NodeName : h:table LocalName :
         * table Prefix : h TagName : h:table
         * 
         */

        /*
         * 
         * Element ==================================================================
         * 
         * NamespaceURI : null NodeName : property LocalName : null Prefix : null
         * TagName : property
         * 
         * 
         * NamespaceURI : http://jasperreports.sourceforge.net/jasperreports NodeName :
         * property LocalName : property Prefix : null TagName : property
         * 
         */

        /*
         * 
         * NamespaceURI : null NodeName : defaultValueExpression LocalName : null Prefix
         * : null TextContent : "The First Jasper Report Ever"
         * 
         * NodeValue : null
         * 
         * 
         * NamespaceURI : http://jasperreports.sourceforge.net/jasperreports NodeName :
         * defaultValueExpression LocalName : defaultValueExpression Prefix : null
         * TextContent : "The First Jasper Report Ever"
         * 
         * NodeValue : null
         * 
         */

        /*
         * 
         * Attribute ==================================================================
         * 
         * NamespaceURI : null NodeName : name LocalName : null Prefix : null
         * TextContent : net.sf.jasperreports.data.adapter NodeValue :
         * net.sf.jasperreports.data.adapter
         * 
         * 
         * NamespaceURI : null NodeName : name LocalName : name Prefix : null
         * TextContent : net.sf.jasperreports.data.adapter NodeValue :
         * net.sf.jasperreports.data.adapter
         * 
         */

        System.out.println("NamespaceURI  : " + element.getNamespaceURI());
        System.out.println("NodeName      : " + element.getNodeName());
        System.out.println("LocalName     : " + element.getLocalName());
        System.out.println("Prefix        : " + element.getPrefix());
        // System.out.println("TagName : " + element.getTagName());
        System.out.println("TextContent   : " + element.getTextContent());
        System.out.println("NodeValue     : " + element.getNodeValue());

        // System.out.println("SchemaTypeInfo: " + element.getSchemaTypeInfo());

    }

}
