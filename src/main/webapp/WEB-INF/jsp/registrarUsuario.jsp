<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- <link rel="stylesheet" type="text/css" href="css/style.css"/> -->
<!-- <link rel="stylesheet" href="css/styles.css" type="text/css" /> -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"   type="text/css"  /> --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
	.login-form {
		width: 385px;
		margin: 30px auto;
	}
    .login-form form {        
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .login-btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .input-group-addon .fa {
        font-size: 18px;
    }
    .login-btn {
        font-size: 15px;
        font-weight: bold;
    }
	.social-btn .btn {
		border: none;
        margin: 10px 3px 0;
        opacity: 1;
	}
    .social-btn .btn:hover {
        opacity: 0.9;
    }
	.social-btn .btn-primary {
        background: #507cc0;
    }
	.social-btn .btn-info {
		background: #64ccf1;
	}
	.social-btn .btn-danger {
		background: #df4930;
	}
    .or-seperator {
        margin-top: 20px;
        text-align: center;
        border-top: 1px solid #ccc;
    }
    .or-seperator i {
        padding: 0 10px;
        background: #f7f7f7;
        position: relative;
        top: -11px;
        z-index: 1;
    }   
</style>
</head>
<body>
	<div class="login-form">
		<c:if test="${not empty mensaje}">
   			<div><a>${mensaje}</a></div> 
		</c:if>
		<form name='r' action="registrar" method='GET'>
	        <div class="form-group">
	        	<div class="input-group">
<!-- 	                <span class="input-group-addon"><i class="fa fa-user"></i></span> -->
	                <input type="text" class="form-control" name="username" placeholder="Usuario" required="required">				
	            </div>
	        </div>
			<div class="form-group">
	            <div class="input-group">
<!-- 	                <span class="input-group-addon"><i class="fa fa-lock"></i></span> -->
	                <input type="password" class="form-control" name="password" placeholder="Contraseña" required="required">				
	            </div>
	        </div> 
	     	<div class="form-group">
	            <div class="input-group">
<!-- 	                <span class="input-group-addon"><i class="fa fa-lock"></i></span> -->
	                <input type="text" class="form-control" name="nroAfiliado" placeholder="Numero Afiliado" required="required">				
	            </div>
	        </div> 
            <div class="form-group">
              <button type="submit" class="btn btn-success login-btn btn-block">Registrar</button>
        	</div>
		</form>
		</div>
	</body>
</html> 