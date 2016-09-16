package com.zaico.cms.servlets.worker;

import com.zaico.cms.entities.User;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.entities.Workplan;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
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
import java.util.Calendar;
import java.util.Date;

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
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            worker = workerService.findWorker((long)id);
            Workplan workplan = worker.getWorkplans().get(0);
            Date fist = workplan.getDate();
            Date last = worker.getWorkplans().get(worker.getWorkplans().size()-1).getDate();
            Calendar calFirst = Calendar.getInstance();
            Calendar calLast = Calendar.getInstance();
            calFirst.setTime(fist);
            calLast.setTime(last);

            String fistDay = calFirst.get(Calendar.DAY_OF_MONTH)+"-"+calFirst.get(Calendar.MONTH)+"-"+calFirst.get(Calendar.YEAR);
            String lastDay = calLast.get(Calendar.DAY_OF_MONTH)+"-"+calLast.get(Calendar.MONTH)+"-"+calLast.get(Calendar.YEAR);
            
            String firstHour = workplan.getSchedules().get(0).getInterval().toString();
            String lasstHour = workplan.getSchedules().get(workplan.getSchedules().size()-1).getInterval().toString();

            request.setAttribute("firstday",fistDay);
            request.setAttribute("lastday",lastDay);
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
