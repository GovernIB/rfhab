<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ScanWebFields" className="es.caib.rfhab.model.fields.ScanWebFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[scanWeb.digitalID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.DIGITALID)}">
          <td>
          ${scanWeb.digitalID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.TRANSACTIONID)}">
          <td>
          ${scanWeb.transactionID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.TRANSACTIONWEBID)}">
          <td>
          ${scanWeb.transactionWebID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.STATUS)}">
          <td>
          ${scanWeb.status}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.FITXERID)}">
          <td>
            <c:if test="${not empty scanWeb.fitxer}">
              <a target="_blank" href="<c:url value="${rfh:fileUrl(scanWeb.fitxer)}"/>">${scanWeb.fitxer.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.FILEINFO)}">
          <td>
          ${scanWeb.fileInfo}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.SIGNEDFILEINFO)}">
          <td>
          ${scanWeb.signedFileInfo}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.METADADES)}">
          <td>
          ${scanWeb.metadades}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.MISSATGE)}">
          <td>
          ${scanWeb.missatge}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.USUARIID)}">
          <td>
          <c:set var="tmp">${scanWeb.usuariID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariForUsuariID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${scanWeb.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.ENTITATID)}">
          <td>
          ${scanWeb.entitatID}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[scanWeb.digitalID]}" />
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


