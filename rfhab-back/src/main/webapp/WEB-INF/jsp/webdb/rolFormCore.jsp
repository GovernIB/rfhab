<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RolFields" className="es.caib.rfhab.model.fields.RolFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RolFields.NOMID)}">
        <tr id="rol_nomID_rowid">
          <td id="rol_nomID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[RolFields.NOMID])?'rol.nomID':__theForm.labels[RolFields.NOMID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[RolFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RolFields.NOMID]}" ></i>
              </c:if>
            </td>
          <td id="rol_nomID_columnvalueid">
       <form:errors path="rol.nom" cssClass="errorField alert alert-danger" />
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
               <form:errors path="rol.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="rol.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,RolFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,RolFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RolFields.CODI)}">
        <tr id="rol_codi_rowid">
          <td id="rol_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[RolFields.CODI])?'rol.codi':__theForm.labels[RolFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[RolFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RolFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="rol_codi_columnvalueid">
            <form:errors path="rol.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RolFields.CODI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,RolFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="rol.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RolFields.DATACREACIO)}">
        <tr id="rol_dataCreacio_rowid">
          <td id="rol_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[RolFields.DATACREACIO])?'rol.dataCreacio':__theForm.labels[RolFields.DATACREACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[RolFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RolFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="rol_dataCreacio_columnvalueid">
    <form:errors path="rol.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="rol_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RolFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#rol_dataCreacio" path="rol.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,RolFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#rol_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#rol_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RolFields.ENTITATID)}">
        <tr id="rol_entitatID_rowid">
          <td id="rol_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[RolFields.ENTITATID])?'rol.entitatID':__theForm.labels[RolFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[RolFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RolFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="rol_entitatID_columnvalueid">
            <form:errors path="rol.entitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RolFields.ENTITATID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,RolFields.ENTITATID)? ' uneditable-input' : ''}"  style=""  path="rol.entitatID"   />

           </td>
        </tr>
        </c:if>
        
