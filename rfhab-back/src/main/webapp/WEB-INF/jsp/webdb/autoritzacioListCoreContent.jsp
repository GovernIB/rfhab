<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AutoritzacioFields" className="es.caib.rfhab.model.fields.AutoritzacioFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[autoritzacio.autoritzacioID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.AUTORITZACIOID)}">
          <td>
          ${autoritzacio.autoritzacioID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.LLOCID)}">
          <td>
          <c:set var="tmp">${autoritzacio.llocID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfLlocForLlocID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.CODISIA)}">
          <td>
          ${autoritzacio.codiSia}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.PROCEDIMENT)}">
          <td>
          ${autoritzacio.procediment}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.CAI)}">
          <td>
          ${autoritzacio.cai}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.DATAINICI)}">
          <td> <fmt:formatDate pattern="${gen:getDatePattern()}" value="${autoritzacio.dataInici}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.DATAFI)}">
          <td> <fmt:formatDate pattern="${gen:getDatePattern()}" value="${autoritzacio.dataFi}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${autoritzacio.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.OBSERVACIONS)}">
          <td>
          ${autoritzacio.observacions}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.USUARIID)}">
          <td>
          ${autoritzacio.usuariID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.FUNCIONARIID)}">
          <td>
          <c:set var="tmp">${autoritzacio.funcionariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFuncionariForFuncionariID[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[autoritzacio.autoritzacioID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


