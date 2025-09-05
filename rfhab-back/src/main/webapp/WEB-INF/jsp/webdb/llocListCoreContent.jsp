<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocFields" className="es.caib.rfhab.model.fields.LlocFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[lloc.llocID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.LLOCID)}">
          <td>
          ${lloc.llocID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.CODILLOC)}">
          <td>
          ${lloc.codiLloc}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.CODILLOCPROPI)}">
          <td>
          ${lloc.codiLlocPropi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.EXPANSIO)}">
          <td>
          ${lloc.expansio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.NOM)}">
          <td>
          ${lloc.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${lloc.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.UNITATID)}">
          <td>
          <c:set var="tmp">${lloc.unitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUnitatForUnitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.PERSONALOAMR)}">
          <td>
          <c:set var="tmp">${lloc.personalOamr}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForPersonalOamr[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.DATAALTA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${lloc.dataalta}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${lloc.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.DATABAIXA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${lloc.dataBaixa}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.OBSERVACIONS)}">
          <td>
          ${lloc.observacions}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[lloc.llocID]}" />
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


