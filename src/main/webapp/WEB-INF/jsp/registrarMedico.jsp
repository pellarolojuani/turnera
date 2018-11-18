<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>

</head>
<body>
	<div class="usuario-form">

<%-- 		<form:form action="registroMedico" method='POST' modelAttribute="registroMedico"> --%>
			<c:if test="${not empty errMsg}">
				<h4 class="error message" style="width: 800px">${errMsg}</h4>
			</c:if>
			<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">Usuario</label> <input type="text" class="form-control" id="nombreUsuario" required="required" maxlength="10">
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">Contraseņa</label> <input type="password" class="form-control" id="contrasenia" required="required" maxlength="10">
			</div>
		</div>
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="date">Nombre y Apellido:</label> <input type="text" class="form-control" id="nombre" required="required" maxlength="100">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="especialidad">Especialidad:</label> <select class="form-control" id="especialidad" name="especialidad">
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="matricula">Matricula:</label> <input type="text" class="form-control" id="matricula" required="required">
				</div>
			</div>
			<div class="form-group">
				<button type="button" id="submitButton" onclick="submitCrearMedico();" class="btn btn-success login-btn btn-block" disabled="disabled">Registrar</button>
			</div>
			<label id="registrarMedico-errorLabel" style="display: none;" class="alert alert-danger"></label>
			<div class="form-group">
				<a type='button' class="btn btn-danger login-btn btn-block" href="/login">Volver</a>
			</div>
<%-- 		</form:form> --%>
	</div>
	<%@ include file="common/footer.jspf"%>
</body>

<script>
    $(document).ready(function () {
        if (localStorage.getItem("permiso") === "admin") {
            $('#submitButton').prop('disabled', false);
        }
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/cargarEspecialidades",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response !== null && response !== undefined) {
                    $.each(response, function (index, value) {
                        $("#especialidad").append(new Option(value.descripcion, value.codigo));
                    });
                }
            }
        });
    });
    
    function submitCrearMedico() {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            data : {
                "usuario" : $('#nombreUsuario').val(),
                "contrasenia" : $('#contrasenia').val(),
                "nombre" : $('#nombre').val(),
                "especialidad" : $('#especialidad').val(),
                "matricula" : $('#matricula').val()
            },
            url : "/registroMedico",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response.result !== null && response.result !== undefined) {
                    if (response.result) {
                        window.location.replace("/login");
                    } else {
                        $('#registrarMedico-errorLabel').text(response.message);
                        $('#registrarMedico-errorLabel').show();
                        setTimeout(function () {
                            $('#registrarMedico-errorLabel').hide();
                        }, 5000);
                    }
                }
            }
        });
    }
</script>
</html>
