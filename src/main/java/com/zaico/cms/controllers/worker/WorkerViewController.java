package com.zaico.cms.controllers.worker;

import com.zaico.cms.entities.Order;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.WorkerService;
import com.zaico.cms.utility.ExceptionCMS;
import com.zaico.cms.utility.ExceptionHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ZAITNIK on 29.10.2016.
 */
@Controller
public class WorkerViewController {

    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(WorkerServiceImpl.class);

    @Autowired
    WorkerService workerService ;
    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    public ModelAndView viewAllWorkers(

    ) {
        ModelAndView mav = new ModelAndView();
        Queue<Worker> workersQue = new LinkedList();
        StringBuffer sss = new StringBuffer();
        try {
            workersQue.addAll(workerService.findAllWorkers());
            while (workersQue.size()>1) {
                Worker worker = workersQue.poll();
                sss.append(worker.getName());
            }
        } catch (ExceptionCMS exceptionCMS) {
            exceptionCMS.printStackTrace();
        }
        try {
            List<Worker> workers = workerService.findAllWorkers();
            mav.addObject("workers",workers);
            if ( workers.size() == 0 ) {
                mav.addObject("infoMessage","No workers yet!");
            }
        } catch (Exception e) {
            String errMes = ExceptionHandler.handleException(e);
            LOG.info(errMes);
        }
        mav.addObject("title","CMS Workers");
        mav.addObject("cmsheader","Workers");
//        mav.addObject("infoMessage",sss);
        mav.setViewName("worker/allworkers");
        return mav;
    }

    @RequestMapping(value = "view_worker*", method = RequestMethod.GET)
    public ModelAndView viewWorkerExecute(
            @RequestParam(value = "id") int id
    ) {
        Worker worker = null;
        ModelAndView mav = new ModelAndView();
        try {
            worker = workerService.findWorker(id);
            WorkerDates workerDates = workerService.findWorkTime(worker);
            List<Order> orders = orderService.getByWorker(worker);
            if ( orders.size() != 0) {
                mav.addObject("orders",orders);
            }

            mav.addObject("worker",worker);
            mav.addObject("dates",workerDates);
        } catch (Exception e) {
            LOG.info("Worker \""+worker.getName()+ "\" notfounded at "+new Date());
            LOG.error(e);
            String errMess = ExceptionHandler.handleException(e);
            mav.addObject("errMessage",errMess);
        }
        mav.addObject("action","/worker");
        mav.addObject("disabled","disabled");
        mav.addObject("button","BACK");
        mav.addObject("title","CMS Worker ");
        mav.addObject("cmsheader","Worker "+worker.getName());
        mav.setViewName("worker/worker");
        return mav;
    }
}