@import io.github.app.dtos.ItemDto
<!--  -->

<!--  -->
 @import java.util.Locale
<!--  -->
@param ItemDto item 
<!--  -->
@template.layout.base(headContent=@`
<title>Detalhes do Item</title>
`, 
bodyContent=@`

<section class="row my-4 g-3">
	<h1>Detalhes do Item</h1>
	<hr>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-secondary w-100" href="/items">Voltar</a>
	</div>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-secondary w-100" href="/items/${item.id()}/edit">Editar</a>
	</div>
	<div class="col-sm-12 col-md-4">
		<button type="button" class="btn btn-secondary w-100"
			data-bs-toggle="modal" data-bs-target="#modalDesativar">
			Desativar</button>
	</div>
</section>


<section class="container my-4">
	<div class="row shadow border border-black py-4">

		<div class="col-sm-12 col-md-3 fw-bold">ID:</div>
		<div class="col-sm-12 col-md-9">${item.id()}</div>

		<div class="col-sm-12 col-md-3 fw-bold">Item:</div>
		<div class="col-sm-12 col-md-9 text-break">${item.name()}</div>

	<div class="col-sm-12 col-md-3 fw-bold">Preço:</div>
		<div class="col-sm-12 col-md-9 text-break">${item.preco()}</div>

	</div>
</section>

<div class="modal fade" id="modalDesativar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Confirmação de Desativação</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Deseja desativar este item?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <form action="/items/${item.id()}" method="post">
			<input type="hidden" name="_method" value="delete">
			<button type="submit" class="btn btn-warning w-100">Confirmar</button>
		</form>
      </div>
    </div>
  </div>
</div>


`)
