package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ValidatorController {


    @Autowired
    private PhoneNumberValidator phoneNumberValidator;

    @GetMapping("/")
    public String listUploadedFiles(Model model) {
        return "validateForm";
    }

    @PostMapping("/validate")
    @ResponseBody
    public String validate(@RequestParam("countryCode") String countryCode,
                                         @RequestParam("phoneNumber") String phoneNumber,
                                         RedirectAttributes redirectAttributes) {

        try {
                return phoneNumberValidator.validateNumber(countryCode, phoneNumber);
            }catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                    "Error in validating phone number");
         }
        return null;
    }
}
