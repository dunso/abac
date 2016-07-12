<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<c:if test="${pagenum>1 }">
<nav>
  <ul class="pagination">
  	<li><a href="javascript:go('subForm','1')">首页</a></li>
    <c:if test="${page>1 }">
	   <li>
	     <a href="javascript:go('subFrom','${page-1 }')" aria-label="Previous">
	       <span aria-hidden="true">&laquo;</span>
	     </a>
	   </li>
    </c:if>
    <c:set var="begin" value="${page-2 }"/>
    <c:set var="end" value="${page+2 }"/>
    <c:if test="${begin <1 }">
    	<c:set var="end" value="${end-begin+1 }"/>
    </c:if>
    <c:if test="${end > pagenum }">
    	<c:set var="end" value="${pagenum }"/>
    	<c:set var="begin" value="${end -5 }"/>
    </c:if>
    <c:if test="${begin < 1 }">
    	<c:set var="begin" value = "1"/>
    </c:if>
    <c:forEach var="i" begin="${begin}" end="${end}">
    	<c:if test="${i >= 1 && i <= pagenum }">
    		<c:if test="${i == page }">
    			<li class = "active"><a href = "javascript:go('subForm','${i }')">第${i }页</a></li>
    		</c:if>
    		<c:if test="${i != page }">
    			<li ><a href = "javascript:go('subForm','${i }')">第${i }页</a></li>
    		</c:if>
    	</c:if>
    </c:forEach> 
    <c:if test="${page < pagenum }">
    <li>
      <a href="javascript:go('subForm','${page+1 }')" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
    <li><a href="javascript:go('subForm','${pagenum}')">尾页</a></li>
  </ul>
</nav>
</c:if>
<script>
function go(formID,page){
	$("#page").val(page);
	$("#"+formID).submit();
	
}
</script>