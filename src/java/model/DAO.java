/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Arthas
 */
public class DAO {

    public DAO() {

    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Northwind;integratedSecurity=true");
        return conn;
    }

    public ResultSet getData() throws ClassNotFoundException {
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select top 50 * from Customers");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public void writeXML(String url) throws ParserConfigurationException, SQLException, TransformerConfigurationException, TransformerException, IOException, ClassNotFoundException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("Customers");
        document.appendChild(root);
        ResultSet rs = this.getData();
        while (rs.next()) {
            Element child = document.createElement("Customer");
            root.appendChild(child);
            Element CustomerID = document.createElement("CustomerID");
            CustomerID.setTextContent(rs.getString("CustomerID"));
            child.appendChild(CustomerID);

            Element CompanyName = document.createElement("CompanyName");
            CompanyName.setTextContent(rs.getString("CompanyName"));
            child.appendChild(CompanyName);

            Element ContactName = document.createElement("ContactName");
            ContactName.setTextContent(rs.getString("ContactName"));
            child.appendChild(ContactName);

            Element ContactTitle = document.createElement("ContactTitle");
            ContactTitle.setTextContent(rs.getString("ContactTitle"));
            child.appendChild(ContactTitle);

            Element Address = document.createElement("Address");
            Address.setTextContent(rs.getString("Address"));
            child.appendChild(Address);

            Element City = document.createElement("City");
            City.setTextContent(rs.getString("City"));
            child.appendChild(City);

            Element Region = document.createElement("Region");
            String regionString = rs.getString("Region");
            if (rs.wasNull()) {
                Region.setTextContent("");
            } else {
                Region.setTextContent(regionString);
            }
            child.appendChild(Region);

            Element PostalCode = document.createElement("PostalCode");
            PostalCode.setTextContent(rs.getString("PostalCode"));
            child.appendChild(PostalCode);

            Element Country = document.createElement("Country");
            Country.setTextContent(rs.getString("Country"));
            child.appendChild(Country);

            Element Phone = document.createElement("Phone");
            Phone.setTextContent(rs.getString("Phone"));
            child.appendChild(Phone);

            Element Fax = document.createElement("Fax");
            String faxString = rs.getString("Fax");
            if (rs.wasNull()) {
                Fax.setTextContent("");
            } else {
                Fax.setTextContent(faxString);
            }
            child.appendChild(Fax);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
        FileWriter fwriter = new FileWriter(new File(url + "Customers.xml"));
        fwriter.write(writer.toString());
        fwriter.close();
    }
}
