<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariFields" className="es.caib.rfhab.model.fields.FuncionariFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[funcionari.funcionariID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.FUNCIONARIID)}">
          <td>
          ${funcionari.funcionariID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.NUMERO)}">
          <td>
          ${funcionari.numero}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.NOM)}">
          <td>
          ${funcionari.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.LLINATGE1)}">
          <td>
          ${funcionari.llinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.LLINATGE2)}">
          <td>
          ${funcionari.llinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.TIPUSIDENTIFICADOR)}">
          <td>
          <c:set var="tmp">${funcionari.tipusIdentificador}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusIdentificador[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.IDENTIFICADOR)}">
          <td>
          ${funcionari.identificador}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.USUARI)}">
          <td>
          ${funcionari.usuari}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.CORREU)}">
          <td>
          ${funcionari.correu}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionari.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.OBSERVACIONS)}">
          <td>
          ${funcionari.observacions}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.DATABAIXA)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionari.dataBaixa}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.ENTITATID)}">
          <td>
          ${funcionari.entitatID}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[funcionari.funcionariID]}" />
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


