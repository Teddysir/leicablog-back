//package leica.blog.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/login/oauth2",produces = "application/json")
//public class LoginController {
//
//    private final LoginService loginService;
//
//    @GetMapping("/code/{registrationId}")
//    public void googleLogin(@RequestParam String code, @PathVariable String registrationId){
//        loginService.googleSocialLogin(code,registrationId);
//        System.out.println("code = " + code);
//        System.out.println("registerId = " + registrationId);
//    }
//}
