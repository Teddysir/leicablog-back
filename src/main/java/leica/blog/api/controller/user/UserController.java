package leica.blog.api.controller.user;

import leica.blog.api.service.UserService;
import leica.blog.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse getUser(){
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        leica.blog.api.entity.user.User  user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }
}
