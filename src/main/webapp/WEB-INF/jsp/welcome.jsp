<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">Welcome ${name}!!</div>
<script>
    $(document).ready(function () {
        if (localStorage.getItem("permiso") === "medico") {
            $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
            $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
        }
    });
</script>
<%@ include file="common/footer.jspf"%>