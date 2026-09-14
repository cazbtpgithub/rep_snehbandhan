

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<html>


<head>
<!-- Google tag (gtag.js) -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-578JZ59RJR"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-578JZ59RJR');
</script>

<script type="text/javascript">
    window.history.forward();

    function noBack() {
      window.history.forward();
    }
  </script>
</head>
	<body  onload="noBack();" onpageshow="if (event.persisted)" noBack();">

	
	
<% session.invalidate(); %>
<% RequestDispatcher dispatcher=request.getRequestDispatcher("/login"); 
System.out.print("logout");
dispatcher.forward(request, response);
%>
</body>
</html>