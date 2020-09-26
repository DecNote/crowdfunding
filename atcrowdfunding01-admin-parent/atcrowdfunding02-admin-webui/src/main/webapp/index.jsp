<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%-- 注意base标签中：冒号和尾部斜杠必须有 --%>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
</head>
<body>

<a href="${pageContext.request.contextPath}/test/ssm.html">测试SSM整合环境</a>

<%-- 使用base标签，href属性值前 没有 斜杠 表明参照base路径，有 斜杠 表明不参照base路径 --%>
<br><a href="test/ssm.html">测试SSM整合环境（base标签）</a>

</body>
</html>
