      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${funcionariRol.funcionariRolID}"/>
       &nbsp;
      </td>
      </c:if>

