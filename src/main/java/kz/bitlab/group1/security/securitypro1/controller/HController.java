package kz.bitlab.group1.security.securitypro1.controller;


import kz.bitlab.group1.security.securitypro1.service.AService;
import kz.bitlab.group1.security.securitypro1.service.UsserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HController {
    private final UsserService usserService;
    private final AService aService;
    @GetMapping(value = "/home")
    public String home(){return "home";}

    @GetMapping(value = "/registr")
    public String registr(){return "registr";}

    @GetMapping(value = "/sign")
    public String sign(){return "sign";}

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/prof")
    public String prof(){return "prof";}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-pan")
    public String adm(){return "adm";}

//    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
//    @GetMapping(value = "/moderator-pan")
//    public String moderat(){return "moderat";}

    @GetMapping(value = "/forbid")
    public String forbid(){return "forbid";}

    @PostMapping(value = "/registr")
    public String registr(@RequestParam(name = "user_email") String email,
                          @RequestParam(name = "user_password") String password,
                          @RequestParam(name = "user_re_password") String rePassword,
                          @RequestParam(name = "user_full_name") String fullName){
        if (aService.registerUsser(email, password, rePassword, fullName)!=null){
            return "redirect:/registr?success";
        }else {
            return "redirect:/registr?error";
        }
    }

    @PostMapping(value = "/update-pass")
    public String updatePass(@RequestParam(name = "user_old_password")String oldPassword,
                             @RequestParam(name = "user_new_password")String newPassword,
                             @RequestParam(name = "user_re_password")String repeatNewPassword){
        if (aService.updatePassword(oldPassword, newPassword, repeatNewPassword)!=null){
            return "redirect:/prof?passwordsuccess";
        }else {
     return "redirect:/prof?passworderror";
        }
    }
}
