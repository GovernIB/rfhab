<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<center>
<img src="<c:url value="/img/app-logo.png"/>" width="195" alt="RFHab" title="RFHab"/>

<br/>
<br/>
<fmt:message key="benvinguda" />

<br/>
<br/>
<table border="0" >
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td valign="top">
<a href="https://governdigital.fundaciobit.org" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="Fundació Bit" title="Fundació Bit"/>
</a>
</td>
</tr>
</table>
<br/>
</center>
 
</div>

<br/>
Username: ${loginInfo.username}<br/>
&#36;{rfh:hasRole(ROLE_ADMIN)}= ${rfh:hasRole('ROLE_ADMIN')}<br/>
&#36;{rfh:hasRole(ROLE_SUPER) }= ${rfh:hasRole('ROLE_SUPER') }<br/>
&#36;{rfh:hasRole(ROLE_USER) }= ${rfh:hasRole('ROLE_USER') }<br/>
Locale = <%=LocaleContextHolder.getLocale() %> <br/>
lang = ${lang} <br/>
> UserInformation:<br/>
 <c:if test="${not empty loginInfo.usuariPersona}">
	name= ${loginInfo.usuariPersona.nom} <br/> 
 	surname1= ${loginInfo.usuariPersona.llinatge1} <br/>
 	surname2= ${loginInfo.usuariPersona.llinatge2} <br/>
 	email= ${loginInfo.usuariPersona.correu} <br/>
 	nif= ${loginInfo.usuariPersona.nif} <br/> 
  <br/>
</c:if>

<c:if test="${rfh:isDesenvolupament()}">
Only in Development Mode
</c:if>
