<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
 xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:g="http://www.example.org/products"
 >
 
 <xsl:param name="productCategory" select="'motorcycles'" as="xs:string"/>
 <xsl:template match="/">
   
  <html>
   <body>
    <h2><xsl:value-of select="$productCategory" /></h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Name</th>
        <th>Quantity of good</th>
      </tr>       
      <xsl:for-each select="g:products/g:category[@name=$productCategory]/g:subcategory">
        <tr>
         <td>
          <a href="/XSLTTransform/xsltcontroller?action=goods&amp;productSubcategory={@name}&amp;productCategory={$productCategory}">
          <xsl:value-of select="@name"/></a>
      </td>
      <td>
        <xsl:value-of select="count(g:good)" />
      </td>
    </tr>
  </xsl:for-each>
</table>
</body>
<form method="post" action="xsltcontroller?action=back&amp;page=categories">
  <button name="subject" type="submit">Back</button>
  </form>
  
</html>
</xsl:template>
</xsl:stylesheet>
