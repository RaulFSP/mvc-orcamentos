 @import java.util.List
 <!--  -->
 @import io.github.app.dtos.ClienteDto
 <!--  -->
 @param List<ClienteDto> clientes 
<!--  -->
@param String requestURI
<!--  -->
 @template.layout.base(requestURI=requestURI, headContent=@`
<title>Clientes</title>
`, bodyContent=@`

<section class="row my-4">
	<h1>Clientes</h1>
	<hr>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-primary w-100" href="/clientes/new">Adicionar um
			novo cliente</a>
	</div>
</section>

<section>
	<table class="table table-striped-columns table-responsive table-hover border ">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			@for(var cliente : clientes)
			<tr>
			<td >${cliente.id()}</td>
			<td>${cliente.name()}</td>
			<td class="text-center"><a href="/clientes/${cliente.id()}"><i class="bi bi-book"></i></a>
			</td>
			</tr>
			@else
			<h3>Não há clientes cadastrados</h3>
			@endfor
		</tbody>
	</table>
</section>
`)
