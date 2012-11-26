<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Send a support package</title>
</head>

<body>
	<div id="videoModal"></div>
	
	<div id="compose">
		<div class="row">
			<div class="span12">
				<div id="ciao"></div>
				<p class="pull-right">Inviare a: <input type="text" id="receiverEmail" name="receiverEmail" placeholder="qui va l'email del destinatario">
				</p>
			</div>
		</div>
	
		<div class="row">
			<div class="span12">
				<p>Ciao <input type="text" id="receiverName" name="receiverName" placeholder="qui va il nome del destinatario" value="Angela"></p>
				
				<p>Ho visto che stai imparando ad usare il computer e questo mi rende davvero</p>
				<p id="moods"></p>
				<p>Per questo ho selezionato per te una lista di video che ti potranno aiutare nell'impresa.</p>
				
				<div class="row" style="margin-bottom: 20px;">
	             		<div class="span4" id="categories"></div>
	             		<div class="span8" id="videos"></div>
				</div>
				
				<p>Spero che questi video ti siano utili per diventare presto un </p>
	            <p id="confidences"></p>
	            <p>Ci vediamo online!</p>
	            <p id="greetings"></p>
	            
	            <p class="pull-right">
	            	<input type="text" id="senderName" name="senderName" placeholder="qui va il tuo nome" value="Fabio"><br/>
	           	 	<input type="text" id="senderEmail" name="senderEmail" placeholder="qui va il tuo indirizzo email" value="fabio.maffioletti@gmail.com">
	         	</p>
			</div>
		</div>
	
		<div class="row">
			<div class="span12" style="text-align: center;">
				<p>
					<input type="button" id="previewButton" class="btn" value="Anteprima">
				</p>
			</div>
		</div>
	</div>
	
	<div id="preview" style="display:none;">
		<p>Ciao <span id="previewReceiverName"></span>,</p>
		<p>Ho visto che stai imparando ad usare il computer e questo mi rende davvero <span id="previewSenderMood"></span>.</p>
		<p>Per questo ho selezionato per te una lista di video che ti potranno aiutare nell'impresa.</p>
		<div id="previewVideos"></div>
		<p>Spero che questi video ti siano utili per diventare presto un <span id="previewSenderConfidence"></span>.</p>
		<p>Ci vediamo online!</p>
		<p><span id="previewSenderGreeting"></span></p>
		<p><span id="previewSenderName"></span></p>
		
		<div class="row">
			<div class="span12" style="text-align: center;">
				<p>
					<input type="button" id="backButton" class="btn" value="Indietro">
					<input type="button" id="sendButton" class="btn" value="Invia">
				</p>
			</div>
		</div>
	</div>

