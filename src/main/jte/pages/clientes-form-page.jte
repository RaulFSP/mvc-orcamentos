@import io.github.app.dtos.ClienteDto
<!--  -->
@import java.util.Map
<!--  -->
@import org.springframework.validation.BindingResult
<!--  -->
@param ClienteDto cliente
<!--  -->
@param BindingResult result
<!--  -->
@param Map<String,String> formFeedback
<!--  -->
@param Boolean isEdit
<!--  -->
@template.layout.base( headContent=@`
<title>${isEdit ? "Editar Cliente" : "Novo Cliente"}</title>
`, bodyContent=@`

<section class="row my-4">
<h1>${isEdit ? "Editar Cliente" : "Novo Cliente"}</h1>
<hr>
<div class="col-sm-12 col-md-4">
		<a class="btn btn-secondary w-100" href="/clientes">Voltar</a>
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


<form action="${isEdit ? "/clientes/" + cliente.id() : "/clientes/create"}" method="post">
@if(isEdit)
	<input type="hidden" name="_method" value="put">
	<input type="hidden" name="active" value="${cliente.active() != null ? cliente.active().toString() : "false"}">
@endif
	

		<div class="mb-3">
			<label for="clienteNome" class="form-label">Nome do Cliente</label> 
			<input
				name="name" type="text"
				class="form-control ${result != null && result.hasFieldErrors("name") ? "	is-invalid" : ""}"
				id="clienteNome"
				placeholder="nome do cliente"
				value="${cliente.name() != null  ? cliente.name() : ""}" > 
				@if(result != null && result.hasFieldErrors("name"))
					<div class="invalid-feedback">
                		${result.getFieldError("name").getDefaultMessage()}
           		 </div>
			@endif
		</div>
		<div class="row g-3">
		<div class="col-sm-12 col-md-6">
		<button type="submit" class="btn btn-primary w-100">${isEdit ? "Alterar" : "Criar"}</button>
		</div>
		<div class="col-sm-12 col-md-6">
		<button type="reset" class="btn btn-warning w-100">Limpar</button>
		</div>
		</div>
	</form>
	</div>
</section>

`)
