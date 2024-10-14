package com.my.articles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mapping")
public class RedirectController {
    @GetMapping("")
    public String testMain() {
        return "/test/testMain";
    }

    @GetMapping("page")
    public String view(Model model) {
        return "/test/page";
    }

    @RequestMapping(value = "requestMapping",
            method = RequestMethod.GET)
    public String requestMapping(Model model) {
        String msg = "RequestMapping";
        model.addAttribute("msg", msg);
//        return "redirect:page";
        return "/test/page";
    }

    @GetMapping("modelAndView")
    public ModelAndView modelAndView(Model model) {
        String msg = "ModelAndView";
        model.addAttribute("msg", msg);
        return new ModelAndView("redirect:page");
    }

    @GetMapping("redirectView")
    public RedirectView redirectView(RedirectAttributes redirectAttributes) {

        String msg = "redirectView";
        redirectAttributes.addFlashAttribute("msg", msg);
        return new RedirectView("page");
    }

    @GetMapping("redirectView2")
    public String redirectView2(RedirectAttributes redirectAttributes) {
        String msg = "redirectView2";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:page";
    }

    @GetMapping("redirect_1")
    public String redirect_1() {
        return "redirect:http://naver.com";
    }

    @GetMapping("redirect_2")
    public ModelAndView redirect_2() {
        String url = "redirect:http://daum.net";
//        String url = "redirect:http://localhost:8081/main";
        return new ModelAndView(url);
    }
}