<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:g="http://www.example.org/products"
	exclude-result-prefixes="g" xmlns:good="xalan://com.epam.parser.dto.Good"
	xmlns:validator="xalan://com.epam.parser.validation.GoodValidator"
	extension-element-prefixes="validator good">
	<xsl:import href="addPage.xslt" />

	<xsl:param name="good" />
	<xsl:param name="validator" />

	<xsl:param name="isAlreadyValidated" select="'false'" />
	<xsl:param name="productCategory" select="'electronics'" />
	<xsl:param name="productSubcategory" select="'phones'" />
	<xsl:output method="xml" indent="yes" encoding="utf-8" />



	<xsl:template match="/" priority="1">
		<xsl:choose>
			<xsl:when test="$isAlreadyValidated='true'">
			<xsl:call-template name="saveProduct" />
			</xsl:when>
			
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="validator:validateGood($validator, $good)">
						<xsl:call-template name="saveProduct" />
					</xsl:when>
					<xsl:otherwise>

						<xsl:call-template name="addProduct">
							<xsl:with-param name="validator"></xsl:with-param>
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
			
		</xsl:choose>
	</xsl:template>

	<xsl:template name="saveProduct" match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<xsl:template name="insertProduct"
		match="g:category[@name=$productCategory]/g:subcategory[@name=$productSubcategory]">

		<xsl:copy>
			<xsl:attribute name="name">
            <xsl:value-of select="$productSubcategory" />
        </xsl:attribute>
			<xsl:apply-templates />

			<xsl:element name="g:good">

				<xsl:element name="g:producer">
					<xsl:value-of select="good:getProducer($good)" />
				</xsl:element>
				<xsl:element name="g:model">
					<xsl:value-of select="good:getModel($good)" />
				</xsl:element>
				<xsl:element name="g:issuedate">
					<xsl:value-of select="good:getIssueDate($good)" />
				</xsl:element>
				<xsl:element name="g:color">
					<xsl:value-of select="good:getColor($good)" />
				</xsl:element>

				<xsl:if test="good:isNotInStock($good)!='true'">
					<xsl:element name="g:price">
						<xsl:value-of select="good:getPrice($good)" />
					</xsl:element>
				</xsl:if>

				<xsl:if test="good:isNotInStock($good)='true'">
					<xsl:element name="g:notinstock">
					</xsl:element>
				</xsl:if>

			</xsl:element>

		</xsl:copy>

	</xsl:template>

</xsl:stylesheet>