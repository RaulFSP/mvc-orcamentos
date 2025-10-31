@import io.github.app.dtos.ClienteDto
<!--  -->
@param ClienteDto cliente
<!--  -->
@template.layout.base(headContent=@`
<title>Detalhes do Cliente</title>
`, bodyContent=@`

<section class="row my-4 g-3">
	<h1>Detalhes do Cliente</h1>
	<hr>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-secondary w-100" href="/clientes">Voltar</a>
	</div>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-secondary w-100" href="/clientes/${cliente.id()}/edit">Editar</a>
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
		<div class="col-sm-12 col-md-9">${cliente.id()}</div>

		<div class="col-sm-12 col-md-3 fw-bold">Nome:</div>
		<div class="col-sm-12 col-md-9 text-break">${cliente.name()}</div>

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
        Deseja desativa este cliente?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <form action="/clientes/${cliente.id()}" method="post">
			<input type="hidden" name="_method" value="delete">
			<button type="submit" class="btn btn-warning w-100">Confirmar</button>
		</form>
      </div>
    </div>
  </div>
</div>


`)
