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
	<table class="table table-striped">
		<caption>Selecionar Turno</caption>
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Medico</th>
				<th>Especialidad</th>
				<th>Duraci&oacute;n</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="tbodySolicitarTurno">
		</tbody>
	</table>

</div>
<%@ include file="turnoSolicitadoModal.jsp"%>
<script>

    $(document).ready(function () {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/cargarEspecialidades",
            dataType : "json",
            cache : false,
            async: false,
            success : function (response) {
                if (response !== null && response !== undefined) {
                    $.each(response, function(index, value) {
                    $("#especialidad").append(new Option(value.descripcion, value.codigo));                        
                    });
                }
            }
        });
        cargarMedicosPorEspecialidad();
    });
    
    function cargarMedicosPorEspecialidad() {
        $.ajax({
            type : "GET",
            async: false,
            contentType : "application/json",
            data: {
            	especialidad : $('#especialidad').val()  
            },
            url : "/cargarMedicos",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response.result !== null && response.result !== undefined) {
                    $("#medico").empty();
                    $.each(response.result, function(index, value) {
                    $("#medico").append(new Option(value.nombre, value.id));                        
                    });
                }
            }
        });
    }
    
    


    function buscarTurno() {
        var especialidad = $('#especialidad').val();
        var fechaTurno = $('#datepickerSolicitar').val();
        var medico =  $('#medico').val();
        $("#tbodySolicitarTurno").empty();

        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/buscarTurnosDisponibles",
            dataType : "json",
            data : {
                especialidad : especialidad,
                fechaTurno : fechaTurno,
                medico: medico
            },
            cache : false,
            success : function (response) {
                var filas = response.length;
                if (response != null && filas > 0) {

                    for (i = 0; i < filas; i++) { //cuenta la cantidad de registros
                        var nuevafila = "<tr><td>" + response[i].fechaString + "</td><td>" + response[i].medico.nombre + "</td><td>" + response[i].especialidad.descripcion + "</td><td>" + response[i].duracion + "</td><td><a type='button' class='btn btn-warning' onclick=solicitar('" + response[i].id + "') >Solicitar</a>" + "</td></tr>"

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
                    $('#mensajeTurno').text("Su turno fue solicitado con éxito. Comprobante" + response.mensaje)
                    $('#turnoSolicitado').modal('show');
                } else {
                    $('#mensajeTurno').text("No se pudo solicitar el turno, por favor vuelva a intentarlo.")
                    $('#turnoSolicitado').modal('show');
                }
            }
        });

    }
</script>
<%@ include file="common/footer.jspf"%>