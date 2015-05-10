<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.File"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="java.io.BufferedInputStream" %>
<%@page import="java.io.FileInputStream"%>


<%
		
//request.getRealPath("/")
			String fileName = request.getRealPath("/")+"/jsp/haohao.rar";
			//String dispFileName = new String(request.getParameter("dispFileName").getBytes("iso8859-1"),"utf-8");
			//String dispFileName = java.net.URLEncoder.encode(request.getParameter("dispFileName"), "UTF-8");
			//String dispFileName = java.net.URLEncoder.encode( new String(request.getParameter("dispFileName").getBytes("iso-8859-1"),"utf-8"), "utf-8");
			//dispFileName = dispFileName.replace("+", "%20");
			//String t_ext = dispFileName.substring(dispFileName.lastIndexOf(".") + 1);
			//fileName = fileName + "." +t_ext;
	//System.out.println("========================================================="+t_ext);
	//System.out.println("========================================================="+dispFileName);
			File file = new File(fileName);
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="+new String("haohao.rar".getBytes("gbk"), "ISO8859-1"));
			response.setContentType("application/x-msdownload;charset=utf-8");
			ServletOutputStream    os = null;
			BufferedInputStream    fis = null;
			try	{


			    os  = response.getOutputStream();
			    fis = new BufferedInputStream(new FileInputStream(fileName));

			    byte[] b = new byte[65000];
			    int    i = -1;
			    while ( (i = fis.read(b)) != -1 ){
			       os.write(b, 0, i);
			    }
			    if(fis.read() == -1){
				    if(file.exists() && file.isFile()){
					    //file.delete();
				    }
			    }
			} catch(RuntimeException re){
				throw new Exception(re.getMessage());
			} catch (Exception e){
				throw new Exception(e.getMessage());
			} finally{
				try {
					fis.close();
				    os.flush();
				    os.close();
				} catch (Exception e) {} // ignore

			    response.flushBuffer();
			    out.clear();
			    out = pageContext.pushBody();
			}
			
%>