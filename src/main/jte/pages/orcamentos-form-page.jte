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
	<input type="hidden" name="id" value="${orcamento.id() != null ? orcamento.id().toString() : ""}">
	<input type="hidden" name="version" value="${orcamento.version() != null ? orcamento.version().toString() : ""}">
@endif
	

	
		
		
		
		
		
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