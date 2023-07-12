<%@page import="java.util.Date"%>
<%@page import="in.co.bus.ticket.controller.BookCtl"%>
<%@page import="in.co.bus.ticket.util.DataUtility"%>
<%@page import="in.co.bus.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Ticket</title>
</head>
<body>
<%@ include file="Header.jsp"%>

	<div class="container">

		<div id="signupbox" style="margin-top: 50px"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Book Ticket</div>
					<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b>
				</div>
				<div class="panel-body">
					<form method="post" action="<%=ATBView.BOOK_CTL%>">
						<jsp:useBean id="bean"
							class="in.co.bus.ticket.bean.BookBean" scope="request"></jsp:useBean>

						<% Long fId=(Long)session.getAttribute("boId"); %>
						
						<input type="hidden" name="flightId" value="<%=fId%>">
						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Email Id
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="emailId" placeholder="Enter emailId"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getEmailId())%>" /> <font
									color="red"><%=ServletUtility.getErrorMessage("emailId", request)%></font>
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Mobile No.
							<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="mobile" placeholder="Enter Mobile No"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getMobileNo())%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
							</div>
						</div>

						
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Date
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="bookDate" placeholder="Enter Date" readonly="readonly"
									style="margin-bottom: 10px" type="text" id="#"
									value="<%=DataUtility.getDateString(new Date())%>" /> <font
									color="red"><%=ServletUtility.getErrorMessage("bookDate", request)%></font>
							</div>
						</div>
						
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">No Of Person
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="noP" placeholder="Enter No of Person"
									style="margin-bottom: 10px" type="text"
									value="<%=(bean.getNoOfPerson()==0)?1:bean.getNoOfPerson()%>" /> <font
									color="red"><%=ServletUtility.getErrorMessage("noP", request)%></font>
							</div>
						</div>
						
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Address<span
								class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<textarea rows="4" cols="5" name="address"
									placeholder="Your Decription "
									class="input-md textinput textInput form-control"
									style="margin-bottom: 10px"><%=DataUtility.getStringData(bean.getAddress())%></textarea>
								<font color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
							</div>
						</div>




						<div class="form-group">
							<div class="aab controls col-md-4 "></div>
							<div class="controls col-md-8 ">
								<input type="submit" name="operation"
									value="<%=BookCtl.OP_PAYMENT%>"
									class="btn btn-primary btn btn-info" />
									 or <input type="submit" name="operation" value="<%=BookCtl.OP_CANCEL%>" class="btn btn btn-primary"  />
							</div>
						</div>

					</form>


				</div>
			</div>
		</div>
	</div>


		<%@ include file="Footer.jsp"%>

</body>
</html>