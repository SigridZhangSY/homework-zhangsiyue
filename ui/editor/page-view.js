$(document).ready(function () {
    editClick();
    renderClick();
    move('.title');
    move('.text');
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
};

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
};

var move = function (selector) {
    $(selector).mousedown(
        function (event) {
            var isMove = true;
            var y = $(this).offset().top;
            var abs_x = event.pageX - $(this).offset().left;
            var abs_y = event.pageY - $(this).offset().top;
            $(this).mousemove(function (event) {
                    if (isMove) {
                        $(this).css({'left': event.pageX - abs_x, 'top': event.pageY - abs_y, 'position': 'absolute'});
                    }
                }
            ).mouseup(
                function () {
                    isMove = false;
                    if (event.pageY - y > 0)
                        $(this).next().insertBefore(this);
                    else{
                        var other = $(this).prev();
                        $(this).insertBefore(other);
                    }
                    $(this).removeAttr('style')
                }
            );
        }
    );
}
