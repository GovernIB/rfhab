<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ActivitatFields" className="es.caib.rfhab.model.fields.ActivitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[activitat.activitatID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ACTIVITATID)}">
          <td>
          ${activitat.activitatID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.FUNCIONARIID)}">
          <td>
          <c:set var="tmp">${activitat.funcionariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFuncionariForFuncionariID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TIPUS)}">
          <td>
          <c:set var="tmp">${activitat.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REGISTRE)}">
          <td>
          ${activitat.registre}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TRAMIT)}">
          <td>
          ${activitat.tramit}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.CODISIA)}">
          <td>
          ${activitat.codiSia}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.AUTORITZACIOID)}">
          <td>
          ${activitat.autoritzacioID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${activitat.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATNOM)}">
          <td>
          ${activitat.interessatNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE1)}">
          <td>
          ${activitat.interessatLlinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE2)}">
          <td>
          ${activitat.interessatLlinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATTIPUS)}">
          <td>
          ${activitat.interessatTipus}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATIDENTIFICACIO)}">
          <td>
          ${activitat.interessatIdentificacio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTNOM)}">
          <td>
          ${activitat.representantNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE1)}">
          <td>
          ${activitat.representantLlinatge1}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE2)}">
          <td>
          ${activitat.representantLlinatge2}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTTIPUS)}">
          <td>
          ${activitat.representantTipus}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTIDENTIFICACIO)}">
          <td>
          ${activitat.representantIdentificacio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TRAMITVERSIO)}">
          <td>
          ${activitat.tramitVersio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUDOCUMENTID)}">
          <td>
          ${activitat.arxiuDocumentID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUEXPEDIENTID)}">
          <td>
          ${activitat.arxiuExpedientID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ESTAT)}">
          <td>
          ${activitat.estat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.URL)}">
          <td>
          ${activitat.url}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.DATAACTIVITAT)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${activitat.dataActivitat}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.IDACTUACIOTRAMIT)}">
          <td>
          ${activitat.idActuacioTramit}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.PROCEDIMENT)}">
          <td>
          ${activitat.procediment}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[activitat.activitatID]}" />
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


