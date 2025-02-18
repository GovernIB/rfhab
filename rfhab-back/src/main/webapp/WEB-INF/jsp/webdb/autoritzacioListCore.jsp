  <c:if test="${empty autoritzacioItems}">
     <%@include file="autoritzacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty autoritzacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="autoritzacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="autoritzacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="autoritzacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="autoritzacio" items="${autoritzacioItems}">

        <tr id="autoritzacio_rowid_${autoritzacio.autoritzacioID}">
          <%@include file="autoritzacioListCoreMultipleSelect.jsp" %>

          <%@include file="autoritzacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="autoritzacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
