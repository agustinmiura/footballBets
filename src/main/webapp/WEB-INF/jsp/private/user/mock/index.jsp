<!--  
 Copyright (C) 2010 Miura Agustín
                    Rozanect Jose  
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.4.2"></script>

<script>
	function callSigninUser() {
		alert('here the ajax request');
		var queryString = $('#loginForm').formSerialize();

		$.post('/hello_ajax.do', queryString, function(data) {
			alert(data);
		});
	}
</script>
</head>
<body>

    <!-- other parts of the JSP -->

    <h2>
        <a href="#">Sign In</a>
    </h2>
    <div>
        <form id="loginForm">

            <label> <span>Email: </span> <input type="text" name="email"
                id="email" />
            </label> <label> <span>Password : </span> <input type="password"
                name="password" id="password" />
            </label> <input type="button" value="Sign In" onClick="callSigninUser()" />
        </form>
    </div>



</body>
</html>