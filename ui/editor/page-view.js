$(document).ready(function () {
    $('input[type="checkbox"]').adaptiveSwitch();
    addClick();
    move('.title');
    move('.text');
    toggleClick();
});

var toggleClick = function () {
    $('#toggle').change(function () {
        if($(this).prop('checked')){
            $('.title').each(function () {
                $(this).children('div.component').replaceWith("<div class='component'><textarea class='edit'/></div>");
                $(this).children('div.component').children('textarea.edit').text($(this).attr("data"));
            });

            $('.text').each(function () {
                $(this).children('div.component').replaceWith("<div class='component'><textarea class='edit'/></div>");
                $(this).children('div.component').children('textarea.edit').text($(this).attr("data"));
            });
        }else {
            $('.title').each(function () {
                var input_value = $(this).children('div.component').children('textarea.edit').val();
                $(this).attr("data", input_value);
                $(this).children('div.component').replaceWith("<div class='component'><h2 class='render'>" + $(this).attr("data") + "</h2></div>");
            });

            $('.text').each(function () {
                var input_value = $(this).children('div.component').children('textarea.edit').val();
                $(this).attr("data", input_value);
                $(this).children('div.component').replaceWith("<div class='component'><p class='render'>" + $(this).attr("data") + "</p></div>");
            });
        }

    });
    
}

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
                                $(this).css({'left': event.pageX - abs_x, 'top': event.pageY - abs_y, 'position': 'absolute', 'border-radius' : '3px', 'border' : 'dotted 1px'});
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
                var innerHtml = $('.content-area').children(':last-child').prop("outerHTML");
                $('.content-area').append(innerHtml);
                move('.text');
                move('.title');
                toggleClick();
            }
        );
    });
};
