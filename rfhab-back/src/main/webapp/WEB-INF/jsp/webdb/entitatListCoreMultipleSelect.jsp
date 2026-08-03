      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
            <label for="selectedItem_${entitat.entitatID}" class="sr-only">Seleccionar entitat ${entitat.entitatID}</label>
            <form:checkbox id="selectedItem_${entitat.entitatID}" path="selectedItems" value="${entitat.entitatID}"/>
       &nbsp;
      </td>
      </c:if>

