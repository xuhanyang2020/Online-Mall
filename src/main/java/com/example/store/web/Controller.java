package com.example.store.web;

import com.example.store.domain.Customer;
import com.example.store.service.*;
import com.example.store.service.Imp.CustomerServiceImp;
import com.example.store.service.Imp.GoodsServiceImp;
import com.example.store.service.Imp.OrderServiceImp;
import com.example.store.service.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "Controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private CustomerService customerService = new CustomerServiceImp();
    private GoodsService goodsService = new GoodsServiceImp();
    private OrderService ordersService = new OrderServiceImp();

    private int totalPageNumber = 0; // 总页数
    private int pageSize = 10; // 每页行数
    private int currentPage = 1; // 当前页数

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("reg")) {
            String userid = req.getParameter("userid");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            String birthday = req.getParameter("birthday");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");

            // 服务器端验证
            List<String> errors = new ArrayList<>();
            if (userid == null || userid.equals("")) {
                errors.add("客户账号不能为空！");
            }
            if (name == null || name.equals("")) {
                errors.add("客户姓名不能为空！");
            }
            if (password == null
                    || password2 == null
                    || !password.equals(password2)) {
                errors.add("两次输入的密码不一致！");
            }

            String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
            if (!Pattern.matches(pattern, birthday)) {
                errors.add("出生日期格式无效！");
            }

            if (errors.size() > 0) { // 验证失败
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("customer_reg.jsp").forward(req, resp);
            } else { // 验证成功
                Customer customer = new Customer();
                customer.setId(userid);
                customer.setName(name);
                customer.setPassword(password);
                customer.setAddress(address);
                customer.setPhone(phone);
                try {
                    Date d = dateFormat.parse(birthday);
                    customer.setBirthday(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // 注册
                try {
                    // 注册成功
                    customerService.register(customer);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                } catch (ServiceException e) {
                    // 客户ID已经注册
                    errors.add("客户ID已经注册！");
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("customer_reg.jsp").forward(req, resp);
                }

            }
        } else if (action.equals("login")){
            String userid = req.getParameter("userid");
            String password = req.getParameter("password");

            Customer customer = new Customer();
            customer.setId(userid);
            customer.setPassword(password);
            boolean login = customerService.login(customer);
            if (!login){
                List<String> errors = new ArrayList<>();
                errors.add("userid and password not correct");
                req.setAttribute("errors",errors);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else{
                HttpSession session = req.getSession();
                // set the information of the customer into the session
                session.setAttribute("customer", customer);
                req.getRequestDispatcher("main.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
