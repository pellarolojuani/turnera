<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
	<div class="span6">
					<div class="control-group">
						<label class="control-label">Especialidad:</label>
						<div class="controls">
							<form:select id="tipoDocumentoBusqueda" name="tipoDocumentoBusqueda" path="especialidad" class="selectBusqueda" margin-left="10%" title="Especialidad M&eacute;dica">
<%-- 								<form:option value="0">CREDENCIAL</form:option> --%>
<%-- 								<form:options items="${beneficiarioForm.documentoTipo}" --%>
<%-- 									itemValue="id" itemLabel="abreviatura" /> --%>
							</form:select>				
						</div>
					</div>
				</div>
	
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
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-todo">Add a Todo</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>