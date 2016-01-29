(function($){
	
	$.ui = {
		'confirm' : confirm,
		'alert' : alert
	};
	
	var dialog1Tpl = ''
		+ '<div class="weui_dialog">'
		+    '<div class="weui_dialog_hd"><strong class="weui_dialog_title">${title}</strong></div>'
		+    '<div class="weui_dialog_bd">${content}</div>'
		+    '<div class="weui_dialog_ft">'
		+        '<a href="javascript:;" class="weui_btn_dialog default ui-confirm-cancel">取消</a>'
		+        '<a href="javascript:;" class="weui_btn_dialog primary ui-confirm-ok">确定</a>'
		+    '</div>'
		+ '</div>'
		+ '';
	
	var dialog2Tpl = ''
		+ '<div class="weui_dialog">'
		+ 	'<div class="weui_dialog_hd"><strong class="weui_dialog_title">${title}</strong></div>'
		+	'<div class="weui_dialog_bd">${content}</div>'
		+	'<div class="weui_dialog_ft">'
		+    	'<a href="javascript:;" class="weui_btn_dialog primary ui-alert-ok">确定</a>'
		+	'</div>'
		+ '</div>'
		+ '';
	
	function confirm(title, content, cancelCallback, okCallback) {
		uiMask();
		
		var dialog = $( 
				dialog1Tpl.replace(/\${title}/g, title)
						  .replace(/\${content}/g, content)
			).appendTo($('body'));
		
		$('.weui_dialog .ui-confirm-cancel').on('click', function(){
			if ( typeof cancelCallback === 'function' ) cancelCallback();
			dialogRemove(dialog);
		});
		
		$('.weui_dialog .ui-confirm-ok').on('click', function(){
			if ( typeof okCallback === 'function' ) okCallback();
			dialogRemove(dialog);
		});
		
		
	}
	
	function alert(title, content, callback) {
		uiMask();
		
		var dialog = $(
				dialog2Tpl.replace(/\${title}/g, title)
						  .replace(/\${content}/g, content)
			).appendTo($('body'));
		
		$('.weui_dialog .ui-alert-ok').on('click', function(){
			if ( typeof callback === 'function' ) callback();
			dialogRemove(dialog);
		});
		
	}
	
	// 阴影层
	function uiMask() {
		$('<div class="weui_mask"></div>').appendTo($('body'));
	}
	
	// dialog 移除
	function dialogRemove(dialog) {
		dialog.remove();
		$('.weui_mask').remove();
	}
	
})(Zepto);

