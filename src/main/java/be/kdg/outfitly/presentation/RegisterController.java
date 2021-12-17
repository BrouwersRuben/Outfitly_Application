package be.kdg.outfitly.presentation;

import be.kdg.outfitly.exceptions.EmailExistsException;
import be.kdg.outfitly.presentation.dto.UserDTO;
import be.kdg.outfitly.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Locale;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("countryCodes", Locale.getISOCountries());
        return "register";
    }

    @ExceptionHandler(EmailExistsException.class)
    public ModelAndView handleError(HttpServletRequest req, EmailExistsException exception) {
        logger.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("emailexistserror");
        return modelAndView;
    }

    @PostMapping
    public String processRegister(Model model, @Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            model.addAttribute("countryCodes", Locale.getISOCountries());
            return "register";
        } else {
            userService.create(userDTO.getEmail(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getWashDay(), userDTO.getPhoneNumber(), userDTO.getCountry(), userDTO.getCountryCode(), userDTO.getCity(), userDTO.getStreetName(), userDTO.getStreetNumber(), userDTO.getApartmentNumber(), userDTO.getZipcode(), new ArrayList<>());
            return "redirect:/login";
        }
    }
}
