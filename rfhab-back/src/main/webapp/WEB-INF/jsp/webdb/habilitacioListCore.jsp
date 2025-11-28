  <c:if test="${empty habilitacioItems}">
     <%@include file="habilitacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty habilitacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="habilitacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="habilitacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="habilitacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="habilitacio" items="${habilitacioItems}">

        <tr id="habilitacio_rowid_${habilitacio.habilitacioID}">
          <%@include file="habilitacioListCoreMultipleSelect.jsp" %>

          <%@include file="habilitacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="habilitacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
