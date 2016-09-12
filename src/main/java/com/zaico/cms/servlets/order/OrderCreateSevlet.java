package com.zaico.cms.servlets.order;

import com.zaico.cms.entities.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.concurrent.TimeUnit.HOURS;

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
        Long orderSkill = Long.parseLong(request.getParameter("orderworktype"));
        Date date = new Date();
        String from = request.getParameter("orderfrom");
        String to = request.getParameter("orderto");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-y HH:mm");
        DateFormat dateF2 = new SimpleDateFormat("dd-MM-y");
        String orderClient = request.getParameter("ordercname");
        int orderCleintNum = Integer.parseInt(request.getParameter("ordertele"));

        try {
            /*Find workers by skill*/
            List<Worker> workers  = workerService.findWorkersBySkill(orderSkill);
            Random random = new Random();
            int id = random.nextInt(workers.size());
//            choose random worker
            Worker worker = workers.get(id);

            /*Set flags*/
            List<Workplan> workplanList = worker.getWorkplans();
            List<Schedule> scheduleList = new ArrayList<Schedule>();
            Calendar time = Calendar.getInstance();
//            set dates from string
            Date dateFrom = dateFormat.parse(from);
            Date dateTo = dateFormat.parse(to);
            time.setTime(dateFrom);
            while(dateFrom.before(dateTo)) {
                for(Workplan workplan: workplanList) {
                    Date wpDate = dateF2.parse(workplan.getDate().toString());
                    Date calDate = dateF2.parse(time.getTime().toString());
                    if(wpDate.equals(calDate) ) {
                        scheduleList = workplan.getSchedules();
                        for ( Schedule schedule: scheduleList) {
                            if ( schedule.getInterval() == time.get(Calendar.HOUR+1)) {
                                schedule.setFlag("W");
                            }
                        }
                    }
                    time.add(Calendar.HOUR_OF_DAY,1);
                }
            }

            /*Create order*/
            Order order = new Order(orderNum,orderDesc,
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
