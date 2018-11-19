<div id="medicoAnulado" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      </div>
      <div class="modal-body">
        <p id="mensajeMedicoAnulado"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="regresarMedicoAnulado()">Aceptar</button>
      </div>
    </div>
  </div>
</div>
<script>
	function regresarMedicoAnulado(){
		location.href= "/eliminarMedico";
	}
</script>