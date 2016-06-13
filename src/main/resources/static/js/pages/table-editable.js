
var map = new Object();
// Data Tables - Config
(function($) {

	'use strict';

	// we overwrite initialize of all datatables here
	// because we want to use select2, give search input a bootstrap look
	// keep in mind if you overwrite this fnInitComplete somewhere,
	// you should run the code inside this function to keep functionality.
	//
	// there's no better way to do this at this time :(
	if ( $.isFunction( $.fn[ 'dataTable' ] ) ) {

		$.extend(true, $.fn.dataTable.defaults, {
			sDom: "<'row datatables-header form-inline'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>r><'table-responsive't><'row datatables-footer'<'col-sm-12 col-md-6'i><'col-sm-12 col-md-6'p>>",
			oLanguage: {
				sLengthMenu: '_MENU_ records per page',
				sProcessing: '<i class="fa fa-spinner fa-spin"></i> Loading'
			},
			fnInitComplete: function( settings, json ) {
				// select 2
				if ( $.isFunction( $.fn[ 'select2' ] ) ) {
					$('.dataTables_length select', settings.nTableWrapper).select2({
						minimumResultsForSearch: -1
					});
				}

				var options = $( 'table', settings.nTableWrapper ).data( 'plugin-options' ) || {};

				// search
				var $search = $('.dataTables_filter input', settings.nTableWrapper);

				$search
					.attr({
						placeholder: typeof options.searchPlaceholder !== 'undefined' ? options.searchPlaceholder : 'Search'
					})
					.addClass('form-control');

				if ( $.isFunction( $.fn.placeholder ) ) {
					$search.placeholder();
				}
			}
		});

	}
	
}).apply( this, [ jQuery ]);


