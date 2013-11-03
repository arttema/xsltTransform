<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:g="http://www.example.org/products"
  exclude-result-prefixes="g"
  xmlns:good="xalan://com.epam.parser.dto.Good"
xmlns:validator="xalan://com.epam.parser.validation.GoodValidator"
extension-element-prefixes="validator good" 
>
  <xsl:param name="validator"/>
  <xsl:param name="good"/>
  <xsl:param name="productCategory" select="'electronics'"/>
  <xsl:param name="productSubcategory" select="'phones'"/>
  <xsl:param name="producerErrorMessage" select="validator:getProducerErrorMessage($validator)"></xsl:param>
  <xsl:output method="html" indent="yes" encoding="utf-8"/>

  <xsl:template name="addProduct" match="/">
  
  <form id="addForm" method="post" 
  action="xsltcontroller?action=save">
    <table align="left" width="100%">
    	<tr>
        <td width="20%"><b>Category</b></td>
        <td width="80%">  
        <xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'category'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="$productCategory"/>
        </xsl:attribute>
        <xsl:attribute name="disabled">
            <xsl:value-of select="disabled"/>
        </xsl:attribute>
        </xsl:element>
        
        <xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'hidden'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'category'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="$productCategory"/>
        </xsl:attribute>
        </xsl:element>
       </td>
       
        </tr>        
        <tr>
        <td><b>Subcategory</b></td>
        <td >
         <xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'" />
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'subcategory'"/>
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="$productSubcategory"/>
        </xsl:attribute>
        <xsl:attribute name="disabled">
            <xsl:value-of select="disabled"/>
        </xsl:attribute>
        </xsl:element>
        <xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'hidden'" />
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'subcategory'"/>
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="$productSubcategory"/>
        </xsl:attribute>
       
        </xsl:element>
        </td>
        </tr>        
        <tr>
        <td><b>Producer</b></td>
        <td >
        <xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'producer'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="good:getProducer($good)"/>
        </xsl:attribute>
        </xsl:element>
        <xsl:value-of select="$producerErrorMessage"/></td>
        
        </tr>        
        <tr>
        <td><b>Issue date</b><br/>
              </td>
        <td >
        <xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'issueDate'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="good:getIssueDate($good)"/>
        </xsl:attribute>
        </xsl:element>
        <xsl:value-of select="validator:getIssueDateErrorMessage($validator)"/></td>
             
        </tr>       
        <tr>
        <td><b>Model</b></td>
        <td >
		<xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'model'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="good:getModel($good)"/>
        </xsl:attribute>
        </xsl:element>
        <xsl:value-of select="validator:getModelErrorMessage($validator)"/></td>
             
        </tr>
        <tr>
        <td><b>Color</b></td>
        <td >
		<xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'color'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="good:getColor($good)"/>
        </xsl:attribute>
        </xsl:element>
        <xsl:value-of select="validator:getColorErrorMessage($validator)"/>
        </td>
       
        </tr>                
        <tr>
        <td><b>Price</b></td>
        <td >
	<xsl:element name="input">
        <xsl:attribute name="type">
            <xsl:value-of select="'text'"/>
        </xsl:attribute>
        <xsl:attribute name="name">
            <xsl:value-of select="'price'" />
        </xsl:attribute>
        <xsl:attribute name="value">
            <xsl:value-of select="good:getPrice($good)"/>
        </xsl:attribute>
        </xsl:element>
        <xsl:value-of select="validator:getPriceErrorMessage($validator)"/></td>
        
        </tr>       
        <tr>
        <td><b>Not in stock?</b></td>
        <td ><input type="checkbox" name="notInStock" /></td>
        </tr>
        <tr>        
        <td  align="left">       
       </td>
       <td  align="left">       
</td>
    </tr>
    </table>  
    </form>  
    <form id="backForm" method="post" action=
       "xsltcontroller?action=back&amp;page=goods&amp;productCategory={$productCategory}&amp;productSubcategory={$productSubcategory}">
  <button name="subject" type="submit">Back</button>
  <button form="addForm" name="subject" type="submit" >Save</button>
  </form>
  </xsl:template>
</xsl:stylesheet>