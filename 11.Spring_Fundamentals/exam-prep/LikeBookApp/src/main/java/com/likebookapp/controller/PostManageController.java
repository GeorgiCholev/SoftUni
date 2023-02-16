package com.likebookapp.controller;

import com.likebookapp.model.dto.PostAddDTO;
import com.likebookapp.model.sessionEntity.CurrentUser;
import com.likebookapp.service.PostManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostManageController {

    private final PostManageService postManageService;
    private final CurrentUser currentUser;

    public PostManageController(PostManageService postManageService, CurrentUser currentUser) {
        this.postManageService = postManageService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("postAddDTO")
    public void putPostAddDTOAsAttr(Model model) {
        model.addAttribute("postAddDTO", new PostAddDTO());
    }

    @GetMapping("/post-add")
    public String getPostAddView() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        return "post-add";
    }

    @PostMapping("/post-add")
    public String addPost(@Valid PostAddDTO postAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAddDTO", postAddDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.postAddDTO", bindingResult);
            return "redirect:/post-add";
        }

        postManageService.addPostForUser(postAddDTO, currentUser.getId());
        return "redirect:/home";
    }

    @GetMapping("/post-remove/{id}")
    public String removePost(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        postManageService.removePost(id);
        return "redirect:/home";
    }

    @GetMapping("/post-like/{id}")
    public String likePost(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        postManageService.likePost(id, currentUser.getId());
        return "redirect:/home";
    }
}
