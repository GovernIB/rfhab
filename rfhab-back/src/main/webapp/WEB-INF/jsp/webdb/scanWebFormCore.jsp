<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ScanWebFields" className="es.caib.rfhab.model.fields.ScanWebFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.TRANSACTIONID)}">
        <tr id="scanWeb_transactionID_rowid">
          <td id="scanWeb_transactionID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.TRANSACTIONID])?'scanWeb.transactionID':__theForm.labels[ScanWebFields.TRANSACTIONID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.TRANSACTIONID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.TRANSACTIONID]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_transactionID_columnvalueid">
            <form:errors path="scanWeb.transactionID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.TRANSACTIONID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ScanWebFields.TRANSACTIONID)? ' uneditable-input' : ''}"  style=""  path="scanWeb.transactionID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.TRANSACTIONWEBID)}">
        <tr id="scanWeb_transactionWebID_rowid">
          <td id="scanWeb_transactionWebID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.TRANSACTIONWEBID])?'scanWeb.transactionWebID':__theForm.labels[ScanWebFields.TRANSACTIONWEBID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.TRANSACTIONWEBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.TRANSACTIONWEBID]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_transactionWebID_columnvalueid">
              <form:errors path="scanWeb.transactionWebID" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.TRANSACTIONWEBID)? 'true' : 'false'}" path="scanWeb.transactionWebID"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_transactionWebID" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_transactionWebID" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.transactionWebID'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('scanWeb.transactionWebID'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.transactionWebID'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_transactionWebID').on('click', function(){
					var valor = ($('#dropdownMenuContainer_transactionWebID').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_transactionWebID').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.STATUS)}">
        <tr id="scanWeb_status_rowid">
          <td id="scanWeb_status_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.STATUS])?'scanWeb.status':__theForm.labels[ScanWebFields.STATUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.STATUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.STATUS]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_status_columnvalueid">
            <form:errors path="scanWeb.status" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.STATUS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ScanWebFields.STATUS)? ' uneditable-input' : ''}"  style=""  path="scanWeb.status"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.FITXERID)}">
        <tr id="scanWeb_fitxerID_rowid">
          <td id="scanWeb_fitxerID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.FITXERID])?'scanWeb.fitxerID':__theForm.labels[ScanWebFields.FITXERID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_fitxerID_columnvalueid">
              <form:errors path="scanWeb.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ScanWebFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${rfh:fileUrl(__theForm.scanWeb.fitxer)}"/>">${__theForm.scanWeb.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ScanWebFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,ScanWebFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.scanWeb.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${rfh:fileUrl(__theForm.scanWeb.fitxer)}"/>">${__theForm.scanWeb.fitxer.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.FILEINFO)}">
        <tr id="scanWeb_fileInfo_rowid">
          <td id="scanWeb_fileInfo_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.FILEINFO])?'scanWeb.fileInfo':__theForm.labels[ScanWebFields.FILEINFO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.FILEINFO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.FILEINFO]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_fileInfo_columnvalueid">
              <form:errors path="scanWeb.fileInfo" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.FILEINFO)? 'true' : 'false'}" path="scanWeb.fileInfo"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_fileInfo" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_fileInfo" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.fileInfo'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('scanWeb.fileInfo'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.fileInfo'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_fileInfo').on('click', function(){
					var valor = ($('#dropdownMenuContainer_fileInfo').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_fileInfo').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.SIGNEDFILEINFO)}">
        <tr id="scanWeb_signedFileInfo_rowid">
          <td id="scanWeb_signedFileInfo_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.SIGNEDFILEINFO])?'scanWeb.signedFileInfo':__theForm.labels[ScanWebFields.SIGNEDFILEINFO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.SIGNEDFILEINFO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.SIGNEDFILEINFO]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_signedFileInfo_columnvalueid">
              <form:errors path="scanWeb.signedFileInfo" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.SIGNEDFILEINFO)? 'true' : 'false'}" path="scanWeb.signedFileInfo"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_signedFileInfo" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_signedFileInfo" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.signedFileInfo'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('scanWeb.signedFileInfo'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.signedFileInfo'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_signedFileInfo').on('click', function(){
					var valor = ($('#dropdownMenuContainer_signedFileInfo').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_signedFileInfo').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.METADADES)}">
        <tr id="scanWeb_metadades_rowid">
          <td id="scanWeb_metadades_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.METADADES])?'scanWeb.metadades':__theForm.labels[ScanWebFields.METADADES]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.METADADES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.METADADES]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_metadades_columnvalueid">
              <form:errors path="scanWeb.metadades" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.METADADES)? 'true' : 'false'}" path="scanWeb.metadades"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_metadades" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_metadades" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.metadades'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('scanWeb.metadades'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.metadades'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_metadades').on('click', function(){
					var valor = ($('#dropdownMenuContainer_metadades').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_metadades').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.MISSATGE)}">
        <tr id="scanWeb_missatge_rowid">
          <td id="scanWeb_missatge_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.MISSATGE])?'scanWeb.missatge':__theForm.labels[ScanWebFields.MISSATGE]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.MISSATGE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.MISSATGE]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_missatge_columnvalueid">
              <form:errors path="scanWeb.missatge" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.MISSATGE)? 'true' : 'false'}" path="scanWeb.missatge"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_missatge" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_missatge" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.missatge'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('scanWeb.missatge'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('scanWeb.missatge'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_missatge').on('click', function(){
					var valor = ($('#dropdownMenuContainer_missatge').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_missatge').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.USUARIID)}">
        <tr id="scanWeb_usuariID_rowid">
          <td id="scanWeb_usuariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.USUARIID])?'scanWeb.usuariID':__theForm.labels[ScanWebFields.USUARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_usuariID_columnvalueid">
          <form:errors path="scanWeb.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ScanWebFields.USUARIID)}" >
          <form:hidden path="scanWeb.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.scanWeb.usuariID,__theForm.listOfUsuariForUsuariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ScanWebFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="scanWeb_usuariID"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };"  cssClass="form-control col-md-9-optional" path="scanWeb.usuariID">
            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.DATACREACIO)}">
        <tr id="scanWeb_dataCreacio_rowid">
          <td id="scanWeb_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.DATACREACIO])?'scanWeb.dataCreacio':__theForm.labels[ScanWebFields.DATACREACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_dataCreacio_columnvalueid">
    <form:errors path="scanWeb.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="scanWeb_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#scanWeb_dataCreacio" path="scanWeb.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ScanWebFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#scanWeb_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#scanWeb_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ScanWebFields.ENTITATID)}">
        <tr id="scanWeb_entitatID_rowid">
          <td id="scanWeb_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ScanWebFields.ENTITATID])?'scanWeb.entitatID':__theForm.labels[ScanWebFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ScanWebFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ScanWebFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="scanWeb_entitatID_columnvalueid">
            <form:errors path="scanWeb.entitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ScanWebFields.ENTITATID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ScanWebFields.ENTITATID)? ' uneditable-input' : ''}"  style=""  path="scanWeb.entitatID"   />

           </td>
        </tr>
        </c:if>
        
