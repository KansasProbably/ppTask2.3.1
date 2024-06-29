package web.controller;

import org.springframework.ui.ModelMap;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }



    @GetMapping("/new")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") @Valid User user) {
            userService.addUser(user);
            return "redirect:/users";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(@RequestParam("id") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String updateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid User user) {
            userService.updateUser(user);
            return "redirect:/users";
    }
}