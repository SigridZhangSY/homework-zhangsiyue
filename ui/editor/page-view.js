$(document).ready(function(){
    editClick();
    renderClick();
});

var editClick = function () {
    $('#edit-button').each(function () {
        $(this).click(
            function () {
                $('.title').each(function () {
                    $(this).children('div.component').replaceWith("<div class='component'><textarea class='edit'/></div>");
                    $(this).children('div.component').children('textarea.edit').text($(this).attr("data"));
                });

                $('.text').each(function () {
                    $(this).children('div.component').replaceWith("<div class='component'><textarea class='edit'/></div>");
                    $(this).children('div.component').children('textarea.edit').text($(this).attr("data"));
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
                    var input_value = $(this).children('div.component').children('textarea.edit').val();
                    $(this).attr("data", input_value);
                    $(this).children('div.component').replaceWith("<div class='component'><h1 class='render'>" + $(this).attr("data") + "</h></div>");
                });

                $('.text').each(function () {
                    var input_value = $(this).children('div.component').children('textarea.edit').val();
                    $(this).attr("data", input_value);
                    $(this).children('div.component').replaceWith("<div class='component'><p class='render'>" + $(this).attr("data") + "</p></div>");
                });
            }
        );
    });
}
