var ws = null;

function fileupload(){
	var files = document.getElementById("file-multiple-input").files;
	if(files.length == 0){
		$("#nofileAlert").html();
		$("#nofileAlert").html("没有文件，请选择文件");
		$("#nofile").click();
	}
		
	else{
		for(var i = 0 ; i < files.length; i ++){
			var filename= files[i].name;
			var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : ''; 
			if(fileExt != "html"){
				$("#nofileAlert").html();
				$("#nofileAlert").html("文件格式不正确，请选择浏览器导出的html文件");
				$("#nofile").click();
				return ;
			}
		}
		
		connect();
		$("#modal").click();
		$("#result").append("正在导入书签.......");
		
		$("#uploadform").ajaxSubmit(function(data){
			$("#result").append(data);
		});
		
		disconnect();
	}
	
}





function connect() {
	ws = new SockJS("/echo");
	ws.onopen = function() {
		console.log('Info: WebSocket connection opened.');
	};
	ws.onmessage = function(event) {
		$("#result").append(event.data + "&nbsp;");
	};
	ws.onclose = function() {
		console.log('Info: WebSocket connection closed.');
	};
}

function disconnect() {
	if (ws != null) {
		ws.close();
		ws = null;
	}
}

function echo() {
	if (ws != null) {
		var message = document.getElementById('message').value;
		log('Sent: ' + message);
		ws.send(message);
	} else {
		alert('WebSocket connection not established, please connect.');
	}
}



(function( $ ) {

	'use strict';

	/*
	Basic
	*/
	$('.modal-basic').magnificPopup({
		type: 'inline',
		preloader: false,
		modal: true
	});

	/*
	Sizes
	*/
	$('.modal-sizes').magnificPopup({
		type: 'inline',
		preloader: false,
		modal: true
	});

	/*
	Modal with CSS animation
	*/
	$('.modal-with-zoom-anim').magnificPopup({
		type: 'inline',

		fixedContentPos: false,
		fixedBgPos: true,

		overflowY: 'auto',

		closeBtnInside: true,
		preloader: false,
		
		midClick: true,
		removalDelay: 300,
		mainClass: 'my-mfp-zoom-in',
		modal: true
	});

	$('.modal-with-move-anim').magnificPopup({
		type: 'inline',

		fixedContentPos: false,
		fixedBgPos: true,

		overflowY: 'auto',

		closeBtnInside: true,
		preloader: false,
		
		midClick: true,
		removalDelay: 300,
		mainClass: 'my-mfp-slide-bottom',
		modal: true
	});

	/*
	Modal Dismiss
	*/
	$(document).on('click', '.modal-dismiss', function (e) {
		e.preventDefault();
		$.magnificPopup.close();
	});

	/*
	Modal Confirm
	*/
	$(document).on('click', '.modal-confirm', function (e) {
		e.preventDefault();
		$.magnificPopup.close();

		new PNotify({
			title: 'Success!',
			text: 'Modal Confirm Message.',
			type: 'success'
		});
	});

	/*
	Form
	*/
	$('.modal-with-form').magnificPopup({
		type: 'inline',
		preloader: false,
		focus: '#name',
		modal: true,

		// When elemened is focused, some mobile browsers in some cases zoom in
		// It looks not nice, so we disable it:
		callbacks: {
			beforeOpen: function() {
				if($(window).width() < 700) {
					this.st.focus = false;
				} else {
					this.st.focus = '#name';
				}
			}
		}
	});

}).apply( this, [ jQuery ]);