
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="llocHabilitacioForm" method="${(empty method)?'post':method}" 
 enctype="multipart/form-data">
  
  <%@include file="llocHabilitacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${llocHabilitacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="llocHabilitacioFormCorePre.jsp" %>

  <%@include file="llocHabilitacioFormCore.jsp" %>

  <%@include file="llocHabilitacioFormCorePost.jsp" %>

  <%@include file="llocHabilitacioFormButtons.jsp" %>

  <c:if test="${not empty llocHabilitacioForm.sections}">
     <c:set var="__basename" value="llocHabilitacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${llocHabilitacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/llocHabilitacioFormModificable.jsp" %>
  </c:if>

</form:form>
<script>
    <%-- Les pipelles no actives de traduccions buides no visualitzen l'avis de camp requerit. --%>
    <%-- Amb aquest codi es posen a primer pla --%>
    document.querySelectorAll('form').forEach(function(form) {
    <%-- 'invalid' no bombolleja: cal utilitzar la fase de captura --%>
    form.addEventListener('invalid', function(e) {
            var pane = e.target.closest('.tab-pane');
            if (pane && !pane.classList.contains('active')) {
                // Activa el tab corresponent al camp invàlid
                var link = document.querySelector('a[href="#' + pane.id + '"]');
                if (link) {
                    $(link).tab('show'); <%-- Bootstrap 4 (jQuery) --%>
                }
            }
        }, true);
    });
</script>


