<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<div class="container">
	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">FechaTurno</label> <input class="form-control" id="datepickerRegistrar" name="fechaRegistroTurno" placeholder="MM/DD/YYY" type="text" />
	</div>
	
	<div class="form-group">
		<div class="input-group">
			<label class="control-label" for="date">Hora Desde</label> <select class="form-control" id="horaDesde" name="horaDesde">
				<option value="8">08:00</option>
				<option value="9">09:00</option>
				<option value="10">10:00</option>
				<option value="11">11:00</option>
				<option value="12">12:00</option>
				<option value="13">13:00</option>
				<option value="14">14:00</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<div class="input-group">
			<label class="control-label" for="date">Hora Hasta</label> <select class="form-control" id="horaHasta" name="horaHasta">
				<option value="8">08:00</option>
				<option value="9">09:00</option>
				<option value="10">10:00</option>
				<option value="11">11:00</option>
				<option value="12">12:00</option>
				<option value="13">13:00</option>
				<option value="14">14:00</option>
				<option value="15">15:00</option>
				<option value="16">16:00</option>
				<option value="17">17:00</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<div class="input-group">
			<label class="control-label" for="date">Duraci&oacute;n Turno</label> <select class="form-control" id="duracionTurno" name="duracionTurno">
				<option value="10">10</option>
				<option value="15">15</option>
				<option value="20">20</option>
				<option value="30">30</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<button id="registrarTurno" class="btn btn-success login-btn btn-block" onclick="registrarTurnos()">Registrar Turnos</button>
	</div>

</div>

<script>

$(document).ready(function () {
    if (localStorage.getItem("permiso") === "medico") {
        $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
        $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
    }
});
function registrarTurnos() {
	 var horaDesde = $('#horaDesde').val();
	 var horaHasta = $('#horaHasta').val();
     var fechaRegistroTurno = $('#datepickerRegistrar').val();
     var duracionTurno =  $('#duracionTurno').val();
    // $("#tbodySolicitarTurno").empty();
    debugger;
    //TODO validar que hasta sea mayor a desde

     $.ajax({
         type : "GET",
         contentType : "application/json",
         url : "/registrarTurnosNuevos",
         dataType : "json",
         data : {
             nombreUsuario : localStorage.getItem("nombreUsuario"),
             horaDesde : horaDesde,
        	 horaHasta : horaHasta,
             fechaRegistroTurno : fechaRegistroTurno,
             duracionTurno: duracionTurno
         },
         cache : false,
         success : function (response) {
//             var filas = response.length;
//              if (response != null && filas > 0) {

//                  for (i = 0; i < filas; i++) { //cuenta la cantidad de registros
//                      var nuevafila = "<tr><td>" + response[i].fechaString + "</td><td>" + response[i].medico.nombre + "</td><td>" + response[i].especialidad.descripcion + "</td><td>" + response[i].duracion + "</td><td><a type='button' class='btn btn-warning' onclick=solicitar('" + response[i].id + "') >Solicitar</a>" + "</td></tr>"

//                      $("#tbodySolicitarTurno").append(nuevafila);
//                  }
//              }
         }
     });
}

</script>

<%@ include file="common/footer.jspf"%>