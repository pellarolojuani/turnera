<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<<<<<<< HEAD
<div class="container">
	Bienvenido ${name}!! <a href="/list-todos">Ingresar</a>
</div>
=======
<div class="container">Welcome ${name}!!</div>
<script>
    $(document).ready(function () {
        if (localStorage.getItem("permiso") === "medico") {
            $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
            $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
        }
    });
</script>
>>>>>>> 2f1e92fd248fcb70f6c1cad3ba3bbf25d3f02eda
<%@ include file="common/footer.jspf"%>