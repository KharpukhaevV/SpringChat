package app.controller;

import app.model.Message;
import app.model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {

    @GetMapping
    public String login(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/chat")
    public String chat(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "login";
        }
        model.addAttribute("user", user);
        return "chat";
    }

    @MessageMapping("/msg")
    @SendTo("/chatting")
    public Message chatting(Message message) {

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        return new Message(HtmlUtils.htmlEscape("(" + date + ") " + message.getSender() + " : " + message.getContent()));
    }

}