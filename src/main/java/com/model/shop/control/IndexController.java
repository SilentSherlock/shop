package com.model.shop.control;

import com.model.shop.entity.*;
import com.model.shop.service.LoginUserService;
import com.model.shop.service.MerchandiseService;
import com.model.shop.service.OrderService;
import com.model.shop.service.ShoppingCartService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private MerchandiseService merchandiseService;
    @Resource
    private LoginUserService loginUserService;
    @Resource
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;

    //用户访问主界面
    @RequestMapping("/index")
    public String Index(Model model) throws Exception {
        List<Merchandise> merchandises = merchandiseService.getAllMer();
        model.addAttribute("merchandises", merchandises);
        return "index";
    }

    //用户登录
    @RequestMapping(value = "/userLogin")
    public String userLogin(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "userLogin";
    }

    //用户验证
    @RequestMapping(value = "/userValidate")
    public String userValidate(@ModelAttribute LoginUser loginUser, HttpSession session, Model model) throws Exception {
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        System.out.println(loginUser.toString());
        if (email != null && password != null) {
            if (loginUserService.userValidateByEmail(email, password)) {
                session.setAttribute("loginUser", loginUserService.getUserByEmailAndPass(email, password));
                //用户登录验证成功后，为用户初始化一个购物车，存放在session中
                shoppingCartService = new ShoppingCartService();
                session.setAttribute("shoppingCartService", shoppingCartService);
                System.out.println("I am in userValidate");
                return Index(model);
            }
        }
        return "error";
    }

    //获取商品详细信息
    @RequestMapping(value = "/getMerInfo")
    public String getMerInfo(HttpServletRequest request, Model model) throws Exception {
        Integer merId = Integer.valueOf(request.getParameter("merId"));
        Merchandise merchandise = merchandiseService.getMerById(merId);
        model.addAttribute("merchandise", merchandise);
        return "merDetail";
    }

    //添加商品到购物车
    @RequestMapping(value = "/addShoppingCart")
    public String addShoppingCart(HttpServletRequest request, Model model) throws Exception {
        //检测用户是否登录,未登录跳转到登录
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return userLogin(model);
        }
        //获取商品信息
        Integer merId = Integer.valueOf(request.getParameter("merId"));
        Merchandise merchandise = merchandiseService.getMerById(merId);
        //将商品信息保存到购物车中
        HttpSession session = request.getSession();
        ShoppingCartService sp = (ShoppingCartService) session.getAttribute("shoppingCartService");
        if (sp != null) {
            sp.addMerchandise(new CartElement(merchandise, 1));
            session.setAttribute("shoppingCartService", sp);//2020.1.3改动，将购物车中的商品封装为CartElement,包含merchandise和个数
        } else {
            System.out.println("sp为空");
        }
        return getMerInfo(request, model);
    }

    //直接购买商品
    @RequestMapping(value = "/purchase")
    public String purchase(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return userLogin(model);
        }


        List<CartElement> orderList = new LinkedList<>();//初始化一个结算清单
        double totalPrice = 0;
        Integer merId = Integer.valueOf(request.getParameter("merId"));
        Merchandise merchandise = merchandiseService.getMerById(merId);
        totalPrice = merchandise.getPrice();
        orderList.add(new CartElement(merchandise, 1));
        System.out.println("totalPrice:" + totalPrice);

        session.setAttribute("orderList", orderList);
        session.setAttribute("totalPrice", totalPrice);
        System.out.println("totalprice " + totalPrice);
        return "orderCheckout";
    }

    //购物车结算
    @RequestMapping(value = "/purchaseWithSC")
    public String purchaseWithSC(@RequestBody String model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<CartElement> orderList = new LinkedList<>();//初始化一个结算清单
        double totalPrice = 0;

        //购物车结算
        ShoppingCartService shoppingCartService = (ShoppingCartService) session.getAttribute("shoppingCartService");
        //将购物车中选中的商品添加进结算清单内，待完善，此步将购物车内所有物品都添加进购物车内
        //获得购物车中的商品列表
        List<CartElement> cartElements = shoppingCartService.getShoppingCart().getMerchandises();
        JSONObject jsonObject = new JSONObject(model);//待修正
        System.out.println(model);
        System.out.println(jsonObject.toString());
        Iterator ptr = jsonObject.keys();
        System.out.println("I am in purchase with shopping cart" + jsonObject.length() + ptr.hasNext());
        //2020.1.6解决了后台接收不到json数据的bug，数据的解析格式还有问题
        int merId = jsonObject.getInt("merId");
        int number = jsonObject.getInt("number");
        for (CartElement cartElement : cartElements) {
            int tmp = cartElement.getMerchandise().getMerId();
            if (merId == tmp) {
                cartElement.setNumber(number);
            }
            System.out.println("tmp" + tmp);
        }
        System.out.println("I am here");
            /*while (ptr.hasNext()) {//貌似进不了循环 2020.1.4
                int merId = jsonObject.getInt("merId");
                int number = jsonObject.getInt("number");
                System.out.println("merId" + merId + "number" + number);
                //获得商品列表
                for (CartElement cartElement : cartElements) {
                    int tmp = cartElement.getMerchandise().getMerId();
                    if (merId == tmp) {
                        cartElement.setNumber(number);
                    }
                    System.out.println("tmp" + tmp);
                }
                System.out.println("I am here");
            }*/

        //修正购物车中商品数目
        ShoppingCart choosed = new ShoppingCart();
        choosed.setMerchandises(cartElements);
        shoppingCartService.setShoppingCart(choosed);
        orderList.addAll(shoppingCartService.getShoppingCart().getMerchandises());//将商品都添加进购物车内
        totalPrice = shoppingCartService.windUp();

        session.setAttribute("orderList", orderList);
        session.setAttribute("totalPrice", totalPrice);
        System.out.println("totalprice " + totalPrice);
        return "orderCheckout";
    }

    //查看购物车界面
    @RequestMapping("/shoppingCart")
    public String shoppingCart(HttpSession session, Model model) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return userLogin(model);
        } else {
            return "shoppingCart";
        }
    }

    //跳转到订单界面
    @RequestMapping("/orderCheckout")
    public String orderCheckout() {
        return "orderCheckout";
    }

    //订单写入数据库并减少库存
    @RequestMapping("/payOrder")
    @ResponseBody
    public String payOrder(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        ShoppingCartService shoppingCartService = (ShoppingCartService) session.getAttribute("shoppingCartService");

        if (loginUser != null && shoppingCartService != null) {
            for (CartElement cartElement : shoppingCartService.getShoppingCart().getMerchandises()) {
                Order order = new Order();
                order.setLoginUser(loginUser);
                System.out.println("loginUserInfo " + loginUser.toString());
                order.setMerchandise(cartElement.getMerchandise());
                order.setMerQuantity(cartElement.getNumber());
                order.setTotalPrice(cartElement.getMerchandise().getPrice());
                orderService.saveOrder(order);
                System.out.println(order.toString());
            }
        }
        return "success";
    }
}
