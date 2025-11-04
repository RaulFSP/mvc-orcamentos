@import java.util.List
<!--  -->
@import io.github.app.dtos.OrcamentosComClienteDto
<!--  -->
@param List<OrcamentosComClienteDto> dtos
<!--  -->
@template.layout.base( headContent=@`
<title>Orcamentos</title>
`, bodyContent=@`
<section class="row my-4">
	<h1>Orcamentos</h1>
	<hr>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-primary w-100" href="/orcamentos/new">Novo
			Orcamento</a>
	</div>
</section>
<table class="table table-hover">
	<thead>
		<tr>
			<td>Numero</td>
			<td>Cliente</td>
			<td>Data</td>
			<td>Preço Total</td>
		</tr>
	</thead>
	<tbody>
	@for(var dto : dtos)
	<tr>
		<td>${dto.idOrcamento().toString()}</td>
		<td>${dto.clienteNome().toString()}</td>
		<td>${dto.dataCriado().toString()}</td>
		<td>${dto.precoTotal().toString()}</td>
	</tr>
	@endfor
	</tbody>
</table>
<section></section>
`)
