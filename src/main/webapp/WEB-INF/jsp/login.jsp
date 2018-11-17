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
     	
<%--     <form:form modelAttribute="userForm" action="checkearUsuario" method='POST'> --%>
        <h2 class="text-center">Bienvenido</h2>  
        <label id="login-errorLabel" style="display: none;" class="alert alert-danger"></label> 
        <div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" id="nombreUsuario" placeholder="Usuario" required="required">				
            </div>
        </div>
		<div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" id="contrasenia" placeholder="Contraseña" required="required">				
            </div>
        </div>        
        <div class="form-group">
              <button type="submit" onclick="checkearUsuario();" class="btn btn-primary login-btn btn-block">Ingresar</button>
        </div>
   		<br></br>
        <div class="clearfix">
            <a href="registrarUsuario" class="pull-center">Registrar Usuario</a>
        </div>

<%--     </form:form> --%>
</div>
</body>

<script>


    $(document).ready(function () {
        
    });
    
    function checkearUsuario() {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            data: {
              "usuario" : $('#nombreUsuario').val(),
              "contrasenia": $('#contrasenia').val()
            },
            url : "/checkearUsuario",
            dataType : "json",
            cache : false,
            success : function (response) {
                if (response.result !== null && response.result !== undefined) {
                    localStorage.setItem("permiso",response.result.permiso);
                    localStorage.setItem("nombreUsuario",response.result.nombreUsuario);
                    window.location.replace("/welcome");
                } else {
                    $('#login-errorLabel').text(response.message);
                    $('#login-errorLabel').show();
                    setTimeout(function () {
                        $('#login-errorLabel').hide();
                    }, 5000);
                }
            }
        });
    }
    
    
    
</script>
</html> 