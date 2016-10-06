package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.utility.CheckFromTo;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 03.09.2016.
 */
@WebServlet("/neworder")
public class OrderCreateSevlet extends HttpServlet {

	/**
	 * The Logger.
	 */
    private static final Logger LOG = LogManager.getLogger(OrderCreateSevlet.class);

	/**
	 * Order service class instance.
	 */
    OrderService orderService = FactoryService.getOrderServiceInstance();

	/**
	 * Skill service class instance.
	 */
    SkillService skillService = FactoryService.getSkillServiceInstance();


	/**
	 * Get method handler
	 * @param request The HttpServletRequest object.
	 * @param response The HttpServletResponse object.
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = null;
        try {
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("order",order);
            request.setAttribute("skills",allSkills);
            request.setAttribute("action","/neworder");
            request.setAttribute("button","CREATE");
			request.setAttribute("title","CMS Create Orders");
			request.setAttribute("cmsheader","Create order");
        } catch (Exception e) {
            LOG.info(e.toString());
        }
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

	/**
	 * Post method handler
	 * @param request The HttpServletRequest object.
	 * @param response The HttpServletResponse object.
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    LOG.debug("Start: create new order ...");
		Order order = null;
		try {
			// Get parameters
			LOG.debug("Get parameters for new order");
			String orderNum = request.getParameter("ordernum");
			String orderDesc = request.getParameter("orderdesc");
			Long skillId = Long.parseLong(request.getParameter("orderworktype"));
			String dateS = request.getParameter("orderday");
			String fromS = request.getParameter("orderfrom");
			String toS = request.getParameter("orderto");
			String orderClient = request.getParameter("ordercname");
			int orderCleintNum = Integer.parseInt(request.getParameter("ordertele"));

			CheckFromTo.checkHours(fromS,toS);
			List<Object> list = new ArrayList<Object>();
			list.add(orderNum);
			list.add(orderDesc);
			list.add(skillId);
			list.add(dateS);
			list.add(fromS);
			list.add(toS);
			list.add(orderClient);
			list.add(orderCleintNum);
	        // Validate input fields
	        LOG.debug("Validation of the fields");
	        validateFields(list);

			// String dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");

			// Create dates
            Date hoursFrom = timeF.parse(fromS);
            Date hoursTo = timeF.parse(toS);
            Date orderDate = dateF.parse(dateS);

//          calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(hoursFrom);

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(hoursTo);

            Calendar calDate = Calendar.getInstance();
            calDate.setTime(orderDate);

	        // Find workers by skill
	        LOG.debug("Find free workers by skill id = " + skillId);
            Worker orderWorker = orderService.findCapacity(calDate, calFrom, calTo, skillId, "W", null);

	        LOG.info("Create new order with parameters...");
	        order = new Order(orderNum, orderDesc, orderDate, hoursFrom, hoursTo, orderCleintNum, orderClient, orderWorker);
            orderService.createOrder(order);
	        LOG.info("Order " + order.toString() + " has been created successfully.");

            String message = "Order \"" + orderNum + "\" created successfuly";
            request.setAttribute("sucMessage",message);
            request.getRequestDispatcher("orders").forward(request, response);
	        LOG.debug("End: create new order ...");
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage", errorMessage);
            String infoMessage = "Try again, please";
            request.setAttribute("infoMessage", infoMessage);
            doGet(request,  response);
        }
    }

	/**
	 * Validates input fields.
	 *
	 * @throws ExceptionCMS
	 */
	private void validateFields(List<Object> list) throws ExceptionCMS {
		try {
			for ( Object object: list) {
				if ( object == null | object.equals("") | object.equals(0) ) {
					throw new ExceptionCMS("You must fill"+ object.toString()+ " field", ErrorCode.ORDER_CREATION_ERROR);
				}
			}
		} catch (Exception e) {
			String errorMsg = "Field is not valid.";
			LOG.error(errorMsg, e);
			throw new ExceptionCMS(errorMsg, e);
		}
	}
}
