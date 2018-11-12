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
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${turnos}" var="turno">
					<tr>
						<td><fmt:formatDate value="${turno.fecha}" pattern="dd/MM/yyyy HH:MM"/></td>
						<td>${turno.medico}</td>
						<td>${turno.especialidad}</td>
						<td>${turno.estado}</td>
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
				$('#mensajeMisTurno').append("Su turno fue anulado con &eacute;xito.")
				$('#turnoAnulado').modal('show');
			}else {
				$('#mensajeMisTurno').append("No se pudo anular el turno, por favor vuelva a intentarlo.")
				$('#turnoAnulado').modal('show');
			}
		}
	});
	
}
</script>
<%@ include file="common/footer.jspf" %>