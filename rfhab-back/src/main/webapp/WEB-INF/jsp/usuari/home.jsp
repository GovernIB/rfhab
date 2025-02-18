<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

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
						<span><em>1</em> Identificació del interessat</span>
					</div>
					<div class="msf-step col-md-3">
						<span><em>2</em> Sel·lecció de tràmit</span>
					</div>
					<div class="msf-step col-md-3">
						<span><em>3</em> Documentació</span>
					</div>
					<div class="msf-step col-md-3">
						<span><em>4</em> Iniciar tràmit</span>
					</div>
				</div>
			</div>

			<div class="msf-content">
				<div class="msf-view">

					<div class="row">
						<div class="col-md-12">

							<h3>1 Dades del interessat</h3>

							<div class="form-group">
								<label>Nom</label> <input id="pas1_nom" name="nom" type="text"
									class="form-control" placeholder="Nom" data-bind="value: Nom"
									data-val="true" data-val-required="el nom és obligatori">
							</div>

							<div class="form-group">
								<label>Llinatges</label> <input id="pas1_llinatges"
									name="llinatges" type="text" class="form-control"
									placeholder="Llinatges" data-bind="value: Llinatges"
									data-val="true"
									data-val-required="els llinatges són obligatoris">
							</div>

							<div class="form-group">
								<label>Tipus Identificació</label> <select
									id="pas1_tipusIdentificacio" name="tipusIdentificacio"
									class="form-control"
									data-bind="options: availableTypes, selectedOptions: chosenType, optionsCaption: 'Tria tipus identificació'"
									data-val="true"
									data-val-required="el tipus d'identificació és obligatori">
									<option value="DNI">DNI</option>
									<option value="NIE">NIE</option>
									<option value="Passaport">Passaport</option>
									<option value="Altres">Altres</option>
								</select>
							</div>

							<div class="form-group">
								<label>Número d'identificació</label> <input
									id="pas1_identificacion" name="identificacio" type="text"
									class="form-control" placeholder="Identificació"
									data-bind="value: Identificacio" data-val="true"
									data-val-required="la identificació és obligatòria">
							</div>

							<div class="form-group">
								<input type="checkbox" name="representant" value="representant"
									id="representant"> Representant

							</div>

							<div class="formRepresentant" style="display: none;">

								<h3>Dades del representant</h3>

								<div class="form-group">
									<label>Nom del representant</label> <input
										id="pas1_representant_nom" name="representant_nom" type="text"
										class="form-control" placeholder="Nom" data-bind="value: Nom"
										data-val="true" data-val-required="el nom és obligatori"
										data-rule-required="#representant:checked">
								</div>

								<div class="form-group">
									<label>Llinatges del representant</label> <input
										id="pas1_representant_llinatges" name="representant_llinatges"
										type="text" class="form-control" placeholder="Llinatges"
										data-bind="value: Llinatges" data-val="true"
										data-val-required="els llinatges són obligatoris"
										data-rule-required="#representant:checked">
								</div>

								<div class="form-group">
									<label>Tipus d'identificació del representant</label> <select
										id="pas1_representant_tipusIdentificacio"
										name="representant_tipusIdentificacio" class="form-control"
										data-bind="options: availableTypes, selectedOptions: chosenType, optionsCaption: 'Tria tipus identificació'"
										data-val="true"
										data-val-required="el tipus d'identificació és obligatori"
										data-rule-required="#representant:checked">
										<option value="DNI">DNI</option>
										<option value="NIE">NIE</option>
										<option value="Passaport">Passaport</option>
										<option value="Altres">Altres</option>
									</select>
								</div>

								<div class="form-group">
									<label>Número d'identificació del representant</label> <input
										id="pas1_representant_identificacion"
										name="representant_identificacio" type="text"
										class="form-control" placeholder="Identificació"
										data-bind="value: Identificacio" data-val="true"
										data-val-required="la identificació és obligatòria"
										data-rule-required="#representant:checked">
								</div>

							</div>
						</div>
					</div>


				</div>

				<div class="msf-view">
					<div class="row">
						<div class="col-md-12">

							<h3>2 Sel·lecció del tràmit</h3>

							<div class="form-group">
								<label>Codi o nom del procediment</label> <input
									id="pas2_procediment" name="procediment" type="text"
									class="form-control"
									placeholder="Codi del procediment o nom del procediment"
									data-bind="value: Procediment" data-val="true"
									data-val-required="el procediment és obligatori"> <input
									type="hidden" id="procedimentId" name="procedimentId" value="1" />
							</div>

							<div class="form-group">
								<label>Codi o nom del tràmit</label> <input id="pas2_tramit"
									name="tramit" type="text" class="form-control"
									placeholder="Codi del tràmit o nom del tràmit"
									data-bind="value: Procediment" data-val="true"
									data-val-required="el procediment és obligatori"> <input
									type="hidden" id="tramitId" name="tramitId" value="1" /> <input
									type="hidden" id="tramitVersio" name="tramitVersio" value="1" />
							</div>

						</div>
					</div>
				</div>

				<div class="msf-view">
					<div class="row">
						<div class="col-md-12">

							<h3>3 Generació de la documentació</h3>

							<div class="form-group pdfVisor">
								<embed src="/rfhabback/dummy.pdf" width="100%" height="600"
									type="application/pdf">
							</div>

							<div class="row buttonsDiv">
								<div class="col-md-6">
									<button type="button" class="btn btn-primary">
										<i class="fa fa-file-pdf"></i> Descarregar
									</button>
								</div>
								<div class="col-md-6">
									<button type="button" class="btn btn-secondary">
										<i class="fa fa-upload"></i> Pujar document
									</button>
								</div>
							</div>

						</div>
					</div>
				</div>

				<div class="msf-view">
					<div class="row">
						<div class="col-md-12" style="min-height: 325px;">

							<h3>Iniciar el tràmit</h3>

							<p>A continuació es redireccionará automàticament a la pàgina
								de tràmits.</p>
							<p>
								Si no es redirecciona automàticament, faci clic <a
									href="https://www.google.com">aquí</a>.
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
								<i class="fa fa-chevron-left"></i> Tornar
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
								class="btn btn-primary msf-nav-button">Finalitzar</button>
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

<script type="text/javascript">
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

									$
											.data(view,
													msfJqueryData.validated,
													false);
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
				;
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
	
	var procediments =[
		<c:forEach items="${llistaProcediments}" var="procediment">
            { value: '${procediment.key} ${procediment.value}', data: '${procediment.value}' },
        </c:forEach>
	];
	
	var countries = [
	    { value: 'Andorra', data: 'AD' },
	    { value: 'España', data: 'AD' },
	    { value: 'Portugal', data: 'AD' },
	    { value: 'Francia', data: 'AD' },
	    { value: 'Alemania', data: 'AD' },
	    { value: 'Suiza', data: 'AD' },
	    { value: 'China', data: 'AD' },
	    { value: 'Zimbabwe', data: 'ZZ' }
	];

	$('#pas2_procediment').autocomplete({
	    lookup: procediments,
	    minChars: 1,
	    showNoSuggestionNotice: true,
        noSuggestionNotice: 'Sorry, no matching results',
	    onSelect: function (suggestion) {
	        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
	    }
	});
	
	$('#pas2_tramit').autocomplete({
	    lookup: countries,
	    minChars: 1,
	    showNoSuggestionNotice: true,
        noSuggestionNotice: 'Sorry, no matching results',
	    onSelect: function (suggestion) {
	        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
	    }
	});
</script>