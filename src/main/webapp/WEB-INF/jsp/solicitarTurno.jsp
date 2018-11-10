<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="form-group">
		<label for="exampleFormControlSelect1">Especialidades</label>
		<form:select class="form-control" id="especialidad"
			name="especialidades" path="especialidades" items="${especialidades}">
		</form:select>
	</div>

	<div class="form-group">
		<!-- Date input -->
		<label class="control-label" for="date">FechaTurno</label> <input
			class="form-control" id="targetDate" name="fechaTurno"
			placeholder="MM/DD/YYY" type="text" />
	</div>
	<div class="form-group">
		<button id="buscarTurno" class="btn btn-success login-btn btn-block"
			onclick="buscarTurno()">Buscar Turno</button>
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
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="tbodySolicitarTurno">
		</tbody>
	</table>

</div>
<script>

	function buscarTurno() {
		var especialidad = $('#especialidad').val();
		var fechaTurno = $('#targetDate').val();
		$("#tbodySolicitarTurno").empty();

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/buscarTurnosDisponibles",
			dataType : "json",
			data : {
				especialidad : especialidad,
				fechaTurno : fechaTurno
			},
			cache : false,
			success : function(response) {
				var filas = response.length;
				if (response != null && filas > 0) {
			
					for (i = 0; i < filas; i++) { //cuenta la cantidad de registros
						var nuevafila = "<tr><td>" + response[i].fechaString
								+ "</td><td>" + response[i].medico
								+ "</td><td>" + response[i].especialidad
								+ "</td><td><a type='button' class='btn btn-warning' onclick=solicitar('"+response[i].id+"') >Solicitar</a>"
								+ "</td></tr>"

						$("#tbodySolicitarTurno").append(nuevafila);
					}
				}
			}
		});
	}
	
	function solicitar(id){
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/solicitar",
			dataType : "json",
			data : {
				id : id
			},
			success : function(response) {
				//TODO aca abro modal
				if(response){
					$('#myModal').modal('show');
				}
			}
		});
		
	}
</script>
<%@ include file="common/footer.jspf"%>