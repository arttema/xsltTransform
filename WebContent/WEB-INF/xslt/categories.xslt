<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:g="http://www.example.org/products">
 <xsl:template match="/">
  <html>
   <body>
    <h2>Categories</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Name</th>
        <th>Quantity of good</th>
      </tr>
      <xsl:for-each select="g:products/g:category">
        <tr>
         <td>
           <a href="/XSLTTransform/xsltcontroller?action=subcategories&amp;productCategory={@name}"><xsl:value-of select="@name"/></a>
        </td>
        <td>
          <xsl:value-of select="count(g:subcategory/g:good)" />
        </td>
      </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>