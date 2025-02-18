<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="HistoricFields" className="es.caib.rfhab.model.fields.HistoricFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[historic.historicID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.HISTORICID)}">
          <td>
          ${historic.historicID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.FUNCIONARIID)}">
          <td>
          <c:set var="tmp">${historic.funcionariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFuncionariForFuncionariID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.NUMEROCAI)}">
          <td>
          ${historic.numeroCai}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.OBSERVACIONS)}">
          <td>
          ${historic.observacions}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${historic.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.USUARIID)}">
          <td>
          <c:set var="tmp">${historic.usuariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariForUsuariID[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[historic.historicID]}" />
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


