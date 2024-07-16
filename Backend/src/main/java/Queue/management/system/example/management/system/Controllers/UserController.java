package Queue.management.system.example.management.system.Controllers;

import Queue.management.system.example.management.system.Service.UserService;
import Queue.management.system.example.management.system.Utils.JWT;
import Queue.management.system.example.management.system.beans.Credentials;
import Queue.management.system.example.management.system.beans.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWT jwt;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Credentials user) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        UserDetails userDetails = userService.loginUser(user);
        headers.set("Authorization", "Bearer " + jwt.generateToken(userDetails));
        return new ResponseEntity<>(true, headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDetails data) throws Exception {
        userService.register(data);
    }
}

