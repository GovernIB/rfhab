  <c:if test="${empty funcionariRolItems}">
     <%@include file="funcionariRolListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty funcionariRolItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="funcionariRolListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="funcionariRolListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="funcionariRolListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="funcionariRol" items="${funcionariRolItems}">

        <tr id="funcionariRol_rowid_${funcionariRol.funcionariRolID}">
          <%@include file="funcionariRolListCoreMultipleSelect.jsp" %>

          <%@include file="funcionariRolListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="funcionariRolListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
