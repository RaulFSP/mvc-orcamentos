@import io.github.app.dtos.OrcamentoDto
<!--  -->
@import io.github.app.dtos.ClienteDto
<!--  -->
@import org.springframework.validation.BindingResult
<!--  -->
@import java.util.Map
<!--  -->
@import java.util.List
<!--  -->
@param BindingResult result
<!--  -->
@param OrcamentoDto orcamento
<!--  -->
@param Map<String,String> formFeedback
<!--  -->
@param Boolean isUpdate
<!--  -->
@param List<ClienteDto> clientes
<!--  -->
@param String requestURI
<!--  -->
@template.layout.base( requestURI=requestURI,headContent=@`
<title>${isUpdate ? "Editar Item" : "Novo Item"}</title>

`, bodyContent=@`
<section class="row my-4">
	<h1>${isUpdate ? "Editar Orcamento" : "Novo Orcamento"}</h1>
	<hr>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-primary w-100" href="/orcamentos">Voltar</a>
	</div>
</section>

<section >
<div class="row shadow border border-black py-4">
@if(formFeedback != null)
<div class="alert alert-${formFeedback.get("alert")} alert-dismissible fade show" role="alert">
  ${formFeedback.get("msg")}
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
@endif


<form action="${isUpdate ? "/orcamentos/" + orcamento.id() : "/orcamentos/create"}" method="post">
@if(isUpdate)
	<input type="hidden" name="_method" value="put">
@endif
	
<div class="mb-3">
	<select class="form-select ${result != null && result.hasFieldErrors("idCliente") ? "	is-invalid" : ""}"  name="idCliente" >

		<option value="">Selecione um cliente</option>
		@for(var cliente : clientes)
		  <option value="${cliente.id()}">${cliente.name()}</option>
  		@endfor
	</select>
		
		@if(result != null && result.hasFieldErrors("idCliente"))
					<div class="invalid-feedback">
                		${result.getFieldError("idCliente").getDefaultMessage()}
           		 </div>
			@endif
	</div>
		
		
		
		
		
		<div class="row g-3">
		<div class="col-sm-12 col-md-6">
		<button type="submit" class="btn btn-primary w-100">${isUpdate ? "Alterar" : "Criar"}</button>
		</div>
		<div class="col-sm-12 col-md-6">
		<button type="reset" class="btn btn-warning w-100">Limpar</button>
		</div>
		</div>
	</form>
	</div>
</section>
`)