  <c:if test="${empty funcionariLlocItems}">
     <%@include file="funcionariLlocListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty funcionariLlocItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="funcionariLlocListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="funcionariLlocListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="funcionariLlocListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="funcionariLloc" items="${funcionariLlocItems}">

        <tr id="funcionariLloc_rowid_${funcionariLloc.funcionarillocID}">
          <%@include file="funcionariLlocListCoreMultipleSelect.jsp" %>

          <%@include file="funcionariLlocListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="funcionariLlocListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
