  <c:if test="${empty historicLlocItems}">
     <%@include file="historicLlocListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty historicLlocItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecció d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="historicLlocListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="historicLlocListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="historicLlocListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="historicLloc" items="${historicLlocItems}">

        <tr id="historicLloc_rowid_${historicLloc.historicllocID}">
          <%@include file="historicLlocListCoreMultipleSelect.jsp" %>

          <%@include file="historicLlocListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="historicLlocListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
