//获取元素距离页面边缘的距离
function getOffset(box,direction){
	
	var setDirection =  (direction == 'top') ? 'offsetTop' : 'offsetLeft' ;
	
	var offset =  box[setDirection];
	
	var parentBox = box.offsetParent;
	while(parentBox){
		
		offset+=parentBox[setDirection];
		parentBox = parentBox.offsetParent;
	}
	parentBox = null;
	
	return parseInt(offset);
}

function moveCode(code){

	var fn = {codeVluae : code};

	var box = document.querySelector("#code-box"),
			progress = box.querySelector("p"),
			codeInput = box.querySelector('.code-input'),
			evenBox = box.querySelector("span");

	//默认事件
	var boxEven = ['mousedown','mousemove','mouseup'];
	//改变手机端与pc事件类型
	if(typeof document.ontouchstart == 'object'){

		boxEven = ['touchstart','touchmove','touchend'];
	}

	var goX,offsetLeft,deviation,evenWidth,endX;

	function moveFn(e){

		e.preventDefault();
		e = (boxEven['0'] == 'touchstart') ? e.touches[0] : e || window.event;
		
		
		endX = e.clientX - goX;
		endX = (endX > 0) ? (endX > evenWidth) ? evenWidth : endX : 0;
	
		if(endX > evenWidth * 0.7){
			
			progress.innerText = '松开验证';
			progress.style.backgroundColor = "#66CC66";
		}else{
			
			progress.innerText = '';
			progress.style.backgroundColor = "#FFFF99";
		}

		progress.style.width = endX+deviation+'px';
		evenBox.style.left = endX+'px';
	}

	function removeFn() {

		document.removeEventListener(boxEven['2'],removeFn,false);
		document.removeEventListener(boxEven['1'],moveFn,false);

		if(endX > evenWidth * 0.7){
			
			progress.innerText = '验证成功';
			progress.style.width = evenWidth+deviation+'px';
			evenBox.style.left = evenWidth+'px'
			
			codeInput.value = fn.codeVluae;
			evenBox.onmousedown = null;
		}else{

			progress.style.width = '0px';
			evenBox.style.left = '0px';
		}
	}

	evenBox.addEventListener(boxEven['0'], function(e) {

		e = (boxEven['0'] == 'touchstart') ? e.touches[0] : e || window.event;

			goX = e.clientX,
				offsetLeft = getOffset(box,'left'),
				deviation = this.clientWidth,
				evenWidth = box.clientWidth - deviation,
				endX;

		document.addEventListener(boxEven['1'],moveFn,false);

		document.addEventListener(boxEven['2'],removeFn,false);
	},false);
	
	fn.setCode = function(code){

		if(code)
			fn.codeVluae = code;
	}

	fn.getCode = function(){

		return fn.codeVluae;
	}

	fn.resetCode = function(){

		evenBox.removeAttribute('style');
		progress.removeAttribute('style');
		codeInput.value = '';
	};

	return fn;
}

function formCheck(){
    var box = document.querySelector("#code-box"),
        progress = box.querySelector("p");
    if('验证成功' != progress.innerText)
    {
        alert("验证失败");
        return false;
    }
    else
    {
        return true;
    }
}

function onExit() {
	$("#accountName").css('visibility','hidden');
	$("#exit").css('visibility','hidden');
	$("#btnlogin").css('visibility','unset');
}

function submitForm() {
	// jquery 表单提交
	$("#loginUser").ajaxSubmit(function(data) {
		// 对于表单提交成功后处理，message为表单正常提交后返回的内容
		if(data.sErrorMsg == null){
			$('#loginAccount').val('');
			$('#loginPwd').val('');
			var box = document.querySelector("#code-box"),
				progress = box.querySelector("p"),
				codeInput = box.querySelector('.code-input'),
				evenBox = box.querySelector("span");
			progress.innerText = '';
			progress.style.backgroundColor = "#FFFF99";
			progress.style.width = '0px';
			evenBox.style.left = '0px';
			$('#myModal').modal('hide');
			$("#accountName").css('visibility','unset');
			$("#exit").css('visibility','unset');
			$("#btnlogin").css('visibility','hidden');
			$("#accountName").html(data.obj.userName);

		}else{
			alert(data.sErrorMsg);
		}
	});
	return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}
window.addEventListener('load',function(){
	var code = "";
	$.ajax({
		url:"/TaxSoft/user/randomCode",
		type:"get",
		data:"",
		contentType:"application/json",
		dataType:"json",
		success:function (data,textStatus) {
			code = data;
			codeFn = new moveCode(code);
		}
	});
	//code是后台传入的验证字符串

	//获取当前的code值
	//console.log(codeFn.getCode());

	//改变code值
	//code = '46asd546as5';
	//codeFn.setCode(code);

	//重置为初始状态
	//codeFn.resetCode();
});