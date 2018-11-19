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
		<label for="exampleFormControlSelect1">Medicos</label>
		<form:select class="form-control" id="medico" name="medicos" path="medicos">
		</form:select>
	</div>

	<div class="form-group">
		<button id="eliminarMedico" class="btn btn-success login-btn btn-block" onclick="eliminarMedico()">Dar de Baja</button>
	</div>
</div>

<%@ include file="medicoAnuladoModal.jsp"%>
<%@ include file="common/footer.jspf"%>
</body>
<script>
    $(document).ready(function () {
        if (localStorage.getItem("permiso") === "admin") {
        	$("#tabOptions").append('<li><a href="/registrarMedico">Registrar Medico</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarMedico">Eliminar Medico</a></li>');
        }
        
     
    $.ajax({
        type : "GET",
        contentType : "application/json",
         url : "/cargarTodosLosMedicos",
        dataType : "json",
        cache : false,
        async : false,
        success : function (response) {
            if (response.result !== null && response.result !== undefined) {
                $.each(response.result, function (index, value) {
                    $("#medico").append(new Option(value.nombre,value.id));
                });
            }
        }
    });
   
});


    function eliminarMedico() {
    
        var medico = $('#medico').val();
       

        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/darBajaMedico",
            dataType : "json",
            data : {
                
                medico : medico
            },
            cache : false,
            success : function (response) {
            	if(response.resultado){
					$('#mensajeMedicoAnulado').text("El medico se ha eliminado correctamente.")
    				$('#medicoAnulado').modal('show');
    			}else {
    				$('#mensajeMedicoAnulado').text("No se pudo anular el turno, por favor vuelva a intentarlo.")
    				$('#MedicoAnulado').modal('show');
    			}
            }
        });
    }

   
</script>

</html>