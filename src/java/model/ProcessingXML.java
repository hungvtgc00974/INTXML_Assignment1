/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nux.xom.xquery.XQueryUtil;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Arthas
 */
public class ProcessingXML {

    public ProcessingXML() {

    }

    public ArrayList readXML(String url) throws ParsingException, ValidityException, IOException {
        nu.xom.Document document = new nu.xom.Builder().build(new File(url + "Customers.xml"));
        nu.xom.Nodes nodes = XQueryUtil.xquery(document, "//Customer[City='London']");
        ArrayList arrayList = new ArrayList();
        System.out.println(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            Customer c = new Customer();
            nu.xom.Node node = nodes.get(i);
            c.setCustomerID(node.getChild(1).getValue());
            c.setCompanyName(node.getChild(3).getValue());
            c.setContactName(node.getChild(5).getValue());
            c.setContactTitle(node.getChild(7).getValue());
            c.setAddress(node.getChild(9).getValue());
            c.setCity(node.getChild(11).getValue());
            c.setRegion(node.getChild(13).getValue());
            c.setPostalCode(node.getChild(15).getValue());
            c.setCountry(node.getChild(17).getValue());
            c.setPhone(node.getChild(19).getValue());
            c.setFax(node.getChild(21).getValue());
            arrayList.add(c);
        }
        return arrayList;
    }

    public String validateXML(String url) throws ParserConfigurationException, IOException {
        String s = "";
        try {
            File f = new File(url + "Customers.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(f);
            DOMSource domSource = new DOMSource(doc);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File(url + "Customers.xsd"));
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(domSource);
            s = "Document is valid";

        } catch (SAXException ex) {
            s = ex.getMessage();
        }
        return s;
    }
}
