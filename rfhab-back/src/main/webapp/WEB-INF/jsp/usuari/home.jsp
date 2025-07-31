<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style type="text/css">
.well {
	padding: 20px 0.75rem;
	margin: 0rem 4rem;
}

form.msf {
	position: relative;
}

.msf-content {
	margin: 0 auto;
	width: 800px;
}

.msf-view {
	display: none;
}

.msf-view h3 {
	margin-top: 30px;
}

.msf-navigation {
	text-align: center;
}

.msf-nav-button {
	display: none;
}

.msf-header {
	padding-top: 10px;
	margin-bottom: 40px;
	color: #777;
}

.msf-header .msf-step {
	font-size: 20px;
	/*display : inline-block;
       vertical-align : middle;*/
}

.msf-header .msf-step:hover {
	cursor: pointer;
}

.msf-header .msf-step i.fa {
	height: 60px;
	width: 60px;
	line-height: 55px;
	text-align: center;
	border: 3px solid #777;
	border-radius: 100%;
	font-size: 30px;
	margin-left: 10px;
	margin-right: 10px;
}

.msf-header .msf-step.msf-step-complete {
	/*color: #ef4035;*/
	color: green;
}

.msf-header .msf-step.msf-step-complete i.fa {
	/*border-color : #ef4035;*/
	border-color: green;
}

.msf-header .msf-step.msf-step-incomplete {
	/*color: #ef4035;*/
	color: red;
}

.msf-header .msf-step.msf-step-incomplete i.fa {
	/*border-color : #ef4035;*/
	border-color: red;
}

.msf-header .msf-step.msf-step-active, .msf-header .msf-step.msf-step-active.msf-step-complete,
	.msf-header .msf-step.msf-step-active.msf-step-incomplete {
	color: blue;
	/*color:#3c763d;*/
}

.msf-header .msf-step.msf-step-active {
	font-weight: bold;
}

.msf-header .msf-step.msf-step-active i.fa, .msf-header .msf-step.msf-step-active.msf-step-complete i.fa,
	.msf-header .msf-step.msf-step-active.msf-step-incomplete i.fa {
	border-color: blue;
	/*border-color : #3c763d;*/
}

