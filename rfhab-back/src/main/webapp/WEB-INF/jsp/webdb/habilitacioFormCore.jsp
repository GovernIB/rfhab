<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="HabilitacioFields" className="es.caib.rfhab.model.fields.HabilitacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,HabilitacioFields.NOMID)}">
        <tr id="habilitacio_nomID_rowid">
          <td id="habilitacio_nomID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HabilitacioFields.NOMID])?'habilitacio.nomID':__theForm.labels[HabilitacioFields.NOMID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[HabilitacioFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HabilitacioFields.NOMID]}" ></i>
              </c:if>
            </td>
          <td id="habilitacio_nomID_columnvalueid">
       <form:errors path="habilitacio.nom" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="habilitacio.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="habilitacio.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,HabilitacioFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,HabilitacioFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HabilitacioFields.CODI)}">
        <tr id="habilitacio_codi_rowid">
          <td id="habilitacio_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HabilitacioFields.CODI])?'habilitacio.codi':__theForm.labels[HabilitacioFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[HabilitacioFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HabilitacioFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="habilitacio_codi_columnvalueid">
            <form:errors path="habilitacio.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HabilitacioFields.CODI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,HabilitacioFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="habilitacio.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HabilitacioFields.DATACREACIO)}">
        <tr id="habilitacio_dataCreacio_rowid">
          <td id="habilitacio_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HabilitacioFields.DATACREACIO])?'habilitacio.dataCreacio':__theForm.labels[HabilitacioFields.DATACREACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[HabilitacioFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HabilitacioFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="habilitacio_dataCreacio_columnvalueid">
    <form:errors path="habilitacio.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="habilitacio_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HabilitacioFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#habilitacio_dataCreacio" path="habilitacio.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,HabilitacioFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#habilitacio_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#habilitacio_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HabilitacioFields.ENTITATID)}">
        <tr id="habilitacio_entitatID_rowid">
          <td id="habilitacio_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HabilitacioFields.ENTITATID])?'habilitacio.entitatID':__theForm.labels[HabilitacioFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[HabilitacioFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HabilitacioFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="habilitacio_entitatID_columnvalueid">
            <form:errors path="habilitacio.entitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HabilitacioFields.ENTITATID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,HabilitacioFields.ENTITATID)? ' uneditable-input' : ''}"  style=""  path="habilitacio.entitatID"   />

           </td>
        </tr>
        </c:if>
        
