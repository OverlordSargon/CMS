package com.zaico.cms.controllers.order;

import com.zaico.cms.servicies.interfaces.OrderService;
import com.zaico.cms.servicies.interfaces.SkillService;
import com.zaico.cms.servlets.order.OrderViewServlet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by nzaitsev on 09.11.2016.
 */
@Controller
public class OrderDeleteContorller {
    Logger LOG = LogManager.getLogger(OrderViewServlet.class.toString());

    @Autowired
    SkillService skillService;
    @Autowired
    OrderService orderService;


}
