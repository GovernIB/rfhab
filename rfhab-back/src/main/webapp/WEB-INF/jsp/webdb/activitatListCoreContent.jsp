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
             <c:if test="${empty activitat.activitatID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.FUNCIONARIID)}">
        <td>
          <c:set var="tmp">${activitat.funcionariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfFuncionariForFuncionariID[tmp]}
          </c:if>
             <c:if test="${empty activitat.funcionariID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TIPUS)}">
        <td>
          <c:set var="tmp">${activitat.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
             <c:if test="${empty activitat.tipus}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REGISTRE)}">
        <td>
          ${activitat.registre}
             <c:if test="${empty activitat.registre}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TRAMIT)}">
        <td>
          ${activitat.tramit}
             <c:if test="${empty activitat.tramit}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.CODISIA)}">
        <td>
          ${activitat.codiSia}
             <c:if test="${empty activitat.codiSia}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.AUTORITZACIOID)}">
        <td>
          ${activitat.autoritzacioID}
             <c:if test="${empty activitat.autoritzacioID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.DATACREACIO)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${activitat.dataCreacio}" />
             <c:if test="${empty activitat.dataCreacio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATNOM)}">
        <td>
          ${activitat.interessatNom}
             <c:if test="${empty activitat.interessatNom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE1)}">
        <td>
          ${activitat.interessatLlinatge1}
             <c:if test="${empty activitat.interessatLlinatge1}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE2)}">
        <td>
          ${activitat.interessatLlinatge2}
             <c:if test="${empty activitat.interessatLlinatge2}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATTIPUS)}">
        <td>
          ${activitat.interessatTipus}
             <c:if test="${empty activitat.interessatTipus}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATIDENTIFICACIO)}">
        <td>
          ${activitat.interessatIdentificacio}
             <c:if test="${empty activitat.interessatIdentificacio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTNOM)}">
        <td>
          ${activitat.representantNom}
             <c:if test="${empty activitat.representantNom}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE1)}">
        <td>
          ${activitat.representantLlinatge1}
             <c:if test="${empty activitat.representantLlinatge1}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE2)}">
        <td>
          ${activitat.representantLlinatge2}
             <c:if test="${empty activitat.representantLlinatge2}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTTIPUS)}">
        <td>
          ${activitat.representantTipus}
             <c:if test="${empty activitat.representantTipus}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTIDENTIFICACIO)}">
        <td>
          ${activitat.representantIdentificacio}
             <c:if test="${empty activitat.representantIdentificacio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TRAMITVERSIO)}">
        <td>
          ${activitat.tramitVersio}
             <c:if test="${empty activitat.tramitVersio}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUDOCUMENTID)}">
        <td>
          ${activitat.arxiuDocumentID}
             <c:if test="${empty activitat.arxiuDocumentID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUEXPEDIENTID)}">
        <td>
          ${activitat.arxiuExpedientID}
             <c:if test="${empty activitat.arxiuExpedientID}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ESTAT)}">
        <td>
          <c:set var="tmp">${activitat.estat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstat[tmp]}
          </c:if>
             <c:if test="${empty activitat.estat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.URL)}">
        <td>
          ${activitat.url}
             <c:if test="${empty activitat.url}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.DATAACTIVITAT)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${activitat.dataActivitat}" />
             <c:if test="${empty activitat.dataActivitat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.IDACTUACIOTRAMIT)}">
        <td>
          ${activitat.idActuacioTramit}
             <c:if test="${empty activitat.idActuacioTramit}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.PROCEDIMENT)}">
        <td>
          ${activitat.procediment}
             <c:if test="${empty activitat.procediment}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUREINTENTS)}">
        <td>
          ${activitat.arxiuReintents}
             <c:if test="${empty activitat.arxiuReintents}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUESTAT)}">
        <td>
          ${activitat.arxiuEstat}
             <c:if test="${empty activitat.arxiuEstat}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUDARRERINTENT)}">
        <td>
          <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${activitat.arxiuDarrerIntent}" />
             <c:if test="${empty activitat.arxiuDarrerIntent}">
             <span aria-hidden="true">&nbsp;</span><span class="sr-only">Sense valor</span>
             </c:if>
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


