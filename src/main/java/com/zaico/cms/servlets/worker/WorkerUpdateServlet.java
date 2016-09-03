package com.zaico.cms.servlets.worker;

import com.zaico.cms.entities.Role;
import com.zaico.cms.entities.Skill;
import com.zaico.cms.entities.User;
import com.zaico.cms.entities.Worker;
import com.zaico.cms.servicies.implementation.FactoryService;
import com.zaico.cms.servicies.implementation.UserServiceImpl;
import com.zaico.cms.servicies.implementation.WorkerServiceImpl;
import com.zaico.cms.servicies.interfaces.RoleService;
import com.zaico.cms.servicies.interfaces.SkillService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 02.09.2016.
 */
@WebServlet("/updateworker")
public class WorkerUpdateServlet extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(WorkerServiceImpl.class);
    WorkerService workerService = FactoryService.getWorkerServiceInstance();
    SkillService skillService = FactoryService.getSkillServiceInstance();
    Worker worker = null;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            worker = workerService.findWorker(id);
            request.setAttribute("worker",worker);
            List<Skill> allSkills = skillService.findAllSkills();
            request.setAttribute("skills",allSkills);

        } catch (Exception e) {
            LOG.info("Worker \""+worker.getName()+ "\" notfounded at "+new Date());
            String errMess = ExceptionHandler.handleException(e);
        }
        request.setAttribute("action","/updateworker");
        request.setAttribute("button","UPDATE");
        request.getRequestDispatcher("pages/worker/worker.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String workerName = request.getParameter("workername");
        int workerNum = Integer.parseInt(request.getParameter("workertele"));
        String[] skills = request.getParameterValues("skills");
        try {
            worker.setName(workerName);
            worker.setTelephone(workerNum);
            List<Skill> workerSkills = new ArrayList<Skill>();
//            if skill id not null
            if (skills != null && skills.length != 0) {
                for ( String skillId: skills) {
//                    Find each skill with id and add to skill list
                    long id = Long.parseLong(skillId);
                    workerSkills.add(skillService.findSkill(id));
                }
            }
//            set all finded skill as user skill
            worker.setSkills(workerSkills);
            workerService.updateWorker(worker);
            LOG.info("Worker "+worker.getName()+ " updated at "+new Date());
            request.setAttribute("sucMessage","Worker \""+worker.getName()+ "\" updated successfully");
        } catch (Exception e) {
            String errMess = ExceptionHandler.handleException(e);
            LOG.info(errMess);
            request.setAttribute("errMessage",errMess);
        }
        request.getRequestDispatcher("/workers").forward(request, response);
    }
}
