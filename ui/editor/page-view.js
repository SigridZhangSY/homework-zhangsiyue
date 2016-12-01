$(document).ready(function(){
    render();
    editClick();
});

var render = function () {
    $('.title').each(function () {
        $(this).children('div.component').replaceWith("<div class='component'><h1 class='render'>" + $(this).attr("data") + "</h1></div>");
    });

    $('.text').each(function () {
        $(this).children('div.component').replaceWith("<div class='component'><p class='render'>" + $(this).attr("data") + "</p></div>");
    });
};

var editClick = function () {
    $('#edit-button').each(function () {
        $(this).click(
            function () {
                // alert("xxx");
                $('.title').each(function () {
                    $(this).children('div.component').replaceWith("<div class='component'><input class='edit' value='" + $(this).attr("data") + "'/></div>");
                });

                $('.text').each(function () {
                    $(this).children('div.component').replaceWith("<div class='component'><input class='edit' value='" + $(this).attr("data") + "'/></div>");
                });
            }
        );
    });
}
