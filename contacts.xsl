<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Template by XMLSpy® from w3c schools-->
<!--This is practice for Info and web tech module-->
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
		<body>
			<h2>Contact List</h2>
			<xsl:for-each select="contacts-list/contact">     
			<p>
				ID: <xsl:value-of select="id"/><br />
				Name: <xsl:value-of select="name"/><br />
				<xsl:if test="note">
					Notes: <i><xsl:value-of select="note"/></i><br />
				</xsl:if>
			</p>
			</xsl:for-each>
		</body>
	</html>
</xsl:template>
</xsl:stylesheet>