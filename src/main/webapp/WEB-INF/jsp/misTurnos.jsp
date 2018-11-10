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
						<td><a type="button" class="btn btn-success"
							href="/solicitar?id=${turno.id}">Solicitar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<!-- 		<div> -->
<!-- 			<a class="button" href="/add-todo">Add a Todo</a> -->
<!-- 		</div> -->
	</div>
<%@ include file="common/footer.jspf" %>