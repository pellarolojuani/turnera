<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
</head>
<body>
	<div class="usuario-form">

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
				<label class="control-label" for="date">Contraseña</label> <input type="password" class="form-control" id="contrasenia" required="required" maxlength="10">
			</div>
		</div>
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="nombre">Nombre:</label> <input type="text" class="form-control" id="nombre" required="required" maxlength="100">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="apellido">Apellido:</label> <input type="text" class="form-control" id="apellido" required="required" maxlength="100">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="dni">DNI:</label><input type="text" class="form-control" id="dni" required="required" maxlength="100">
				</div>
			</div>
			<div class="form-group">
				<button type="button" id="submitButton" onclick="submitCrearTutor();" class="btn btn-success login-btn btn-block" >Registrar</button>
			</div>
			<label id="registrarTutor-errorLabel" style="display: none;" class="alert alert-danger"></label>
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
            $("#tabOptions").append('<li><a href="/registrarAlumno">Registrar Alumno</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarAlumno">Eliminar Alumno</a></li>');
            $("#tabOptions").append('<li><a href="/registrarTutor">Registrar Tutor</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarTutor">Eliminar Tutor</a></li>');
            $("#tabOptions").append('<li><a href="/registrarDocente">Registrar Docente</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarDocente">Eliminar Docente</a></li>');
            $("#tabOptions").append('<li><a href="/buzonDeEntrada">Mensajes</a></li>');
        }
        
    });
    
    function submitCrearTutor() {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            data : {
                "usuario" : $('#nombreUsuario').val(),
                "contrasenia" : $('#contrasenia').val(),
                "nombre" : $('#nombre').val(),
                "apellido" : $('#apellido').val(),
                "dni" : $('#dni').val()
            },
            url : "/registroTutor",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response.result !== null && response.result !== undefined) {
                    if (response.result) {
                        window.location.replace("/login");
                    } else {
                        $('#registrarTutor-errorLabel').text(response.message);
                        $('#registrarTutor-errorLabel').show();
                        setTimeout(function () {
                            $('#registrarTutor-errorLabel').hide();
                        }, 5000);
                    }
                }
            }
        });
    }
</script>
</html>