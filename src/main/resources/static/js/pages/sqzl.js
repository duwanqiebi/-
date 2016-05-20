var sqzlJson;

$(function () { 
	
	$.ajax({  
        type : "GET",  //提交方式  
        url : "/sqzl/getall",//路径  
        success : function(result) {//返回数据根据结果进行相应的处理  
        	createTree(result);
        }  
    }); 
	
	
	
});


/**生成目录树**/
function createTree(result){
	result = eval("" + result + "");  
	
	$('#sqzl')
		.jstree({
			  "core" : {
			    "animation" : 0,
			    "check_callback" : true,
			    "themes" : { "stripes" : true },
			    'data' : result
			  },
			  "plugins" : [
			    "contextmenu","dnd", "search",
			    "state", "types", "wholerow"
			  ]
		 })
		 .on('create_node.jstree', function (e, data) {
				$.get('/sqzl/tree/create_node', { 'id' : data.node.parent, 'position' : data.position, 'text' : data.node.text })
					.done(function (d) {
						d = eval("[" + d + "]");  
						data.instance.set_id(data.node, d[0].id);
					})
					.fail(function () {
						data.instance.refresh();
					});
		})
		.on('rename_node.jstree', function (e, data) {
				$.get('/sqzl/tree/rename_node', { 'id' : data.node.id, 'text' : data.node.text })
					.fail(function () {
						data.instance.refresh();
					});
		})
		.on('delete_node.jstree', function (e, data) {
				$.get('/sqzl/tree/delete_node', { 'id' : data.node.id, 'text' : data.node.text  })
					.fail(function () {
						data.instance.refresh();
					});
		})
		.on('move_node.jstree', function (e, data) {
				$.get('/sqzl/tree/move_node', { 'id' : data.node.id, 'parent' : data.parent, 'position' : data.position })
					.fail(function () {
						data.instance.refresh();
					});
		});
}

/**创建节点*/
function create() {
	var ref = $('#sqzl').jstree(true),
		sel = ref.get_selected();
	if(!sel.length) { return false; }
	sel = sel[0];
	sel = ref.create_node(sel, {"type":"file"});
	if(sel) {
		ref.edit(sel);
	}
};