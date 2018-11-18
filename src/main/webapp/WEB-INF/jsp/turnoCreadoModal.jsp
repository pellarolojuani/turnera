<div id="turnoRegistrar" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      </div>
      <div class="modal-body">
        <p id="mensajeRegitrarTurnos"></p>
        <table class="table table-striped">
		<caption>Turnos</caption>
		<thead>
		</thead>
		<tbody id="tbodyRegistrarTurnos">
		</tbody>
	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="regresarRegistrarTurnos()">Aceptar</button>
      </div>
    </div>
  </div>
</div>
<script>
	function regresarRegistrarTurnos(){
		location.href= "/registrarTurnosInit";
	}
</script>