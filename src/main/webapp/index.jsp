<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>desbravadores Gideoes</title>
<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
	color: blue;
}

h5 {
	position: absolute;
	top: 30%;
	left: 33%;
	right: 33%;
	color:blue;
}

h6 {
	position: absolute;
	top: 80%;
	left: 33%;
	right: 33%;
	color: red;
}


</style>
</head>

<body>
<h5> Seja Bem Vindo ao Clube Gideões</h5>
<form action="ServletLogin" method="post"
		class="row g-3   needs-validation" novalidate>


		<div class="col-md-6">
			<label class="form-label">Login</label> <input class="form-control"
				name="login" type="text" required="required">
		</div>
		<div class="invalid-feedback">Informe o Login!</div>


		<div class="col-md-6">
			<label class="form-label">Senha</label> <input class="form-control"
				name="senha" type="password" required="required">
		</div>
		<div class="invalid-feedback">Informe a Senha!</div>


		<input name="acessar" type="submit" class="btn btn-primary">




	</form>
	<input type="hidden" value=" <%=request.getParameter("url")%>"
		name="url">
	<h6>${msg}</h6>
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>

</body>
</html>