@import gg.jte.Content
<!--  -->
@param Content headContent
<!--  -->
@param Content bodyContent
<!--  -->
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/bootstrap-icons/bootstrap-icons.css">


${headContent}
</head>
<body>
	<header> @template.layout.navbar() </header>
	<main class="container">${bodyContent}</main>
	<footer></footer>
	<script type="text/javascript" src="/js/bootstrap.bundle.js"></script>
</body>
</html>