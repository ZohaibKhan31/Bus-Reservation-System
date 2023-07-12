<%@page import="in.co.bus.ticket.model.BookModel"%>
<%@page import="in.co.bus.ticket.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.bus.ticket.bean.BookBean"%>
<%@page import="in.co.bus.ticket.controller.BookListCtl"%>
<%@page import="in.co.bus.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booked Ticket List</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<form action="<%=ATBView.BOOK_LIST_CTL%>" method="post">
		<div class="container">
			<div class="row">
				<center>
					<h3>Book Ticket List</h3>
				</center>

				<hr>
				<div class="col-md-12">

					<div class="table-responsive">

						<table class="table table-bordred table-striped">
							<tr>
								<th>Name</th>
								<td><input type="text" name="name"
									placeholder="Search By Name"
									value="<%=ServletUtility.getParameter("name", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<th></th>
								<th>Email Id</th>
								<td><input type="text" name="emailId"
									placeholder="Search By Email Id"
									value="<%=ServletUtility.getParameter("emailId", request)%>"
									class="input-md  textinput textInput form-control"></td>
								<td><input type="submit"
									class="btn btn-primary btn btn-info" name="operation"
									value="<%=BookListCtl.OP_SEARCH%>"></td>
							</tr>
						</table>
						<center>
							<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
							<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
						</center>
						<table id="mytable" class="table table-bordred table-striped">


							<thead>
								<tr>
									<th>Bus Name</th>
									<th>User Name</th>
									<th>Mobile No</th>
									<th>Book Date</th>
									<th>Email Id</th>
									<th>No Of Person</th>
									<th>Ticket Price</th>
									<th>Final Price</th>
									
								</tr>
							</thead>
							<tbody>
								<%
									int pageNo = ServletUtility.getPageNo(request);
									int pageSize = ServletUtility.getPageSize(request);
									int index = ((pageNo - 1) * pageSize) + 1;
									BookBean bean = null;
									List list = ServletUtility.getList(request);
									Iterator<BookBean> it = list.iterator();

									while (it.hasNext()) {

										bean = it.next();
								%>
								<tr>
									
									<td><%=bean.getBusName()%></td>
									<td><%=bean.getFirstName()+" "+bean.getLastName()%></td>
									<td><%=bean.getMobileNo()%></td>
									<td><%=DataUtility.getDateString(bean.getBookDate())%></td>
									<td><%=bean.getEmailId()%></td>
									<td><%=bean.getNoOfPerson()%></td>
									<td><%=bean.getPrice()%></td>
									<td><%=bean.getFinalPrice()%></td>
									
								</tr>
								<%
									}
								%>
							</tbody>

						</table>

						<div class="clearfix"></div>
						<ul class="pagination pull-right">
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BookListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>></li>
							
						
							<%
								BookModel model = new BookModel();
							%>
							<li><input type="submit" name="operation"
								class="btn btn-primary btn btn-info"
								value="<%=BookListCtl.OP_NEXT%>"
								<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></li>
						</ul>

					</div>

				</div>
			</div>
		</div>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<div style="margin-top: 168px">
	<%@ include file="Footer.jsp"%>
</div>	
</body>
</html>