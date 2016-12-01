$(document).ready(function(){
    view();
});

var view = function () {
    $('.title').each(function (title) {
        $(this).children('div.component').replaceWith("<div class='component'><h1>" + $(this).attr("data") + "</h1></div>");
    });

    $('.text').each(function (text) {
        $(this).children('div.component').replaceWith("<div class='component'>" + $(this).attr("data") + "</div>");
    });
};
