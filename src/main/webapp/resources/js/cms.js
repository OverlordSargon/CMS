
// Options
var options = {
    offset: 80
};

// Create a new instance of Headhesive.js and pass in some options
var header = new Headhesive('header', options);

$(document).before(function() {
    var windowH = $(window).height();
    var bodyH = $('body').height();
    if( windowH > bodyH) {
        $('body').css({'height':(windowH-30)+'px'});
        $('footer').css({'width':'85%','position':'absolute','bottom':'0'});
    }
});