.input-validation-error {
	border-color: red;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(255, 0, 0, 0.6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(255, 0, 0, 0.6);
}

.input-validation-error:focus {
	border-color: red;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(255, 0, 0, 0.6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(255, 0, 0, 0.6);
}

form label {
	font-weight: bold;
}

.pdfVisor {
	margin-top: 20px;
	margin-bottom: 20px;
}

.buttonsDiv {
	margin-bottom: 30px;
	
	div:last-child{
		text-align: end;
	}

	button[disabled] {
		pointer-events: none;
		opacity: 0.5;
		filter: grayscale(0.75);
	}
}

button[disabled][type="submit"] {
	pointer-events: none;
	opacity: 0.5;
	filter: grayscale(0.75);
}


/* Autocomplete */

.autocomplete-suggestions { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; border: 1px solid #999; background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); }
.autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; }
.autocomplete-no-suggestion { padding: 2px 5px;}
.autocomplete-selected { background: #F0F0F0; }
.autocomplete-suggestions strong { font-weight: bold; color: #000; }
.autocomplete-group { padding: 2px 5px; font-weight: bold; font-size: 16px; color: #000; display: block; border-bottom: 1px solid #000; }

</style>

<div id="wrapper">

	<div id="container body-content">

		<div class="progress">
			<div class="progress-bar progress-bar-success progress-bar-striped"
				role="progressbar" aria-valuenow="0" aria-valuemin="0"
				aria-valuemax="100" style="width: 0%">
				<span class="sr-only">0%</span>
			</div>
		</div>
		<form class="form-horizontal msf">
			<div class="msf-header">
				<div class="row text-center">
					<div class="msf-step col-md-3">
						<span><em>1</em> <fmt:message key="usuari.tramit.titol.dades" /></span>
					</div>
					<div class="msf-step col-md-3">
						<span><em>2</em> <fmt:message key="usuari.tramit.titol.seleccio" /></span>
					</div>
					<div class="msf-step col-md-3">
						<span><em>3</em> <fmt:message key="usuari.tramit.titol.documentacio" /></span>
					</div>
					<div class="msf-step col-md-3">
						<span><em>4</em> <fmt:message key="usuari.tramit.titol.iniciar" /></span>
					</div>
				</div>
			</div>

			<div class="msf-content">
				<div class="msf-view">
					<div class="row">
						<div class="col-md-12">

							<h3>1 <fmt:message key="usuari.tramit.dades.titol" /></h3>

							<div class="form-group">
								<label><fmt:message key="usuari.tramit.dades.nom" /></label> <input id="pas1_nom" name="nom" type="text"
									class="form-control" placeholder="<fmt:message key="usuari.tramit.dades.nom.placeholder" />" data-bind="value: Nom"
									data-val="true" data-val-required="<fmt:message key="usuari.tramit.dades.nom.required" />">
							</div>

							<div class="form-group">
								<label><fmt:message key="usuari.tramit.dades.llinatges" /></label> <input id="pas1_llinatges"
									name="llinatges" type="text" class="form-control"
									placeholder="<fmt:message key="usuari.tramit.dades.llinatges.placeholder" />" data-bind="value: Llinatges"
									data-val="true"
									data-val-required="<fmt:message key="usuari.tramit.dades.llinatges.required" />">
							</div>

							<div class="form-group">
								<label><fmt:message key="usuari.tramit.dades.tipusidentificacio" /></label>
								<select
									id="pas1_tipusIdentificacio" name="tipusIdentificacio"
									class="form-control"
									data-bind="options: availableTypes, selectedOptions: chosenType, optionsCaption: '<fmt:message key="usuari.tramit.dades.numeroidentificacio.placeholder" />'"
									data-val="true"
									data-val-required="<fmt:message key="usuari.tramit.dades.tipusidentificacio.required" />">
									<option value="DNI"><fmt:message key="usuari.tramit.dades.tipusidentificacio.dni" /></option>
									<option value="NIE"><fmt:message key="usuari.tramit.dades.tipusidentificacio.nie" /></option>
									<option value="Passaport"><fmt:message key="usuari.tramit.dades.tipusidentificacio.passaport" /></option>
									<option value="Altres"><fmt:message key="usuari.tramit.dades.tipusidentificacio.altres" /></option>
								</select>
							</div>

							<div class="form-group">
								<label><fmt:message key="usuari.tramit.dades.numeroidentificacio" /></label> <input
									id="pas1_identificacion" name="identificacio" type="text"
									class="form-control" placeholder="<fmt:message key="usuari.tramit.dades.numeroidentificacio" />"
									data-bind="value: Identificacio" data-val="true"
									data-val-required="<fmt:message key="usuari.tramit.dades.numeroidentificacio.required" />">
							</div>

							<div class="form-group">
								<input type="checkbox" name="representant" value="representant"
									id="representant"> <fmt:message key="usuari.tramit.dades.representant" />
							</div>

							<div class="formRepresentant" style="display: none;">

								<h3><fmt:message key="usuari.tramit.dades.representant.titol" /></h3>

								<div class="form-group">
									<label><fmt:message key="usuari.tramit.dades.representant.nom" /></label>
									<input
										id="pas1_representant_nom" name="representant_nom" type="text"
										class="form-control" placeholder="<fmt:message key="usuari.tramit.dades.nom.placeholder" />" data-bind="value: Nom"
										data-val="true" data-val-required="<fmt:message key="usuari.tramit.dades.nom.required" />"
										data-rule-required="#representant:checked">
								</div>

								<div class="form-group">
									<label><fmt:message key="usuari.tramit.dades.representant.llinatges" /></label>
									<input
										id="pas1_representant_llinatges" name="representant_llinatges"
										type="text" class="form-control" placeholder="<fmt:message key="usuari.tramit.dades.llinatges.placeholder" />"
										data-bind="value: Llinatges" data-val="true"
										data-val-required="<fmt:message key="usuari.tramit.dades.llinatges.required" />"
										data-rule-required="#representant:checked">
								</div>

								<div class="form-group">
									<label><fmt:message key="usuari.tramit.dades.representant.tipusidentificacio" /></label>
									<select
										id="pas1_representant_tipusIdentificacio"
										name="representant_tipusIdentificacio" class="form-control"
										data-bind="options: availableTypes, selectedOptions: chosenType, optionsCaption: '"<fmt:message key="usuari.tramit.dades.tipusidentificacio.placeholder" />"'"
										data-val="true"
										data-val-required="<fmt:message key="usuari.tramit.dades.tipusidentificacio.required" />"
										data-rule-required="#representant:checked">
										<option value="DNI"><fmt:message key="usuari.tramit.dades.tipusidentificacio.dni" /></option>
										<option value="NIE"><fmt:message key="usuari.tramit.dades.tipusidentificacio.nie" /></option>
										<option value="Passaport"><fmt:message key="usuari.tramit.dades.tipusidentificacio.passaport" /></option>
										<option value="Altres"><fmt:message key="usuari.tramit.dades.tipusidentificacio.altres" /></option>
									</select>
								</div>

								<div class="form-group">
									<label><fmt:message key="usuari.tramit.dades.representant.numeroidentificacio" /></label> <input
										id="pas1_representant_identificacion"
										name="representant_identificacio" type="text"
										class="form-control" placeholder="<fmt:message key="usuari.tramit.dades.numeroidentificacio.placeholder" />"
										data-bind="value: Identificacio" data-val="true"
										data-val-required="<fmt:message key="usuari.tramit.dades.numeroidentificacio.required" />"
										data-rule-required="#representant:checked">
								</div>

							</div>
						</div>
					</div>
				</div>

				<div class="msf-view">
					<div class="row">
						<div class="col-md-12">

							<h3>2 <fmt:message key="usuari.tramit.seleccio.titol" /></h3>

							<div class="form-group">
								<label for="pas2_procediment" class="w-100">
									<fmt:message key="usuari.tramit.seleccio.procediment" />
									<c:set var="containsValues" value="false" />
									<select id="pas2_procediment" name="procediment-select" class="form-control input-ample-tota-linia" onchange="if(typeof onSelectedProcediment == 'function') {  onSelectedProcediment(this); };">
										<c:forEach items="${llistaProcediments}" var="tmp">
											<option value="${tmp.key}">${tmp.value[2]} &nbsp; ${tmp.value[0]}</option>
											<c:if test="${not empty tmp.key}">
												<c:set var="containsValues"  value="true" />
											</c:if>
										</c:forEach>
										<c:if test="${containsValues}">
											<option value="" selected="true" >"<fmt:message key="usuari.tramit.seleccio.procediment.placeholder" />"</option>
										</c:if>
									</select>
									<script>
										$(document).ready(function() {
											$('#pas2_procediment').select2(
												{
													placeholder: "<fmt:message key="usuari.tramit.seleccio.procediment.placeholder" />",
													allowClear: true,
													language: "${lang}",
													minimumInputLength: 0
												}
											);

											const select2Container = $("#pas2_procediment").closest('label').find('.select2');
											select2Container.addClass("input-ample-tota-linia");
										});
									</script>
									<input type="hidden" id="procediment-id" class="form-control always-validate" data-rule-procediment="true" name="procediment-id" value="" />
								</label>
							</div>

							<div class="form-group">
								<label><fmt:message key="usuari.tramit.seleccio.tramit" /></label>
								<p id="spinner-carregant-tramits" style="text-align: center; display: none;">
									<svg style="width:20%;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 150"><path fill="none" stroke="#007BFF" stroke-width="15" stroke-linecap="round" stroke-dasharray="300 385" stroke-dashoffset="0" d="M275 75c0 31-27 50-50 50-58 0-92-100-150-100-28 0-50 22-50 50s23 50 50 50c58 0 92-100 150-100 24 0 50 19 50 50Z"><animate attributeName="stroke-dashoffset" calcMode="spline" dur="2" values="685;-685" keySplines="0 0 1 1" repeatCount="indefinite"></animate></path></svg>
								</p>
								<input id="pas2_tramit" name="tramit" type="text" disabled
									class="form-control"
									placeholder="<fmt:message key="usuari.tramit.seleccio.tramit.placeholder" />"
									data-bind="value: Tramit" data-val="true"
									data-val-required="<fmt:message key="usuari.tramit.seleccio.tramit.placeholder" />">
								<input type="hidden" id="tramitId" class="form-control always-validate" name="tramitId" value="1" />
								<input type="hidden" id="tramitVersio" name="tramitVersio" value="1" />
								<input type="hidden" id="tramitParametres" name="tramitParametres" value="1" />
								<input type="hidden" id="idTraTel" name="idTraTel" value="1" />
							</div>
						</div>
					</div>
				</div>

				<div class="msf-view">
					<div class="row">
						<div class="col-md-12">

							<h3>3 <fmt:message key="usuari.tramit.documentacio.titol" /></h3>

							<div class="form-group pdfVisor">
								<embed id="embed-pdf" src="/rfhabback/dummy.pdf" width="100%" height="600"
									type="application/pdf">
								<iframe id="iframe-pdf" style="display: none;" src="" type="application/pdf" width="100%" height="600" style="overflow: auto;"></iframe>
								<input style="display: none;" id="input-pdf" name="inputPdf" type="text" class="form-control" 
									data-bind="value: Pdf" data-val="true" data-val-required="<fmt:message key="usuari.tramit.iniciar.apoderament" />">
							</div>

							<div class="row buttonsDiv">
								<div class="col-md-6">
									<button type="button" id="btn-descarregar-firmat-id" style="display: none;" class="btn btn-primary" disabled onclick="if(typeof onClickDescarregarFirmat == 'function') {  onClickDescarregarFirmat(this); };">
										<i class="fa fa-file-pdf"></i> <fmt:message key="usuari.tramit.documentacio.descarregarfirmat" />
									</button>
								</div>
								<div class="col-md-6">
									<button type="button" class="btn btn-secondary"  onclick="if(typeof onClickPujarDocument == 'function') {  onClickPujarDocument(this); };">
										<i class="fa fa-upload"></i> <fmt:message key="usuari.tramit.documentacio.pujardocument" />
									</button>										
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="msf-view">
					<div class="row">
						<div class="col-md-12" style="min-height: 325px;" id="div-iniciar-tramit">

							<h3><fmt:message key="usuari.tramit.iniciar.titol" /></h3>

							<p><fmt:message key="usuari.tramit.iniciar.redireccionamentautomatic" /></p>
							<p>
								<fmt:message key="usuari.tramit.iniciar.noredireccionament" />&nbsp;<a
									href="#" id="link-iniciar-tramit">aquí</a>.
							</p>

						</div>
						<div class="col-md-12" style="min-height: 325px;" id="div-iniciar-tramit-error" style="display: none;">
							<p class="text-danger">
								<fmt:message key="usuari.tramit.iniciar.error" />
							</p>
						</div>
						<div class="col-md-12" style="min-height: 325px;" id="spinner-carregant-iniciartramit" style="display: none;">
							<p  style="text-align: center;">
								<svg style="width:20%;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 150"><path fill="none" stroke="#007BFF" stroke-width="15" stroke-linecap="round" stroke-dasharray="300 385" stroke-dashoffset="0" d="M275 75c0 31-27 50-50 50-58 0-92-100-150-100-28 0-50 22-50 50s23 50 50 50c58 0 92-100 150-100 24 0 50 19 50 50Z"><animate attributeName="stroke-dashoffset" calcMode="spline" dur="2" values="685;-685" keySplines="0 0 1 1" repeatCount="indefinite"></animate></path></svg>
							</p>
						</div>
					</div>
				</div>
			</div>

			<div class="msf-navigation">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-4">
							<button type="button" data-type="back"
								class="btn btn-default msf-nav-button">
								<i class="fa fa-chevron-left"></i> <fmt:message key="usuari.tramit.navegacio.tornar" />
							</button>
						</div>
						<div class="col-md-4">
							<button type="button" data-type="next"
								class="btn btn-default msf-nav-button">
								Continuar <i class="fa fa-chevron-right"></i>
							</button>
						</div>
						<div class="col-md-4">
							<button type="submit" data-type="submit"
								class="btn btn-primary msf-nav-button"><fmt:message key="usuari.tramit.navegacio.finalitzar" /></button>
						</div>
					</div>
				</div>
			</div>
		</form>

	</div>
</div>

<!--  script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script -->

<!--  script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.1.min.js"></script -->

<script src="<c:url value="/js/jquery-3.7.1.min.js"/>"></script>

<script src="<c:url value="/js/jquery.autocomplete.js"/>"></script>

<!--   script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.4.2/knockout-min.js"></script -->

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validation-unobtrusive/3.2.6/jquery.validate.unobtrusive.min.js"></script>


<!-- Latest compiled and minified JavaScript -->
<script type="text/javascript"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script src="<c:url value="/js/select2.min.js"/>"></script>
<script src="<c:url value="/js/select2_i18n/${lang}.js"/>"></script>

<script type="text/javascript">
	function comprovaInputPdf() {
		var inputPdf = document.getElementById('input-pdf');
		var submitBtn = document.querySelector("button[type='submit'][data-type='submit']");
		if (inputPdf && submitBtn) {
			if (inputPdf.value && inputPdf.value.trim() !== "") {
				submitBtn.removeAttribute('disabled');
			} else {
				submitBtn.setAttribute('disabled', 'disabled');
			}
		}
	}

	$.validator.addMethod("procediment", function(value, element) {
		const select2Container = $("#pas2_procediment").closest('label').find('.select2');
		if (value === "null" || !value) {
			// If the value is null or empty, add an error class to the select2 container
			select2Container.addClass("input-validation-error");
			return false;
		}
		select2Container.removeClass("input-validation-error");
		return true;
	}, "<fmt:message key="usuari.tramit.seleccio.procediment.required" />");
	$.validator.setDefaults({
		ignore: function (index, el) {
								const $el = $(el);

								if ($el.hasClass('always-validate')) {
									return false;
								}

								// Default behavior
								return $el.is(':hidden');
							}
	});

	(function(factory) {
		'use strict';
		if (typeof define === 'function' && define.amd) {
			// AMD is used - Register as an anonymous module.
			define([ 'jquery', 'jquery-validation' ], factory);
		} else if (typeof exports === 'object') {
			factory(require('jquery'), require('jquery-validation'));
		} else {
			// Neither AMD nor CommonJS used. Use global variables.
			if (typeof jQuery === 'undefined') {
				throw 'multi-step-form-js requires jQuery to be loaded first';
			}
			if (typeof jQuery.validator === 'undefined') {
				throw 'multi-step-form-js requires requires jquery.validation.js to be loaded first';
			}
			factory(jQuery);
		}
	}
			(function($) {
				'use strict';

				const msfCssClasses = {
					header : "msf-header",
					step : "msf-step",
					statuses : {
						stepComplete : "msf-step-complete",
						stepIncomplete : "msf-step-incomplete",
						stepActive : "msf-step-active"
					},
					content : "msf-content",
					view : "msf-view",
					navigation : "msf-navigation",
					navButton : "msf-nav-button"
				};

				const msfNavTypes = {
					back : "back",
					next : "next",
					submit : "submit"

				};

				const msfJqueryData = {
					validated : "msf-validated",
					visited : "msf-visited"
				};

				const msfEventTypes = {
					viewChanged : "msf:viewChanged"
				};

				$.fn.multiStepForm = function(options) {
					var form = this;

					var defaults = {
						activeIndex : 0,
						validate : {},
						hideBackButton : false,
						allowUnvalidatedStep : false,
						allowClickNavigation : false
					};

					var settings = $.extend({}, defaults, options);

					//find the msf-content object
					form.content = this.find("." + msfCssClasses.content)
							.first();

					if (form.content.length === 0) {
						throw new Error(
								'Multi-Step Form requires a child element of class \''
										+ msfCssClasses.content + '\'');
					}

					//find the msf-views within the content object
					form.views = $(this.content).find("." + msfCssClasses.view);

					if (form.views.length === 0) {
						throw new Error(
								'Multi-Step Form\'s element of class \''
										+ msfCssClasses.content
										+ '\' requires n elements of class \''
										+ msfCssClasses.view + '\'');
					}

					form.header = this.find("." + msfCssClasses.header).first();
					form.navigation = this.find("." + msfCssClasses.navigation)
							.first();
					form.steps = [];
					//form.completedSteps = 0;

					form.getActiveView = function() {
						return form.views.filter(function() {
							return this.style && this.style.display !== ''
									&& this.style.display !== 'none'
						});
					};

					form.setActiveView = function(index) {
						var previousView = form.getActiveView()[0];
						var previousIndex = form.views.index(previousView);

						$(previousView).hide();
						//if(previousView)
						//    previousView.hide();

						var view = form.views.eq(index);
						view.show();
						view.find(':input').first().focus();

						var completedSteps = 0;
						$.each(form.views, function(index, view) {
							if ($.data(view, msfJqueryData.validated)) {
								completedSteps++;
							}
						});

						//trigger the 'view has changed' event
						form.trigger(msfEventTypes.viewChanged, {
							currentIndex : index,
							previousIndex : previousIndex,
							totalSteps : form.steps.length - 1,
							completedSteps : completedSteps
						});
					}

					form.setStatusCssClass = function(step, cssClass) {
						$(step)
								.removeClass(
										msfCssClasses.statuses.stepComplete);
						$(step).removeClass(
								msfCssClasses.statuses.stepIncomplete);

						$(step).addClass(cssClass);
					}

					form.tryNavigateToView = function(currentIndex, targetIndex) {
						if (targetIndex <= currentIndex) {

							form.validateView(form.views[currentIndex]);

							if (!settings.hideBackButton)
								form.setActiveView(targetIndex);
							return;
						}

						if (!form.validateViews(currentIndex, targetIndex
								- currentIndex, function(i) {
							if (!settings.allowUnvalidatedStep) {
								form.setActiveView(i);
								return false;
							}

							return true;
						})) {
							if (!settings.allowUnvalidatedStep) {
								return;
							}
						}
						form.setActiveView(targetIndex);
					}

					form.init = function() {

						this.initHeader = function() {
							if (form.header.length === 0) {
								form.header = $("<div/>", {
									"class" : msfCssClasses.header,
									"display" : "none"
								});

								$(form).prepend(form.header);
							}

							form.steps = $(form.header).find(
									"." + msfCssClasses.step);

							this.initStep = function(index, view) {

								//append steps to header if they do not exist
								if (form.steps.length < index + 1) {
									$(form.header).append($("<div/>", {
										"class" : msfCssClasses.step,
										"display" : "none"
									}));
								}

								if (settings.allowClickNavigation) {
									//bind the click event to the header step
									$(form.steps[index])
											.click(
													function(e) {
														var view = form
																.getActiveView()[0];
														var currentIndex = form.views
																.index(view);
														var targetIndex = form.steps
																.index($(
																		e.target)
																		.closest(
																				"."
																						+ msfCssClasses.step)[0]);

														form.tryNavigateToView(
																currentIndex,
																targetIndex);
													});
								}
							}

							$.each(form.views, this.initStep);

							form.steps = $(form.header).find(
									"." + msfCssClasses.step);
						};

						this.initNavigation = function() {

							if (form.navigation.length === 0) {
								form.navigation = $("<div/>", {
									"class" : msfCssClasses.navigation
								});

								$(form.content).after(form.navigation);
							}

							this.initNavButton = function(type) {
								var element = this.navigation
										.find("button[data-type='" + type
												+ "'], input[type='button']"), type;
								if (element.length === 0) {
									element = $("<button/>", {
										"class" : msfCssClasses.navButton,
										"data-type" : type,
										"html" : type
									});
									element.appendTo(form.navigation);
								}
								return element;
							};

							form.backNavButton = this
									.initNavButton(msfNavTypes.back);
							form.nextNavButton = this
									.initNavButton(msfNavTypes.next);
							form.submitNavButton = this
									.initNavButton(msfNavTypes.submit);
						};

						this.initHeader();
						this.initNavigation();

						this.views
								.each(function(index, view) {

									$.data(view, msfJqueryData.validated, false);
									$.data(view, msfJqueryData.visited, false);

									//if this is not the last view do not allow the enter key to submit the form as it is not completed yet                  
									if (index !== form.views.length - 1) {
										$(view)
												.find(':input')
												.not('textarea')
												.keypress(
														function(e) {
															if (e.which === 13) // Enter key = keycode 13
															{
																form.nextNavButton
																		.click();
																return false;
															}
														});
									}

									$(view)
											.on(
													'show',
													function(e) {
														if (this !== e.target)
															return;

														var view = e.target;
														$
																.data(
																		view,
																		msfJqueryData.visited,
																		true);

														var index = form.views
																.index(view);
														var step = form.steps[index];

														$(step)
																.addClass(
																		msfCssClasses.statuses.stepActive);
														//form.setStatusCssClass(step, msfCssClasses.statuses.stepActive);

														//choose which navigation buttons should be displayed based on index of view 
														if (index > 0
																&& !settings.hideBackButton) {
															form.backNavButton
																	.show();
														}

														if (index == form.views.length - 2) {
															form.nextNavButton
																	.hide();
															form.submitNavButton
																	.show();
														} else if (index == form.views.length - 1) {
															form.nextNavButton
																	.hide();
															form.backNavButton
																	.hide();
															form.submitNavButton
																	.hide();
														} else {
															form.submitNavButton
																	.hide();
															form.nextNavButton
																	.show();
														}
													});

									$(view)
											.on(
													'hide',
													function(e) {
														if (this !== e.target)
															return;

														var index = form.views
																.index(e.target);
														var step = form.steps[index];

														$(step)
																.removeClass(
																		msfCssClasses.statuses.stepActive);

														if ($
																.data(
																		e.target,
																		msfJqueryData.validated)
																&& $
																		.data(
																				e.target,
																				msfJqueryData.visited)) {
															form
																	.setStatusCssClass(
																			step,
																			msfCssClasses.statuses.stepComplete);
														} else if ($
																.data(
																		e.target,
																		msfJqueryData.visited)) {
															form
																	.setStatusCssClass(
																			step,
																			msfCssClasses.statuses.stepIncomplete);
														} else {
															form
																	.setStatusCssClass(
																			step,
																			"");
														}

														//hide all navigation buttons, display choices will be set on show event
														form.backNavButton
																.hide();
														form.nextNavButton
																.hide();
														form.submitNavButton
																.hide();
													});

									//initially hide each view
									$(view).hide();
								});

						if (settings.activeIndex > 0) {
							$(form).ready(
									function() {
										form.tryNavigateToView(0,
												settings.activeIndex);
									});
						} else {
							form.setActiveView(0);
						}

						comprovaInputPdf();//TODO:parxe dolent, no se com fer validació de la darrera pantalla per habilitar/deshabilitar el botó de submit
					};

					form.validateView = function(view) {
						var index = form.views.index(view);

						if (form.validate().subset(view)) {
							$.data(view, msfJqueryData.validated, true);
							form.setStatusCssClass(form.steps[index],
									msfCssClasses.statuses.stepComplete);
							return true;
						} else {
							$.data(view, msfJqueryData.validated, false);
							form.setStatusCssClass(form.steps[index],
									msfCssClasses.statuses.stepIncomplete);
							return false;
						}
					};

					form.validateViews = function(currentIndex, length, invalid) {
						currentIndex = typeof currentIndex === 'undefined' ? 0
								: currentIndex;
						length = typeof length === 'undefined' ? form.views.length
								: length;

						var validationIgnore = ""; // Saving the existing validator ignore settings to reset them after validating multi-step form
						var isValid = true;

						//remember original validation setings for ignores
						if ($(form).data("validator")) {
							var formValidatorSettings = $(form).data(
									"validator").settings;
							validationIgnore = formValidatorSettings.ignore;

							var currentValidationIgnoreSettingsArray = validationIgnore
									.split(",");
							if (currentValidationIgnoreSettingsArray.length >= 1) {
								// Remove the ":hidden" selector from validator ignore settings as we want our hidden fieldsets/steps to be validated before final submit
								var hiddenIndex = $.inArray(":hidden",
										currentValidationIgnoreSettingsArray);
								currentValidationIgnoreSettingsArray.splice(
										hiddenIndex, 1);
								$(form).data("validator").settings.ignore = currentValidationIgnoreSettingsArray
										.toString();
							}
						}

						for (var i = currentIndex; i < currentIndex + length; i++) {
							if (!form.validateView(form.views[i])) {
								isValid = false;

								if (!invalid(i)) {
									break;
								}
							}
						}

						if ($(form).data("validator")) {
							$(form).data("validator").settings.ignore = validationIgnore;
						}

						return isValid;
					}

					form.init();

					form.nextNavButton.click(function() {
						var view = form.getActiveView()[0];
						var index = form.views.index(view);

						if (form.validateView(view)) {
							form.setActiveView(index + 1);
						} else if (settings.allowUnvalidatedStep) {
							form.setActiveView(index + 1);
						}
					});

					form.backNavButton.click(function() {
						var view = form.getActiveView()[0];
						var index = form.views.index(view);

						form.validateView(view);

						form.setActiveView(index - 1);
					});

					form.submit(function(e) {

						var view = form.getActiveView()[0];
						var index = form.views.index(view);

						if (form.validateView(view)) {
							form.setActiveView(index + 1);
						} else if (settings.allowUnvalidatedStep) {
							form.setActiveView(index + 1);
						}

						var validationIgnore = "";

						form.validateViews(0, form.views.length, function() {
							e.preventDefault();
							return true;
						});

						iniciarTramit();
					});
					return form;
				};

				$.validator.prototype.subset = function(container) {
					var ok = true;
					var self = this;
					$(container).find(':input').each(function() {
						if (!self.element($(this)))
							ok = false;
					});
					return ok;
				};

				$.each([ 'show', 'hide' ], function(i, ev) {
					var el = $.fn[ev];
					$.fn[ev] = function() {
						this.trigger(ev);
						return el.apply(this, arguments);
					};
				});
			}));
</script>

<script type="text/javascript">
	$(document).on(
			"msf:viewChanged",
			function(event, data) {
				var progress = Math
						.round((data.completedSteps / data.totalSteps) * 100);

				$(".progress-bar").css("width", progress + "%").attr(
						'aria-valuenow', progress);

				comprovaInputPdf();

				const pas2Procediment = $('#pas2_procediment');
				//TODO:revisar aquesta comrpovació
				if (pas2Procediment && (!pas2Procediment.data('select2') || $('#pas2_procediment+.select2').css("display") == "none" || $('#pas2_procediment+.select2').css("display") == "hidden"))
				{
					pas2Procediment.select2(
						{
							placeholder: "<fmt:message key="usuari.tramit.seleccio.procediment.placeholder" />",
							allowClear: true,
							language: "${lang}",
							minimumInputLength: 0
						}
					);
					const select2Container = pas2Procediment.closest('label').find('.select2');
					select2Container.addClass("input-ample-tota-linia");
				}
			});

	$(".msf:first").multiStepForm({
		activeIndex : 0,
		validate : {},
		hideBackButton : false,
		allowUnvalidatedStep : false,
		allowClickNavigation : true
	});

	$('#representant').change(function() {
		if (this.checked) {
			$('.formRepresentant').show();
		} else {
			$('.formRepresentant').hide();
		}
	});
	
	var procediments = [
		<c:forEach items="${llistaProcediments}" var="procediment">
            { nom: '${procediment.value[0]}', codiRolsac: '${procediment.key}', llengua: '${procediment.value[1]}', codiSia: '${procediment.value[2]}' },
        </c:forEach>
	];
	
	var tramitsProcediment = [];

	function actualitzaLlistatDeTramits(procediment){
		const pas2Tramit = $("#pas2_tramit");
		if(procediment === 'null' || !procediment){
			tramitsProcediment = [];
			pas2Tramit.prop("disabled", true);
			pas2Tramit.autocomplete().setOptions({ lookup: tramitsProcediment });
			return;
		}
		pas2Tramit.prop("disabled", false);
		
		$('#spinner-carregant-tramits').show();
		pas2Tramit.hide();

		// tramitsProcediment = tramitsAll.filter(function(tramit) {
		// 	return tramit.procediment === procediment;
		// });
		// console.log('Tramits per al procediment ' + procediment + ': ', tramitsProcediment);
		// $('#spinner-carregant-tramits').hide();
		// $('#pas2_tramit').show();

		const url = "<%=request.getContextPath()%>" + "/usuari/obtenirtramits/" + procediment;
		const request = new Request(url, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json'
			}
		});
		fetch(request)
			.then(response => response.json())
			.then(data => {
				tramitsProcediment = [];
				for (const tramit in data) {
					if (Object.prototype.hasOwnProperty.call(data, tramit)) {
						const nomTramit = data[tramit][0];
						const procedimentId = data[tramit][1];
						tramitsProcediment.push(
							{ 
								value: tramit + " " + nomTramit,
								data: nomTramit,
								procediment: procedimentId,
								llengua: data[tramit][2],
								tramitId: data[tramit][3],
								tramitVersio: data[tramit][4],
								tramitParametres: data[tramit][5],
								idTraTel: data[tramit][6]
							});
						}
					}
				console.log('Tramits per al procediment ' + procediment + ': ', tramitsProcediment);

				$('#spinner-carregant-tramits').hide();
				pas2Tramit.autocomplete().setOptions({ lookup: tramitsProcediment });
				pas2Tramit.show();
			})
			.catch(error => {
				const errorMsg = 'Error al carregar els tràmits: ' + error.message;
				inserirMsg('danger', errorMsg);
				console.error('Error obtenint tramits:', error);

				$('#spinner-carregant-tramits').hide();
				pas2Tramit.show();
			});
	}


	var pas2_procediment_id = null;
	var pas2_tramit_value = null;
	function onSelectedProcediment (suggestion) {
		if(pas2_procediment_id != suggestion.value) {
			pas2_procediment_id = suggestion.value;
			pas2_tramit_value = null;
			$('#pas2_tramit').val('');
			console.log('You selected: ' + pas2_procediment_id);

			$('#procediment-id').val(pas2_procediment_id);
			$('#tramitId').val(1);
			$('#tramitVersio').val(1);
			$('#tramitParametres').val(1);
			$('#idTraTel').val(1);
			actualitzaLlistatDeTramits(pas2_procediment_id);
		}
	}

	// https://github.com/devbridge/jQuery-Autocomplete
	$('#pas2_tramit').autocomplete({
	    lookup: tramitsProcediment,
	    minChars: 1,
	    showNoSuggestionNotice: true,
        noSuggestionNotice: 'Sorry, no matching results',
	    onSelect: function (suggestion) {
			if(pas2_tramit_value != suggestion.data) {
				pas2_tramit_value = suggestion.data;
	        	console.log('You selected: ' + suggestion.value + ', ' + suggestion.data + 
	        		'::: procediment: ' + suggestion.procediment + 
	        		', llengua: ' + suggestion.llengua +
	        		', tramitId: ' + suggestion.tramitId +
	        		', tramitVersio: ' + suggestion.tramitVersio +
	        		', tramitParametres: ' + suggestion.tramitParametres +
	        		', idTraTel: ' + suggestion.idTraTel);
				$('#tramitId').val(suggestion.tramitId);
				$('#tramitVersio').val(suggestion.tramitVersio);
				$('#tramitParametres').val(suggestion.tramitParametres);
				$('#idTraTel').val(suggestion.idTraTel);
			}
	    }
	});
</script>

<script type="text/javascript">
	const MODAL_PUJAR_DOCUMENT_ID = 'pujar-document-digitalib-modal';
	const IFRAME_DIGITALIB_ID = 'modal-iframe-digitalib';
	const URL_NO_CARREGAT_IFRAME_DIGITALIB_ID = 'modal-urlnotloaded-iframe-digitalib';
	const NO_CARREGAT_IFRAME_DIGITALIB_ID = 'modal-body-nocarregat';
	const CARREGANT_IFRAME_DIGITALIB_ID = 'modal-body-carregant';
	var FITXER_ENCRYPTED_ID = [];
	var POLLING_CHECK_SCAN_WEB_FINAL_RUNNING = false;

	function showIframePdf(url) {
		const iframe = document.getElementById('iframe-pdf');
		const embed = document.getElementById('embed-pdf');
		iframe.style.display = 'block';
		iframe.src = url;

		embed.style.display = 'none';
	}

	function showEmbedPdf(url) {
		const iframe = document.getElementById('iframe-pdf');
		const embed = document.getElementById('embed-pdf');
		embed.style.display = 'block';
		embed.src = url;

		iframe.style.display = 'none';
		iframe.src = '';
	}

	function inserirMsg(tipusMsg, msg) {
		const alertHtml = '<div class="alert alert-' + tipusMsg + '" role="alert">'
			+ '<button type="button" class="close" data-dismiss="alert">&times;</button>'
			+ msg
			+ '</div>';
		$('#contingut').prepend(alertHtml);
	}

	function createModalPujarDocument(modalId) {
			$('body').append('<div id="' + modalId + '" class="modal hide fade show" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
                        + '<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg" role="document">'
							
                        + '<div class="modal-content">'

                        + '<div class="modal-header">' + '<h4 id="myModalLabel"><i class="fa fa-upload"></i>&nbsp;'
                        + "<fmt:message key="usuari.tramit.documentacio.pujardocument" />"
                        + '</h4>'
                        + '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
                        + '</div>'

                        + '<div class="modal-body" id="' + CARREGANT_IFRAME_DIGITALIB_ID + '">'
                        + '<p id="modal-message-carregant">'
						+ "<fmt:message key="usuari.tramit.documentacio.pujardocument.carregant" />"
                        + '</p>'
                        + '<p id="modal-spinner-carregant" style="text-align: center;">'
						+ '<svg style="width:20%;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 150"><path fill="none" stroke="#007BFF" stroke-width="15" stroke-linecap="round" stroke-dasharray="300 385" stroke-dashoffset="0" d="M275 75c0 31-27 50-50 50-58 0-92-100-150-100-28 0-50 22-50 50s23 50 50 50c58 0 92-100 150-100 24 0 50 19 50 50Z"><animate attributeName="stroke-dashoffset" calcMode="spline" dur="2" values="685;-685" keySplines="0 0 1 1" repeatCount="indefinite"></animate></path></svg>'
                        + '</p>'
                        + '</div>'

                        + '<div class="modal-body" id="' + NO_CARREGAT_IFRAME_DIGITALIB_ID + '">'
							+ '<p id="modal-msgnotloaded-iframe-digitalib">' 
								+ "<fmt:message key="usuari.tramit.documentacio.pujardocument.iframenocarrega"/>" 
								+ '&nbsp;'
								+ '<a id=' + URL_NO_CARREGAT_IFRAME_DIGITALIB_ID + ' target="_blank" href="#">AQUÍ</a>'
							+ '</p>'
                        + '</div>'

						+ '</div>' + '</div>' + '</div>');

			$('#' + modalId).on('hidden.bs.modal', function () {
				POLLING_CHECK_SCAN_WEB_FINAL_RUNNING = false;
			})
    }

	async function tancarExpedient(identificadorExpedient) {
		const url = "<%=request.getContextPath() + "/usuari/tancarexpedient/"%>";
		const dataObj = {
			identificadorExpedient: identificadorExpedient
		};

		console.log("cridant a tancarexpedient, URL: " + url);
		console.log("cridant a tancarexpedient, Data object: ", dataObj);

		const makeRequest = async () => { 
			$.ajax({
				url: url,
				method: 'GET',
				data: dataObj,
				success: function(data, status, xhr) {
					const successText = "Resposta correcta de tancarExpedient";
					console.log(successText);
					inserirMsg('success', successText);
				},
				error: function(xhr, status, error) {
					let errorText = 'Error tancant expedient: ' + (xhr.responseText || error);
					inserirMsg('danger', errorText);
				}
			});
		};

		makeRequest();
	}

	async function downloadFitxer(fitxerId, fileReturnedCallback){
		console.log("Descarregar fitxer amb ID: " + fitxerId);

		if (!fitxerId || fitxerId.indexOf(' ') >= 0) {
			let errorText = "No s'ha pogut descarregar el fitxer: " + fitxerId;//TODO:afegir missatge a traduccions
			console.error(errorText);
			inserirMsg('danger', errorText);
			return;
		}

		const url = "<%=request.getContextPath()%>" + fitxerId;
		console.log("Descarregant fitxer, URL: " + url);
		const request = new Request(url, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/octet-stream'
			},
			responseType: 'blob'
		});
		fetch(request)
			.then(response => {
				$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
				if (!response.ok) {
					throw new Error('Error al descarregar el fitxer: ' + response.statusText);
				}
				return response.blob();
			})
			.then(blob => {
				if(fileReturnedCallback){
					const file = downloadPdf(blob, true);
					fileReturnedCallback(file);
				}
				else{
					downloadPdf(blob);
				}
			})
			.catch(error => {
				let errorText = 'Error descarregant el fitxer: ' + error.message;//TODO:afegir missatge a traduccions
				inserirMsg('danger', errorText);
				$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
			});
	}

	async function insertFitxerInIframe(fitxerId) {
		console.log("Descarregar fitxer amb ID: " + fitxerId);

		if (!fitxerId || fitxerId.indexOf(' ') >= 0) {
			let errorText = "No s'ha pogut descarregar el fitxer: " + fitxerId;//TODO:afegir missatge a traduccions
			console.error(errorText);
			inserirMsg('danger', errorText);
			return;
		}

		const url = "<%=request.getContextPath()%>" + fitxerId;
		console.log("Descarregant fitxer, URL: " + url);
		const request = new Request(url, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/octet-stream'
			},
			responseType: 'blob'
		});
		fetch(request)
			.then(response => {
				if (!response.ok) {
					throw new Error('Error al descarregar el fitxer: ' + response.statusText);
				}
				return response.blob();
			})
			.then(blob => {
				const iframe = document.createElement('iframe');
				// const html = '<body>Foo</body>';
				const html = blob;
				iframe.src = 'data:text/html;charset=utf-8,' + encodeURI(html);
				document.body.appendChild(iframe);
				console.log('iframe.contentWindow =', iframe.contentWindow);
			})
			.catch(error => {
				let errorText = 'Error descarregant el fitxer: ' + error.message;//TODO:afegir missatge a traduccions
				inserirMsg('danger', errorText);
			});

	}

	async function documentImprimible(identificadorDocument) {
		const url = "<%=request.getContextPath() + "/usuari/documentimprimible/"%>";
		const dataObj = {
			identificadorDocument: identificadorDocument
		};

		console.log("cridant a documentimprimible, URL: " + url);
		console.log("cridant a documentimprimible, Data object: ", dataObj);

		const makeRequest = async () => { 
			$.ajax({
				url: url,
				method: 'GET',
				data: dataObj,
				success: function(data, status, xhr) {
					const successText = "Consulta a Arxiu correcta, procedim a visualitzar el fitxer";
					console.log(successText);
					inserirMsg('success', successText);
					document.getElementById('input-pdf').value = data;
					// $("#input-pdf").trigger("input");
					comprovaInputPdf();
					downloadFitxer(data, showIframePdf);
				},
				error: function(xhr, status, error) {
					let errorText = 'Error consultant el document imprimible: ' + (xhr.responseText || error);
					inserirMsg('danger', errorText);
					$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
				}
			});
		};

		makeRequest();
	}

	async function guardarFitxerAarxiu(encryptedIdFitxer, perfilfirma, tipusFirma) {
		const url = "<%=request.getContextPath() + "/usuari/guardararxiu/"%>";

		let $form = $('.msf');
		const ciutadaNif = $form.find('#pas1_identificacion').val() || '';
		let interessats = [ciutadaNif];
		interessats = interessats.join('--');//convertim a string. No puc passar un array com a queryparam

		const dataObj = {
			encryptedIdFitxer: encryptedIdFitxer,
			interessats: interessats,
			perfilfirma: perfilfirma,
			tipusFirma: tipusFirma
		};

		console.log("cridant a guardararxiu, URL: " + url);
		console.log("cridant a guardararxiu, Data object: ", dataObj);

		$('#modal-spinner-carregant').show();
		$('#' + NO_CARREGAT_IFRAME_DIGITALIB_ID).hide();

		const makeRequest = async () => { 
			$.ajax({
				url: url,
				method: 'GET',
				data: dataObj,
				success: function(data, status, xhr) {
					console.log("Resposta correcta del guardat a arxiu: ", data);

					$('#modal-spinner-carregant').hide();

					if (data && typeof data === "object") {
						if (data.hasOwnProperty("error")) {
							// Hi ha errors, mostra'ls per pantalla
							let error = data["error"];
							let errorText = "S'ha produït un error:<br>" + error + "<br>";
							// Mostra l'error dins el modal o com vulguis
							inserirMsg('danger', errorText);
							$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
						} else {
							let documentImmprimibleTancaraElModal = false;
							for (const expedientId in data) {
								if (data.hasOwnProperty(expedientId)) {
									const documentId = data[expedientId];
									if(documentId){
										inserirMsg('success', "Fitxer guardat correctament a Arxiu amb ID d'expedient: " + expedientId + 
											" i ID de document: " + documentId);//TODO: ficar codi de traduccions
										documentImmprimibleTancaraElModal = true;
										documentImprimible(documentId);
									}
									else {
										let errorText = "No s'ha pogut guardar el fitxer amb ID d'expedient " + expedientId + " dins Arxiu.";//TODO: ficar codi de traduccions
										inserirMsg('danger', errorText);
									}
								}
							}
							if(!documentImmprimibleTancaraElModal){
								$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
							}
						}
					} else {
						let errorText = "Resposta invàlida del servidor.";
						inserirMsg('danger', errorText);
						$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
					}	
					
					// Procés acabat
					// document.querySelector('#btn-descarregar-firmat-id').setAttribute('disabled', true);
					// FITXER_ENCRYPTED_ID = [];
				},
				error: function(xhr, status, error) {
					let errorText = 'Error pujant a arxiu el document: ' + (xhr.responseText || error);
					inserirMsg('danger', errorText);
					$('#modal-spinner-carregant').hide();
					$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
				}
			});
		};

		makeRequest();
	}

	async function pollCheckResultScanweb(transactionID, pollingInterval, maxPollingDuration) {
		const url = "<%=request.getContextPath() + "/usuari/checkfinalscanweb/"%>";
		const dataObj = {
			transactionID: transactionID
		};

		console.log("cridant a scanweb, URL: " + url);
		console.log("cridant a scanweb, Data object: ", dataObj);

		const startTime = Date.now(); // Record the start time

		POLLING_CHECK_SCAN_WEB_FINAL_RUNNING = true;
		const makeRequest = async () => { 
			$.ajax({
				url: url,
				method: 'GET',
				data: dataObj,
				// xhrFields: {
				// 	responseType: 'blob'
				// },
				//retornam una url on hi ha el fitxer pujat
				// success: function(data, status, xhr) {
				// 	if(data === null || data === undefined || data === '' || data === 'null' || data === 'undefined' || data.length === 0) {
				// 		const errorText = "No s'ha pogut pujar el document o no s'ha retornat cap URL.";
				// 		console.error(errorText);
				// 		console.error("Resposta --> " + data);
				// 		// Aquí pots mostrar errorText per pantalla
				//         inserirMsg('danger', errorText);
				// 		return;
				// 	}
				// 	// Aquí pots gestionar la resposta del servidor
				// 	// per exemple, mostrar un missatge de confirmació
				// 	console.log("Resposta correcta al procés de pujada del document: ", data);

				// 	// Si vols redirigir a una altra pàgina o fer alguna acció
				// 	// window.location.href = data;
				// 	for (let i = 0; i < data.length; i++) {
				// 		const url = data[i];
				// 		if (!url || !/^https?:\/\/|^\/|^\.\/|^\.\.\/|^[a-zA-Z]:[\\/]{1,2}.*/.test(url)) {
				// 			console.error("La resposta no sembla ser una URL o path vàlid: " + url);
				// 			const errorText = "No s'ha pogut pujar el document o no s'ha retornat cap URL.";
				// 			console.error(errorText);
				// 			console.error("Resposta --> " + url);
				// 			// Aquí pots mostrar errorText per pantalla
				// 			inserirMsg('danger', errorText);
				// 			continue;
				// 		}
				// 		console.log("Document pujat correctament");
				// 		console.log("URL del document pujat: " + url);
				// 		window.open(url);
				// 		// window.open(url, '_blank');
				// 	}
				// },
				success: function(data, status, xhr) {
					console.log("Resposta correcta a la comprovació del procés de pujada del document: ", data);

					// Procés en curs:
					if(!data || data === 'null' || data === 'undefined' || data.size === 0) {
						const elapsedTime = Date.now() - startTime;

						if (elapsedTime < maxPollingDuration && POLLING_CHECK_SCAN_WEB_FINAL_RUNNING) {
							setTimeout(makeRequest, pollingInterval); // Schedule next request
						} else {
							console.log('Maximum polling duration reached. Stopping polling.');
							$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
							let errorText = 'Exhaurit el temps màxim per pujar el document. Si us plau, obri la finestra modal de nou i torna-ho a intentar.';
							inserirMsg('danger', errorText);
							POLLING_CHECK_SCAN_WEB_FINAL_RUNNING = false;
						}
						return;
					}

					// Procés acabat
					console.log("Procés de pujada acabat!");
					document.querySelector('#btn-descarregar-firmat-id').setAttribute('disabled', true);
					FITXER_ENCRYPTED_ID = [];
					POLLING_CHECK_SCAN_WEB_FINAL_RUNNING = false;

					// Errors:
					if(data.length === 0) {
						const errorText = "No s'ha pogut pujar el document o no s'ha retornat cap fitxer ID i tampoc errors.";//TODO:afegir missatge a traduccions
						console.error(errorText);
						console.error("Resposta --> " + data);
						inserirMsg('danger', errorText);
						$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
						return;
					}

					//OK o Errors
					for (let i = 0; i < data.length; i++) {
						const errorResponseOrFileId = data[i];
						if(!errorResponseOrFileId || errorResponseOrFileId.error){
							const errorText = "No s'ha pogut pujar el document: " + errorResponseOrFileId.error;//TODO:afegir missatge a traduccions
							console.error(errorText);
							inserirMsg('danger', errorText);
							$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
							return;
						}

						document.querySelector('#btn-descarregar-firmat-id').removeAttribute('disabled');
						FITXER_ENCRYPTED_ID.push(errorResponseOrFileId.urlFitxer);
						console.log("Document pujat correctament");
						inserirMsg('success', "El document firmat s'ha guardat correctament a la base de dades. Pujant a Arxiu...");//TODO:afegir missatge a traduccions
						const encryptedIdFitxer = errorResponseOrFileId.urlFitxer.substring(errorResponseOrFileId.urlFitxer.lastIndexOf('/') + 1);
						guardarFitxerAarxiu(encryptedIdFitxer, errorResponseOrFileId.perfilFirma, errorResponseOrFileId.tipusFirma);
						return;
					}
				},
				error: function(xhr, status, error) {
					$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
					let errorText = 'Error descarregant el document: ' + (xhr.responseText || error);//TODO:afegir missatge a traduccions
					inserirMsg('danger', errorText);
				}
			});
		};

		makeRequest(); // Start the first request
	}

	function afegeixIframeDigitalib(redirectUrl){
		$('#' + CARREGANT_IFRAME_DIGITALIB_ID).append('<iframe id="' + IFRAME_DIGITALIB_ID + '" src="'+ redirectUrl +'" style="width:100%; height:450px; border:none;"></iframe>');

		const iframe = document.getElementById(IFRAME_DIGITALIB_ID);
		// Detectar error de càrrega
		iframe.addEventListener('error', function () {
			console.error('Error: L\'iframe no s\'ha pogut carregar correctament.');
			//destruit iframe
			$('#' + IFRAME_DIGITALIB_ID).remove();
		});

		// confirmar que s’ha carregat bé
		iframe.addEventListener('load', function () {
			try {
				// Prova d’accedir al contingut per comprovar si la càrrega ha estat correcta
				const doc = iframe.contentDocument || iframe.contentWindow.document;
				console.log('L\'iframe ' + IFRAME_DIGITALIB_ID + ' s\'ha carregat correctament.');
				$('#' + NO_CARREGAT_IFRAME_DIGITALIB_ID).hide();
			} catch (e) {
				// Si no es pot accedir al contingut, probablement per política CORS
				console.warn('L\'iframe ha carregat però no es pot accedir al contingut (CORS?).');
				//destruit iframe
				$('#' + IFRAME_DIGITALIB_ID).remove();
			}
		});
	}

	function onScanwebIniciat(redirectUrl, transactionID) {
		console.log("scanweb iniciat");	

		document.getElementById(URL_NO_CARREGAT_IFRAME_DIGITALIB_ID)?.setAttribute("href", redirectUrl);
		$('#' + NO_CARREGAT_IFRAME_DIGITALIB_ID).show();
		afegeixIframeDigitalib(redirectUrl);

		const pollingInterval = 5000; // 5 seconds in milliseconds
		const maxPollingDuration = 900000; // 900 seconds (15 minutes) in milliseconds

		pollCheckResultScanweb(transactionID, pollingInterval, maxPollingDuration);
	}

	function onClickDescarregarFirmat(button) {
		console.log("Descarregar fitxer firmat");
		if(FITXER_ENCRYPTED_ID.length === 0) {
			let errorText = "No hi ha cap fitxer per descarregar.";
			inserirMsg('danger', errorText);
			return;
		}

		for (let i = 0; i < FITXER_ENCRYPTED_ID.length; i++) {
			const fitxerId = FITXER_ENCRYPTED_ID[i];
			downloadFitxer(fitxerId);
		}
	}

	function iniciarTramitShowError(errorText) {
		inserirMsg('danger', errorText);
		$('#spinner-carregant-iniciartramit').hide();
		$('#div-iniciar-tramit').hide();
		$('#div-iniciar-tramit-error').show();
	}

	async function iniciarTramit() {
		console.log("Iniciar tramit");	

		$('#spinner-carregant-iniciartramit').show();
		$('#div-iniciar-tramit').hide();
		$('#div-iniciar-tramit-error').hide();

		const url = "<%=request.getContextPath() + "/usuari/ticketAccesFh/"%>";
		const dataObj = getFormData();

		console.log("ticketAccesFh, URL: " + url);
		console.log("ticketAccesFh, Data object: ", dataObj);
		$.ajax({
			url: url,
			method: 'GET',
			data: dataObj,
        success: function(data, status, xhr) {
            console.log("Resposta ticketAccesFh:", data);
			if(data !== null && data !== undefined && data.startsWith('ERROR:')) {
				let errorText = data; // si hi ha un error, el retorn és un text que comença per 'ERROR:'
				iniciarTramitShowError(errorText);
				return;
			}
			$('#spinner-carregant-iniciartramit').hide();
			$('#div-iniciar-tramit').show();
			$('#div-iniciar-tramit-error').hide();

			$('#link-iniciar-tramit').attr('href', data);
			window.open(data, '_blank').focus();
        },
        error: function(xhr, status, error) {
			let errorText = 'Error obtenint el ticket d´accés al tràmit: ' + (xhr.responseText || error);
			iniciarTramitShowError(errorText);
        }});
	}

	function onClickPujarDocument(button) {
		console.log("Pujar document");	

		$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('show');

		$('#' + NO_CARREGAT_IFRAME_DIGITALIB_ID).hide();
		$('#' + IFRAME_DIGITALIB_ID)?.remove();

		const url = "<%=request.getContextPath() + "/usuari/preparescanweb/"%>";
		const dataObj = getFormData();

		console.log("preparescanweb, URL: " + url);
		console.log("preparescanweb, Data object: ", dataObj);
		$.ajax({
			url: url,
			method: 'GET',
			data: dataObj,
        success: function(data, status, xhr) {
            console.log("Resposta preparescanweb:", data);
			$('#modal-spinner-carregant').hide();
			$('#modal-message-carregant').hide();

            if (data && typeof data === "object") {
                if (data.hasOwnProperty("error")) {
                    // Hi ha errors, mostra'ls per pantalla
                    let error = data["error"];
                    let errorText = "S'ha produït un error:<br>" + error + "<br>";
                    // Mostra l'error dins el modal o com vulguis
                    inserirMsg('danger', errorText);
                } else {
					for (const transactionID in data) {
						if (data.hasOwnProperty(transactionID)) {
							const redirectUrl = data[transactionID];
							if (transactionID && redirectUrl) {
								onScanwebIniciat(redirectUrl, transactionID);
								return;
							} else {
								let errorText = "Falten dades per continuar el procés de pujada del document.";
								inserirMsg('danger', errorText);
							}
						}
					}
				}
			} else {
				let errorText = "Resposta invàlida del servidor.";
				inserirMsg('danger', errorText);
			}	

			$('#' + MODAL_PUJAR_DOCUMENT_ID).modal('hide');
        },
        error: function(xhr, status, error) {
            let errorText = 'Error descarregant el document: ' + (xhr.responseText || error);
			inserirMsg('danger', errorText);
			$('#modal-spinner-carregant').hide();
			$('#modal-message-carregant').hide();
        }});
	}

	function getFormData() {
		let $form = $('.msf');
		let data = {
			languageUI: $('html').attr('lang') || '', // suposant que el languageUI ve del lang de l'html
			interessats: [],
			ciutadaTipusIdentificacio: $form.find('#pas1_tipusIdentificacio').val() || '',
			ciutadaNif: $form.find('#pas1_identificacion').val() || '',
			ciutadaNom: $form.find('#pas1_nom').val() || '',
			ciutadaLlinatges: $form.find('#pas1_llinatges').val() || '',
			representant: $form.find('#representant').is(':checked') ? 'true' : 'false',
			representantNom: $form.find('#pas1_representant_nom').val() || '',
			representantLlinatges: $form.find('#pas1_representant_llinatges').val() || '',
			representantTipusIdentificacio: $form.find('#pas1_representant_tipusIdentificacio').val() || '',
			representantIdentificacio: $form.find('#pas1_representant_identificacion').val() || '',
			procediment: $form.find('#procediment-id').val() || '',
			tramitCodi: $form.find('#tramitId').val() || '',
			tramitVersio: $form.find('#tramitVersio').val() || '',
			tramitParametres: $form.find('#tramitParametres').val() || '',
			idTraTel: $form.find('#idTraTel').val() || '',
		};

		data.interessats.push(data.ciutadaNif);
		data.interessats = data.interessats.join('--');//convertim a string. No puc passar un array com a queryparam
		// interessats i organs: si hi hagués inputs múltiples, per exemple amb class .interessat o .orga
		// $form.find('.interessat').each(function() {
		// 	var v = $(this).val();
		// 	if (v) data.interessats.push(v);
		// });

		return data;
	}

	$(document).ready(function() {
		createModalPujarDocument(MODAL_PUJAR_DOCUMENT_ID);
		console.log("Modal created with ID: " + MODAL_PUJAR_DOCUMENT_ID);
		document.getElementById('input-pdf').addEventListener('input', comprovaInputPdf);
	});
</script>