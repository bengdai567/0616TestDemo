$.dataTablesSettings = {
    deferRender: true,// 当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
    bStateSave: true,//表格状态保持
    searching: false,//搜索框
    bPaginate: true, // 翻页功能
    bLengthChange: true, // 改变每页显示数据数量
    bFilter: true, // 过滤功能
    bInfo: true,// 页脚信息
    bAutoWidth: false,// 是否自动计算表格各列宽度
    iDisplayLength: 10,
    bProcessing: false,//加载动画
    serverSide: true, // 启用服务器端分页
    ajax: function (data, callback, settings) {
        showLoading();
        // 封装请求参数
        var param = {};
        param.limit = data.length;// 页面显示记录条数，在页面显示每页显示多少项的时候
        param.start = data.start;// 开始的记录序号
        param.page = (data.start / data.length) + 1;// 当前页码
        param.keywords = $("#keywords").val();
        param.park_id = $('#parks_value').val();
        param.status = $('#status').val();
        param.is_old = $('#is_old').val();
        param.type_industry = $('#type_industry').val();
        param.contract_type = $('#contract_type').val();

        var len = data.order.length;
        if (len > 1) {
            // 第一次以id排序
            param.order = "id desc";
        } else {
            // 单排序条件
            if (data.order) {
                if (data.order[0].dir) {
                    var index = data.order[0].column;
                    var name = data.columns[index].name;
                    param.order = name + " " + data.order[0].dir;
                }
            }
        }

        $.ajax({
            type: 'post',
            url: xxx,
            data: param,
            dataType: 'json',
            success: function (res) {
                var returnData = {};
                returnData.draw = parseInt(data.draw);// 这里直接自行返回了draw计数器,应该由后台返回
                returnData.recordsTotal = res.total;
                returnData.recordsFiltered = res.total;// 后台不实现过滤功能，每次查询均视作全部结果
                returnData.data = res.data;
                callback(returnData);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("获取失败");
            }
        });
    },
    columns: [{
        data: function (mdata) {
            var html = '';
            html += '<div class="checkbox"><input name="checks[]" value="' + mdata.id + '" class="select_checkbox" id="checkbox_' + mdata.id + '" type="checkbox"><label for="checkbox_' + mdata.id + '"></label></div>';
            return html;
        },
        orderable: false
    }, {
        data: "pName",
        orderable: false
    }, {
        data: "name",
        name: "name"
    }, {
        data: "cName",
        orderable: false
    }, {
        data: "room",
        orderable: false
    }, {
        data: "legalperson",
        orderable: false
    }, {
        data: "contact_phone",
        orderable: false
    }, {
        data: function (mdata) {
            if (mdata.status == 0) {
                return "<span style='color: #f8ac59'>未执行</span>";
            } else if (mdata.status == 1) {
                return "<span  style='color: #1c84c6'>已执行</span>";
            } else {
                return "<span  style='color: #ed5565'>已终止</span>";
            }
        },
        name: "status"
    }, {
        data: "start_date",
        name: "start_date"
    }, {
        data: "end_date",
        name: "end_date"
    }, {
        data: function (mdata) {
            if (mdata.is_c == 1) {
                return "<span  style='color: #1c84c6'>已续签</span>";
            } else {
                return "<span  style='color: #a8a8a8'>未续签</span>";
            }
        },
        name: "is_c"
    }, {
        data: "area",
        orderable: false
    }, {
        data: function (mdata) {
            return "<span style='color:red;font-weight:bold;'>" + mdata.all_money + "</span>";
        },
        orderable: false
    }, {
        data: "uName",
        orderable: false
    }, {
        data: function (mdata) {
            var html = '';
            var mark = mdata.mark.replace(/\ +/g, "");
            if (mark) {
                var temp = mark.replace(/\n/g, "**");
                html = '<a href="javascript:;" onclick="flag(' + mdata.id + ', \'' + temp + '\')">' + mark + '</a>';
            } else {
                html = '<a href="javascript:;" onclick="flag(' + mdata.id + ',\'' + mark + '\')"><i class="fa fa-flag"></i></a>';
            }
            return html;
        },
        orderable: false
    }, {
        data: function (mdata) {
            var html = '';
            if (is_look == true) {
                html += ' <button type="button" class="btn btn-info btn-xs" onclick="look(' + mdata.id + ')">查看</button>';
            }
            if (is_edit == true && mdata.status == 0) {
                html += ' <button type="button" class="btn btn-primary btn-xs" onclick="edit(' + mdata.id + ')">编辑</button>';
            }
            if (is_change == true && mdata.status == 1) {
                html += ' <button type="button" class="btn btn-success btn-xs" onclick="continues(' + mdata.id + ', ' + mdata.status + ', \'' + mdata.end_date + '\')">续租</button>';
            }
            if (is_end == true && mdata.status != 2) {
                html += ' <button type="button" class="btn btn-danger btn-xs" onclick="endBargain(' + mdata.id + ')">终止</button>';
            }
            html += ' <button type="button" class="btn btn-default btn-xs" onclick="printBargain(' + mdata.id + ')">打印</button>';
            return html;
        },
        orderable: false
    }],
    fnInitComplete: function (oSettings, json) {
        hideLoading();
        //表格渲染完成执行
    },
    drawCallback: function () {
        //表格每画一次执行
    },
    columnDefs: [{
        "orderable": false,
        "targets": 0
    }],
    aaSorting: [[2, 'desc'], [7, 'desc'], [8, 'desc'],[9, 'desc'], [10,'desc']]
};
initTable = $(".dataTables-example").dataTable($.dataTablesSettings);