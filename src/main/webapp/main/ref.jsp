<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- Bootstrap core CSS -->
<link href="<%=request.getContextPath()%>/resources/asserts/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/asserts/datatables/css/dataTables.bootstrap.css" rel="stylesheet">
<%--jQuery--%>
<script type="text/javascript" charset="utf8" src="<%=request.getContextPath()%>/resources/asserts/js/jquery.js"></script>
<%--datatables--%>
<script type="text/javascript" charset="utf8" src="<%=request.getContextPath()%>/resources/asserts/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="<%=request.getContextPath()%>/resources/asserts/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $.extend( $.fn.dataTable.defaults, {
        dom:'<"toolbar">rt<"row"<"col-xs-6"i><"col-xs-6"p>>',
        "pageLength": 20,
        stateSave:  true,
        ordering:  true,
        "processing": true,
        serverSide: true,
        bLengthChange :true,
// 		    "initComplete":initComplete||(function(){}),
        language: {
            url: '<%=request.getContextPath()%>/resources/js/datatables/zh_cn.json'
        }
    } );
</script>