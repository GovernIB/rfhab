  <c:if test="${empty rolItems}">
     <%@include file="rolListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty rolItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="rolListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="rolListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="rolListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="rol" items="${rolItems}">

        <tr id="rol_rowid_${rol.rolID}">
          <%@include file="rolListCoreMultipleSelect.jsp" %>

          <%@include file="rolListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="rolListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
