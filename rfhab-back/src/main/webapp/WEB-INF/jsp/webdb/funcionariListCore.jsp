  <c:if test="${empty funcionariItems}">
     <%@include file="funcionariListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty funcionariItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecció d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="funcionariListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="funcionariListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="funcionariListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="funcionari" items="${funcionariItems}">

        <tr id="funcionari_rowid_${funcionari.funcionariID}">
          <%@include file="funcionariListCoreMultipleSelect.jsp" %>

          <%@include file="funcionariListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="funcionariListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
