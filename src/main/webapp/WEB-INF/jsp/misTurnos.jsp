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
						<td><fmt:formatDate value="${turno.fecha}" pattern="dd/MM/yyyy HH:MM"/></td>
						<td>${turno.medico.nombre}</td>
						<td>${turno.especialidad.descripcion}</td>
						<td>${turno.estado}</td>
						<td>${turno.numeroComprobante}</td>
						<td><a type='button' class='btn btn-warning' onclick=anularTurno(${turno.id}) >Anular</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<%@ include file="turnoAnuladoModal.jsp"%>
<script type="text/javascript">
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
			if(response){
				$('#mensajeMisTurno').text("Su turno fue anulado con éxito.")
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