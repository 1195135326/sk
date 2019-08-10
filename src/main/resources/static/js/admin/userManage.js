$(document).ready(function () {
    $("#btnSubmit").click(function(){
        var json = "{\"userCode\":\""+ $("#userCode").val() +"\",\"userName\":\""+ $("#userName").val() +"\"," +
            "\"loginAccount\":\""+ $("#loginAccount").val() +"\",\"loginPassword\":\""+ $("#loginPassword").val() +"\"," +
            "\"deptName\":\""+ $("#deptName").val() +"\",\"phoneNum\":\""+ $("#phoneNum").val() +"\"," +
            "\"telNum\":\""+ $("#telNum").val() +"\",\"email\":\""+ $("#email").val() +"\"}";
        $.ajax({
            url:"/TaxSoft/user/addUser",
            type:"post",
            data:json,
            contentType:"application/json",
            dataType:"json",
            success:function (data,textStatus) {
                alert(data.sErrorMsg+"-status:"+textStatus);
            }
        })
    });

    $('#userTable').bootstrapTable({
        url : '/TaxSoft/user/queryUser', // 请求后台的URL（*）
        method : 'post', // 请求方式（*）
        toolbar : '#toolbar', // 工具按钮用哪个容器
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable : true, //用 是否启排序
        sortOrder : "asc", // 排序方式
        sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
        pagination : true, // 是否显示分页（*）
        pageNumber: 1,    //如果设置了分页，首页页码
        pageSize: 3,                       //每页的记录行数（*）
        pageList: [3,5,6],        //可供选择的每页的行数（*）
        //	onlyInfoPagination:false, //设置为 true 只显示总数据数，而不显示分页
        showRefresh : true, // 是否显示刷新按钮
//		queryParamsType:'',
        clickToSelect : true, // 是否启用点击选中行
        //	uniqueId : "fileid", // 每一行的唯一标识，一般为主键列
        showToggle : false, // 是否显示详细视图和列表视图的切换按钮
        //	cardView : false, // 是否显示详细视图
        //	detailView : false, // 是否显示父子表
        search:true,   //是否启用搜索框

        columns : [ {
            field : 'fcode',
            title : '客户代码',
            align: 'center',
            valign: 'middle'
        },{
            field : 'fname',
            title : '客户名称',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'floginaccount',
            title : '登录账号',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'fdeptname',
            title : '机构名称',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'fphoneNum',
            title : '手机号',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'ftelNum',
            title : '电话号码',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'femail',
            title : '邮箱',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'faddress',
            title : '地址',
            align: 'center',
            valign: 'middle'
        } ],
        silent : true, // 刷新事件必须设置

    });
});