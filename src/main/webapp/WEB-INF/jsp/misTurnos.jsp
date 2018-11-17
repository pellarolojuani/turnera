<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Turnos</caption>
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Medico</th>
					<th>Especialidad</th>
					<th>Estado</th>
					<th>Nro Comprobante</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${turnos}" var="turno">
					<tr>
						<td>${turno.fechaString}</td>
						<td>${turno.medico.nombre}</td>
						<td>${turno.especialidad.descripcion}</td>
						<td>${turno.estadoMostrar}</td>
						<td>${turno.numeroComprobante}</td>
						<td>
						<c:if test = "${turno.mostrarBotonAnular}">
							<button type='button' class='btn btn-warning' onclick="anularTurno('${turno.id}')" >Anular</button>
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<%@ include file="turnoAnuladoModal.jsp"%>
<script type="text/javascript">

$(document).ready(function () {
    if (localStorage.getItem("permiso") === "medico") {
        $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
        $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
    }
});

function anularTurno(id){
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/anularTurno",
		dataType : "json",
		data : {
			id : id
		},
		success : function(response) {
			if(response.resultado){
				$('#mensajeMisTurno').text("Su turno fue anulado con éxito. Comprobante: " + response.mensaje);
				$('#turnoAnulado').modal('show');
			}else {
				$('#mensajeMisTurno').text("No se pudo anular el turno, por favor vuelva a intentarlo.")
				$('#turnoAnulado').modal('show');
			}
		}
	});
	
}
</script>
<%@ include file="common/footer.jspf" %>