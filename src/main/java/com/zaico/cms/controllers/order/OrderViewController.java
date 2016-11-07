package com.zaico.cms.controllers.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servlets.order.OrderViewServlet;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 07.11.2016.
 */
@Controller
public class OrderViewController {

    // Logger
    Logger LOG = LogManager.getLogger(OrderViewServlet.class.toString());

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    protected ModelAndView cmsOrders() {
        ModelAndView mav = new ModelAndView();
        try {
            List<Order> orders = orderService.findAllOrders();
            for (Order order : orders) {
                order.getWorker();
            }
            mav.addObject("orders", orders);
            if ( orders.size() == 0 ) {
                mav.addObject("infoMessage","No orders yet!");
            }
            LOG.info("START: get all orders");
        } catch (Exception e) {
            String errMes = ExceptionHandler.handleException(e);
            LOG.info(errMes);
            mav.addObject("errMessage",errMes);
        }
        mav.addObject("title","CMS Orders");
        mav.addObject("cmsheader","Orders");
        mav.setViewName("order/allorders");
        return mav;
    }

//    /**
//     * Get method handler
//     * @throws ServletException
//     * @throws IOException
//     */
    @RequestMapping(value = "/view_order**", method = RequestMethod.GET)
    public ModelAndView viewOrder(
            @RequestParam("id") int id
    ) throws ServletException, IOException {
        Order order = null;
        ModelAndView mav = new ModelAndView();
        try {
            order = orderService.findOrder(id);
            mav.addObject("order",order);
            LOG.info("VIEW: Order \""+order.getOrdNumber()+ "\"");
            mav.addObject("title","CMS Update Orders");
            mav.addObject("cmsheader","Order "+order.getOrdNumber());
        } catch (Exception e) {
            LOG.info("VIEW: Error Order \""+order.getOrdNumber()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        mav.addObject("action","/order");
        mav.addObject("disabled","disabled");
        mav.addObject("button","BACK");
        mav.setViewName("order/order");
        return mav;
    }
}
