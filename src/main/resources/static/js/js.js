//客户应用案例轮播图
var dir=1;//每步移动像素，大＝快
var speed=0.3;//循环周期（毫秒）大＝慢
var MyMar=null;

function Marquee(){//正常移动
	var scrollbox = document.getElementById("scrollbox");
	var scrollcopy = document.getElementById("scrollcopy");
	if(dir>0&&(scrollcopy.offsetWidth-scrollbox.scrollLeft)<=0){
		scrollbox.scrollLeft=0;
	}
	if(dir<0 &&(scrollbox.scrollLeft<=0)){
		scrollbox.scrollLeft=scrollcopy.offsetWidth;
	}
		scrollbox.scrollLeft+=dir;
}

function onmouseoverMy(){
	window.clearInterval(MyMar);
}//暂停移动

function onmouseoutMy() {
	MyMar=setInterval(Marquee,speed);
}//继续移动

function r_left(){
	if(dir==-1)
	dir=1;
}//换向左移

function r_right(){
	if(dir==1)
	dir=-1;
}//换向右移

function IsIE(){
	var browser=navigator.appName
	if((browser=="Netscape")){
		return false;
	}
	else if(browser=="Microsoft Internet Explorer"){
		return true;
	}else{
		return null;
	}
}

var _IsIE = IsIE();
var _MousePX = 0;
var _MousePY = 0;
var _DivLeft = 0;
var _DivRight = 0;
var _AllDivWidth = 0;
var _AllDivHeight = 0;

function MoveDiv(e){

	var obj = document.getElementById("scrollbox");
	_MousePX = _IsIE ? (document.body.scrollLeft + event.clientX) : e.pageX;
	_MousePY = _IsIE ? (document.body.scrollTop + event.clientY) : e.pageY;
	//Opera Browser Can Support ''window.event'' and ''e.pageX''
	
	var obj1 = null;

	if(obj.getBoundingClientRect){
		//IE
		obj1 = document.getElementById("scrollbox").getBoundingClientRect();
		_DivLeft = obj1.left;
		_DivRight = obj1.right;
		_AllDivWidth = _DivRight - _DivLeft;
	}else if(document.getBoxObjectFor){
		//FireFox
		obj1 = document.getBoxObjectFor(obj); 
		var borderwidth = (obj.style.borderLeftWidth != null && obj.style.borderLeftWidth != "") ? parseInt(obj.style.borderLeftWidth) : 0;
		_DivLeft = parseInt(obj1.x) - parseInt(borderwidth);
		_AllDivWidth = Cut_Px(obj.style.width);
		_DivRight = _DivLeft + _AllDivWidth;
	}else{
		//Other Browser(Opera)
		_DivLeft = obj.offsetLeft;
		_AllDivWidth = Cut_Px(obj.style.width);
		var parent = obj.offsetParent;
		
		if(parent != obj){
			while (parent){  
				_DivLeft += parent.offsetLeft; 
				parent = parent.offsetParent;
			}
		}
		_DivRight = _DivLeft + _AllDivWidth;
	}

	var pos1,pos2;
	pos1 = parseInt(_AllDivWidth * 0.4) + _DivLeft;
	pos2 = parseInt(_AllDivWidth * 0.6) + _DivLeft;

	if(_MousePX > _DivLeft && _MousePX < _DivRight){
		if(_MousePX > _DivLeft && _MousePX < pos1){
			r_left(); //Move left
		}
		else if(_MousePX < _DivRight && _MousePX > pos2){
			r_right(); //Move right
		}
		if(_MousePX > pos1 && _MousePX < pos2){
			onmouseoverMy(); //Stop
			MyMar=null;
		}else if(_MousePX < pos1 || _MousePX > pos2){
			if(MyMar==null){
				MyMar=setInterval(Marquee,speed);
			}
		}
	}
}

function Cut_Px(cswidth){
	cswidth = cswidth.toLowerCase();
	if(cswidth.indexOf("px") != -1){
		cswidth.replace("px","");
		cswidth = parseInt(cswidth);
	}
	return cswidth;
}

function MoveOutDiv(){
	if(MyMar == null){
		MyMar=setInterval(Marquee,speed);
	}
}
//客户应用案例轮播图 end