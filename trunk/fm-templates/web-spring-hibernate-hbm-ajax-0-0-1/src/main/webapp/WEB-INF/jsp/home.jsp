<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>Home</title>
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="span3" id="countries"></div>
				<div class="span9" id="cities"></div>
			</div>
		</div>
	</div>
	
	<script>
		$(window).load(function() {
			var countries = $("#countries");
			$.getJSON('/countries.html', function(data) {
				if(data.responseCode == "100") {
					table = $("<table/>", {class: "table table-striped table-bordered table-condensed"}).appendTo(countries);
					thead = $("<thead/>").appendTo(table);
					tr = $("<tr/>").appendTo(thead);
					th = $("<th/>", {text: "country"}).appendTo(tr);
					th = $("<th/>", {text: "cities"}).appendTo(tr);
					tbody = $("<tbody/>").appendTo(table);
					$(data.responseObject).each(function(i) {
						tr = $("<tr/>").appendTo(tbody);
						td = $("<td/>").appendTo(tr);
						a = $("<a/>", {onclick: "getCities("+this.id+")", text: this.name}).appendTo(td);
						td = $("<td/>", {text: this.cities.length}).appendTo(tr);
					});
					
					tr = $("<tr/>").appendTo(tbody);
					td = $("<td/>").appendTo(tr);
					a = $("<a/>", {onclick: "getCities(9)", text: "Not existing country"}).appendTo(td);
					td = $("<td/>").appendTo(tr);
				}				
			});
		});
		
		function getCities(id) {
			var cities = $("#cities");
			$(cities).empty();
			$.getJSON('/cities.html?id='+id, function(data) {
				if(data.responseCode == "100") {
					table = $("<table/>", {class: "table table-striped table-bordered table-condensed"}).appendTo(cities);
					thead = $("<thead/>").appendTo(table);
					tr = $("<tr/>").appendTo(thead);
					th = $("<th/>", {text: "city"}).appendTo(tr);
					tbody = $("<tbody/>").appendTo(table);
					$(data.responseObject.cities).each(function(i) {
						tr = $("<tr/>").appendTo(tbody);
						td = $("<td/>", {text: this.name}).appendTo(tr);
					});
				} else {
					$("<p/>", {text: data.responseText}).appendTo(cities);
				}
			});
		}
	</script>
</body>

</html>