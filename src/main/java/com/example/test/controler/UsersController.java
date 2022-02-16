package com.example.test.controler;

import com.example.test.model.Role;
import com.example.test.model.Users;
import com.example.test.service.RoleService;
import com.example.test.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    RoleService roleService;
    @Value("${uploadPart}")
    private String uploadPart;

    @ModelAttribute("users")
    public  Users getUsers(){
        return  new Users();
    }

    @GetMapping("/users")
    public ModelAndView showAll(@RequestParam (defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("users",usersService.findAll(PageRequest.of(page,2)));
        return modelAndView;
    }
    @ModelAttribute("role")
        public List<Role> showRole(){
            return roleService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("createUsers");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }

    @PostMapping("create")
    public Object add(@Valid @ModelAttribute(value = "users") Users users, BindingResult bindingResult, @RequestParam MultipartFile uppImg) {

        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("createUsers");
            return modelAndView;
        }
        String filename = uppImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppImg.getBytes(),new File(uploadPart+"img/" + filename));
            users.setAvatar("img/" +filename);
        } catch (IOException e) {
            users.setAvatar("http://pm1.narvii.com/7236/db9fa5d064fbd91fc4aa18262f5b81acc12db043r1-2048-2048v2_uhq.jpg");
            e.printStackTrace();
        }
        usersService.save(users);
        return "redirect:/users";
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("editUsers");
        modelAndView.addObject("users", usersService.findById(id));
        return modelAndView;
    }

    @PostMapping("edit")
    public String editUser(@Valid @ModelAttribute(value = "users") Users users, BindingResult bindingResult, @RequestParam ("uppImg") MultipartFile uppImg) {
//        validate_userName.validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "redirect:/editUsers";
        }
        if (uppImg.getSize() != 0) {
            String file1 = uploadPart + users.getAvatar();
            File file = new File(file1);
            file.delete();
            String nameFile = uppImg.getOriginalFilename();
            try {
                FileCopyUtils.copy(uppImg.getBytes(), new File(uploadPart +"img/"+ nameFile));
                users.setAvatar("img/" + nameFile);

            } catch (IOException e) {
                users.setAvatar("http://pm1.narvii.com/7236/db9fa5d064fbd91fc4aa18262f5b81acc12db043r1-2048-2048v2_uhq.jpg");
                e.printStackTrace();
            }
        }
        usersService.save(users);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public ModelAndView deleteForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("deleteUsers");
        modelAndView.addObject("users", usersService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        Users users = usersService.findById(id);
        if(users.getAvatar().isEmpty()){
            usersService.delete(id);
            return "redirect:/users";
        }
        String filedelete = users.getAvatar().replaceAll("img/","");
        String file1 = uploadPart + "img/" +filedelete;
        File file = new File(file1);
        if(file.exists()){
            file.delete();
        }
        usersService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/search")
    public ModelAndView searchByName(@RequestParam (value = "search") String search,@RequestParam (defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("users", usersService.findUsersByName(search,PageRequest.of(page,2)));
        return modelAndView;
    }



}
