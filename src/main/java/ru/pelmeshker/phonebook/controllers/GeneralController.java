package ru.pelmeshker.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pelmeshker.phonebook.entity.PhoneBookEntry;
import ru.pelmeshker.phonebook.service.PhoneBookEntryService;

import java.util.List;
import java.util.Objects;

@Controller
public class GeneralController {

    @Autowired
    private PhoneBookEntryService phoneBookEntryService;

    @RequestMapping("/")
    public String showPhoneBookEntries(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = user.getAuthorities().toString();
        if (Objects.equals(userRole, "[ROLE_ADMIN]")) {
            List<PhoneBookEntry> PhoneBookEntries = phoneBookEntryService.getAllPhoneBookEntries();
            model.addAttribute("PhoneBookEntries", PhoneBookEntries);
        } else {
            List<PhoneBookEntry> PhoneBookEntries = phoneBookEntryService.getUserPhoneBookEntries(user.getUsername());
            model.addAttribute("PhoneBookEntries", PhoneBookEntries);
        }
        return "all-phonebookentry";
    }

    @RequestMapping("/addPhoneBookEntry")
    public String addPhoneBookEntry(Model model) {
        PhoneBookEntry phoneBookEntry = new PhoneBookEntry();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        phoneBookEntry.setAuthorId(userName);
        model.addAttribute(phoneBookEntry);
        return "certain-entry";
    }

    @RequestMapping("/savePhoneBookEntry")
    public String savePhoneBookEntry(@ModelAttribute("phoneBookEntry") PhoneBookEntry phoneBookEntry) {
        phoneBookEntryService.savePhoneBookEntry(phoneBookEntry);
        return "redirect:/";
    }

    @RequestMapping("/updatePhoneBookEntry{id}")
    public String updatePhoneBookEntry(@PathVariable int id, Model model) {
        PhoneBookEntry phoneBookEntry = phoneBookEntryService.getPhoneBookEntry(id);
        model.addAttribute("phoneBookEntry", phoneBookEntry);
        return "certain-entry";
    }


    @RequestMapping("/deletePhoneBookEntry/{id}")
    public String deletePhoneBookEntry(@PathVariable int id) {
        phoneBookEntryService.deletePhoneBookEntry(id);
        return "redirect:/";
    }
}
