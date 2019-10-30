package com.demomvc.demomvc

import org.apache.juli.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/user")
class UserCRUD {
    var users= mutableListOf<User> ();
    var userId=1;
    private val LOGGER = LogFactory.getLog("UserCRUD.class")

    @GetMapping("/create")
    fun create(model: Model): String {
        model.addAttribute("user", User())
        return "userCreate"
    }
    @PostMapping("/createUser")
    fun saveUser(@ModelAttribute("user") userUpdate: User): RedirectView {
        if(userUpdate.id == 0){
            userUpdate.id = userId;
            userId++;
            users.add(userUpdate);
        }else{
            var usUpdate =  users.find {us -> us.id.equals(userUpdate.id)}
            if (usUpdate != null) {
                usUpdate.user = userUpdate.user
                usUpdate.pass = userUpdate.pass
            };
        }
        return RedirectView("/user/getAll");
    }
    @PostMapping("/updateUser")
    fun updateUser(@ModelAttribute("user") user: User): RedirectView {
        users.add(user);
        return RedirectView("/user/getAll");
    }
    @GetMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: String): RedirectView {
        val user =  users.find { us -> us.id.equals(id) }
        users.remove(user);
        return RedirectView("/user/getAll");
    }

    @GetMapping("/edit/{id}")
    fun get(@PathVariable("id") id: String): ModelAndView {
        val user =  users.find { id.equals(id) }
        var mav = ModelAndView("userCreate")
        mav.addObject("user", user)
        return mav
    }

    @GetMapping("/getAll")
    fun getAll():ModelAndView{
        var mav = ModelAndView("allUser");
        mav.addObject("Users",users)
        return mav
    }
}