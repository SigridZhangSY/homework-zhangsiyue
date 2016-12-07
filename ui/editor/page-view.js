$(document).ready(function () {
    editClick();
    renderClick();
    addClick();
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
    $(selector).each(function () {
            $(this).mousedown(
                function (event) {
                    var isMove = true;
                    var y = $(this).offset().top;
                    var abs_x = event.pageX - $(this).offset().left;
                    var abs_y = event.pageY - $(this).offset().top;
                    console.log(this.className)
                    $(this).mousemove(function (event) {
                            if (isMove) {
                                $(this).css({'left': event.pageX - abs_x, 'top': event.pageY - abs_y, 'position': 'absolute'});
                            }
                        }
                    ).mouseup(
                        function () {
                            isMove = false;
                            var next_bottom = $(this).nextAll().length !== 0 ? $(this).next().offset().top + $(this).next().height() : -1;
                            var pre_top = $(this).prevAll().length !== 0 ? $(this).prev().offset().top : -1;
                            if ($(this).offset().top - y > 0 && next_bottom >= 0 && $(this).offset().top > next_bottom)
                                $(this).next().insertBefore(this);
                            if ($(this).offset().top - y < 0 && pre_top >= 0 && $(this).offset().top < pre_top)
                                $(this).insertBefore($(this).prev());
                            $(this).removeAttr('style');
                        }
                    );
                }
            )
        }
    );
};

var addClick = function () {
    $('#add-button').each(function () {
        $(this).click(
            function () {
                var innerHtml = '<li class="text area add" data="content"><div class="component"><p class="render">content</p></div></li>';
                $('.content-area').append(innerHtml);
            }
        );
    });
}
