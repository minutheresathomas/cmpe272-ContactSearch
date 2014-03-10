package com.sjsu.cmpe272.contactsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	private SQLClient db = new SQLClient();
	
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("SearchServlet");
    	String v_name = "";
    	String column = "contactname";
    	Map<String,String> resultParamsMap = new HashMap<String,String>();
    	Map<Integer,Map<String,String>> resultParamsListMap = new HashMap<Integer,Map<String,String>> ();
		//ArrayList<Map<String,String>> resultParamsList = new ArrayList<Map<String,String>>();

    	
    	try {
    		//v_name = (String) request.getAttribute("v_name");
    		v_name = request.getParameter("name");
    		column = request.getParameter("column");
    		if (v_name!= null && !v_name.equalsIgnoreCase(""))
    		{
    			System.out.println("v_name - "+ v_name);
    			System.out.println("column - "+ column);
    			
    			resultParamsListMap =  db.getContactResults(v_name,column);
    			
    			if (resultParamsListMap.isEmpty())
    			{
    				System.out.println("Empty Results");
    			}
    		}
    		else 
    		{
    			System.out.println("V_NAME  = NULL"+ v_name);
    		}
    		
    		//ArrayList<ArrayList<String>> rows = db.getResults(request.getAttribute("v_name").toString() );
    		
    		//request.setAttribute("result",rows);
    		request.setAttribute("columnmsg", "Search Results for Field - " + column );
    		request.setAttribute("result",resultParamsListMap);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
    	
        response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("/contactsearch.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Upload Servlet");
    	
    	try {
					int rows = db.addEmployees();
					String msg = "Added " + rows + " rows.";
					request.setAttribute("msg", msg);
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace(System.err);
		}
    	
        response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("/contactsearch.jsp").forward(request, response);
    }

}