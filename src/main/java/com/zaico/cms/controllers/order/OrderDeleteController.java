package com.zaico.cms.controllers.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nzaitsev on 09.11.2016.
 */
@Controller
public class OrderDeleteController {

    /**
     * Logger
     */
    Logger logger = LogManager.getLogger(OrderDeleteController.class);

    @Autowired
    OrderService orderService;
    
    @RequestMapping(value = "delete_order*", method = RequestMethod.GET)
    public ModelAndView orderDeletePrepare(
            @RequestParam("id") int id
    ) {
        ModelAndView mav = new ModelAndView();
        Order order = null;
        try {
            order = orderService.findOrder(id);
            mav.addObject("order",order);
            mav.addObject("infoMessage","You want to delete Order \""+order.getOrdNumber()+"\". Are you sure?");
        } catch (Exception e) {
            logger.info("Order \""+order.getOrdNumber()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        mav.addObject("action","/delete_order");
        mav.addObject("disabled","disabled");
        mav.addObject("button","DELETE");
        mav.addObject("title","CMS Delete Order");
        mav.addObject("cmsheader","Delete order "+ order.getOrdNumber());
        mav.setViewName("order/order");
        return mav;
    }

    @RequestMapping(value = "delete_order*",method = RequestMethod.POST)
    public String orderDeleteExecute (
            @RequestParam("id") int id,
            RedirectAttributes redirectAttributes
    ) {
        Order order = null;
        try {
            order = orderService.findOrder(id);
            logger.info("START: delete order "+order.getOrdNumber());
            // string dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");
            // create dates
            Date fromDate = order.getFrom();
            Date toDate = order.getTo();
            Date dateDate = order.getDate();
            // calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);
            Calendar calDate = Calendar.getInstance();
            calDate.setTime(dateDate);
            orderService.findCapacity(calDate,calFrom,calTo,null,"F",order.getWorker());
            orderService.deleteOrder(order);
            String message = "Order \""+order.getOrdNumber()+"\" deleted successfully";
            redirectAttributes.addFlashAttribute("sucMessage",message);
            logger.info("END: delete order "+order.getOrdNumber());
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            logger.error("ERROR:order delete "+order.getOrdNumber());
            redirectAttributes.addFlashAttribute("errMessage",message);
        }
        return "redirect:/orders";
    }
}
