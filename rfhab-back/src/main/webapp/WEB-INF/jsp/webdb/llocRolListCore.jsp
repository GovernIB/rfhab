  <c:if test="${empty llocRolItems}">
     <%@include file="llocRolListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty llocRolItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="llocRolListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="llocRolListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="llocRolListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="llocRol" items="${llocRolItems}">

        <tr id="llocRol_rowid_${llocRol.llocRolID}">
          <%@include file="llocRolListCoreMultipleSelect.jsp" %>

          <%@include file="llocRolListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="llocRolListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
