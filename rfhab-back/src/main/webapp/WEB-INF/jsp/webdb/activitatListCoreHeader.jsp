<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ActivitatFields" className="es.caib.rfhab.model.fields.ActivitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ACTIVITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.ACTIVITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.FUNCIONARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.FUNCIONARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TIPUS)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REGISTRE)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.REGISTRE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TRAMIT)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.TRAMIT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.CODISIA)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.CODISIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.AUTORITZACIOID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.AUTORITZACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATNOM)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.INTERESSATNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE1)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.INTERESSATLLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE2)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.INTERESSATLLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATTIPUS)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.INTERESSATTIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.INTERESSATIDENTIFICACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.INTERESSATIDENTIFICACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTNOM)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.REPRESENTANTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE1)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.REPRESENTANTLLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE2)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.REPRESENTANTLLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTTIPUS)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.REPRESENTANTTIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.REPRESENTANTIDENTIFICACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.REPRESENTANTIDENTIFICACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.TRAMITVERSIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.TRAMITVERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUDOCUMENTID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.ARXIUDOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ARXIUEXPEDIENTID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.ARXIUEXPEDIENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.ESTAT)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.URL)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.URL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.DATAACTIVITAT)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.DATAACTIVITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.IDACTUACIOTRAMIT)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.IDACTUACIOTRAMIT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ActivitatFields.PROCEDIMENT)}">
        <th>${rfh:getSortIcons(__theFilterForm,ActivitatFields.PROCEDIMENT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

