<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer class="bg-light pt-5">
	<div class="row">
		<div class="col bg-gradient">
			<div class="liner"></div>
			<div class="rrss text-center p-5">
				<em class="fab fa-facebook text-info fs-1 p-3"></em> <em
					class="fab fa-instagram text-info fs-1 p-3"></em> <em
					class="fab fa-twitter text-info fs-1 p-3"></em> <em
					class="fab fa-youtube text-info fs-1 p-3"></em>
			</div>
		</div>
	</div>
	<div class="row bg-info text-center">
		<p class="text-white pt-3">© 2021 RCTAS</p>
	</div>
</footer>
<script>

	const ckEditor = document.getElementById("ckEditor");
	
	function verReceta(id) {
		window.location.href = "receta/" + id;
	}
	
	if (ckEditor) {
	ClassicEditor
		.create(document.querySelector('#editor1'))
		.catch(error => {
			console.error(error);
		});

	ClassicEditor
		.create(document.querySelector('#editor2'))
		.catch(error => {
			console.error(error);
		});
	}
	
</script>
</body>
</html>