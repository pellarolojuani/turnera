<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<link href="css/bootstrap.min.css"  rel="stylesheet">
<link href="css/styles.css"  rel="stylesheet">
<script src="js/jquery.min.js"></script>
<!-- <script src="js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
 
</style>
</head>
<body>
<div class="login-form">
      <c:if test="${not empty errMsg}">
         <h2 class="error message" style="width: 900px">${errMsg}</h2>
     </c:if>
    <form:form modelAttribute="userForm" action="checkearUsuario" method='POST'>
        <h2 class="text-center">Bienvenido</h2>   
        <div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name="nombreUsuario" placeholder="Usuario" required="required">				
            </div>
        </div>
		<div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="contrasenia" placeholder="Contraseña" required="required">				
            </div>
        </div>        
        <div class="form-group">
              <button type="submit" class="btn btn-primary login-btn btn-block">Ingresar</button>
        </div>
   		<br></br>
        <div class="clearfix">
            <a href="registrarInit" class="pull-center">Registrar Usuario</a>
        </div>

    </form:form>
</div>
</body>
</html> 