/**
 * Created by fxp on 2018/2/7.
 */
var $$ = Dom7;

var myApp = new Framework7();

var mainView = myApp.addView('.view-main');

var expandableList = function () {
    var page,                   // 页面DOM
        templateStr,            // 列表模板html
        imageDir = "images/";   // 图片路径

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

    /**
     * 页面生命周期监听-pageBeforeInit
     */
    $$(document).on('pageBeforeInit', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.beforeInit(page);
        }
    });

    /**
     * 页面生命周期监听-pageInit
     */
    $$(document).on('pageInit', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.init(page);
        }
    });

    /**
     * 页面生命周期监听-pageBeforeAnimation
     */
    $$(document).on('pageBeforeAnimation', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.beforeAnimation(page);
        }
    });

    /**
     * 页面生命周期监听-pageBeforeRemove
     */
    $$(document).on('pageBeforeRemove', function (e) {
        let page = e.detail.page;
        if (page.name === 'expandable-list') {
            expandableList.beforeRemove(page);
        }
    });

    /**
     * pageBeforeInit回调
     * @param {*} p 
     */
    function beforeInit(p) {
        page = $$(p.container);

    }

    /**
     * pageInit回调
     * @param {*} p 
     */
    function init(p) {

        initViews();

        bindEvent('on');
    }

    /**
     * pageBeforeAnimation回调
     * @param {*} p 
     */
    function beforeAnimation(p) {

    }

    /**
     * pageBeforeRemove回调
     * @param {*} p 
     */
    function beforeRemove(p) {

        bindEvent('off');

        destroy();
    }

    /**
     * 绑定/卸载事件
     * @param {*} method 
     */
    function bindEvent(method) {
        if (method === 'on' || method === 'off') {
            page[method]("click",".accordion_outer_equip_name",listStateHandler);
        }
    }

    function initViews() {
        utils.get("data/list.json",function(res){
            var data = JSON.parse(res).data.data;
            rendList(data);
        },function(error){

        });
    }

    /**
     * 组装、渲染列表
     * @param {*二级列表数据} items 
     */
    function rendList(items) {
        var datas, htmlstr = '', accordionList;
        datas = {
            equips: items,
            img_dir: imageDir
        };
        try {
            htmlstr = juicer(templateStr, datas);
            accordionList = page.find("#accordion-list");
            accordionList.html(htmlstr);
        } catch (error) {
            console.log(error);
        }
    }

    /*
    * 二级列表打开／收缩事件
    * */
    function listStateHandler(e) {
        let item = $$(this).parents(".cusv1-accordion-item");
        if (item.hasClass("accordion-item-expanded")) {
            myApp.accordionClosev1(item);
        } else {
            myApp.accordionOpenv1(item);
        }
    }

    function destroy() {
        page = null;

    }

    return {
        beforeInit: beforeInit,
        init: init,
        beforeAnimation: beforeAnimation,
        beforeRemove: beforeRemove,
    }
}();

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
