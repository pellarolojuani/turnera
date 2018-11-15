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
				<option value="08:00">08:00</option>
				<option value="09:00">09:00</option>
				<option value="10:00">10:00</option>
				<option value="11:00">11:00</option>
				<option value="12:00">12:00</option>
				<option value="13:00">13:00</option>
				<option value="14:00">14:00</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<div class="input-group">
			<label class="control-label" for="date">Hora Hasta</label> <select class="form-control" id="horaDesde" name="horaDesde">
				<option value="08:00">08:00</option>
				<option value="09:00">09:00</option>
				<option value="10:00">10:00</option>
				<option value="11:00">11:00</option>
				<option value="12:00">12:00</option>
				<option value="13:00">13:00</option>
				<option value="14:00">14:00</option>
				<option value="15:00">15:00</option>
				<option value="16:00">16:00</option>
				<option value="17:00">17:00</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<div class="input-group">
			<label class="control-label" for="date">Duraci&oacute;n Turno</label> <select class="form-control" id="horaDesde" name="horaDesde">
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
function registrarTurnos() {
	
}

</script>

<%@ include file="common/footer.jspf"%>