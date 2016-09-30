package com.zaico.cms.servlets.worker;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.User;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.UserService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/viewworker")
public class WorkerViewServlet extends HttpServlet {
    
    private  static final Log LOG = LogFactory.getLog(WorkerServiceImpl.class);
    WorkerService workerService = FactoryService.getWorkerServiceInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Worker worker = null;
        OrderService orderService = FactoryService.getOrderServiceInstance();
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            worker = workerService.findWorker(id);
            Workplan workplan = worker.getWorkplans().get(0);
            Date fist = workplan.getDate();
            Date last = worker.getWorkplans().get(worker.getWorkplans().size()-1).getDate();
            Calendar calFirst = Calendar.getInstance();
            Calendar calLast = Calendar.getInstance();
            calFirst.setTime(fist);
            calLast.setTime(last);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-y");
            Date dateF = dateFormat.parse(calFirst.getTime().toString());
            Date dateL = dateFormat.parse(calLast.getTime().toString());

            int firstHour = workplan.getSchedules().get(0).getInterval();
            int lasstHour = workplan.getSchedules().get(workplan.getSchedules().size()-1).getInterval()+1;
            List<Order> orders = orderService.getByWorker(worker);
            if ( orders.size() != 0) {
                request.setAttribute("orders",orders);
            }
            request.setAttribute("firstday",dateF);
            request.setAttribute("lastday",dateL);
            request.setAttribute("firsthour",firstHour);
            request.setAttribute("lasthour",lasstHour);
            request.setAttribute("worker",worker);
        } catch (Exception e) {
            LOG.info("Worker \""+worker.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/worker");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","BACK");
        request.getRequestDispatcher("pages/worker/worker.jsp").forward(request, response);
    }

}
