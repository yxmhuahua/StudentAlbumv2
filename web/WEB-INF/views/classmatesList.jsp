<%@ page  contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN">
<!-- 引入核心标签库 c -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>

    <title>同学信息列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>

    <!--引入bootstrap的 css和js文件 -->
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
    <!--引入Jquery -->
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!--搭建显示页面-->
<div class="container">

    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h1>花花的同学录</h1>
        </div>
    </div>
    <!--按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-12">
            <button class="btn btn-primary">新增信息</button>
            <button class="btn btn-danger">删除信息</button>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">

            <table class="table table-hover">
                <tr>
                    <!--<th>ID</th>-->
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>


                <c:forEach items="${PageInfo.list}" var="classmates">
                    <tr>
                        <th>${classmates.name}</th>
                        <th>${classmates.gender=="M"?"男":"女"}</th>
                        <th>${classmates.email}</th>

                        <th>
                            <button class="btn btn-info">
                                <span class="glyphicon glyphicon-pencil"></span>
                                编辑
                            </button>
                            <button class="btn btn-warning">
                                <span class="glyphicon glyphicon-trash"></span>
                                删除
                            </button>
                        </th>

                    </tr>

                </c:forEach>




            </table>

        </div>
    </div>
    <!--显示分页信息-->
    <div class="row">
        <!--分页文字信息 -->
        <div class="col-md-6">
            当前${PageInfo.pageNum }页,总${PageInfo.pages } 页,总${PageInfo.total}条记录
        </div>
        <!--分页条信息 -->
        <div class="col-md-6">

            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="#">首页</a></li>
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach items="${PageInfo.navigatepageNums}" var="page_Num" >
                        <c:if test="${page_Num==PageInfo.pageNum}">
                            <li class="active"><a href="#">${page_Num}</a>  </li>
                        </c:if>
                        <c:if test="${page_Num!=PageInfo.pageNum}">
                            <li ><a href="#">${page_Num}</a>  </li>
                        </c:if>
                    </c:forEach>

                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <li><a href="#">尾页</a></li>
                </ul>
            </nav>

        </div>
    </div>

</div>

</body>
</html>
