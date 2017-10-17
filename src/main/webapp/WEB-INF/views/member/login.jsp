<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <!-- contextpath를 가져온다 root전까지-->
<main>
<form action="${path}/login" method="post">
<label>아이디:</label><input type="text" name="username"/><br/>
<label>비밀번호:</label><input type="text" name="password"/><br/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type="submit" value="로그인" />
</form>
</main>

<!-- name는 이미 정해져있다 스프링만든애가 정한거겠지 -->

<!-- 에러해결법 Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-CSRF-TOKEN'. -->
<%-- <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/> --%>