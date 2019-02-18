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

		<div id="leftcolumn"
			style="position: absolute; top: 17%; right: 79%; height: 75%; width: 20%;">
			<div class="headerUtil">
				<a type='button' class="btn btn-primary login-btn btn-block">New Message</a>
			</div>
			<div class="columnContent">
				<select id="selectMessage" name="selectMessage"
					style="overflow: auto; min-height: 95%; width: 100%; height: auto; padding-top: 15px;"></select>
			</div>
		</div>
		<div id="divMessageContent">
			<div id="contentliquid">
				<div id="divMessageContent">
<!-- 					<div class="columnContent"> -->
						<select id="messageData" name="messageData"
							style="overflow: auto; min-height: 77%; width: 100%; height: auto; padding-top: 15px;"></select>
<!-- 					</div> -->
				</div>
			</div>
		</div>

		<div class="form-group">
			<a type='button' class="btn btn-danger login-btn btn-block"
				href="/login">Volver</a>
		</div>
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
        
        for (var i = 1; i < 6; i++) {
            $('#anio').append(new Option(i, i));
        }
    
    
    
</script>
</html>