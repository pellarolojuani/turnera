<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.min.css"  rel="stylesheet">
<link href="css/styles.css"  rel="stylesheet">
<script src="js/jquery.min.js"></script>
	
</head>
<body>
	<div class="usuario-form">
		<c:if test="${not empty mensaje}">
   			<div><a>${mensaje}</a></div> 
		</c:if>
		<form name='r' action="registrar" method='POST' modelAttribute="usuario">
	        <div class="form-group">
	        	<div class="input-group">
	        		<label class="control-label" for="date">Usuario</label>
	                <input type="text" class="form-control" name="nombreUsuario"  required="required"  maxlength="10">				
	            </div>
	        </div>
			<div class="form-group">
	            <div class="input-group">
	           		<label class="control-label" for="date">Contraseña</label>
	                <input type="password" class="form-control" name="contrasenia"  required="required"  maxlength="10">				
	            </div>
	        </div> 
	     	<div class="form-group">
	            <div class="input-group">
	            	<label class="control-label" for="date">Nombre y Apellido</label>
	                <input type="text" class="form-control" name="nombreApellido"  required="required"  maxlength="100">				
	            </div>
	        </div> 
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="date">Sexo</label>
					<select class="form-control" id="sexo" name="sexo">
					   <option value="F">Femenino</option>
					   <option value="M">Masculino</option>
					   <option value="I">Indefinido</option>
					</select>
	            </div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="date">Tipo Documento</label>
					<select class="form-control" id="tipoDocumento" name="tipoDocumento" >
					   <option value="DNI">DNI</option>
					</select>
	            </div>
			</div>
	     	<div class="form-group">
	            <div class="input-group">
	            	<label class="control-label" for="date">Número Documento</label>
	                <input type="text" class="form-control" name="numeroDocumento"  required="required"  maxlength="8">				
	            </div>
	        </div> 	
			<div class="form-group">
				<label class="control-label" for="date">Fecha de Nacimiento</label> <input
				class="form-control" id="datepickerFechaNacimiento" name="fechaNacimiento"
				placeholder="MM/DD/YYY" type="text"  required="required" />
			</div>
			
            <div class="form-group">
              <a type="button" class="btn btn-success login-btn btn-block">Registrar</a>
        	</div>
     	    <div class="form-group">
              <a type='button' class="btn btn-danger login-btn btn-block" href="/login" >Volver</a>
        	</div>
		</form>
		</div>
		<%@ include file="common/footer.jspf"%>
	</body>
</html> 