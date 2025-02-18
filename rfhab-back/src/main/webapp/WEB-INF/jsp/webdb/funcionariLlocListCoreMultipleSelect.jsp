      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${funcionariLloc.funcionarillocID}"/>
       &nbsp;
      </td>
      </c:if>

