<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<div class="container">
	<div class="form-group">
		<label for="exampleFormControlSelect1">Especialidades</label>
		<form:select class="form-control" id="especialidad" name="especialidades" path="especialidades" onchange="cargarMedicosPorEspecialidad();">
		</form:select>
	</div>

	<div class="form-group">
		<label for="exampleFormControlSelect2">Medicos</label>
		<form:select class="form-control" id="medico" name="medicos" path="medicos">
		</form:select>
	</div>

	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">FechaTurno</label> <input class="form-control" id="datepickerSolicitar" name="fechaTurno" placeholder="MM/DD/YYY" type="text" />
	</div>
	<div class="form-group">
		<button id="buscarTurno" class="btn btn-success login-btn btn-block" onclick="buscarTurno()">Buscar Turno</button>
	</div>
</div>
<div class="form-group">
	<table class="table table-striped" style="margin-left: 30px;">
		<caption>Selecionar Turno</caption>
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Medico</th>
				<th>Especialidad</th>
				<th>Duraci&oacute;n</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="tbodySolicitarTurno">
		</tbody>
	</table>

</div>
<%@ include file="turnoSolicitadoModal.jsp"%>
<%@ include file="infoMedicoModal.jsp"%>
<script>
    $(document).ready(function () {
        if (localStorage.getItem("permiso") === "medico") {
            $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
            $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
        }
        
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/cargarEspecialidades",
            dataType : "json",
            cache : false,
            async : false,
            success : function (response) {
                if (response !== null && response !== undefined) {
                    $.each(response, function (index, value) {
                        $("#especialidad").append(new Option(value.descripcion, value.codigo));
                    });
                }
            }
        });
        cargarMedicosPorEspecialidad();
    });
    
    function formattedDate(d, hora, minuto) {
    	  let month = String(d.getMonth() + 1);
    	  let day = String(d.getDate());
    	  const year = String(d.getFullYear());

    	  if (month.length < 2) month = '0' + month;
    	  if (day.length < 2) day = '0' + day;
    	  
    	  let hour = hora;
    	  if (hour< 2) hour =  '0' + hour;
    	  
    	  let minute = minuto;
    	  if (minute < 2) minute =  '0' + minute;
    	  return day +"/"+ month +"/"+ year +" "+hour +":" +minute;	  
    	}

    function cargarMedicosPorEspecialidad() {
        $.ajax({
            type : "GET",
            async : false,
            contentType : "application/json",
            data : {
                especialidad : $('#especialidad').val()
            },
            url : "/cargarMedicos",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response.result !== null && response.result !== undefined) {
                    $("#medico").empty();
                    $.each(response.result, function (index, value) {
                        $("#medico").append(new Option(value.nombre, value.id));
                    });
                }
            }
        });
    }

    function buscarTurno() {
        var especialidad = $('#especialidad').val();
        var fechaTurno = $('#datepickerSolicitar').val();
        var medico = $('#medico').val();
        $("#tbodySolicitarTurno").empty();

        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/buscarTurnosDisponibles",
            dataType : "json",
            data : {
                especialidad : especialidad,
                fechaTurno : fechaTurno,
                medico : medico
            },
            cache : false,
            success : function (response) {
                var filas = response.length;
                if (response != null && filas > 0) {

                    for (i = 0; i < filas; i++) { //cuenta la cantidad de registros
                		var fecha = new Date(response[i].fecha);
                		var fechaString = formattedDate(fecha,response[i].hora,response[i].minutos);
                    	
                        var nuevafila = "<tr><td>" + fechaString + "</td><td>" + response[i].medico.nombre + "</td><td>" + response[i].especialidad.descripcion + "</td><td>" + response[i].duracion +
                        "</td><td><a type='button' class='btn btn-info ' onclick=masInfoMedico('" + response[i].medico.id + "') >Info Medico</a>" 
                        + "</td><td><a type='button' class='btn btn-warning' onclick=solicitar('" + response[i].id + "') >Solicitar</a></td></tr>";

                        $("#tbodySolicitarTurno").append(nuevafila);
                    }
                }
            }
        });
    }

    function solicitar(id) {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/solicitar",
            dataType : "json",
            data : {
                id : id
            },
            success : function (response) {
                //TODO aca abro modal
                if (response.resultado) {
                    $('#mensajeTurno').text("Su turno fue solicitado con éxito. Comprobante: " + response.mensaje)
                    $('#turnoSolicitado').modal('show');
                } else {
                    $('#mensajeTurno').text("No se pudo solicitar el turno, por favor vuelva a intentarlo.")
                    $('#turnoSolicitado').modal('show');
                }
            }
        });

    }
    
    
	function limiarModalMasInfo(){
		$('#nombreLabel').text("");
		$('#especialidadLabel').text("");
		$('#matriculaLabel').text("");
	}
	
	function masInfoMedico(id){
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/infoMedico",
			dataType : "json",
			data : {
				id : id
			},
			success : function(response) {
				//TODO aca abro modal
				if(response != null){
					limiarModalMasInfo();
					$('#nombreLabel').text(response.nombre);
					$('#especialidadLabel').text(response.especialidad.descripcion);
					$('#matriculaLabel').text(response.matricula);
					
					$('#infoMedico').modal('show');
				}
			}
		});
		
	}
</script>
<%@ include file="common/footer.jspf"%>