(function($) {
	$.fn.extend({
		picasize : function(options) {
			var defaults = {
				userid : null,
				albumid : null,
				imgsize : 1024,
				thumbsize : '75c',
				startindex : 1,
				results : 6
			}

			var options = $.extend(defaults, options);

			return this.each(function() {
				var gallery = $("#" + this.id);
				var o = options;

				var ul = $("<ul/>", {
					class : 'thumbnails'
				}).appendTo(gallery);
				$.getJSON('http://picasaweb.google.com/data/feed/base/user/'
						+ o.userid + '/albumid/' + o.albumid
						+ '?alt=json&kind=photo&imgmax=' + o.imgsize
						+ '&thumbsize=' + o.thumbsize + '&max-results='
						+ o.results + "&start-index=" + o.startindex, function(
						data) {
					$(data.feed.entry).each(function(i) {
						var content = this.media$group.media$content;
						var thumb = this.media$group.media$thumbnail;
						var li = $("<li/>").appendTo(ul);
						var a = $("<a/>", {
							class : 'thumbnail',
							href : $(content).attr("url")
						}).appendTo(li);
						$("<img/>", {
							src : $(thumb).attr("url")
						}).appendTo(a);
					});
				});

			});
		}
	});

})(jQuery);