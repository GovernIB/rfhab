  <c:if test="${empty historicItems}">
     <%@include file="historicListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty historicItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="historicListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="historicListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="historicListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="historic" items="${historicItems}">

        <tr id="historic_rowid_${historic.historicID}">
          <%@include file="historicListCoreMultipleSelect.jsp" %>

          <%@include file="historicListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="historicListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
