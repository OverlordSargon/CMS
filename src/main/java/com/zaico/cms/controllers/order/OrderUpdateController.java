package com.zaico.cms.controllers.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.ScheduleService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servlets.order.OrderViewServlet;
import com.zaico.cms.utility.CheckFromTo;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 10.11.2016.
 */
@Controller
public class OrderUpdateController {
    
    Logger LOG = LogManager.getLogger(OrderViewServlet.class.toString());

    @Autowired
    OrderService orderService;
    @Autowired
    SkillService skillService;


    @RequestMapping(value = "update_order*", method = RequestMethod.GET)
    public ModelAndView updatePrepare(
            @RequestParam("id") int id
    ) {
        ModelAndView mav = new ModelAndView();
        Order order = null;
        try {
            order = orderService.findOrder(id);
            mav.addObject("order",order);
            List<Skill> allSkills = skillService.findAllSkills();
            mav.addObject("skills",allSkills);
            mav.addObject("dates",new OrderDates());
        } catch (Exception e) {
            LOG.info("Order \""+order.getOrdNumber()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
             mav.addObject("errMessage",errMess);
        }
         mav.addObject("button","UPDATE");
         mav.addObject("action","/update_order");
         mav.addObject("button","UPDATE");
         mav.addObject("title","CMS Update Orders");
         mav.addObject("cmsheader","Update order "+order.getOrdNumber());
        mav.setViewName("order/order");
        return mav;
    }

    @RequestMapping(value = "update_order*", method = RequestMethod.POST)
    public String deleteOrderExecute (
            @ModelAttribute("order") Order order,
            @ModelAttribute("orderDates") OrderDates orderDates,
            RedirectAttributes redirectAttributes,
            @RequestParam("skill") int skillId
    ) {
        //Get parameters
        Order orderUpdated = null;
        try {
            orderUpdated = orderService.findOrder(order.getId());

            String dateS = orderDates.getOrderDay();
            String fromS = orderDates.getOrgerBeginHour();
            String toS = orderDates.getOrgerEndHour();

            CheckFromTo.checkHours(fromS, toS);

            //string dates into dates
            DateFormat timeF = new SimpleDateFormat("HH:mm");
            DateFormat dateF = new SimpleDateFormat("dd-MM-y");
            /*Set old flags as F*/
            Calendar calendarD = Calendar.getInstance();
            Calendar calendarF = Calendar.getInstance();
            Calendar calendarT = Calendar.getInstance();
            calendarD.setTime(orderUpdated.getDate());
            calendarF.setTime(orderUpdated.getFrom());
            calendarT.setTime(orderUpdated.getTo());

            //create dates
            Date fromDate = timeF.parse(fromS);
            Date toDate = timeF.parse(toS);
            Date dateDate = dateF.parse(dateS);

            //calendars for all dates
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(fromDate);

            Calendar calTo = Calendar.getInstance();
            calTo.setTime(toDate);

            Calendar calDate = Calendar.getInstance();
            calDate.setTime(dateDate);

            /*Find workers by skill*/
            Worker workerOrder = orderService.findCapacity(calendarD, calendarF, calendarT, null, "F", orderUpdated.getWorker());
            orderService.findCapacity(calDate, calFrom, calTo, (long) skillId, "W", null);

            orderUpdated.setOrdNumber(order.getOrdNumber());
            orderUpdated.setDescription(order.getDescription());
            orderUpdated.setDate(dateDate);
            orderUpdated.setFrom(fromDate);
            orderUpdated.setTo(toDate);
            orderUpdated.setTelNumber(order.getTelNumber());
            orderUpdated.setClientName(order.getClientName());
            orderUpdated.setWorker(workerOrder);

            LOG.info("New " + order.toString());

            orderService.updateOrder(orderUpdated);
            String message = "Order \"" + order.getOrdNumber() + "\"updated successfully";
            redirectAttributes.addFlashAttribute("sucMessage", message);
            LOG.info("END: successful update order " + order.getOrdNumber());
            return "redirect:/orders";
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            redirectAttributes.addFlashAttribute("errMessage", errorMessage);
            String infoMessage = "Try again, please.";
            redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
            LOG.error("END:update order error " + order.getOrdNumber());
            return "redirect:/order_update?id="+order.getId();
        }
    }
}
