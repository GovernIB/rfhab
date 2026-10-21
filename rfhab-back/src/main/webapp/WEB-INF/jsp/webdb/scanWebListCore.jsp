  <c:if test="${empty scanWebItems}">
     <%@include file="scanWebListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty scanWebItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="scanWebListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="scanWebListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="scanWebListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="scanWeb" items="${scanWebItems}">

        <tr id="scanWeb_rowid_${scanWeb.digitalID}">
          <%@include file="scanWebListCoreMultipleSelect.jsp" %>

          <%@include file="scanWebListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="scanWebListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
