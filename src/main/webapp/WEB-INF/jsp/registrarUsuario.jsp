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
	<div class="login-form">
		<c:if test="${not empty mensaje}">
   			<div><a>${mensaje}</a></div> 
		</c:if>
		<form name='r' action="registrar" method='POST' modelAttribute="usuario">
	        <div class="form-group">
	        	<div class="input-group">
<!-- 	                <span class="input-group-addon"><i class="fa fa-user"></i></span> -->
	                <input type="text" class="form-control" name="nombreUsuario" placeholder="Usuario" required="required">				
	            </div>
	        </div>
			<div class="form-group">
	            <div class="input-group">
<!-- 	                <span class="input-group-addon"><i class="fa fa-lock"></i></span> -->
	                <input type="password" class="form-control" name="contrasenia" placeholder="Contraseña" required="required">				
	            </div>
	        </div> 
<!-- 	     	<div class="form-group"> -->
<!-- 	            <div class="input-group"> -->
<!-- 	                <input type="text" class="form-control" name="nroAfiliado" placeholder="Numero Afiliado" required="required">				 -->
<!-- 	            </div> -->
<!-- 	        </div>  -->
            <div class="form-group">
              <button type="submit" class="btn btn-success login-btn btn-block">Registrar</button>
        	</div>
		</form>
		</div>
	</body>
</html> 