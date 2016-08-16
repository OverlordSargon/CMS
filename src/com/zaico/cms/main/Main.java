package com.zaico.cms.main;

import com.zaico.cms.dao.implementation.FactoryDAO;
import com.zaico.cms.dao.implementation.SkillDAOImpl;
import com.zaico.cms.dao.implementation.UserDAOImpl;
import com.zaico.cms.dao.interfaces.*;
import com.zaico.cms.entities.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nzaitsev on 01.08.2016.
 */
public class Main {
    public static void main(String[] args)  throws Exception, SQLException, ClassNotFoundException,InterruptedException {
        /* DAO */
        SkillDAO si = FactoryDAO.getSkillDAOInstance();
        WorkerDAO workerDAO = FactoryDAO.getWorkerDAOInstance();
        WorkplanDAO wpd = FactoryDAO.getWorkplanDAOInstance();
        OrderDAO orderDAO= FactoryDAO.getOrderDAOInstance();
        ScheduleDAO schD = FactoryDAO.getScheduleDAOInstance();
        UserDAO userDAO = FactoryDAO.getUserDAOInstance();
        RoleDAO roleDAO = FactoryDAO.getRoleDAOInstance();

        Worker worker1 = new Worker("Grizly",767657);
//        workerDAO.create(worker1);
        Worker w2 = workerDAO.read((long)30);
        Order order1 = new Order("566g","DEsc",new Date(),new Date(),new Date(),234,"rsax",w2);
//        order1.setOrdNumber("gdgsgg3");
//        order1.setDescription("||}}}|||");
//        order1.setWorker(worker1);
//        order1.setClientName("HIGJ");
//        order1.setTelNumber(343441);
        orderDAO.create(order1);
    }
}