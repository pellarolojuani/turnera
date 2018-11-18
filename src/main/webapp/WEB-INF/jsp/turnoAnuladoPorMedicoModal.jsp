<div id="turnoAnuladoPorMedico" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      </div>
      <div class="modal-body">
        <p id="mensajeVerTurnos"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="regresarVerTurnos()">Aceptar</button>
      </div>
    </div>
  </div>
</div>
<script>
	function regresarVerTurnos(){
		location.href= "/verTurnos";
	}
</script>