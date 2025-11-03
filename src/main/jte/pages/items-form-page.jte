@import io.github.app.dtos.ItemDto
<!--  -->
@import org.springframework.validation.BindingResult
<!--  -->
@import java.util.Map
<!--  -->
@param BindingResult result
<!--  -->
@param ItemDto item
<!--  -->
@param Map<String,String> formFeedback
<!--  -->
@param Boolean isUpdate
<!--  -->
@template.layout.base( headContent=@`
<title>${isUpdate ? "Editar Item" : "Novo Item"}</title>
`, bodyContent=@`

<section class="row my-4">
<h1>${isUpdate ? "Editar Item" : "Novo Item"}</h1>
<hr>

<div class="col-sm-12 col-md-4">
		<a class="btn btn-secondary w-100" href="/items">Voltar</a>
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


<form action="${isUpdate ? "/items/" + item.id() : "/items/create"}" method="post">
@if(isUpdate)
	<input type="hidden" name="_method" value="put">
	<input type="hidden" name="id" value="${item.id() != null ? item.id().toString() : ""}">
	<input type="hidden" name="active" value="${item.active() != null ? item.active().toString() : "false"}">
	<input type="hidden" name="version" value="${item.version() != null ? item.version().toString() : ""}">
@endif
	

		<div class="mb-3">
			<label for="itemNome" class="form-label">Nome do Item</label> 
			<input
				name="name" type="text"
				class="form-control ${result != null && result.hasFieldErrors("name") ? "	is-invalid" : ""}"
				id="itemNome"
				placeholder="nome do item"
				value="${item.name() != null  ? item.name() : ""}" > 
				@if(result != null && result.hasFieldErrors("name"))
					<div class="invalid-feedback">
                		${result.getFieldError("name").getDefaultMessage()}
           		 </div>
			@endif
		</div>
		
		<div class="mb-3">
			<label for="itemPreco" class="form-label">Preço do Item</label> 
			<input
				name="preco" type="number" step="0.01"
				class="form-control ${result != null && result.hasFieldErrors("preco") ? "	is-invalid" : ""}"
				id="itemPreco"
				placeholder="1.00"
				value="${item.preco() != null  ? item.preco().toString() : ""}" > 
				@if(result != null && result.hasFieldErrors("preco"))
					<div class="invalid-feedback">
                		${result.getFieldError("preco").getDefaultMessage()}
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
