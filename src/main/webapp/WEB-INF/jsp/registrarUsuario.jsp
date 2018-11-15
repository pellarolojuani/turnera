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

		<%-- 		<form:form  action="registrar" method='POST' modelAttribute="registroUsuario"> --%>
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
				<label class="control-label" for="date">Nombre y Apellido</label> <input type="text" class="form-control" id="nombreApellido" required="required" maxlength="100">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label" for="date">Fecha de Nacimiento</label> <input class="form-control" id="datepickerFechaNacimiento" name="fechaNacimiento" placeholder="MM/DD/YYY" type="text" required="required" />
		</div>
		<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">Tipo Documento</label> <select class="form-control" id="tipoDocumento" name="tipoDocumento">
					<option value="DNI">DNI</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">N&uacute;mero Documento</label> <input type="text" class="form-control" id="documento" required="required" maxlength="8">
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">Sexo</label> <select class="form-control" id="sexo" name="sexo">
					<option value="F">Femenino</option>
					<option value="M">Masculino</option>
					<option value="I">Indefinido</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">Email</label> <input type="email" class="form-control" id="mail" required="required">
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<label class="control-label" for="date">Tel&eacute;fono</label> <input type="text" class="form-control" id="telefono" required="required">
			</div>
		</div>
		<div class="form-group">
			<button type="submit" onclick="registrarUsuario();" class="btn btn-success login-btn btn-block">Registrar</button>
		</div>
		<label id="registrarUsuario-errorLabel" style="display: none;" class="alert alert-danger"></label>
		<div class="form-group">
			<a type='button' class="btn btn-danger login-btn btn-block" href="/login">Volver</a>
		</div>
		<%-- 		</form:form> --%>
	</div>
	<%@ include file="common/footer.jspf"%>
</body>
<script>
    $(document).ready(function () {

    });

    function registrarUsuario() {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            data : {
                "usuario" : $('#nombreUsuario').val(),
                "contrasenia" : $('#contrasenia').val(),
                "nombreApellido" : $('#nombreApellido').val(),
                "fechaNacimiento" : $('#datepickerFechaNacimiento').val(),
                "tipoDocumento" : $('#tipoDocumento').val(),
                "documento" : $('#documento').val(),
                "sexo" : $('#sexo').val(),
                "mail" : $('#mail').val(),
                "telefono" : $('#telefono').val()
            },
            url : "/registrar",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response.result !== null && response.result !== undefined) {
                    debugger;
                    if (response.result) {
                        window.location.replace("/login");
                    } else {
                        $('#registrarUsuario-errorLabel').text(response.message);
                        $('#registrarUsuario-errorLabel').show();
                        setTimeout(function () {
                            $('#registrarUsuario-errorLabel').hide();
                        }, 5000);
                    }
                }
            }
        });
    }
</script>
</html>
