<%--
    Document   : Product
    Created on : Jan 9, 2019, 9:32:59 AM
    Author     : Arthas
--%>

<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml-stylesheet type="text/xsl" href="customer.xsl"?>
<Customers>
    <c:forEach var="customer" items="${customers}">
        <Customer>
            <CustomerID>${customer.customerID}</CustomerID>
            <CompanyName>${customer.companyName}</CompanyName>
            <ContactName>${customer.contactName}</ContactName>
            <ContactTitle>${customer.contactTitle}</ContactTitle>
            <Address>${customer.address}</Address>
            <City>${customer.city}</City>
            <Region>${customer.region}</Region>
            <PostalCode>${customer.postalCode}</PostalCode>
            <Country>${customer.country}</Country>
            <Phone>${customer.phone}</Phone>
            <Fax>${customer.fax}</Fax>
        </Customer>
    </c:forEach>
</Customers>
