<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UnitatFields" className="es.caib.rfhab.model.fields.UnitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[unitat.unitatID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.UNITATID)}">
          <td>
          ${unitat.unitatID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.CODI)}">
          <td>
          ${unitat.codi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.VERSIO)}">
          <td>
          ${unitat.versio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.DENOMINACIO)}">
          <td>
          ${unitat.denominacio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.COOFICIAL)}">
          <td>
          ${unitat.cooficial}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.ARREL)}">
          <td>
          ${unitat.arrel}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.ARRELVERSIO)}">
          <td>
          ${unitat.arrelVersio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.SUPERIOR)}">
          <td>
          ${unitat.superior}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.SUPERIORVERSIO)}">
          <td>
          ${unitat.superiorVersio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.ESTAT)}">
          <td>
          ${unitat.estat}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[unitat.unitatID]}" />
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


