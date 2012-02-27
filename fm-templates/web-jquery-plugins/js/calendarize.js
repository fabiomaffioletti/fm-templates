(function($) {
    $.fn.extend({ 
        calendarize: function(options) {
        	var now = new Date();
            var mintime = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + now.getDate() + "T00:00:00Z";
        	
            var defaults = {
                calendarid: null,
                apikey: null,
                results: null,
                mintime: mintime
            }

            var options =  $.extend(defaults, options);

            return this.each(function() {
                var cal = $(this);
                var o = options;

                $.ajax({
                  url: 'https://www.googleapis.com/calendar/v3/calendars/'+o.calendarid+'/events?key='+o.apikey+'&maxResults='+o.results+'&timeMin='+mintime+'&callback=?',
                  type: 'GET',
                  dataType: 'json',
                  success: function(data) {
                   var dl = $("<dl/>").appendTo(cal);
                    $(data.items).each(function(i) {
                        var dt = $("<dt/>").appendTo(dl);
                        $("<dd/>", {text: this.start.dateTime}).appendTo(dt);
                        $("<dd/>", {text: this.summary}).appendTo(dl);
                        $("<dd/>", {text: this.location}).appendTo(dl);
                        $("<dd/>", {text: this.description, class: 'last'}).appendTo(dl);
                    });

                  },
                  error: function(msg) {
                    console.debug(msg);
                  }
                });
             
            });
        }
    });
     
})(jQuery);