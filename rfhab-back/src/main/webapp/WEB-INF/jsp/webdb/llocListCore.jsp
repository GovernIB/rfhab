  <c:if test="${empty llocItems}">
     <%@include file="llocListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty llocItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
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
  </div>
  </c:if>
  
