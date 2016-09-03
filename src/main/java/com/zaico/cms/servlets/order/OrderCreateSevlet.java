package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 03.09.2016.
 */
@WebServlet("/neworder")
public class OrderCreateSevlet extends HttpServlet {
    private static final Log LOG = LogFactory.getLog(WorkerServiceImpl.class);
    OrderService orderService = FactoryService.getOrderServiceInstance();
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    SkillService skillService = FactoryService.getSkillServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Worker> allWorkers = workerService.findAllWorkers();
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("workers",allWorkers);
            request.setAttribute("skills",allSkills);
            request.setAttribute("action","/neworder");
            request.setAttribute("button","CREATE");
        } catch (Exception e) {

        }
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Get parameters
        String orderNum = request.getParameter("ordernum");
        String orderDesc = request.getParameter("orderdesc");
        String orderWorktype = request.getParameter("orderworktype");
        Date date = new Date();
        String from = request.getParameter("orderfrom");
        String to = request.getParameter("orderto");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-y HH:mm");
        String orderClient = request.getParameter("ordercname");
        int orderCleintNum = Integer.parseInt(request.getParameter("ordertele"));
        Long workerId = Long.parseLong(request.getParameter("orderworker"));

        try {
            Date dateFrom = dateFormat.parse(from);
            Date dateTo = dateFormat.parse(to);
            Worker worker = workerService.findWorker(workerId);
            Order order = new Order(orderNum,orderDesc,orderWorktype,
                    date,dateFrom,dateTo,orderCleintNum,orderClient,worker);
            orderService.createOrder(order);
            String message = "Order \""+orderNum+"\" created at "+new Date();
            LOG.info(message);
            request.setAttribute("sucMessage",message);
        } catch (Exception e) {
            String errorMessage = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage"+" Try again please, check parameters", errorMessage);
        }
        request.getRequestDispatcher("/orders").forward(request, response);
    }
}
