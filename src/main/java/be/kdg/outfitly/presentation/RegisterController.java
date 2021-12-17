package be.kdg.outfitly.presentation;

import be.kdg.outfitly.exceptions.EmailExistsException;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private UserService userService;
//    private final Map<String, String> countries = Countries.getCountryCodesAndNames();

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String register(Model model){
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("countryCodes", Locale.getISOCountries());
//        model.addAttribute("countryNames", countries.values());
//        model.addAttribute("countries", countries.entrySet());
//        logger.debug(countries.entrySet().toString());

        return "register";
    }

    @ExceptionHandler(EmailExistsException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This location is not found in the database")
    public ModelAndView handleError(HttpServletRequest req, EmailExistsException exception) {
        logger.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("emailexistserror");
        return modelAndView;
    }

    @PostMapping
    public String processRegister(Model model, @Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            model.addAttribute("countryCodes", Locale.getISOCountries());
            return "register";
        } else {
            //check if mail already exists
//            List<User> users = userService.read();
//            boolean result = users.stream().anyMatch(user -> Objects.equals(user.getEmail(), userDTO.getEmail()));
//            if(result){
//                model.addAttribute("errorMessage", "That email already exists");
//                return "register";
//            } else {
                //No clothes yet
                //add user
            logger.debug("Country code of the registering user: "+userDTO.getCountryCode());
            userService.create(userDTO.getEmail(),userDTO.getPassword(),userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber(), userDTO.getCountry(), userDTO.getCountryCode(), userDTO.getCity(), userDTO.getStreetName(), userDTO.getStreetNumber(), userDTO.getApartmentNumber(), userDTO.getZipcode(), new ArrayList<>());
            return "redirect:/login";
        }
    }
}
