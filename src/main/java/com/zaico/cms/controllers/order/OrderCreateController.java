package com.zaico.cms.controllers.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servlets.order.OrderViewServlet;
import com.zaico.cms.utility.CheckFromTo;
import com.zaico.cms.utility.ErrorCode;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
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
 * Created by nzaitsev on 07.11.2016.
 */
@Controller
public class OrderCreateController {

    Logger LOG = LogManager.getLogger(OrderViewServlet.class.toString());

    @Autowired
    SkillService skillService;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order_create", method = RequestMethod.GET)
    public ModelAndView viewOrder(
            Model model
        ) {
        Order order = null;
        ModelAndView mav = new ModelAndView();
        try {
            List<Skill> allSkills = skillService.findAllSkills();
            mav.addObject("order",order);
            mav.addObject("orderDates",new OrderDates());
            mav.addObject("skills",allSkills);
            mav.addObject("action","/order_create");
            mav.addObject("button","CREATE");
            mav.addObject("title","CMS Create Orders");
            mav.addObject("cmsheader","Create order");
        } catch (Exception e) {
            LOG.info(e.toString());
        }
        mav.setViewName("order/order");
        return mav;
    }

    @RequestMapping(value = "/order_create*", method = RequestMethod.POST)
    public String createOrderExecute(
            @ModelAttribute("order") Order order,
            @ModelAttribute("orderDates") OrderDates orderDates,
            RedirectAttributes redirectAttributes,
            @RequestParam("skill") int skillId
        ) {
        LOG.debug("Start: create new order ...");
        try {
            // Get parameters
            LOG.debug("Get parameters for new order");

            String dateS = orderDates.getOrderDay();
            String fromS = orderDates.getOrgerBeginHour();
            String toS = orderDates.getOrgerEndHour();

            CheckFromTo.checkHours(fromS,toS);
            // String dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");
            // Create dates
            Date hoursFrom = timeF.parse(fromS);
            Date hoursTo = timeF.parse(toS);
            Date orderDate = dateF.parse(dateS);
            // calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(hoursFrom);
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(hoursTo);
            Calendar calDate = Calendar.getInstance();
            calDate.setTime(orderDate);

            // Find workers by skill
            LOG.debug("Find free workers by skill id = " + skillId);
            Worker orderWorker = orderService.findCapacity(calDate, calFrom, calTo, (long)skillId, "W", null);
            order.setWorker(orderWorker);
            if ( orderWorker == null) {
                throw new ExceptionCMS("No capacity!", ErrorCode.ORDER_CREATION_ERROR);
            }
            LOG.info("Create new order with parameters...");
            order.setWorker(orderWorker);
            order.setDate(orderDate);
            order.setFrom(hoursFrom);
            order.setTo(hoursTo);
            orderService.createOrder(order);
            LOG.info("Order " + order.toString() + " has been created successfully.");

            String message = "Order \"" + order.getOrdNumber() + "\" created successfuly";
            redirectAttributes.addFlashAttribute("sucMessage",message);
            LOG.debug("End: create new order ...");
            return "redirect:/orders";
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage", errorMessage);
            String infoMessage = "Try again, please";
            redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
            redirectAttributes.addFlashAttribute("order",order);
            return "redirect:/order_create";
        }
    }
}
