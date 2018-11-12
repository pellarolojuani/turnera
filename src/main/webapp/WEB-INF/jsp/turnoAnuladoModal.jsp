<div id="turnoAnulado" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      </div>
      <div class="modal-body">
        <p id="mensajeMisTurno"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="regresarMisTurnos()">Aceptar</button>
      </div>
    </div>
  </div>
</div>
<script>
	function regresarMisTurnos(){
		location.href= "/misTurnos";
	}
</script>