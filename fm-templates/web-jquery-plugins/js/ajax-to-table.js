(function($) {
    $.fn.extend({ 
        ajaxtotable: function(options) {
        	var defaults = {
				data : null,
				orientation: null,
				props: null
			}

            return this.each(function() {
            	var o = options;
            	
				table = $("<table/>", {class: 'table table-bordered'}).appendTo($(this));
				
				if(o.orientation == 'v') {
					tbody = $("<tbody/>").appendTo(table);
					for(var key in o.props) {
						tr = $("<tr/>").appendTo(tbody);
						th = $("<th/>", {text: o.props[key]}).appendTo(tr);
						td = $("<td/>", {text: o.data[key]}).appendTo(tr);
					}
				} else if(o.orientation == 'h') {
					thead = $("<thead/>").appendTo(table);
					tr = $("<tr/>").appendTo(thead);
					for(var key in o.props) {
						th = $("<th/>", {text: o.props[key]}).appendTo(tr);
					}
					tbody = $("<tbody/>").appendTo(table);
					$(o.data).each(function() {
						tr = $("<tr/>").appendTo(tbody);
						for(var key in o.props) {
							td = $("<td/>", {text: this[key]}).appendTo(tr);
						}
					});
				}
				
            });
        }
    });
})(jQuery);