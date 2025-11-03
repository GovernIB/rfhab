<un:useConstants
  var="ActivitatFields"
  className="es.caib.rfhab.model.fields.ActivitatFields"
/>
<un:useConstants
  var="FuncionariFields"
  className="es.caib.rfhab.model.fields.FuncionariFields"
/>

<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<script type="text/javascript">
  const tipusActivitatOptions = [];
  console.log("tipusActivitatFiltreCerca: ${tipusActivitatFiltreCerca}");//todo:eliminar
  <c:forEach items="${tipusActivitatFiltreCerca}" var="tipusActivitat">
  	console.log("tipusActivitat: ${tipusActivitat.key} - ${tipusActivitat.value}");
  	tipusActivitatOptions.push({
  		value: "${tipusActivitat.key}",
  		text: "${tipusActivitat.value}",
  	});
  </c:forEach>

  $(document).ready(function() {
  	document.getElementById('FilterDiv').style.display = 'inherit';
  	document.getElementById('FilterButton').style.display = 'none';

  	replaceTipusActivitatFilter(tipusActivitatOptions);
	const funcionarisNomInputPlaceholder = "<fmt:message key="genapp.form.searchby"><fmt:param><fmt:message key="${FuncionariFields.NOM.codeLabel}" /></fmt:param></fmt:message>";
	const funcionarisNifInputPlaceholder = "<fmt:message key="genapp.form.searchby"><fmt:param><fmt:message key="${FuncionariFields.IDENTIFICADOR.codeLabel}" /></fmt:param></fmt:message>";
  	replaceFuncionarisFilter("<fmt:message key="${ActivitatFields.FUNCIONARIID.codeLabel}" />" + " " + "<fmt:message key="${FuncionariFields.NOM.codeLabel}" />", funcionarisNomInputPlaceholder);
  	addFuncionarisNifFilter("<fmt:message key="${ActivitatFields.FUNCIONARIID.codeLabel}" />" + " " + "<fmt:message key="${FuncionariFields.IDENTIFICADOR.codeLabel}" />", funcionarisNifInputPlaceholder);
  });
</script>
