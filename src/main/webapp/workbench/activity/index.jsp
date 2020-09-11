<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/en.js"></script>
    <script type="text/javascript">

        $(function () {
            //操作添加模块
            //获取添加按钮弹出添加模块
            $("#addBtn").click(function () {
                $(".time").datetimepicker({
                    minView: "month",
                    language: 'zh-CN',
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    todayBtn: true,
                    pickerPosition: "bottom-left"
                });
                //向服务器发送ajax请求 获取到用户list
                $.ajax({
                    url: "workbench/Activity/getUserList.do",
                    dataType: "json",
                    success: function (data) {
                        //data:userListJason对象

                        var html = "<option>1</option>"
                        $.each(data, function (i, e) {
                            html = html + "<option value='" + e.id + "'>" + e.name + "</option>"
                        })
                        //将拼接的html代码加入到列表框中
                        $("#create-Owner").html(html);
                        var id = "${user.id}"
                        //$("#create-Owner option[value="+id+"]").attr("selected","selected")
                        $("#create-Owner").val(id)
                    }
                })
                //操控模块
                $("#createActivityModal").modal("show");
            })
            //市场活动信息插入操作
            $("#saveBtn").click(function () {

                $.ajax({
                    url: "workbench/Activity/save.do",
                    data: {
                        "id": $.trim($("#create-id").val()),
                        "owner": $.trim($("#create-Owner").val()),
                        "name": $.trim($("#create-name").val()),
                        "startDate": $.trim($("#create-startDate").val()),
                        "endDate": $.trim($("#create-endDate").val()),
                        "cost": $.trim($("#create-cost").val()),
                        "description": $.trim($("#create-description").val())

                    },
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $("#activityAddFrm")[0].reset();
                            $("#createActivityModal").modal("hide");
                            pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
                        } else {
                            alert("添加失败")
                        }
                    }
                })
            })
            //市场活动更新操作update
            $("#updateBtn").click(function () {
                $.ajax({
                    url: "workbench/Activity/update.do",
                    data: {
                        "id": $.trim($("#edit-id").val()),
                        "owner": $.trim($("#edit-Owner").val()),
                        "name": $.trim($("#edit-name").val()),
                        "startDate": $.trim($("#edit-startDate").val()),
                        "endDate": $.trim($("#edit-endDate").val()),
                        "cost": $.trim($("#edit-cost").val()),
                        "description": $.trim($("#edit-description").val())
                    },
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            //$("#activityAddFrm")[0].reset();
                            $("#editActivityModal").modal("hide");
                            pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
                                ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
                        } else {
                            alert("添加失败")
                        }
                    }
                })
            })

            //查询按钮单击事件
            $("#serchBtn").click(function () {

                //在执行查询时将隐藏域中的数据保存起来
                $("#hidden-name").val($.trim($("#serch-name").val()));
                $("#hidden-owner").val($.trim($("#serch-owner").val()));
                $("#hidden-startDate").val($.trim($("#serch-startDate").val()));
                $("#hidden-endDate").val($.trim($("#serch-endDate").val()));
                pageList(1,2);
            });
            pageList(1, 2);
            //复选框按钮单击事件 设置全选
            $("#qx").click(function () {
                $("input[name='xz']").prop("checked",this.checked)
            })

            //要给动态生成的对象绑定事件需要用到 $("有效元素").on("事件"，动态对象，function(){})
            $("#activityTbody").on("click",$("input[name='xz']"),function () {
                $("#qx").prop("checked",$("input[name='xz']").length == $("input[name='xz']:checked").length);
            })

            //市场活动删除
            $("#deleteBtn").click(function () {

                if ($("input[name='xz']:checked").length==0){
                    alert($("input[name='xz']").length)
                    alert("请选择要删除选项")

                }else {
                    if (confirm("您确定要删除吗?")) {
                        //一定有选中的项
                        //获取选中的项循环遍历出id
                        var $xz = $("input[name='xz']:checked")
                        var parm = "";
                        for (var i = 0; i < $xz.length; i++) {
                            parm += "id=" + $($xz[i]).val()
                            //如果不是最后一项在末尾加上&
                            if (i < $xz.length - 1) {
                                parm += "&";
                            }
                        }

                        //通过ajax发送删除请求 url:"workbench/Activity/delete.do?id=xxx&id=xxx"
                        $.ajax({
                            url: "workbench/Activity/delete.do",
                            data: parm,
                            type: "post",
                            dataType: "json",
                            success: function (data) {
                                //data:{"success:true/false"}
                                if (data.success) {
                                    //删除成功后调用pageList局部刷新页面
                                    pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
                                } else {
                                    alert("删除失败")
                                }
                            }
                        })

                    }
                }
            })
                //打开市场活动修改页面
                $("#editBtn").click(function () {

                    $(".time").datetimepicker({
                        minView: "month",
                        language: 'zh-CN',
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        todayBtn: true,
                        pickerPosition: "bottom-left"
                    });

                    var $xz=$("input[name=xz]:checked");
                    if ($xz.length==0){
                        alert("请选择修改的对象")
                    }else if ($xz.length>1){
                        alert("最多只能选择一个对象")
                    }else {
                        //获取需要修改的对象Id
                        var id =$xz.val();
                        //发送ajax 取回userList 和Activity单条 显示到模态窗口中
                        $.ajax({
                            url:"workbench/Activity/selectUlistAndActivity.do",
                            data:{
                                "id":id,
                            },
                            type:"get",
                            dataType:"json",
                            success:function (data) {
                                //data:"ulist":[{}],"activity":对象
                                var html ="<option></option>";
                                $.each(data.uList,function (i,n) {

                                    html+="<option value='"+n.id+"'>"+n.name+"</option>"
                                })
                                $("#edit-Owner").html(html)

                                $("#edit-Owner").val(data.activity.owner)
                                $("#edit-name").val(data.activity.name);
                                $("#edit-startDate").val(data.activity.startDate);
                                $("#edit-endDate").val(data.activity.endDate);
                                $("#edit-cost").val(data.activity.cost);
                                $("#edit-description").val(data.activity.description);
                                $("#edit-id").val(data.activity.id);

                                <%--var id ="${user.id}";--%>
                                <%--$("#edit-Owner").val(id);--%>

                            }
                        })

                        //显示模态窗口
                        $("#editActivityModal").modal("show")

                    }
                })
        });


        function pageList(pageNo, pageSize) {
            //取消选中 全选复选框的√
            $("#qx").prop("checked",false);

            $("#serch-name").val($.trim($("#hidden-name").val()));
            $("#serch-owner").val($.trim($("#hidden-owner").val()));
            $("#serch-startDate").val($.trim($("#hidden-startDate").val()));
            $("#serch-endDate").val($.trim($("#hidden-endDate").val()));

            $.ajax({
                url: "workbench/Activity/pageList.do",
                data: {
                    "pageNo": pageNo,
                    "pageSize": pageSize,
                    "name": $.trim($("#serch-name").val()),
                    "owner": $.trim($("#serch-owner").val()),
                    "startDate": $.trim($("#serch-startDate").val()),
                    "endDate": $.trim($("#serch-endDate").val())

                },
                type: "get",
                dataType: "json",
                success: function (data) {
                    //返回的数据格式： {"total":total,"dataList":[{},{},{}]}

                    var html = "";
                    $.each(data.list,function (i,n) {
                        html += '<tr class="active"> ';
                        html += '<td><input type="checkbox" name="xz" value="'+n.id+'" /></td>';
                        html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/Activity/detail.do?id='+n.id+'\';">'+n.name+'</a></td>';
                        html += '<td>'+n.owner+'</td>';
                        html += '<td>'+n.startDate+'</td>';
                        html += '<td>'+n.endDate+'</td>';
                        html += '</tr>';
                    })
                    $("#activityTbody").html(html);

                    //在pageList.do处理ajax返回值后，加入分页组件
                    var totalPages = data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;
                    $("#activityPage").bs_pagination({
                        currentPage: pageNo, // 页码
                        rowsPerPage: pageSize, // 每页显示的记录条数
                        maxRowsPerPage: 20, // 每页最多显示的记录条数
                        totalPages: totalPages, // 总页数
                        totalRows: data.total, // 总记录条数

                        visiblePageLinks: 3, // 显示几个卡片

                        showGoToPage: true,
                        showRowsPerPage: true,
                        showRowsInfo: true,
                        showRowsDefaultInfo: true,

                        onChangePage : function(event, data){
                            pageList(data.currentPage , data.rowsPerPage);
                        }
                    });
                }
            })

        }
    </script>
