  <c:if test="${empty llocItems}">
     <%@include file="llocListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty llocItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="llocListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="llocListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="llocListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="lloc" items="${llocItems}">

        <tr id="lloc_rowid_${lloc.llocID}">
          <%@include file="llocListCoreMultipleSelect.jsp" %>

          <%@include file="llocListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="llocListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
