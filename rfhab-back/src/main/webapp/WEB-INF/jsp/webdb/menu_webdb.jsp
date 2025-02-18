<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Activitat --%>
       <fmt:message var="entityname" key="activitat.activitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/activitat/list/1"/>" ><span style="${(fn:contains(url, 'activitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Autoritzacio --%>
       <fmt:message var="entityname" key="autoritzacio.autoritzacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/autoritzacio/list/1"/>" ><span style="${(fn:contains(url, 'autoritzacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- ScanWeb --%>
       <fmt:message var="entityname" key="scanWeb.scanWeb.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/scanWeb/list/1"/>" ><span style="${(fn:contains(url, 'scanWeb/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Entitat --%>
       <fmt:message var="entityname" key="entitat.entitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitat/list/1"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Fitxer --%>
       <fmt:message var="entityname" key="fitxer.fitxer.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Funcionari --%>
       <fmt:message var="entityname" key="funcionari.funcionari.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/funcionari/list/1"/>" ><span style="${(fn:contains(url, 'funcionari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- FuncionariLloc --%>
       <fmt:message var="entityname" key="funcionariLloc.funcionariLloc.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/funcionariLloc/list/1"/>" ><span style="${(fn:contains(url, 'funcionariLloc/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- FuncionariRol --%>
       <fmt:message var="entityname" key="funcionariRol.funcionariRol.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/funcionariRol/list/1"/>" ><span style="${(fn:contains(url, 'funcionariRol/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Historic --%>
       <fmt:message var="entityname" key="historic.historic.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/historic/list/1"/>" ><span style="${(fn:contains(url, 'historic/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- HistoricLloc --%>
       <fmt:message var="entityname" key="historicLloc.historicLloc.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/historicLloc/list/1"/>" ><span style="${(fn:contains(url, 'historicLloc/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Idioma --%>
       <fmt:message var="entityname" key="idioma.idioma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Lloc --%>
       <fmt:message var="entityname" key="lloc.lloc.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/lloc/list/1"/>" ><span style="${(fn:contains(url, 'lloc/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Plugin --%>
       <fmt:message var="entityname" key="plugin.plugin.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/plugin/list/1"/>" ><span style="${(fn:contains(url, 'plugin/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Rol --%>
       <fmt:message var="entityname" key="rol.rol.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/rol/list/1"/>" ><span style="${(fn:contains(url, 'rol/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Traduccio --%>
       <fmt:message var="entityname" key="traduccio.traduccio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Unitat --%>
       <fmt:message var="entityname" key="unitat.unitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/unitat/list/1"/>" ><span style="${(fn:contains(url, 'unitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Usuari --%>
       <fmt:message var="entityname" key="usuari.usuari.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuari/list/1"/>" ><span style="${(fn:contains(url, 'usuari/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- UsuariEntitat --%>
       <fmt:message var="entityname" key="usuariEntitat.usuariEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
