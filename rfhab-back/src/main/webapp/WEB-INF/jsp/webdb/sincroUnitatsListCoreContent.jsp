<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SincroUnitatsFields" className="es.caib.rfhab.model.fields.SincroUnitatsFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[sincroUnitats.sincrounitatsId]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.SINCROUNITATSID)}">
          <td>
          ${sincroUnitats.sincrounitatsId}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${sincroUnitats.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.DATADARRERASINCRO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${sincroUnitats.dataDarreraSincro}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.DATAPRIMERASINCRO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${sincroUnitats.dataPrimeraSincro}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.CODIENTITAT)}">
          <td>
          ${sincroUnitats.codiEntitat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.OBSERVACIONS)}">
          <td>
          ${sincroUnitats.observacions}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SincroUnitatsFields.USUARIID)}">
          <td>
          <c:set var="tmp">${sincroUnitats.usuariId}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariForUsuariId[tmp]}
          </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[sincroUnitats.sincrounitatsId]}" />
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


