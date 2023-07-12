<%@page import="in.co.bus.ticket.controller.BusCtl"%>
<%@page import="in.co.bus.ticket.util.DataUtility"%>
<%@page import="in.co.bus.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight</title>

</head>
<body>
	<%@ include file="Header.jsp"%>

	<div class="container">

		<div id="signupbox" style="margin-top: 50px"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Bus</div>
					<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b>
				</div>
				<div class="panel-body">
					<form method="post" action="<%=ATBView.FLIGHT_CTL%>">
						<jsp:useBean id="bean"
							class="in.co.bus.ticket.bean.BusBean" scope="request"></jsp:useBean>


						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Bus
								No.<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="flightNo" placeholder="Enter Flight No."
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getBusNo())%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("flightNo", request)%></font>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Bus
								Name<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="name" placeholder="Enter Flight name"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getBusName())%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">From
								City <span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="fromCity" placeholder="Enter From City"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getFromCity())%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("fromCity", request)%></font>
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">To
								City <span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="toCity" placeholder="Enter To City"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getToCity())%>" /> <font
									color="red"><%=ServletUtility.getErrorMessage("toCity", request)%></font>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Date
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="date" placeholder="Enter Date" readonly="readonly"
									style="margin-bottom: 10px" type="text" id="datepicker"
									value="<%=DataUtility.getDateString(bean.getDate())%>" /> <font
									color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Time
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="time" placeholder="Enter Time"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getTime())%>" /> <font
									color="red"><%=ServletUtility.getErrorMessage("time", request)%></font>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Travel
								Duration <span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="duration"
									placeholder="Enter Travel Duration" style="margin-bottom: 10px"
									type="text"
									value="<%=DataUtility.getStringData(bean.getTravelDuraion())%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("duration", request)%></font>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Airport
								Name <span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="airPort" placeholder="Enter Airport Name"
									style="margin-bottom: 10px" type="text"
									value="<%=DataUtility.getStringData(bean.getBusStopName())%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("airPort", request)%></font>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Ticket
								Price <span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="price" placeholder="Enter Ticket Price"
									style="margin-bottom: 10px" type="text"
									value="<%=(bean.getTicketPrice()==0)?"":bean.getTicketPrice()%>" />
								<font color="red"><%=ServletUtility.getErrorMessage("price", request)%></font>
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Description<span
								class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<textarea rows="4" cols="5" name="description"
									placeholder="Your Decription "
									class="input-md textinput textInput form-control"
									style="margin-bottom: 10px"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
								<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
							</div>
						</div>




						<div class="form-group">
							<div class="aab controls col-md-4 "></div>
							<div class="controls col-md-8 ">
								<input type="submit" name="operation"
									value="<%=BusCtl.OP_SAVE%>"
									class="btn btn-primary btn btn-info" />
									 or <input type="submit" name="operation" value="<%=BusCtl.OP_RESET%>" class="btn btn btn-primary"  />
							</div>
						</div>

					</form>


				</div>
			</div>
		</div>
	</div>

	<div style="margin-top: 198px">
		<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>