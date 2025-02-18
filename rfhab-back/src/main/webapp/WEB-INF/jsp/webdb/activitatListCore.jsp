  <c:if test="${empty activitatItems}">
     <%@include file="activitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty activitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="activitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="activitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="activitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="activitat" items="${activitatItems}">

        <tr id="activitat_rowid_${activitat.activitatID}">
          <%@include file="activitatListCoreMultipleSelect.jsp" %>

          <%@include file="activitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="activitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
