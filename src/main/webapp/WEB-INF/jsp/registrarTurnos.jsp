<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<div class="container">
	<div class="form-group">
		<label id="login-errorLabel" style="display: none;" class="alert alert-danger"></label>
	</div> 
	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">FechaTurno</label> <input class="form-control" id="datepickerRegistrar" name="fechaRegistroTurno" placeholder="MM/DD/YYYY" type="text" />
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
<%@ include file="turnoCreadoModal.jsp"%>
<script>

$(document).ready(function () {
    if (localStorage.getItem("permiso") === "medico") {
        $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
        $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
    }else{
  	  $("#tabOptions").append('<li><a href="/solicitarTurno">Solicitar Turno</a></li>');
	  $("#tabOptions").append('<li><a href="/misTurnos">Mis Turnos</a></li> ');
}
    

	$('#datepickerRegistrar').datepicker({
		  format: "dd/mm/yyyy",
	        startDate: '-0d',
	        endDate: '+35d',
		});
	
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
	
function registrarTurnos() {
	 var horaDesde = $('#horaDesde').val();
	 var horaHasta = $('#horaHasta').val();
     var fechaRegistroTurno = $('#datepickerRegistrar').val();
     var duracionTurno =  $('#duracionTurno').val();
	 debugger;
     if(parseInt(horaHasta) > parseInt(horaDesde)  ){
    	 if(fechaRegistroTurno != null && fechaRegistroTurno != ''){
	     $.ajax({
	         type : "GET",
	         contentType : "application/json",
	         url : "/registrarTurnosNuevos",
	         dataType : "json",
	         data : {
	             horaDesde : horaDesde,
	        	 horaHasta : horaHasta,
	             fechaRegistroTurno : fechaRegistroTurno,
	             duracionTurno: duracionTurno
	         },
	         cache : false,
	         success : function (response) {
	            var filas = response.length;
	             if (response != null && filas > 0) {
	
	                 for (i = 0; i < filas; i++) { //cuenta la cantidad de registros
                		var fecha = new Date(response[i].fecha);
                		var fechaString = formattedDate(fecha,response[i].hora,response[i].minutos);
	                     var nuevafila = "<tr><td>" + fechaString + "</td></tr>"
						 
	                     $("#tbodyRegistrarTurnos").append(nuevafila);
	                 }
	             }
	             $('#mensajeRegitrarTurnos').text("Se crearon los siguientes turnos: ");
				 $('#turnoRegistrar').modal('show');
	         }
	     });
    	 } else{
    	  	 $('#login-errorLabel').text("Debe seleccionar una fecha de turno.");
             $('#login-errorLabel').show();
             setTimeout(function () {
                 $('#login-errorLabel').hide();
             }, 5000); 
    	 }
     } else {
    	 $('#login-errorLabel').text("La hora hasta debe ser mayor a la hora desde.");
         $('#login-errorLabel').show();
         setTimeout(function () {
             $('#login-errorLabel').hide();
         }, 5000);
     }
}

</script>

<%@ include file="common/footer.jspf"%>