<div id="infoPaciente" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      </div>
      <div class="modal-body">
      	<div class="form-group">
	            <div class="input-group">
	            	<label class="control-label" for="date">Nombre y Apellido</label>
	                <label id="nombreLabel" class="form-control" ></label>			
	            </div>
	        </div> 
			<div class="form-group">
				<label class="control-label" for="date">Fecha de Nacimiento</label> <input
				class="form-control" id="datepickerFechaNacimiento" name="fechaNacimiento"
				placeholder="MM/DD/YYY" type="text"  required="required" />
			</div>	
			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="date">Tipo Documento</label>
					<select class="form-control" id="tipoDocumento" name="tipoDocumento" >
					   <option value="DNI">DNI</option>
					</select>
	            </div>
			</div>
	     	<div class="form-group">
	            <div class="input-group">
	            	<label class="control-label" for="date">N&uacute;mero Documento</label>
	                <input type="text" class="form-control" name="documento"  required="required"  maxlength="8">				
	            </div>
	        </div> 
   			<div class="form-group">
				<div class="input-group">
					<label class="control-label" for="date">Sexo</label>
					<select class="form-control" id="sexo" name="sexo">
					   <option value="F">Femenino</option>
					   <option value="M">Masculino</option>
					   <option value="I">Indefinido</option>
					</select>
	            </div>
			</div>
      	     <div class="form-group">
	            <div class="input-group">
	            	<label class="control-label" for="date">Email</label>
	                <input type="email" class="form-control" name="mail"  required="required" >				
	            </div>
	        </div> 	
       	     <div class="form-group">
	            <div class="input-group">
	            	<label class="control-label" for="date">Tel&eacute;fono</label>
	                <input type="text" class="form-control" name="telefono"  required="required" >				
	            </div>
	        </div>
      </div>
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-primary" onclick="regresarVerTurnos()">Aceptar</button> -->
<!--       </div> -->
    </div>
  </div>
</div>
<script>
// 	function regresarVerTurnos(){
// 		location.href= "/verTurnos";
// 	}
</script>