/*Datatable - editable*/
(function( $ ) {

	'use strict';

	var EditableTable = {

		options: {
			addButton: '#addToTable',
			table: '#datatable-editable',
			dialog: {
				wrapper: '#dialog',
				cancelButton: '#dialogCancel',
				confirmButton: '#dialogConfirm',
			}
		},
		
		initialize: function() {
			this.setVars()
				.build()
				.events();
		},

		setVars: function() {
			this.$table				= $( this.options.table );
			this.$addButton			= $( this.options.addButton );

			// dialog
			this.dialog				= {};
			this.dialog.$wrapper	= $( this.options.dialog.wrapper );
			this.dialog.$cancel		= $( this.options.dialog.cancelButton );
			this.dialog.$confirm	= $( this.options.dialog.confirmButton );

			return this;
			
		},

		build: function() {
			this.datatable = this.$table.DataTable({
				aoColumns: [
				     { "sWidth": "5%"},
				     { "sWidth": "57%" },
				     { "sWidth": "20%" },
				     { "sWidth": "9%" },
					{ "sWidth": "9%","bSortable": false }
				]
			});
			
			window.dt = this.datatable;

			return this;
		},

		events: function() {
			var _self = this;

			this.$table
				.on('click', 'a.save-row', function( e ) {
					e.preventDefault();

					_self.rowSave( $(this).closest( 'tr' ) );
				})
				.on('click', 'a.cancel-row', function( e ) {
					e.preventDefault();

					_self.rowCancel( $(this).closest( 'tr' ) );
				})
				.on('click', 'a.edit-row', function( e ) {
					e.preventDefault();

					_self.rowEdit( $(this).closest( 'tr' ) );
				})
				.on( 'click', 'a.remove-row', function( e ) {
					e.preventDefault();

					var $row = $(this).closest( 'tr' );
					
					$(document).on('click', '.modal-confirm', function (e) {
						e.preventDefault();
						_self.rowRemove( $row );
						$.magnificPopup.close();

						new PNotify({
							title: 'Success!',
							text: '删除成功',
							type: 'success'
						});
					});
					
					$(document).on('click', '.modal-dismiss', function (e) {
						e.preventDefault();

						$.magnificPopup.close();
					});
					
					$.magnificPopup.open({
						items: {
							src: '#dialog',
							type: 'inline'
						},
						preloader: false,
						modal: true,
						callbacks: {
							change: function() {
								_self.dialog.$confirm.on( 'click', function( e ) {
									e.preventDefault();

									_self.rowRemove( $row );
									$.magnificPopup.close();
								});
							},
							close: function() {
								_self.dialog.$confirm.off( 'click' );
							}
						}
					});
				});

			this.$addButton.on( 'click', function(e) {
				e.preventDefault();

				_self.rowAdd();
			});

			this.dialog.$cancel.on( 'click', function( e ) {
				e.preventDefault();
				$.magnificPopup.close();
			});

			return this;
		},

		// ==========================================================================================
		// ROW FUNCTIONS
		// ==========================================================================================
		rowAdd: function() {
			this.$addButton.attr({ 'disabled': 'disabled' });

			var actions,
				data,
				$row;

			actions = [
				'<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>',
				'<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>',
				'<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>',
				'<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>'
			].join(' ');

			data = this.datatable.row.add([ '', '', '', actions ]);
			$row = this.datatable.row( data[0] ).nodes().to$();

			$row
				.addClass( 'adding' )
				.find( 'td:last' )
				.addClass( 'actions' );

			this.rowEdit( $row );

			this.datatable.order([0,'asc']).draw(); // always show fields
		},

		rowCancel: function( $row ) {
			var _self = this,
				$actions,
				i,
				data;

			if ( $row.hasClass('adding') ) {
				this.rowRemove( $row );
			} else {

				data = this.datatable.row( $row.get(0) ).data();
				this.datatable.row( $row.get(0) ).data( data );

				$actions = $row.find('td.actions');
				if ( $actions.get(0) ) {
					this.rowSetActionsDefault( $row );
				}

				this.datatable.draw();
			}
		},

		rowEdit: function( $row ) {
			var _self = this,
				data;
			
			data = this.datatable.row( $row.get(0) ).data();
			
			var id = data[0];		//书签id
			//console.log("id" + id);

			$row.children( 'td' ).each(function( i ) {
				//id为隐藏列 ，i ++
				//i ++;
				var $this = $( this );
				
				if ( $this.hasClass('actions') ) {
					_self.rowSetActionsEditing( $row );
				} else {
					if( $this.hasClass('select') ){
						
						//计算书签种类td宽度
						var width = $("th:contains('书签种类')").outerWidth() - 16;
						
						var html = '<input type="text" class="form-control input-block" value="' + data[i] + '"/>';
							html += '<div  class="sqgl_droptree form-control input-block " style="width:' + width +'px;height:300px; overflow:auto;display:none"><div id="sqzl"></div></div>';
						
						$this.html(html);
							

						$this.find("input").on("click",function(){
							$this.find("div:first").slideToggle();
						});
						
						$.ajax({  
					        type : "GET",  //提交方式  
					        url : "/sqzl/getall",//路径  
					        success : function(result) {//返回数据根据结果进行相应的处理  
					        	createTree(result,$this);
					        }  
					    });
					}else if( $this.hasClass('bookmarkname') ){
						//console.log("bookmarkname");
						//console.log($this);
						//console.log(data[i]);
						//将id与图标对应存入map中
						map[id] = $("<div>" + data[i] + "</div>").find("img").attr("src");
						
						var bookmarkname = $("<div>" + data[i] + "</div>").find("a").text();
						$this.html( '<input type="text" class="form-control input-block" id="bookmarkname" value="' + bookmarkname + '"/>' );
						
					}else if($this.hasClass('bookmarkid')){
						$this.html( '<input type="text" class="form-control input-block" value="' + data[i] + '"/>' );
					}else{
						$this.html( '<input type="text" class="form-control input-block" value="' + data[i] + '"/>' );
					}
					
				}
			});
		},

		rowSave: function( $row ) {
			var _self     = this,
				$actions,
				values    = [];

			if ( $row.hasClass( 'adding' ) ) {
				this.$addButton.removeAttr( 'disabled' );
				$row.removeClass( 'adding' );
			}
			
			/**取得id*/
			//console.log($row);
			var id = $row.find('td').find("input").first().val();
			
			values = $row.find('td').map(function() {

				var $this = $(this);
				
				if ( $this.hasClass('actions') ) {
					_self.rowSetActionsDefault( $row );
					return _self.datatable.cell( this ).data();
				} else {
					//如果是下拉框，则取下拉框的值
					if($this.find('#selected').length > 0){
						return $.trim( $this.find('#selected').val() );
					}
					//如果是书签名，则加上图表生成html
					if($this.find("#bookmarkname").length > 0){
						console.log("<img" + map[id] + "/><a>" + $this.find('input').val() + "</a>");
						return $.trim("<img src='" + map[id] + "' /><a>" + $this.find('input').val() + "</a>");
					}
					//如果是input，则取input的值
					return $.trim( $this.find('input').val() );
				}
			});
			
			console.log(values);
			
			var datatable = this.datatable;
			$.ajax({  
		        type : "get",  //提交方式  
		        url : "/sqgl/save",//路径  
		        data:{
		        	id:values[0],
		        	name:$("<div>" + values[1] + "</div>").find("a").text(),
		        	sqzl:values[2]
		        },
		        success : function(result) {//返回数据根据结果进行相应的处理  
		        	datatable.row( $row.get(0) ).data( values );
		        }  
		    });
			
			

			$actions = $row.find('td.actions');
			if ( $actions.get(0) ) {
				this.rowSetActionsDefault( $row );
			}

			this.datatable.draw();
		},

		rowRemove: function( $row ) {
			var datatable = this.datatable;
			
			if ( $row.hasClass('adding') ) {
				this.$addButton.removeAttr( 'disabled' );
			}
			/**ajax请求 删除**/
			var id = datatable.row( $row.get(0) ).data()[0];
			$.ajax({  
		        type : "get",  //提交方式  
		        url : "/sqgl/delete",//路径  
		        data:{id:id},
		        success : function(result) {//返回数据根据结果进行相应的处理  
		        	datatable.row( $row.get(0) ).remove().draw();
		        }  
		    });
			
		},

		rowSetActionsEditing: function( $row ) {
			$row.find( '.on-editing' ).removeClass( 'hidden' );
			$row.find( '.on-default' ).addClass( 'hidden' );
		},

		rowSetActionsDefault: function( $row ) {
			$row.find( '.on-editing' ).addClass( 'hidden' );
			$row.find( '.on-default' ).removeClass( 'hidden' );
		}

	};
	$(function() {
		EditableTable.initialize();
	});

}).apply( this, [ jQuery ]);


$(document).ready(function() {
    $('#datatable-editable tbody').on( 'click', 'tr', function () {
    	//alert("click");
    	//console.log($(this));
        $(this).toggleClass('selected');
    } );
});



/**生成目录树**/
function createTree(result,$this){
	result = eval("" + result + ""); 
	var state = new Object();
	state.opened = true;
	state.selected = false;
	
	//为json添加state属性
	for(var index = 0; index　< result.length ; index ++){
		result[index].state =  state;
	}

	
	$this.find("div:last").jstree({
			  "core" : {
			    "animation" : 0,
			    "check_callback" : true,
			    "themes" : { "stripes" : true },
			    'data' : result
			  },
			  "plugins" : [
			    "dnd", "search",
			    "types", "wholerow"
			  ]
		 }).on('select_node.jstree', function (e, data) {
			 $this.find("input").val(data.node.id + "-" +data.node.text);
			 $this.find("div:first").slideToggle();
		 });
	
}