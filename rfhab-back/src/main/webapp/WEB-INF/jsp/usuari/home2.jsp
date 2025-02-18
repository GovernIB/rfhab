<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Asistente test</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	type="text/css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	type="text/css">


<style type="text/css">
.msf-view {
	display: none;
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
</style>


</head>
<body>
<body>

	<div id="wrapper">

		<div id="container body-content">

			<div class="progress">
				<div class="progress-bar progress-bar-success progress-bar-striped"
					role="progressbar" aria-valuenow="0" aria-valuemin="0"
					aria-valuemax="100" style="width: 0%">
					<span class="sr-only">0% Complete</span>
				</div>
			</div>
			<form class="form-horizontal msf">
				<div class="msf-header">
					<div class="row text-center">
						<div class="msf-step col-md-4">
							<i class="fa fa-clipboard"></i> <span>Enter Request
								Details</span>
						</div>
						<div class="msf-step col-md-4">
							<i class="fa fa-credit-card"></i><span>Further Details</span>
						</div>
						<div class="msf-step col-md-4">
							<i class="fa fa-check"></i> <span>Review and Submit</span>
						</div>
					</div>
				</div>

				<div class="msf-content">
					<div class="msf-view">

						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<div class="form-group">
									<input id="name" name="name" type="text" class="form-control"
										placeholder="Name" data-bind="value: Name" data-val="true"
										data-val-required="name is required">
									<!--data-val="true" data-val-required="name is required"-->
								</div>
								<div class="form-group">
									<input id="email" name="email" type="text" class="form-control"
										placeholder="Email" data-bind="value: Email" data-val="true"
										data-val-required="email is required">
									<!-- data-val="true" data-val-required="email is required -->
								</div>
								<div class="form-group">
									<textarea id="details" name="details" rows="10"
										class="form-control" placeholder="Enter Details"
										data-bind="value: Details"></textarea>
								</div>

							</div>
						</div>


					</div>
					<div class="msf-view">
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<div class="form-group">
									<select id="countries" name="countries" class="form-control"
										data-bind="options: availableCountries, selectedOptions: chosenCountries"
										data-val="true" data-val-required="select a country" size="5"
										multiple="true"></select>
								</div>

								<div class="form-group">

									<select id="type" name="type" class="form-control"
										data-bind="options: availableTypes, selectedOptions: chosenType, optionsCaption: 'Choose Request Type'"
										data-val="true" data-val-required="Request type is required.">
									</select>

									<!-- data-val="true" data-val-required="email is required -->
								</div>
								<!--   <div class="form-group">
                  <textarea id="additionaldetails" name="additionaldetails" rows="10" class="form-control" placeholder="Enter Additional Details" data-bind="value: AdditionalDetails"></textarea>
                </div>
                -->

							</div>
						</div>
					</div>
					<div class="msf-view">
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<label>Name</label> : <span data-bind="text: Name"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<label>Email</label> : <span data-bind="text: Email"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<label>Type</label> : <span data-bind="text: chosenType"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<label>Countries</label> : <span
									data-bind="text: chosenCountries"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<label>Details</label> : <span data-bind="text: Details"></span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<div class="form-group">
									<input id="additional" name="additional" type="text"
										class="form-control" placeholder="Additional Details"
										data-val="true" data-val-required="name is required">
								</div>
							</div>
						</div>



					</div>
				</div>



				<div class="msf-navigation">
					<div class="row">
						<div class="col-md-3">

							<button type="button" data-type="back"
								class="btn btn-default msf-nav-button">
								<i class="fa fa-chevron-left"></i> Back
							</button>

						</div>

						<div class="col-md-3 col-md-offset-6">
							<button type="button" data-type="next"
								class="btn btn-default msf-nav-button">
								Next <i class="fa fa-chevron-right"></i>
							</button>

							<button type="submit" data-type="submit"
								class="btn btn-primary msf-nav-button">Submit</button>
						</div>

					</div>
				</div>
			</form>

		</div>
	</div>

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.4.2/knockout-min.js"></script>

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
	        define(['jquery', 'jquery-validation'], factory);
	    }
	    else if (typeof exports === 'object') {
	        factory(require('jquery'), require('jquery-validation'));
	    }
	    else {
	        // Neither AMD nor CommonJS used. Use global variables.
	        if (typeof jQuery === 'undefined') {
	            throw 'multi-step-form-js requires jQuery to be loaded first';
	        }
	        if (typeof jQuery.validator === 'undefined') {
	            throw 'multi-step-form-js requires requires jquery.validation.js to be loaded first';
	        }
	        factory(jQuery);
	    }
	}(function($) {
	    'use strict';

	    const msfCssClasses = {
	        header: "msf-header",
	        step: "msf-step",
	        statuses: {
	            stepComplete: "msf-step-complete",
	            stepIncomplete: "msf-step-incomplete",
	            stepActive: "msf-step-active"
	        },
	        content: "msf-content",
	        view: "msf-view",
	        navigation: "msf-navigation",
	        navButton: "msf-nav-button"
	    };

	    const msfNavTypes = {
	        back: "back",
	        next: "next",
	        submit: "submit"

	    };

	    const msfJqueryData = {
	        validated: "msf-validated",
	        visited: "msf-visited"
	    };

	    const msfEventTypes = {
	        viewChanged: "msf:viewChanged"
	    };

	    $.fn.multiStepForm = function(options) {
	        var form = this;

	        var defaults = {
	            activeIndex: 0,
	            validate: {},
	            hideBackButton: false,
	            allowUnvalidatedStep: false,
	            allowClickNavigation: false
	        };

	        var settings = $.extend({}, defaults, options);

	        //find the msf-content object
	        form.content = this.find("." + msfCssClasses.content).first();

	        if (form.content.length === 0) {
	            throw new Error('Multi-Step Form requires a child element of class \'' + msfCssClasses.content + '\'');
	        }

	        //find the msf-views within the content object
	        form.views = $(this.content).find("." + msfCssClasses.view);

	        if (form.views.length === 0) {
	            throw new Error('Multi-Step Form\'s element of class \'' + msfCssClasses.content + '\' requires n elements of class \'' + msfCssClasses.view + '\'');
	        }

	        form.header = this.find("." + msfCssClasses.header).first();
	        form.navigation = this.find("." + msfCssClasses.navigation).first();
	        form.steps = [];
	        //form.completedSteps = 0;

	        form.getActiveView = function() {
	            return form.views.filter(function() {
	                return this.style && this.style.display !== '' && this.style.display !== 'none'
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
	                currentIndex: index,
	                previousIndex: previousIndex,
	                totalSteps: form.steps.length,
	                completedSteps: completedSteps
	            });
	        }

	        form.setStatusCssClass = function(step, cssClass) {
	            $(step).removeClass(msfCssClasses.statuses.stepComplete);
	            $(step).removeClass(msfCssClasses.statuses.stepIncomplete);

	            $(step).addClass(cssClass);
	        }

	        form.tryNavigateToView = function(currentIndex, targetIndex) {
	            if (targetIndex <= currentIndex) {

	                form.validateView(form.views[currentIndex]);

	                if(!settings.hideBackButton)
	                    form.setActiveView(targetIndex);
	                return;
	            }

	            if (!form.validateViews(currentIndex, targetIndex - currentIndex, function(i) {
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
	                        "class": msfCssClasses.header,
	                        "display": "none"
	                    });

	                    $(form).prepend(form.header);
	                }

	                form.steps = $(form.header).find("." + msfCssClasses.step);

	                this.initStep = function(index, view) {

	                    //append steps to header if they do not exist
	                    if (form.steps.length < index + 1) {
	                        $(form.header).append($("<div/>", {
	                            "class": msfCssClasses.step,
	                            "display": "none"
	                        }));
	                    }

	                    if (settings.allowClickNavigation) {
	                        //bind the click event to the header step
	                        $(form.steps[index]).click(function(e) {
	                            var view = form.getActiveView()[0];
	                            var currentIndex = form.views.index(view);
	                            var targetIndex = form.steps.index($(e.target).closest("." + msfCssClasses.step)[0]);

	                            form.tryNavigateToView(currentIndex, targetIndex);
	                        });
	                    }
	                }

	                $.each(form.views, this.initStep);

	                form.steps = $(form.header).find("." + msfCssClasses.step);
	            };


	            this.initNavigation = function() {

	                if (form.navigation.length === 0) {
	                    form.navigation = $("<div/>", {
	                        "class": msfCssClasses.navigation
	                    });

	                    $(form.content).after(form.navigation);
	                }

	                this.initNavButton = function(type) {
	                    var element = this.navigation.find("button[data-type='" + type + "'], input[type='button']"),
	                        type;
	                    if (element.length === 0) {
	                        element = $("<button/>", {
	                            "class": msfCssClasses.navButton,
	                            "data-type": type,
	                            "html": type
	                        });
	                        element.appendTo(form.navigation);
	                    }
	                    return element;
	                };

	                form.backNavButton = this.initNavButton(msfNavTypes.back);
	                form.nextNavButton = this.initNavButton(msfNavTypes.next);
	                form.submitNavButton = this.initNavButton(msfNavTypes.submit);
	            };

	            this.initHeader();
	            this.initNavigation();

	            this.views.each(function(index, view) {

	                $.data(view, msfJqueryData.validated, false);
	                $.data(view, msfJqueryData.visited, false);

	                //if this is not the last view do not allow the enter key to submit the form as it is not completed yet                  
	                if (index !== form.views.length - 1) {
	                    $(view).find(':input').not('textarea').keypress(function(e) {
	                        if (e.which === 13) // Enter key = keycode 13
	                        {
	                            form.nextNavButton.click();
	                            return false;
	                        }
	                    });
	                }

	                $(view).on('show', function(e) {
	                    if (this !== e.target)
	                        return;

	                    var view = e.target;
	                    $.data(view, msfJqueryData.visited, true);

	                    var index = form.views.index(view);
	                    var step = form.steps[index];

	                    $(step).addClass(msfCssClasses.statuses.stepActive);
	                    //form.setStatusCssClass(step, msfCssClasses.statuses.stepActive);

	                    //choose which navigation buttons should be displayed based on index of view 
	                    if (index > 0 && !settings.hideBackButton) {
	                        form.backNavButton.show();
	                    }

	                    if (index == form.views.length - 1) {
	                        form.nextNavButton.hide();
	                        form.submitNavButton.show();
	                    }
	                    else {
	                        form.submitNavButton.hide();
	                        form.nextNavButton.show();
	                    }
	                });

	                $(view).on('hide', function(e) {
	                    if (this !== e.target)
	                        return;

	                    var index = form.views.index(e.target);
	                    var step = form.steps[index];

	                    $(step).removeClass(msfCssClasses.statuses.stepActive);

	                    if ($.data(e.target, msfJqueryData.validated) && $.data(e.target, msfJqueryData.visited)) {
	                        form.setStatusCssClass(step, msfCssClasses.statuses.stepComplete);
	                    }
	                    else if ($.data(e.target, msfJqueryData.visited)) {
	                        form.setStatusCssClass(step, msfCssClasses.statuses.stepIncomplete);
	                    }
	                    else {
	                        form.setStatusCssClass(step, "");
	                    }

	                    //hide all navigation buttons, display choices will be set on show event
	                    form.backNavButton.hide();
	                    form.nextNavButton.hide();
	                    form.submitNavButton.hide();
	                });

	                //initially hide each view
	                $(view).hide();
	            });


	            if (settings.activeIndex > 0) {
	                $(form).ready(function() {
	                    form.tryNavigateToView(0, settings.activeIndex);
	                });
	            }
	            else {
	                form.setActiveView(0);
	            }

	        };

	        form.validateView = function(view) {
	            var index = form.views.index(view);

	            if (form.validate().subset(view)) {
	                $.data(view, msfJqueryData.validated, true);
	                form.setStatusCssClass(form.steps[index], msfCssClasses.statuses.stepComplete);
	                return true;
	            }
	            else {
	                $.data(view, msfJqueryData.validated, false);
	                form.setStatusCssClass(form.steps[index], msfCssClasses.statuses.stepIncomplete);
	                return false;
	            }
	        };

	        form.validateViews = function(currentIndex, length, invalid) {
	            currentIndex = typeof currentIndex === 'undefined' ? 0 : currentIndex;
	            length = typeof length === 'undefined' ? form.views.length : length;


	            var validationIgnore = ""; // Saving the existing validator ignore settings to reset them after validating multi-step form
	            var isValid = true;

	            //remember original validation setings for ignores
	            if ($(form).data("validator")) {
	                var formValidatorSettings = $(form).data("validator").settings;
	                validationIgnore = formValidatorSettings.ignore;

	                var currentValidationIgnoreSettingsArray = validationIgnore.split(",");
	                if (currentValidationIgnoreSettingsArray.length >= 1) {
	                    // Remove the ":hidden" selector from validator ignore settings as we want our hidden fieldsets/steps to be validated before final submit
	                    var hiddenIndex = $.inArray(":hidden", currentValidationIgnoreSettingsArray);
	                    currentValidationIgnoreSettingsArray.splice(hiddenIndex, 1);
	                    $(form).data("validator").settings.ignore = currentValidationIgnoreSettingsArray.toString();
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
	            }
	            else if (settings.allowUnvalidatedStep) {
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
	            if (!self.element($(this))) ok = false;
	        });
	        return ok;
	    };

	    $.each(['show', 'hide'], function(i, ev) {
	        var el = $.fn[ev];
	        $.fn[ev] = function() {
	            this.trigger(ev);
	            return el.apply(this, arguments);
	        };
	    });
	}));
	</script>

	<script type="text/javascript">
		function ViewModel() {
			var self = this;

			self.Name = ko.observable('');
			self.Email = ko.observable('');
			self.Details = ko.observable('');

			self.AdditionalDetails = ko.observable('');
			self.availableTypes = ko
					.observableArray([ 'New', 'Open', 'Closed' ]);
			self.chosenType = ko.observable('');

			self.availableCountries = ko.observableArray([ 'France', 'Germany',
					'Spain', 'United States', 'Mexico' ]),
					self.chosenCountries = ko.observableArray([]) // Initially, only Germany is selected

		}

		var viewModel = new ViewModel();

		ko.applyBindings(viewModel);

		$(document)
				.on(
						"msf:viewChanged",
						function(event, data) {
							var progress = Math
									.round((data.completedSteps / data.totalSteps) * 100);

							$(".progress-bar").css("width", progress + "%")
									.attr('aria-valuenow', progress);
							;
						});

		$(".msf:first").multiStepForm({
			activeIndex : 0,
			validate : {},
			hideBackButton : false,
			allowUnvalidatedStep : false,
			allowClickNavigation : true
		});
	</script>

</body>

</html>