<%@ include file="/common/taglibs.jsp"%>

<head>
	<script type="text/javascript" src="/javascript/jquery-1.5.2.js"></script>
</head>

<ul id="left-nav">
	<li class="title">Categories</li>
	<li><a href="/list-category.html">Category list</a></li>
	<li class="last"><a href="/edit-category.html">Add new category</a></li>
	
	<li class="title">Difficulties</li>
	<li><a href="/list-difficulty.html">Difficulty list</a></li>
	<li class="last"><a href="/edit-difficulty.html">Add new difficulty</a></li>
	
	<li class="title">Variables</li>
	<li><a href="/list-variable.html">Variable list</a></li>
	<li class="last"><a href="/edit-variable.html">New variable</a></li>
	
	<li class="title">Videos</li>
	<li>
		<a href="#" id="list-video-button">Video list</a>
		<select id="list-video-language" style="margin-left:3px;">
		</select>
	</li>
	<li class="last">
		<a href="#" id="edit-video-button">Add new video</a>
		<select id="edit-video-language" style="margin-left:3px;">
		</select>
	</li>
	
	<li class="title">Supports</li>
	<li class="last"><a href="/list-support.html">Support list</a></li>
	
	<li class="title">Blacklist</li>
	<li><a href="/list-blacklist.html">View blacklist</a></li>
	<li class="last"><a href="/edit-blacklist.html">Blacklist email</a></li>
	
	<li class="title">Unsubscribed</li>
	<li><a href="/list-unsubscribed.html">View unsubscribed</a></li>
	<li class="last"><a href="/edit-unsubscribed.html">Unsubscribe email</a></li>
	
	<li class="title">Caches</li>
	<li class="last"><a href="/list-cache.html">Cache list</a></li>
	
	<li class="title">Users</li>
	<li><a href="/list-user.html">User list</a></li>
	<li class="last"><a href="/edit-user.html">Add new user</a></li>
	
	<li class="title">Logout</li>
	<li class="last"><a href="/logout.jsp">Logout</a></li>
</ul>

<script>
	$(document).ready(function() {
		$.getJSON("/languages.html", function(data) {
			$.each(data, function(key, val) {
				$('#edit-video-language').append('<option value=\"' + val.isoShort + '\">'+val.isoShort+'</option>');
				$('#list-video-language').append('<option value=\"' + val.isoShort + '\">'+val.isoShort+'</option>');
			});
		});
		
		$('#edit-video-button').click(function() {
			document.location.href = '/edit-video.html?language='+$('#edit-video-language').val();
		});
		
		$('#list-video-button').click(function() {
			document.location.href = '/list-video.html?language='+$('#list-video-language').val();
		});
	});
</script>