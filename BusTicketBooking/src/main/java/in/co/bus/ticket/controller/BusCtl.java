package in.co.bus.ticket.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.bus.ticket.bean.BaseBean;
import in.co.bus.ticket.bean.BusBean;
import in.co.bus.ticket.exception.ApplicationException;
import in.co.bus.ticket.exception.DuplicateRecordException;
import in.co.bus.ticket.model.BusModel;
import in.co.bus.ticket.util.DataUtility;
import in.co.bus.ticket.util.DataValidator;
import in.co.bus.ticket.util.PropertyReader;
import in.co.bus.ticket.util.ServletUtility;

/**
 * Servlet implementation class FlightCtl
 */
@WebServlet(name="FlightCtl",urlPatterns={"/ctl/BusCtl"})
public class BusCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(BusCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("FlightCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Flight Name"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("flightNo"))) {
            request.setAttribute("flightNo",
                    PropertyReader.getValue("error.require", "Flight No"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("fromCity"))) {
            request.setAttribute("fromCity",
                    PropertyReader.getValue("error.require", "From City"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("toCity"))) {
            request.setAttribute("toCity",
                    PropertyReader.getValue("error.require", "To City"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("date"))) {
            request.setAttribute("date",
                    PropertyReader.getValue("error.require", "Date"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("time"))) {
            request.setAttribute("time",
                    PropertyReader.getValue("error.require", "Time"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("duration"))) {
            request.setAttribute("duration",
                    PropertyReader.getValue("error.require", "Travel Duration"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("price"))) {
            request.setAttribute("price",
                    PropertyReader.getValue("error.require", "Ticket Price"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("airPort"))) {
            request.setAttribute("airPort",
                    PropertyReader.getValue("error.require", "Air Port Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }

        log.debug("FlightCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FlightCtl populateBean method start");
		BusBean bean=new BusBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBusName(DataUtility.getString(request.getParameter("name")));
		bean.setBusNo(DataUtility.getString(request.getParameter("flightNo")));
		bean.setToCity(DataUtility.getString(request.getParameter("toCity")));
		bean.setFromCity(DataUtility.getString(request.getParameter("fromCity")));
		bean.setTravelDuraion(DataUtility.getString(request.getParameter("duration")));
		bean.setTicketPrice(DataUtility.getLong(request.getParameter("price")));
		bean.setTime(DataUtility.getString(request.getParameter("time")));
		bean.setBusStopName(DataUtility.getString(request.getParameter("airPort")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("FlightCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FlightCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   BusModel model = new BusModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				BusBean bean;
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
			log.debug("FlightCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FlightCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		BusModel model=new BusModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			BusBean bean=(BusBean)populateBean(request);
				try {
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(ATBView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		BusBean bean=	(BusBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(ATBView.FLIGHT_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ATBView.FLIGHT_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(ATBView.FLIGHT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("FlightCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ATBView.FLIGHT_VIEW;
	}

}
