<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <!-- contextpath�� �����´� root������-->
<main>
<form action="${path}/login" method="post">
<label>���̵�:</label><input type="text" name="username"/><br/>
<label>��й�ȣ:</label><input type="text" name="password"/><br/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type="submit" value="�α���" />
</form>
</main>

<!-- name�� �̹� �������ִ� ����������ְ� ���ѰŰ��� -->

<!-- �����ذ�� Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'. -->
<%-- <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/> --%>