</head>
<body>

<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
            </div>
            <div class="modal-body">

                <form id="activityAddFrm" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="createOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="create-Owner">

                            </select>
                        </div>
                        <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-startTime" class="col-sm-2 control-label ">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="create-startDate">
                        </div>
                        <label for="create-endTime" class="col-sm-2 control-label ">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="create-endDate">
                        </div>
                    </div>
                    <div class="form-group">

                        <label for="create-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-describe" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="create-description"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="edit-id">
                    <div class="form-group">
                        <label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-Owner">

                            </select>
                        </div>
                        <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="edit-startDate" >
                        </div>
                        <label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="edit-endDate" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-cost" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-describe" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="edit-description"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="updateBtn">更新</button>
            </div>
        </div>
    </div>
</div>


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>市场活动列表</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
                <input type="hidden" id="hidden-name">
                <input type="hidden" id="hidden-owner">
                <input type="hidden" id="hidden-startDate">
                <input type="hidden" id="hidden-endDate">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">名称</div>
                        <input class="form-control" type="text" id="serch-name">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所有者</div>
                        <input class="form-control" type="text" id="serch-owner">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">开始日期</div>
                        <input class="form-control" type="text" id="serch-startDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">结束日期</div>
                        <input class="form-control" type="text" id="serch-endDate">
                    </div>
                </div>

                <button type="button" id="serchBtn" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span>
                    创建
                </button>
                <button type="button" class="btn btn-default" id="editBtn"><span
                        class="glyphicon glyphicon-pencil"></span> 修改
                </button>
                <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
            </div>

        </div>


        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input type="checkbox" id="qx"/></td>
                    <td>名称</td>
                    <td>所有者</td>
                    <td>开始日期</td>
                    <td>结束日期</td>
                </tr>
                </thead>
                <tbody id="activityTbody">
                <%--<tr class="active">
                    <td><input type="checkbox" /></td>
                    <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                    <td>zhangsan</td>
                    <td>2020-10-10</td>
                    <td>2020-10-20</td>
                </tr>
                <tr class="active">
                    <td><input type="checkbox" /></td>
                    <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                    <td>zhangsan</td>
                    <td>2020-10-10</td>
                    <td>2020-10-20</td>
                </tr>
            </tbody>--%>
            </table>
        </div>

        <div style="height: 50px; position: relative;top: 30px;">
            <div id="activityPage">

            </div>
        </div>

    </div>

</div>
</body>
</html>