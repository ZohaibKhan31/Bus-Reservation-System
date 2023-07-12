<%@page import="in.co.bus.ticket.controller.BookCtl"%>
<%@page import="in.co.bus.ticket.util.DataUtility"%>
<%@page import="in.co.bus.ticket.bean.BusBean"%>
<%@page import="in.co.bus.ticket.bean.BookBean"%>
<%@page import="in.co.bus.ticket.model.BusModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Raleway:400,500');

body{
 

  font-family: 'Raleway', sans-serif;
}

.main-wrap{
  padding:3%;
  max-width:1200px;
  display:block;
  margin: 10px auto;
}

.table.table-striped {
  width:100%;
    border-collapse: collapse;
      background: #fff;
    overflow: hidden;
    box-shadow: 0 0px 40px 0px rgba(0, 0, 0, 0.15);
    -moz-box-shadow: 0 0px 40px 0px rgba(0, 0, 0, 0.15);
    -webkit-box-shadow: 0 0px 40px 0px rgba(0, 0, 0, 0.15);
    -o-box-shadow: 0 0px 40px 0px rgba(0, 0, 0, 0.15);
    -ms-box-shadow: 0 0px 40px 0px rgba(0, 0, 0, 0.15);
}

.table.table-striped thead {
    background: #fff;
    box-shadow: 0 5px 20px 0px rgba(0, 0, 0, 0.1);
    -moz-box-shadow: 0 5px 20px 0px rgba(0, 0, 0, 0.1);
    -webkit-box-shadow: 0 5px 20px 0px rgba(0, 0, 0, 0.1);
    -o-box-shadow: 0 5px 20px 0px rgba(0, 0, 0, 0.1);
    -ms-box-shadow: 0 5px 20px 0px rgba(0, 0, 0, 0.1);
}

.table.table-striped th {
    padding: 0.9rem 0.5rem;
    font-weight: bold;
      border: 1px solid #efefef;
    text-align: left;
    border-top: 0px;
}

.table.table-striped tbody tr:nth-child(odd){
    background-color: rgba(0,0,0,.02);
}

.table.table-striped tbody tr td a {
    color: #3c2f17;
    font-size: 15px;
    text-decoration:none;
  font-weight:500;
}

.table.table-striped tr:nth-child(even) {
    background-color: #fff;
}

.table.table-striped tbody tr td {
    border: 1px solid #efefef;
    padding: 0.7rem;
    text-align: left;
    border-top: 0px;
}

.ftr{
    text-align: center;
    margin-top: 20px;
    font-weight: bold;
}

.ftr a{
    color: #fff;
}

</style>
</head>
<body>
	<%@ include file="Header.jsp"%>

<div class="main-wrap">
					<%
						Long flId=(Long)session.getAttribute("boId");
												BusModel model=new BusModel();
											BusBean fBean=model.findByPK(flId);
											
												BookBean bBean=(BookBean)session.getAttribute("BookB");
					%>
<table class="table table-striped">
   <thead>
      <tr>
         <th data-sort="name" colspan="2">Confirm Detail</th>
      </tr>
   </thead>
   <tbody>
      <tr>
         	<td ><b>Bus No.:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=fBean.getBusNo()%> </td>
           	<td ><b>Bus Name:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=fBean.getBusName()%></td>
           
      </tr>
      <tr>
         	<td ><b>Travel:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=fBean.getFromCity()+" To "+fBean.getToCity()%></td>
           <td ><b>Date:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=DataUtility.getDateString(fBean.getDate())%></td>
           	
      </tr>
      <tr>
         	<td ><b>Time:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=fBean.getTime()%></td>
           <td ><b>Travel Duration:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=fBean.getTravelDuraion()%></td>  	
      </tr>
       <tr>
       			
         	<td ><b>Station Name:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=fBean.getBusStopName()%></td>
           	<td ><b>Ticket Price:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<%=fBean.getTicketPrice()%></td>  	
      </tr>
      
      <thead>
      <tr>
      <%  String mssg=(String)request.getAttribute("msg"); %>
         <th data-sort="name" colspan="2">Your Detail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><font color="green"> <%=(mssg==null)?"":mssg%>
						</font></b></th>
      </tr>
      
   </thead>
   <tbody>
   <tr>
         	<td ><b>Name:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=bBean.getFirstName()+" "+bBean.getLastName()%></td>
           	<td ><b>Contect No:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=bBean.getMobileNo()%></td>
           
      </tr>
      <tr>
         	<td ><b>EmailId:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=bBean.getEmailId()%></td>
           	<td ><b>Date:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=DataUtility.getDateString(bBean.getBookDate())%></td>
           	
      </tr>
      <tr>
         	<td ><b>No Of Person:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=bBean.getNoOfPerson()%></td>
           	<td ><b>Address:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=bBean.getAddress()%></td>  	
      </tr>
       <tr>
       		<% String disc=(String)session.getAttribute("disc"); %>
         	<td colspan="2"><b>Final Amount:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<%=bBean.getFinalPrice()+" "%><%=(disc!=null)?disc:""%></td>
      </tr>
   </tbody>
     
</table>
</div>
<%if(mssg==null){%>
<div class="container">

		<div id="signupbox" style="margin-top: 50px"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Payment</div>
					
				</div>
				<div class="panel-body">
					<form method="post" action="<%=ATBView.BOOK_CTL%>">
						


						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Card No.
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="" placeholder="Enter Card No"
									style="margin-bottom: 10px" type="text"
									value=""/>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Card Holder Name
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="" placeholder="Enter Card Holder Name"
									style="margin-bottom: 10px" type="text"
									value=""/>
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Month
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="" placeholder="MM"
									style="margin-bottom: 10px" type="text"
									value=""/>
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">Year
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="" placeholder="yyyy"
									style="margin-bottom: 10px" type="text"
									value=""/>
							</div>
						</div>
						<div class="form-group required">
							<label class="control-label col-md-4  requiredField">CVV No.
								<span class="asteriskField">*</span>
							</label>
							<div class="controls col-md-8 ">
								<input class="input-md  textinput textInput form-control"
									maxlength="30" name="" placeholder="Enter Card Holder Name"
									style="margin-bottom: 10px" type="text"
									value=""/>
							</div>
						</div>

						<div class="form-group">
							<div class="aab controls col-md-4 "></div>
							<div class="controls col-md-8 ">
								<input type="submit" name="operation"
									value="<%=BookCtl.OP_PAYMENT_BOOK%>"
									class="btn btn-primary btn btn-info" />
									 or <input type="submit" name="operation" value="<%=BookCtl.OP_CANCEL%>" class="btn btn btn-primary"  />
							</div>
						</div>

					</form>


				</div>
			</div>
		</div>
	</div>
	<%} %>
	
	<div style="margin-top: 10px">
	<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>