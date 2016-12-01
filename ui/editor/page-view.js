$(document).ready(function(){
    render();
    editClick();
    renderClick();
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

var renderClick = function () {
    $('#render-button').each(function () {
        $(this).click(
            function () {
                $('.title').each(function () {
                    var input_value = $(this).children('div.component').children('input.edit').val();
                    $(this).attr("data", input_value);
                    $(this).children('div.component').replaceWith("<div class='component'><h1 class='render'>" + $(this).attr("data") + "</h></div>");
                });

                $('.text').each(function () {
                    var input_value = $(this).children('div.component').children('input.edit').val();
                    $(this).attr("data", input_value);
                    $(this).children('div.component').replaceWith("<div class='component'><p class='render'>" + $(this).attr("data") + "</p></div>");
                });
            }
        );
    });
}
