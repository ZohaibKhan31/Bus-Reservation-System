package in.co.bus.ticket.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import in.co.bus.ticket.bean.BaseBean;
import in.co.bus.ticket.bean.BookBean;
import in.co.bus.ticket.bean.BusBean;
import in.co.bus.ticket.bean.UserBean;
import in.co.bus.ticket.exception.ApplicationException;
import in.co.bus.ticket.exception.DuplicateRecordException;
import in.co.bus.ticket.model.BookModel;
import in.co.bus.ticket.model.BusModel;
import in.co.bus.ticket.util.DataUtility;
import in.co.bus.ticket.util.DataValidator;
import in.co.bus.ticket.util.PropertyReader;
import in.co.bus.ticket.util.ServletUtility;



/**
 * Servlet implementation class BookCtl
 */
@WebServlet(name = "BookCtl", urlPatterns = { "/BookCtl" })
public class BookCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(BookCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("BookCtl validate method start");
		boolean pass = true;
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		if (DataValidator.isNull(request.getParameter("bookDate"))) {
			request.setAttribute("bookDate", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("emailId"))) {
			request.setAttribute("emailId", PropertyReader.getValue("error.require", "Email ID"));
			pass = false;
		}
		
		
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("noP"))) {
			request.setAttribute("noP", PropertyReader.getValue("error.require", "No Of Person"));
			pass = false;
		}

	
		
		if(OP_PAYMENT_BOOK.equalsIgnoreCase(op)) {
			pass=true;
		}
		
		log.debug("BookCtl validate method end");
		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookCtl populateBean method start");
		BookBean bean = new BookBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBookDate(DataUtility.getDate(request.getParameter("bookDate")));
		bean.setNoOfPerson(DataUtility.getLong(request.getParameter("noP")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setBusId(DataUtility.getLong(request.getParameter("flightId")));
		populateDTO(bean, request);
		log.debug("BookCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		HttpSession session=request.getSession();
		
		long Mid=DataUtility.getLong(request.getParameter("bId"));
		if(Mid>0) {
		session.setAttribute("boId",Mid);
		}
		BookModel model = new BookModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			BookBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("BookCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		BookModel model = new BookModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		HttpSession session=request.getSession();
		if (OP_PAYMENT.equalsIgnoreCase(op)) {

			BookBean bean = (BookBean) populateBean(request);	
			bean.setBusId(bean.getBusId());
			BusModel fModel=new BusModel();
			BusBean fBean;
			try { 
				fBean = fModel.findByPK(bean.getBusId());
				UserBean uBean=(UserBean)session.getAttribute("user");
				bean.setFirstName(uBean.getFirstName());
				bean.setLastName(uBean.getLastName());
				long duration  = fBean.getDate().getTime() - new java.util.Date().getTime();
				long days = TimeUnit.MILLISECONDS.toDays(duration);
			    System.out.println("Diff in Day: "+days);
				System.out.println("Booking Date: "+bean.getBookDate());
				
				//if(cuttentDate)
				if(days==3) {
					bean.setFinalPrice(fBean.getTicketPrice()*bean.getNoOfPerson()-(bean.getNoOfPerson()*5));
					System.out.println("3: "+(fBean.getTicketPrice()*bean.getNoOfPerson()-5));
					session.setAttribute("disc","After Discount Of $"+bean.getNoOfPerson()*5);
				}else if(days>3 && days<7){
					bean.setFinalPrice(fBean.getTicketPrice()*bean.getNoOfPerson()-(bean.getNoOfPerson()*25));
					System.out.println("4-7: "+(fBean.getTicketPrice()*bean.getNoOfPerson()-25));
					session.setAttribute("disc","After Discount Of $"+bean.getNoOfPerson()*25);
				}else if(days>=7 && days<14){
					bean.setFinalPrice(fBean.getTicketPrice()*bean.getNoOfPerson()-(bean.getNoOfPerson()*50));
					System.out.println("7-14: "+(fBean.getTicketPrice()*bean.getNoOfPerson()-50));
					session.setAttribute("disc","After Discount Of $"+bean.getNoOfPerson()*50);
				}else if(days>=14 && days<=21){
					bean.setFinalPrice(fBean.getTicketPrice()*bean.getNoOfPerson()-100);
					System.out.println("14-21: "+(fBean.getTicketPrice()*bean.getNoOfPerson()-(bean.getNoOfPerson()*100)));
					session.setAttribute("disc","After Discount Of $"+bean.getNoOfPerson()*100);
				}else {
					bean.setFinalPrice(fBean.getTicketPrice()*bean.getNoOfPerson());
				}
				
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("BookB", bean);
			
			ServletUtility.forward(ATBView.PAYMENT_VIEW, request, response);
		
		
		}else if(OP_PAYMENT_BOOK.equalsIgnoreCase(op)) {
			long pk=0;
			BookBean bBean=(BookBean)session.getAttribute("BookB");
			System.out.println("Py Book Conf-----:"+bBean.getFinalPrice());
			try {
				 pk=model.add(bBean);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("msg","Ticket is Successfully Booked");
			ServletUtility.forward(ATBView.PAYMENT_VIEW, request, response);
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ATBView.INDEX_CTL, request, response);
			return;
	} 

		ServletUtility.forward(getView(), request, response);
		log.debug("BookCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ATBView.BOOK_VIEW;
	}

}
