<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<form name='r' action="buscarTurnosDisponibles" method='GET'>
	<div class="span12">
					<div class="control-group">
						<label class="control-label">Especialidad:</label>
						<div class="controls">
							 <form:select name="especialidad" path="especialidades" items="${especialidades}"/>
						</div>
						<div><label>FechaTurno: </label><input name="fechaTurno" type="text" id="targetDate"></div>
					</div>
            <div class="form-group">
              <button type="submit" class="btn btn-success login-btn btn-block">Buscar Turno</button>
        	</div>
				</div>
		</form>
		<c:forEach items="${turnos}" var="turno">
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
						<td><a type="button" class="btn btn-warning"
							href="/anularTurno?id=${turno.id}">Anular</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:forEach>
		<div>
			<a class="button" href="/add-todo">Add a Todo</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>