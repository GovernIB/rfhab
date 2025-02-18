<%@page import="es.caib.rfhab.back.security.LoginInfo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>
<!DOCTYPE html><html xmlns="http://www.w3.org/1999/xhtml"
    xml:lang="<c:out value="${pageContext.response.locale.language}"/>"
    lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>



<c:if test="${loginInfo.needConfigUser}">
	<%
	LoginInfo.getInstance().setNeedConfigUser(false);
	%>
 
	<c:redirect
		url="/usuari/nou/${loginInfo.usuariPersona.usuariid}/check" />
	
</c:if>




<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

    <!--  INICI CAPÇALERA -->

    <tiles:insertAttribute name="cap">
        <tiles:putAttribute name="data" value="${data}" />
    </tiles:insertAttribute>

    <div class="row-fluid container main" style="max-width: none;">
        <%-- INICI CONTINGUT --%>
        <div class="well well-white" style="padding: 10px">
            <tiles:insertAttribute name="nomes_contingut">
                <tiles:putAttribute name="contingut" value="${contingut_tile}" />
            </tiles:insertAttribute>
            <%-- FINAL CONTINGUT --%>
        </div>
    </div>

    <div class="container-fluid">
        <tiles:insertAttribute name="peu"></tiles:insertAttribute>
    </div>

</body>
</html>
