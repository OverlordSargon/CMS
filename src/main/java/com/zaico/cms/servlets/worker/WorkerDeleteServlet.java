package com.zaico.cms.servlets.worker;

import com.zaico.cms.entities.User;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.UserService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionHandler;


import org.apache.log4j.LogManager; import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nzaitsev on 03.09.2016.
 */
@WebServlet("/deleteworker")
public class WorkerDeleteServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    Worker worker = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            worker = workerService.findWorker((long)id);
            request.setAttribute("worker",worker);
            request.setAttribute("infoMessage","You want to delete this role. Are you sure?");
        } catch (Exception e) {
            LOG.info("Worker \""+worker.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/deleteworker");
        request.setAttribute("disabled","disabled");
        request.setAttribute("button","DELETE");
        request.getRequestDispatcher("pages/worker/worker.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            worker = workerService.findWorker((long)id);
            workerService.deleteWorker(worker);
            String message = "Worker \""+worker.getName()+"\" deleted successfully";
            LOG.info(message);
            request.setAttribute("infoMessage",message);
        } catch (Exception e) {
            String message = ExceptionHandler.handleException(e);
            request.setAttribute("errMessage",message);
        }
        request.getRequestDispatcher("/workers").forward(request,response);
    }
}
