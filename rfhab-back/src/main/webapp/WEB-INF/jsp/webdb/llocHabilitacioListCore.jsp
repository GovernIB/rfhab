  <c:if test="${empty llocHabilitacioItems}">
     <%@include file="llocHabilitacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty llocHabilitacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="llocHabilitacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="llocHabilitacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="llocHabilitacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="llocHabilitacio" items="${llocHabilitacioItems}">

        <tr id="llocHabilitacio_rowid_${llocHabilitacio.llocHabilitacioID}">
          <%@include file="llocHabilitacioListCoreMultipleSelect.jsp" %>

          <%@include file="llocHabilitacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="llocHabilitacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
