<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Template by XMLSpy® from w3c schools-->
<!--This is practice for Info and web tech module-->
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>     
		<body>
			<h2>Meeting List</h2>
			<xsl:for-each select="meetings-list/meeting">     
			<p>
<!--				<xsl:if test="future-meeting">
					<b><xsl:value-of select="future-meeting"/></b><br />
				</xsl:if>	
-->				Meeting date: <xsl:value-of select="date"/><br />
				ID: <xsl:value-of select="id"/><br />
				Attendees: <xsl:for-each select="contact"> 
					<xsl:value-of select="."/> <br />
				</xsl:for-each>
				<xsl:if test="notes">
					Notes: <i><xsl:value-of select="notes"/></i>
				</xsl:if>	
			</p>
			</xsl:for-each>
		</body>
	</html>
</xsl:template>
</xsl:stylesheet>