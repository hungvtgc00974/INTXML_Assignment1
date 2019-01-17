<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : product.xsl
    Created on : January 9, 2019, 9:40 AM
    Author     : Arthas
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules
         syntax recommendation http://www.w3.org/TR/xslt
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>customer</title>
            </head>
            <body>
                <h2>Displaying customers who live in London</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>CompanyName</th>
                        <th>ContactName</th>
                        <th>ContactTitle</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Region</th>
                        <th>PostalCode</th>
                        <th>Country</th>
                        <th>Phone</th>
                        <th>Fax</th>
                    </tr>
                    <xsl:for-each select="Customers/Customer">
                        <tr>
                            <td>
                                <xsl:value-of select="ContactName"/>
                            </td>
                            <td>
                                <xsl:value-of select="ContactTitle"/>
                            </td>
                            <td>
                                <xsl:value-of select="CompanyName"/>
                            </td>
                            <td>
                                <xsl:value-of select="Address"/>
                            </td>
                            <td>
                                <xsl:value-of select="City"/>
                            </td>
                            <td>
                                <xsl:value-of select="Region"/>
                            </td>
                            <td>
                                <xsl:value-of select="PostalCode"/>
                            </td>
                            <td>
                                <xsl:value-of select="Country"/>
                            </td>
                            <td>
                                <xsl:value-of select="Phone"/>
                            </td>
                            <td>
                                <xsl:value-of select="Fax"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
