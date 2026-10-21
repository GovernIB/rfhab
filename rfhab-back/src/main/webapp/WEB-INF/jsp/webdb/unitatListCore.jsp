  <c:if test="${empty unitatItems}">
     <%@include file="unitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty unitatItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="unitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="unitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="unitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="unitat" items="${unitatItems}">

        <tr id="unitat_rowid_${unitat.unitatID}">
          <%@include file="unitatListCoreMultipleSelect.jsp" %>

          <%@include file="unitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="unitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
