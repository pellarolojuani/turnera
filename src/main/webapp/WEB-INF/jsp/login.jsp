<!DOCTYPE html>
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
    <form name='userForm' modelAttribute="userForm" action="login" method='POST'>
        <h2 class="text-center">Bienvenido</h2>   
        <div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name="username" placeholder="Usuario" required="required">				
            </div>
        </div>
		<div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="password" placeholder="Contraseña" required="required">				
            </div>
        </div>        
        <div class="form-group">
              <button type="submit" class="btn btn-primary login-btn btn-block">Ingresar</button>
        </div>
   		<br></br>
        <div class="clearfix">
            <a href="registrarInit" class="pull-center">Registrar Usuario</a>
        </div>

    </form>
</div>
</body>
</html> 