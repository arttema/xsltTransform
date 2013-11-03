<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
 xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:g="http://www.example.org/products">
 <xsl:param name="productCategory" select="'motorcycles'" as="xs:string"/>
 <xsl:param name="productSubcategory" select="'chopper'" as="xs:string"/>
 <xsl:template match="/">
  <html>
   <body>
    <h2><xsl:value-of select="$productSubcategory" /></h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Producer</th>
        <th>Model</th>
        <th>Date fo Issue</th>
        <th>Color</th>
        <th>Price</th>
      </tr>
      <xsl:for-each select="g:products/g:category[@name=$productCategory]/g:subcategory[@name=$productSubcategory]/g:good">
        <tr>
         <td>
          <xsl:value-of select="g:producer"/>
        </td>
        <td>
          <xsl:value-of select="g:model"/>
        </td>
        <td>
          <xsl:value-of select="g:issuedate"/>
        </td>
        <td>
          <xsl:value-of select="g:color"/>
        </td>
        <td>
        <xsl:value-of select="g:price"/>
          <xsl:if test="g:notinstock=''" >         
            (not in stock)
          </xsl:if>
        </td>
      </tr>
    </xsl:for-each>
  </table>
  
  
  <form method="post" action="xsltcontroller?action=back&amp;productSubcategory={$productSubcategory}&amp;productCategory={$productCategory}&amp;page=subcategories">
  <button name="subject" type="submit">Back</button>
  <button form="addPageForm" name="subject" type="submit" >Add</button>
  </form>
  <form id="addPageForm" method="post" action="xsltcontroller?action=add&amp;productSubcategory={$productSubcategory}&amp;productCategory={$productCategory}">
  </form>
  
</body>
</html>
</xsl:template>
</xsl:stylesheet>