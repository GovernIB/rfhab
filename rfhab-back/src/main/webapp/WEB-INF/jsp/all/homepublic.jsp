<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<div style="text-align:center;">
<img src="<c:url value="/img/app-logo.png"/>"  alt="RFHab"/>

<br/>
<br/>

PAGINA PUBLICA <br/>

This page is generated automatically. Please edit.

<br/>
<br/>
<div>
<a href="http://blog.fundaciobit.org/category/admindigital/" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="Fundació Bit"/>
</a>
</div>
<br/>
</div>
 
</div>

<c:if test="${rfh:isDesenvolupament()}">
<br/>

LOGIN ANONIM <br/>
Locale = <%=LocaleContextHolder.getLocale() %> <br/>
lang = ${lang} <br/>
<br/>

Only in Development Mode
</c:if>
