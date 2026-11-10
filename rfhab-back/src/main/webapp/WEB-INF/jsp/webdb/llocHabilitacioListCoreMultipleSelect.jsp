      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
        <th>
        <form:checkbox path="selectedItems" aria-label="Seleccionar" value="${llocHabilitacio.llocHabilitacioID}"/>
            <span class="sr-only">Seleccionar fila</span>
        </th>
      </c:if>

