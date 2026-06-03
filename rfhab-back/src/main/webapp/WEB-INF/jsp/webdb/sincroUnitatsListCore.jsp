  <c:if test="${empty sincroUnitatsItems}">
     <%@include file="sincroUnitatsListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty sincroUnitatsItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="sincroUnitatsListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="sincroUnitatsListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="sincroUnitatsListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="sincroUnitats" items="${sincroUnitatsItems}">

        <tr id="sincroUnitats_rowid_${sincroUnitats.sincrounitatsId}">
          <%@include file="sincroUnitatsListCoreMultipleSelect.jsp" %>

          <%@include file="sincroUnitatsListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="sincroUnitatsListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
