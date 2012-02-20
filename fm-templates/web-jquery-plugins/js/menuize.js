(function($) {
    $.fn.extend({ 
        menuize: function() {
            return this.each(function() {
            	ul = $("<ul/>", {class: 'nav nav-list'}).appendTo($(this));
				li = $("<li/>", {class: 'nav-header', text: 'Google Picasa Web Album Feed'}).appendTo(ul);
				li = $("<li/>").appendTo(ul);
				$("<a/>", {href: 'picasa-albumize.html', text: 'picasa-albumize.js'}).appendTo(li);
            });
        }
    });
})(jQuery);