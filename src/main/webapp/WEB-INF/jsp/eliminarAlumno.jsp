<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- <script src="js/jquery.min.js"></script> -->
<!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="css/styles.css" rel="stylesheet"> -->
</head>
<body>

	<div class="usuario-form">
	
	<div class="form-group">
		<label for="exampleFormControlSelect1">Alumnos</label>
		<form:select class="form-control" id="alumno" name="alumnos" path="alumnos">
		</form:select>
	</div>

	<div class="form-group">
		<button id="eliminarAlumno" class="btn btn-success login-btn btn-block" onclick="eliminarAlumno()">Dar de Baja</button>
	</div>
</div>

<%@ include file="alumnoAnuladoModal.jsp"%>
<%@ include file="common/footer.jspf"%>
</body>
<script>
    $(document).ready(function () {
        if (localStorage.getItem("permiso") === "admin") {
            $("#tabOptions").append('<li><a href="/registrarAlumno">Registrar Alumno</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarAlumno">Eliminar Alumno</a></li>');
            $("#tabOptions").append('<li><a href="/registraTutor">Registrar Tutor</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarTutor">Eliminar Tutor</a></li>');
            $("#tabOptions").append('<li><a href="/registraDocente">Registrar Docente</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarDocente">Eliminar Docente</a></li>');
            $("#tabOptions").append('<li><a href="/buzonDeEntrada">Mensajes</a></li>');
        }
        
     
//     $.ajax({
//         type : "GET",
//         contentType : "application/json",
//          url : "/cargarTodosLosMedicos",
//         dataType : "json",
//         cache : false,
//         async : false,
//         success : function (response) {
//             if (response.result !== null && response.result !== undefined) {
//                 $.each(response.result, function (index, value) {
//                     $("#medico").append(new Option(value.nombre,value.id));
//                 });
//             }
//         }
//     });
   
});


    function eliminarAlumno() {
    
        var medico = $('#medico').val();
       

        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/darBajaMedico",
            dataType : "json",
            data : {
                
//                 medico : medico
            },
            cache : false,
            success : function (response) {
            	if(response.resultado){
					$('#mensajeAlumnoAnulado').text("El alumno se ha eliminado correctamente.")
    				$('#alumnoAnulado').modal('show');
    			}else {
//     				$('#mensajeMedicoAnulado').text("No se pudo anular el turno, por favor vuelva a intentarlo.")
//     				$('#MedicoAnulado').modal('show');
    			}
            }
        });
    }

   
</script>

</html>