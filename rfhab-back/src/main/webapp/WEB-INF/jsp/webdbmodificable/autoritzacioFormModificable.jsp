<script type="text/javascript">
	
	function cargarSelect() {
		$.ajax({
			url : '/rfhabback/admin/autoritzacio/getProcediments', 
			type : 'GET', 
			dataType : 'json',
			success : function(data) {
				let miSelect = $('<select>', {
					id : 'autoritzacio.procediment',
					name : 'autoritzacio.procediment',
					class : 'form-control col-md-9-optional'
				});

				$.each(data, function(key, value) {
					$('<option>', {
						value : key,
						text : value
					}).appendTo(miSelect);
				});

				
				let miTd = $('#autoritzacio_procediment_columnvalueid');
				if (miTd.length) {
					miTd.empty(); 
					miTd.append(miSelect); 
				} else {
					console.error('No se encontró el TD con ID "autoritzacio_procediment_columnvalueid"');
				}
			},
			error : function(xhr, status, error) {
				console.error('Ocurrió un error:', status, error);
			}
		});
	}

	$(document).ready(function() {
		cargarSelect();
	});
	
</script>