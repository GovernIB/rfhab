      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${sincroUnitats.sincrounitatsId}"/>
       &nbsp;
      </td>
      </c:if>

