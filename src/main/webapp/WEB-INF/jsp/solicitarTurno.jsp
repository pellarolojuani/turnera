<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<form name='r' action="buscarTurnosDisponibles" method='GET'>
	<div class="span12">
					<div class="control-group">
						<label class="control-label">Especialidad:</label>
						<div class="controls">
							 <form:select path="especialidades" items="${especialidades}"/>
						</div>
						<div><label>FechaTurno: </label><input type="text" id="targetDate"></div>
					</div>
            <div class="form-group">
              <button type="submit" class="btn btn-success login-btn btn-block">Buscar Turno</button>
        	</div>
				</div>
		</form>
		<c:forEach items="${turnos}" var="turno">
		<table class="table table-striped">
			<caption>Your todos are</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?id=${todo.id}">Delete</a></td>
					</tr>
			</tbody>
		</table>
		</c:forEach>
		<div>
			<a class="button" href="/add-todo">Add a Todo</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>