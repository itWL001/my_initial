package org.initial.heart.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.initial.heart.entity.Seller;
import org.initial.heart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/11")
    public String test(){
        //Assert.notNull(null,"参数为空");
        return "111";
    }
    @GetMapping("/users")
    public String getUser1(String username, HttpServletRequest request){
        return "传入的参数"+username;
    }

    @GetMapping("/users/{username}")
    public String getUser2(@PathVariable String username){
        return "传入的参数"+username;
    }

    @Autowired
    SellerService sellerService;
    @GetMapping("/seller")
    public String getSeller(){
        List<Seller> list = sellerService.list();
        System.out.println(list);
        return list.toString();
    }

}
