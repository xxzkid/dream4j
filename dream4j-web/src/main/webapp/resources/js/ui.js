(function($){
    
    $.ui = {
        'confirm' : confirm,
        'alert' : alert,
        'toast' : toast,
        'loadingShow' : loadingShow,
        'loadingHide' : loadingHide,
        'actionsheet' : actionsheet
    };
    
    var confirmTpl = ''
        + '<div class="weui_dialog">'
        +    '<div class="weui_dialog_hd"><strong class="weui_dialog_title">${title}</strong></div>'
        +    '<div class="weui_dialog_bd">${content}</div>'
        +    '<div class="weui_dialog_ft">'
        +        '<a href="javascript:;" class="weui_btn_dialog default ui-confirm-cancel">取消</a>'
        +        '<a href="javascript:;" class="weui_btn_dialog primary ui-confirm-ok">确定</a>'
        +    '</div>'
        + '</div>'
        + '';
    
    var alertTpl = ''
        + '<div class="weui_dialog">'
        +   '<div class="weui_dialog_hd"><strong class="weui_dialog_title">${title}</strong></div>'
        +   '<div class="weui_dialog_bd">${content}</div>'
        +   '<div class="weui_dialog_ft">'
        +       '<a href="javascript:;" class="weui_btn_dialog primary ui-alert-ok">确定</a>'
        +   '</div>'
        + '</div>'
        + '';
    
    var toastTpl = ''
        + '<div class="weui_toast">'
        +    '<i class="weui_icon_toast"></i>'
        +    '<p class="weui_toast_content">${content}</p>'
        + '</div>'
        + '';
    
    var loadingTpl = ''
        + '<div id="loadingToast" class="weui_loading_toast">'
        +   '<div class="weui_mask_transparent"></div>'
        +   '<div class="weui_toast">'
        +       '<div class="weui_loading">'
        +           '<div class="weui_loading_leaf weui_loading_leaf_0"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_1"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_2"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_3"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_4"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_5"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_6"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_7"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_8"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_9"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_10"></div>'
        +           '<div class="weui_loading_leaf weui_loading_leaf_11"></div>'
        +       '</div>'
        +       '<p class="weui_toast_content">数据加载中</p>'
        +   '</div>'
        + '</div>'
        + '';

    var actionsheetTpl = ''
        + '<div id="actionSheet_wrap" style="display:none">'
        + '<div class="weui_mask_transition" id="mask"></div>'
        + '<div class="weui_actionsheet" id="weui_actionsheet">'
        +    '<div class="weui_actionsheet_menu">'
        +    '</div>'
        +    '<div class="weui_actionsheet_action">'
        +        '<div class="weui_actionsheet_cell" id="actionsheet_cancel">取消</div>'
        +    '</div>'
        + '</div>'
        + '</div>'
        + '';

    
    // 确定取消框
    function confirm(title, content, okCallback, cancelCallback) {
        uiMask();
        
        var dialog = $( 
                confirmTpl.replace(/\${title}/g, title)
                          .replace(/\${content}/g, content)
            ).appendTo( $('body') );
        
        $('.weui_dialog .ui-confirm-cancel').on('click', function(){
            if ( typeof cancelCallback === 'function' ) cancelCallback();
            dialogRemove(dialog);
        });
        
        $('.weui_dialog .ui-confirm-ok').on('click', function(){
            if ( typeof okCallback === 'function' ) okCallback();
            dialogRemove(dialog);
        });
        
        
    }
    
    // 确定框
    function alert(title, content, okCallback) {
        uiMask();
        
        var dialog = $(
                alertTpl.replace(/\${title}/g, title)
                          .replace(/\${content}/g, content)
            ).appendTo( $('body') );
        
        $('.weui_dialog .ui-alert-ok').on('click', function(){
            if ( typeof okCallback === 'function' ) okCallback();
            dialogRemove(dialog);
        });
        
    }
    
    function toast(content, second) {
        var toast = $(
                toastTpl.replace(/\${content}/g, content)
            ).appendTo( $('body') );
        
        setTimeout( function(){
            dialogRemove(toast);
        }, $.trim(second) == '' ? 2000 : parseInt(second) * 1000 );
        
    }
    
    function loadingShow() {
        $( loadingTpl ).appendTo( $('body') );
    }
    
    function loadingHide() {
        $('#loadingToast').remove();
    }

    function actionsheet(data, itemClick) {
        $( actionsheetTpl ).appendTo( $('body') );

        var actionsheet = $('#actionSheet_wrap').show();

        var mask = $('#mask');
        var weuiActionsheet = $('#weui_actionsheet');
        weuiActionsheet.addClass('weui_actionsheet_toggle');
        mask.show().addClass('weui_fade_toggle').click(function () {
            hideActionSheet(weuiActionsheet, mask);
            actionsheet.remove();
        });
        // 去掉按钮绑定
        $('#actionsheet_cancel').click(function () {
            hideActionSheet(weuiActionsheet, mask);
        });
        weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');
        
        // 生成选项
        var menu = $('#weui_actionsheet .weui_actionsheet_menu');
        $.each(data, function(index, node){
            var item = '<div class="weui_actionsheet_cell" data-id="'+ node.id +'">' + node.name + '</div>';
            menu.append(item);
        });
        
        // 每一个选项绑定事件
        $('#weui_actionsheet .weui_actionsheet_menu .weui_actionsheet_cell').on('click', function(){
            var id = $(this).attr('data-id');
            var name = $(this).text();
            itemClick(id, name);
            hideActionSheet(weuiActionsheet, mask);
        });
        
        // 隐藏
        function hideActionSheet(weuiActionsheet, mask) {
            weuiActionsheet.removeClass('weui_actionsheet_toggle');
            mask.removeClass('weui_fade_toggle');
            weuiActionsheet.on('transitionend', function() {
                mask.hide();
            }).on('webkitTransitionEnd', function() {
                mask.hide();
            });
            actionsheet.remove();
        };
    }
    
    // 阴影层
    function uiMask() {
        return $('<div class="weui_mask"></div>').appendTo($('body'));
    }
    
    // dialog 移除
    function dialogRemove(dialog) {
        dialog.remove();
        $('.weui_mask').remove();
    }
    
})(this.jQuery || this.Zepto);
