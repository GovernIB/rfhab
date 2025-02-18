<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariLlocFields" className="es.caib.rfhab.model.fields.FuncionariLlocFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[funcionariLloc.funcionarillocID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.FUNCIONARILLOCID)}">
          <td>
          ${funcionariLloc.funcionarillocID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.LLOCID)}">
          <td>
          <c:set var="tmp">${funcionariLloc.llocID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfLlocForLlocID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.FUNCIONARIID)}">
          <td>
          <c:set var="tmp">${funcionariLloc.funcionariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFuncionariForFuncionariID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.DATAINICI)}">
          <td> <fmt:formatDate pattern="${gen:getDatePattern()}" value="${funcionariLloc.dataInici}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.DATAFI)}">
          <td> <fmt:formatDate pattern="${gen:getDatePattern()}" value="${funcionariLloc.dataFi}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariLloc.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.USUARIID)}">
          <td>
          ${funcionariLloc.usuariID}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[funcionariLloc.funcionarillocID]}" />
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


