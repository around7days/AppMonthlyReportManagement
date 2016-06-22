/**
 * CSSスタイル変更
 */
function changeStyle(style){
	// フェードアウト後にCSS変更
	$("body").fadeOut("normal", function(){
		$("#mystyle").attr("href", "../../static/fw/bootstrap/css/bootswatch-theme-"+ style +".css");
	});

	// N秒後にフェードイン
	setTimeout(function(){
		$("body").fadeIn("normal");
	},1500);

	// TODO ローカルストレージに保存
	//localStorage.setItem("mystyle", style);
}