<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">Bienvenido</div>
<script>
    $(document).ready(function () {

        //ESTO EVENTUALMENTE LO VAMOS A BORRAR 
        if (localStorage.getItem("permiso") === "medico") {
            $("#tabOptions").append('<li><a href="/verTurnos">Ver Turnos</a></li>');
            $("#tabOptions").append('<li><a href="/registrarTurnosInit">Registrar Turnos</a></li>');
        } else if (localStorage.getItem("permiso") === "usuario") {
            $("#tabOptions").append('<li><a href="/solicitarTurno">Solicitar Turno</a></li>');
            $("#tabOptions").append('<li><a href="/misTurnos">Mis Turnos</a></li> ');
        }

        // ESTO ES LO QUE VA A QUEDAR. CON LOS NUEVOS PERMISOS
        if (localStorage.getItem("permiso") === "admin") {
            $("#tabOptions").append('<li><a href="/registrarAlumno">Registrar Alumno</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarAlumno">Eliminar Alumno</a></li>');
            $("#tabOptions").append('<li><a href="/registrarTutor">Registrar Tutor</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarTutor">Eliminar Tutor</a></li>');
            $("#tabOptions").append('<li><a href="/registrarDocente">Registrar Docente</a></li>');
            $("#tabOptions").append('<li><a href="/eliminarDocente">Eliminar Docente</a></li>');
            $("#tabOptions").append('<li><a href="/buzonDeEntrada">Mensajes</a></li>');
        } else if (localStorage.getItem("permiso") === "alumno") {
            //             $("#tabOptions").append('<li><a href="/solicitarTurno">Solicitar Turno</a></li>');
            //             $("#tabOptions").append('<li><a href="/misTurnos">Mis Turnos</a></li> ');
        } else if (localStorage.getItem("permiso") === "docente") {
            //             $("#tabOptions").append('<li><a href="/registrarMedico">Registrar Medico</a></li>');
            //             $("#tabOptions").append('<li><a href="/eliminarMedico">Eliminar Medico</a></li>');
        } else if (localStorage.getItem("permiso") === "tutor") {
            //             $("#tabOptions").append('<li><a href="/registrarMedico">Registrar Medico</a></li>');
            //             $("#tabOptions").append('<li><a href="/eliminarMedico">Eliminar Medico</a></li>');
        }
    });
</script>

<%@ include file="common/footer.jspf"%>