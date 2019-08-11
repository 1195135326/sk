$(document).ready(function () {
    var type = 0;
    $("#btnSubmit").click(function(){
        var json = "{\"userCode\":\""+ $("#userCode").val() +"\",\"userName\":\""+ $("#userName").val() +"\"," +
            "\"loginAccount\":\""+ $("#loginAccount").val() +"\",\"loginPassword\":\""+ $("#loginPassword").val() +"\"," +
            "\"deptName\":\""+ $("#deptName").val() +"\",\"phoneNum\":\""+ $("#phoneNum").val() +"\"," +
            "\"telNum\":\""+ $("#telNum").val() +"\",\"email\":\""+ $("#email").val() +"\"}";
        if(type == 0)
        {
            $.ajax({
                url:"/TaxSoft/user/addUser",
                type:"post",
                data:json,
                contentType:"application/json",
                dataType:"json",
                success:function (data,textStatus) {
                    if(data.sErrorMsg != null)
                    {
                        alert(data.sErrorMsg);
                        return;
                    }
                    $('#myModal').modal('hide');
                    $("#userTable").bootstrapTable('refresh');
                    clearInput();
                }
            })
        }
        else{
            $.ajax({
                url:"/TaxSoft/user/editUser",
                type:"post",
                data:json,
                contentType:"application/json",
                dataType:"json",
                success:function (data,textStatus) {
                    if(data.sErrorMsg != null)
                    {
                        alert(data.sErrorMsg);
                        return;
                    }
                    $('#myModal').modal('hide');
                    $("#userTable").bootstrapTable('refresh');
                    clearInput();
                    type = 0;
                }
            })
        }
    });
    onReflash();
    $("#btnEdit").click(function(){
        var row = getSelectRow();
        if(row == undefined)
        {
            alert("请选中需要修改的行!");
        }
        setInput(row);
        type = 1;
    });
    $("#btnDel").click(function(){
        var row = getSelectRow();
        if(row == undefined)
        {
            alert("请选中需要删除的行!");
        }
        $.ajax({
            url:"/TaxSoft/user/delUser",
            type:"post",
            data:"{\"userCode\":\""+row.fcode+"\"}",
            contentType:"application/json",
            dataType:"json",
            success:function (data,textStatus) {
                if(data.sErrorMsg != null)
                {
                    alert(data.sErrorMsg);
                    return;
                }
                $("#userTable").bootstrapTable('refresh');
            }
        })
    });
});

function onReflash(){
    $('#userTable').bootstrapTable({
        url : '/TaxSoft/user/queryUser', // 请求后台的URL（*）
        method : 'post', // 请求方式（*）
        toolbar : '#toolbar', // 工具按钮用哪个容器
        striped : true, // 是否显示行间隔色
        cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable : false, //用 是否启排序
        // sortOrder : "asc", // 排序方式
        sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
        pagination : true, // 是否显示分页（*）
        pageNumber: 1,    //如果设置了分页，首页页码
        pageSize: 3,                       //每页的记录行数（*）
        pageList: [5,10,20],        //可供选择的每页的行数（*）
        //	onlyInfoPagination:false, //设置为 true 只显示总数据数，而不显示分页
        showRefresh : true, // 是否显示刷新按钮
		// queryParamsType:'',
        // queryParams:function (param) {
        //     var tmp={
        //         pageSize:param.pageSize,
        //         pageIndex:param.pageNumber
        //     };
        //     return tmp;
        // },
        clickToSelect : true, // 是否启用点击选中行
        //	uniqueId : "fileid", // 每一行的唯一标识，一般为主键列
        showToggle : false, // 是否显示详细视图和列表视图的切换按钮
        //	cardView : false, // 是否显示详细视图
        //	detailView : false, // 是否显示父子表
        search:true,   //是否启用搜索框
        onClickRow:function (row,$element) {
            clickRowStyle('userTable',$element);
        },
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
            field : 'fphonenum',
            title : '手机号',
            align: 'center',
            valign: 'middle'

        }, {
            field : 'ftelnum',
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
        responseHandler: function(data){
            return data.rows;
        }
    });
};

function clickRowStyle(tableId,el){
    $('#'+tableId).find('.info').removeClass('info');
    el.addClass('info');
}

function getSelectRow(){
    var index = $('#userTable').find('.info').data('index');
    var row = $('#userTable').bootstrapTable('getData')[index];
    return row;
}

function clearInput(){
    $("#userCode").val('');
    $("#userName").val('');
    $("#loginAccount").val('');
    $("#loginPassword").val('');
    $("#deptName").val('');
    $("#phoneNum").val('');
    $("#telNum").val('');
    $("#email").val('');
}

function setInput(row){
    $("#userCode").val(row.fcode);
    $("#userName").val(row.fname);
    $("#loginAccount").val(row.floginaccount);
    $("#loginPassword").val(row.fpwd);
    $("#deptName").val(row.fdeptname);
    $("#phoneNum").val(row.fphonenum);
    $("#telNum").val(row.ftelnum);
    $("#email").val(row.femail);
}