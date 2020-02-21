package com.model.shop.control;

import com.model.shop.entity.Admin;
import com.model.shop.entity.Order;
import com.model.shop.service.AdminService;
import com.model.shop.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private OrderService orderService;

    @RequestMapping("/adminLogin")
    public String adminLogin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "adminLogin";
    }

    @RequestMapping(value = "/adminValidate", method = RequestMethod.POST)
    public String adminValidate(@ModelAttribute Admin admin, HttpServletRequest request) throws Exception {
        //Admin admin = (Admin) model.getAttribute("admin");
        String adminName = admin.getAdminName();
        String password = admin.getPassword();
        if (adminName != null && password != null) {
            System.out.println(adminName + " " + password);
            if (adminService.adminValidate(adminName, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", adminService.getAdminByNameAndPass(adminName, password));
                return manage(request);
            }
        }
        System.out.println("I am here");
        System.out.println(adminName + "" + password);
        return "error";
    }

    @RequestMapping(value = "/manage")
    public String manage(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<Order> orderList = orderService.getAllOrder();
        session.setAttribute("orders", orderList);//使用orders，注意与结算时使用的orderList区分
        return "manage";
    }
}