<script id="moods" type="text/html">
{{#moods}}
	<label class="radio inline">
		<input type="radio" name="mood" value="{{ id }}" checked> {{ value }}
	</label>
{{/moods}}
</script>

<script id="confidences" type="text/html">
{{#confidences}}
	<label class="radio inline">
		<input type="radio" name="confidence" value="{{ id }}" checked> {{ value }}
	</label>
{{/confidences}}
</script>

<script id="greetings" type="text/html">
{{#greetings}}
	<label class="radio inline">
		<input type="radio" name="greeting" value="{{ id }}" checked> {{ value }}
	</label>
{{/greetings}}
</script>

<script id="categories" type="text/html">
<h5>Categoria</h5>
<ul class="unstyled">
	{{#categories}}	
		<li style="cursor:pointer" onclick="selectCategory({{id}})">{{ name }}</li>
	{{/categories}}
</ul>
</script>

<script id="videos" type="text/html">
<h5>Video</h5>
{{#videos}}
	<label class="checkbox" id="v{{id}}">
		<input type="checkbox" name="video" value="{{ id }} "><a href="#" id="{{ id }}" onclick="showVideoModal({{ id }})">{{ name }}</a>
	</label>
{{/videos}}
</script>

<script id="previewVideos" type="text/html">
<ul class="thumbnails">
{{#videos}}
	<li class="thumbnail span2">
		<a href="#">
			<img src="http://placehold.it/180x101" onclick="showVideoModal({{ id }})">
		</a>
		<div class="caption">
			<p>{{name}}</p>
		</div>
	</li>
{{/videos}}
</ul>
</script>



<script id="videoModal" type="text/html">
<div class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:510px">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">{{ name }}</h3>
	</div>
	<div class="modal-body">
		<p><iframe width="480" height="360" src="{{ url }}?rel=0" frameborder="0"></iframe></p>
	</div>
	<div class="modal-footer" style="text-align: justify;">
		<p>{{ description }}</p>
	</div>
</div>
</script>

<script type="text/javascript">
	var model, jsonMoods, jsonConfidences, jsonGreetings;
	
	var preview = $("#preview");
	var compose = $("#compose");
	var previewButton = $("#previewButton");
	var sendButton = $("#sendButton");
	var backButton = $("#backButton");
	
	$(document).ready(function () {
		
		$.ajax({
		  url: '/rest/model',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data) {
				model = data.value;
				
				jsonMoods = {"moods" : jlinq.from(model.variables).equals("name", "mood").select()};
				jsonConfidences = {"confidences" : jlinq.from(model.variables).equals("name", "confidence").select()};
				jsonGreetings = {"greetings" : jlinq.from(model.variables).equals("name", "greeting").select()};
				
				$('#moods').html(ich.moods(jsonMoods));
				$('#greetings').html(ich.greetings(jsonGreetings));
				$('#confidences').html(ich.confidences(jsonConfidences));
				$('#categories').html(ich.categories(model));
				$('#videos').html(ich.videos(model));
			  
				selectCategory(model.categories[0].id);
		  }
		});
		
		$(previewButton).click(showPreview);
		$(backButton).click(backToCompose);
		$(sendButton).click(send);
		
	});
	
	function backToCompose() {
		$(compose).show();
		$(preview).hide();
	}
	
	function selectCategory(categoryId) {
		var videosToHide = jlinq.from(model.videos).not().equals("category", categoryId).select();
		var videosToShow = jlinq.from(model.videos).equals("category", categoryId).select();
		for(i=0; i<videosToHide.length; i++) {
			$("#v"+videosToHide[i].id).hide();
		}
		for(i=0; i<videosToShow.length; i++) {
			$("#v"+videosToShow[i].id).show();
		}
	}
	
	function showVideoModal(videoId) {
		var videoModal = ich.videoModal(jlinq.from(model.videos).equals("id", videoId).first());
		$("#videoModal").html(videoModal);
		$("#videoModal").modal();
	}
	
	function showPreview() {
		$("#previewReceiverName").text($("#receiverName").val());
		var mood = $('input[name=mood]:checked').val();
		var confidence = $('input[name=confidence]:checked').val();
		var greeting = $('input[name=greeting]:checked').val();
		var videos = [];
		
		$("#previewSenderMood").text(jlinq.from(jsonMoods.moods).equals("id", parseInt(mood)).first().value);
		$("#previewSenderConfidence").text(jlinq.from(jsonConfidences.confidences).equals("id", parseInt(confidence)).first().value);
		$("#previewSenderGreeting").text(jlinq.from(jsonGreetings.greetings).equals("id", parseInt(greeting)).first().value);
		$("#previewSenderName").text($("#senderName").val());
		
		$('input[name=video]:checked').each(function() {
			videos.push(jlinq.from(model.videos).equals("id", parseInt($(this).val().trim())).first());
		});
		
		var jsonVideos = {"videos" : videos};
		var jsonVideosHTML = $('#previewVideos').html(ich.previewVideos(jsonVideos));
		
		$(compose).hide();
		$(preview).show();
	}
	
	function send() {
		var senderName = $("#senderName").val();
		var senderEmail = $("#senderEmail").val();
		var receiverName = $("#receiverName").val();
		var receiverEmail = $("#receiverEmail").val();
		var mood = $('input[name=mood]:checked').val();
		var confidence = $('input[name=confidence]:checked').val();
		var greeting = $('input[name=greeting]:checked').val();
		var videos = [];
		$('input[name=video]:checked').each(function() {
			videos.push($(this).val().trim());
		});
		var variables = [];
		variables.push(mood);
		variables.push(confidence);
		variables.push(greeting);
		
		var toSend = {
			"senderEmail" : senderEmail,
			"receiverEmail" : receiverEmail,
			"senderName" : senderName,
			"receiverName" : receiverName,
			"videos" : videos,
			"variables": variables
		};

		$.ajax({
			type : 'POST',
			url : '/rest/send',
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(toSend),
			success : function(data) {
				alert("messaggio inviato? forse... :-|")
			}
		});
		
	}
</script>

</body>
</html>
