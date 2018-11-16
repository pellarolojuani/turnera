<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<div class="container">
	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">FechaTurno</label> <input class="form-control" id="datepickerRegistrar" name="fechaTurno" placeholder="MM/DD/YYY" type="text" />
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
			<label class="control-label" for="date">Duraci&oacute;n Turno</label> <select class="form-control" id="duracion" name="duracion">
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
        $("#tabOptions").append('<li><a href="/registrarTurnos">Registrar Turnos</a></li>');
    }
});
function registrarTurnos() {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        data : {
            "nombreUsuario" : localStorage.getItem("nombreUsuario"),
            "fecha" : $('#datepickerRegistrar').val(),
            "desde": $('#horaDesde').val(),
            "hasta": $('#horaHasta').val(),
            "duracion": $('#duracion').val()
        },
        url : "/registrarTurnos",
        dataType : "json",
        cache : false,
        success : function (response) {
            //TODO
//             if (response.result !== null && response.result !== undefined) {
//                 if (response.result) {
//                     window.location.replace("/login");
//                 } else {
//                     $('#registrarUsuario-errorLabel').text(response.message);
//                     $('#registrarUsuario-errorLabel').show();
//                     setTimeout(function () {
//                         $('#registrarUsuario-errorLabel').hide();
//                     }, 5000);
//                 }
//             }
        }
    });
}

</script>

<%@ include file="common/footer.jspf"%>