<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<div class="container">
	<div class="form-group">
		<label id="login-errorLabel" style="display: none;" class="alert alert-danger"></label>
	</div> 
	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">Fecha Desde</label> <input
			class="form-control" id="datepickerFechaDesde" name="fechaDesde"
			placeholder="MM/DD/YYY" type="text" />
	</div>
	
	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">Fecha Hasta</label> <input
			class="form-control" id="datepickerFechaHasta" name="fechaHasta"
			placeholder="MM/DD/YYY" type="text" />
	</div>
	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="checkbox">Ver solo turnos ocupados</label> 
		 <input type="checkbox" id="ocupado" name="ocupado" > <br>
	</div>
	<div class="form-group">
		<button id="buscarTurnoMedico" class="btn btn-success login-btn btn-block"
			onclick="buscarTurnoMedico()">Buscar Turno</button>
	</div>
</div>
<div class="form-group">
	<table class="table table-striped">
<!-- 		<caption>Selecionar Turno</caption> -->
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Estado</th>
				<th>Paciente</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="tbodyVerTurnos">
		</tbody>
	</table>

</div>
<%@ include file="turnoAnuladoPorMedicoModal.jsp"%>
<%@ include file="infoPacienteModal.jsp"%>
<script>

$(document).ready(function () {
    if (localStorage.getItem("permiso") === "medico") {
        $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
        $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
    }
   
    var getDate = function (input) {
        return new Date(input.date.valueOf());
    }

    $('#datepickerFechaDesde, #datepickerFechaHasta').datepicker({
        format: "dd/mm/yyyy",
        language: 'es'
    });

    $('#datepickerFechaDesde').datepicker({
        startDate: '-1d',
        endDate: '+35d',
    }).on('changeDate',
        function (selected) {
            $('#datepickerFechaHasta').datepicker('setStartDate', getDate(selected));
        });

    $('#datepickerFechaHasta').datepicker({
        startDate: '+6d',
        endDate: '+36d',
    }).on('changeDate',
        function (selected) {
            $('#datepickerFechaDesde').datepicker('setEndDate', getDate(selected));
        });
    
    
    
    
});

	function buscarTurnoMedico() {

		var fechaDesde = $('#datepickerFechaDesde').val();
		var fechaHasta = $('#datepickerFechaHasta').val();
		var ocupado = $('#ocupado').val();
		$("#tbodyVerTurnos").empty();
		debugger;
		if(fechaDesde != null && fechaDesde != ''){
			if(fechaHasta != null && fechaHasta != ''){
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/verProximosTurnos",
					dataType : "json",
					data : {
						fechaDesde : fechaDesde,
						fechaHasta : fechaHasta,
						ocupado : ocupado
					},
					cache : false,
					success : function(response) {
						var filas = response.length;
						if (response != null && filas > 0) {
					
							for (i = 0; i < filas; i++) { //cuenta la cantidad de registros
								var nuevafila = "<tr><td>" + response[i].fechaString
										+ "</td><td>" + response[i].estado
										+ "</td><td>" + response[i].paciente.nombreApellido
										+ "</td><td><a type='button' class='btn btn-info' onclick=masInfoPaciente('"+response[i].paciente.id+"')>Info Paciente</a>"
										+ "</td><td><a type='button' class='btn btn-warning' onclick=anularPorMedico('"+response[i].id+"') >Anular</a>"
										+ "</td></tr>"
		
								$("#tbodyVerTurnos").append(nuevafila);
							}
						}
					}
				});
			} else{
	    	  	 $('#login-errorLabel').text("Debe seleccionar una fecha de turno hasta.");
	             $('#login-errorLabel').show();
	             setTimeout(function () {
	                 $('#login-errorLabel').hide();
	             }, 5000); 
			}
		} else{
	   	  	 $('#login-errorLabel').text("Debe seleccionar una fecha de turno desde.");
	         $('#login-errorLabel').show();
	         setTimeout(function () {
	             $('#login-errorLabel').hide();
	         }, 5000); 
		}
	}
	
	function anularPorMedico(id){
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/anularPorMedico",
			dataType : "json",
			data : {
				id : id
			},
			success : function(response) {
				//TODO aca abro modal
				if(response){
					$('#mensajeVerTurnos').text("El turno fue anulado con �xito.")
					$('#turnoAnuladoPorMedico').modal('show');
				}else {
					$('#mensajeVerTurnos').text("No se pudo anular el turno, por favor vuelva a intentarlo.")
					$('#turnoAnuladoPorMedico').modal('show');
				}
			}
		});
		
	}
	
	function limiarModalMasInfo(){
		$('#nombreLabel').text("");
		$('#fechaNacimientoLabel').text("");
		$('#tipoDocumentoFechaNacimientoLabel').text("");
		$('#numeroDocumentoLabel').text("");
		$('#sexoLabel').text("");
		$('#emailLabel').text("");
		$('#telefonoLabel').text("");	
	}
	
	function masInfoPaciente(id){
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/infoPaciente",
			dataType : "json",
			data : {
				id : id
			},
			success : function(response) {
				//TODO aca abro modal
				if(response != null){
					limiarModalMasInfo();
					$('#nombreLabel').text(response.nombreApellido);
					$('#fechaNacimientoLabel').text(response.fechaNacimiento);
					$('#tipoDocumentoFechaNacimientoLabel').text(response.tipoDocumento);
					$('#numeroDocumentoLabel').text(response.documento);
					$('#sexoLabel').text(response.sexo);
					$('#emailLabel').text(response.mail);
					$('#telefonoLabel').text(response.telefono);
					
					$('#infoPaciente').modal('show');
				}
			}
		});
		
	}
</script>
<%@ include file="common/footer.jspf"%>