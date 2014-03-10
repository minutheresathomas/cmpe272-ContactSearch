<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.lang.Integer" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Contacts Search</title>
  <link href="/css/bootstrap.css" rel="stylesheet">
  <style>
    .hero-unit {
      margin-top: 60px;
    }
  </style>
  <script src="/js/bootstrap.min.js"></script>
</head>
<meta charset="utf-8">
<body>
  <div class="container">
    <div class="hero-unit">
      <div class="text-center">
        <h1><a href="/">Contact Search</a></h1>
        <p>
       <!-- <form action="/contactsearch" method="POST" enctype="multipart/form-data">
        <input type="submit" value="Initialize"/>
        </form>
        </p>  -->
        <p>
          <form action="/contactsearch" method="GET" enctype="multipart/form-data">
            Enter Text:
            <input type="text" name="name">
            <select name="column">
  <option selected="selected" value="contactname" >Name</option>
  <option value="emailaddress">Email Address</option>
  <option value="phone">Phone</option>
   <option value="street" >Street</option>
  <option value="city" >City</option>
   <option value="zip">Zip</option>
  <option value="state">State</option>
  <option value="country" >Country</option>
</select>	
		
		
            <input type="submit" class="btn" value="Search" />
          </form>
        </p>
        <% if (request.getAttribute("msg") != null) { %>
        	  <div class="alert alert-info"><%= request.getAttribute("msg") %></div>
        <% } %>
        <% if (request.getAttribute("column") != null) { %>
        	  <div class="alert alert-info"><%= request.getAttribute("columnmsg") %></div>
        <% } %>
      </div>
    </div>
    <div>
      <table class="table">
      <% 
      
  	Map<String,Map<String,String>> resultParamsListMap = (Map<String,Map<String,String>>) request.getAttribute("result");

    //  List resultParamsList = (ArrayList)request.getAttribute("result");
      
      
      
      
  	//Map<String,String> resultParamsMap = (Map<String,String>)request.getAttribute("result");
  	if (resultParamsListMap != null) {
  	        	 
  	// for (Map<String,String> resultParamsMap : resultParamsList)
  	// {
  		//for (Map.Entry  resultParamsMap : resultParamsListMap.entrySet())	
  		//{
  		 
  			Iterator iter = resultParamsListMap.keySet().iterator();
	while(iter.hasNext()) {
    String key = (String)iter.next();
    Map<String,String> resultParamsMap = (Map<String,String>)resultParamsListMap.get(key);
   // System.out.println("key,val: " + key + "," + val);

  			
  		//	Map<String,String> resultParamsMap = (Map<String,String>)o;
  	
  if (resultParamsMap!= null)
  {
	  Iterator iter1 = resultParamsMap.keySet().iterator();
		while(iter1.hasNext()) {
	    String key1 = (String)iter1.next();
	    String value1 = (String)resultParamsMap.get(key1);
  
  
  		//	for (Map.Entry<String,String>  entry : resultParamsMap.entrySet()) {
	 %> <tr><td><% 
	//out.println(entry.getKey() ); 
	 out.println(key1); 
	 %>
	 </td>
	 <td>
    <%out.println(value1);%>
  
    </td></tr><%
		}
	 	
		} %>  <tr><td> <% out.println(" "); %></td><td><% out.println(" ");%> </td></tr> <% %>  <tr><td><% out.println(" "); %></td><td><% out.println(" ");%> </td></tr> <%
  	 }
 
  	}
  	else {
  		 %> <tr><td>Empty Results</td></tr><%
  		
  	}

  	
  
  	
  	
   //      ArrayList<ArrayList<String>> result = (java.util.ArrayList<String>) request.getAttribute("result");
    //     if (request == null) {
     //   	 request = new ArrayList<String>();
     //    }
     //    for (ArrayList<String> row : result) {
      	%>
        	<tr>
        	<%
       //  	for (String cell : row) {
      		%>
        		<td> <%//= cell %> </td>
      		<%// } %>
      	</tr>
      	<% //} %>
      </table>
    </div>
  </div>
</body>
</html>
