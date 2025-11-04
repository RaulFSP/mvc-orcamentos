@param String requestURI
<!-- -->
<nav class="navbar navbar-expand-lg bg-body-tertiary py-4 fs-4 bg-dark"
	data-bs-theme="dark">
	<div class="container container-fluid">
		<a class="navbar-brand" href="/">MVC/Orçamento</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<li class="nav-item">
					<a class="nav-link ${requestURI.equals("/")?"active" :""}" href="/">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${requestURI.startsWith("/clientes")?"active" :""}" href="/clientes">Clientes</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${requestURI.startsWith("/items")?"active" :""}" href="/items">Items</a>
				</li>
				
				<li class="nav-item dropdown">
          <a 
          class="nav-link dropdown-toggle ${requestURI.startsWith("/orcamentos")?"active" :""}" 
          href="#" 
          role="button" 
          data-bs-toggle="dropdown" 
          >
            Orcamentos
          </a>
          <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="/orcamentos/new">Novo Orçamento</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/orcamentos/aberto">Abertos</a></li>
            <li><a class="dropdown-item" href="/orcamentos/aprovado">Aprovados</a></li>
            <li><a class="dropdown-item" href="/orcamentos/cancelado">Cancelados</a></li>
            
          </ul>
        </li>
			</ul>

		</div>
	</div>
</nav>