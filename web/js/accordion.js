/*===============================================================================
 ************   Accordion   ************
 ===============================================================================*/
(function () {
    var $ = $$, app = myApp;

    app.accordionOpenv1 = function (item) {
        item = $(item);
        var list = item.parents('.cusv1-accordion-list').eq(0);
        var content = item.children('.accordion-item-content');
        if (content.length === 0) content = item.find('.accordion-item-content');
        var expandedItem = list.length > 0 && item.parent().children('.accordion-item-expanded');
        if (expandedItem.length > 0) {
            // 打开当前item项时，关闭其他item项
            // app.accordionClose(expandedItem);
        }
        content.css('height', content[0].scrollHeight + 'px').transitionEnd(function () {
            if (item.hasClass('accordion-item-expanded')) {
                content.transition(0);
                content.css('height', 'auto');
                var clientLeft = content[0].clientLeft;
                content.transition('');
                item.trigger('opened');
            }
            /* else {
             content.css('height', '');
             item.trigger('closed');
             } */
        });
        item.trigger('open');
        item.addClass('accordion-item-expanded');
    };
    app.accordionClosev1 = function (item) {
        item = $(item);
        var content = item.children('.accordion-item-content');
        if (content.length === 0) content = item.find('.accordion-item-content');
        item.removeClass('accordion-item-expanded');
        content.transition(0);
        content.css('height', content[0].scrollHeight + 'px');
        // Relayout
        var clientLeft = content[0].clientLeft;
        // Close
        content.transition('');
        content.css('height', '').transitionEnd(function () {
            /* if (item.hasClass('accordion-item-expanded')) {
             content.transition(0);
             content.css('height', 'auto');
             var clientLeft = content[0].clientLeft;
             content.transition('');
             item.trigger('opened');
             }
             else {*/
            content.css('height', '');
            item.trigger('closed');
            // }
        });
        item.trigger('close');
    };

})();