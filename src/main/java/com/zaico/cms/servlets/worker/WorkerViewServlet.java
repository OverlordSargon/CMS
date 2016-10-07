package com.zaico.cms.servlets.worker;

import com.mysql.jdbc.log.Log;
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
import org.apache.log4j.LogManager;
import org.apache.log4j.LogManager; import org.apache.log4j.Logger;


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
@WebServlet("/admin/viewworker")
public class WorkerViewServlet extends HttpServlet {
    /**
     * Logger
     */
    private  static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);
    /**
     * Worker service class instance
     */
    WorkerService workerService = FactoryService.getWorkerServiceInstance();

    /**
     * GET method handler
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Worker worker = null;
        OrderService orderService = FactoryService.getOrderServiceInstance();
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            worker = workerService.findWorker(id);
            workerService.findWorkTime(worker,request);
            List<Order> orders = orderService.getByWorker(worker);
            if ( orders.size() != 0) {
                request.setAttribute("orders",orders);
            }

            request.setAttribute("worker",worker);
        } catch (Exception e) {
            LOG.info("Worker \""+worker.getName()+ "\" notfounded at "+new Date());
            LOG.error(e);
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/worker");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","BACK");
        request.setAttribute("title","CMS Worker ");
        request.setAttribute("cmsheader","Worker "+worker.getName());
        request.getRequestDispatcher("/admin/pages/worker/worker.jsp").forward(request, response);
    }

}
