/**
 * Created by fxp on 2018/2/7.
 */
var $$ = Dom7;

var myApp = new Framework7();

var mainView = myApp.addView('.view-main');

var utils = {
    post: function (data, sf, ef, p) {
        var url = utils.postUrl();
        $$.ajax({
            url: url,
            async: true,
            method: 'POST',
            contentType: 'text/plain',
            crossDomain: true,
            data: data,
            success: function (e) {
                if ("function" == typeof sf)
                    sf(e, p);
            },
            error: function (e) {
                console.log(e);
                if ("function" == typeof ef)
                    ef(e, p);
            }
        });
    },
    get: function (url, sf, ef, p) {
        $$.ajax({
            url: url,
            async: true,
            method: 'GET',
            contentType: 'application/x-www-form-urlencoded',
            crossDomain: true,
            success: function (e) {
                if ('function' == typeof sf)
                    sf(e, p);
            },
            error: function (e) {
                if ('function' == typeof ef)
                    ef(e, p);
            }
        });
    }
}

var expandableList = function () {
    var page, templateStr;

    /*
     * 获取模板文本
     * */
    $$.get("html/list-item-tpl.html", function (data) {
        templateStr = data;
    });

    /*
     * 加载expandable-list.html
     * */
    mainView.router.load(
        {
            url: 'html/expandable-list.html'
        }
    );

    $$(document).on('pageBeforeInit', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.beforeInit(page);
        }
    });

    $$(document).on('pageInit', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.init(page);
        }
    });

    $$(document).on('pageBeforeAnimation', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.beforeAnimation(page);
        }
    });

    $$(document).on('pageBeforeRemove', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.beforeRemove(page);
        }
    });

    function beforeInit(p) {
        page = $$(p.container);

    }

    function init(p) {

        initViews();

        bindEvent('on');
    }

    function beforeAnimation(p) {

    }

    function beforeRemove(p) {

        bindEvent('off');

        destroy();
    }

    function destroy() {
        page = null;

    }

    function bindEvent(method) {
        if (method === 'on' || method === 'off') {

        }
    }

    function initViews() {

    }


    return {
        beforeInit: beforeInit,
        init: init,
        beforeAnimation: beforeAnimation,
        beforeRemove: beforeRemove,
    }
}();