 @import java.util.List
 <!--  -->
 @import io.github.app.dtos.ItemDto
 <!--  -->
 @param List<ItemDto> items 
<!--  -->
@param String requestURI
<!--  -->
 @template.layout.base(requestURI=requestURI, headContent=@`
<title>Items</title>
`, bodyContent=@`

<section class="row my-4">
	<h1>Items</h1>
	<hr>
	<div class="col-sm-12 col-md-4">
		<a class="btn btn-primary w-100" href="/items/new">Adicionar um
			novo item</a>
	</div>
</section>

<section>
	<table class="table table-striped-columns table-responsive table-hover border ">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Preco</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			@for(var item : items)
			<tr>
			<td >${item.id()}</td>
			<td>${item.name()}</td>
			<td>${item.preco()}</td>
			<td class="text-center">
			<a href="/items/${item.id()}">
			<i class="bi bi-book">
			</i>
			</a>
			</td>
			</tr>
			@else
			<h3>Não há items cadastrados</h3>
			@endfor
		</tbody>
	</table>
</section>
`)
