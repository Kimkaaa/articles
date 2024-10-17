package com.my.articles.controller;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.repository.ArticleRepository;
import com.my.articles.service.ArticleService;
import com.my.articles.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    PaginationService paginationService;

    @GetMapping("")
    public String showAllArticles(Model model,
                                  @PageableDefault(
                                  page = 0,
                                  size = 5,
                                  sort = "id",
                                  direction = Sort.Direction.DESC)
                                  Pageable pageable) {
//        List<ArticleDTO> dtoList = articleService.getAllArticle();
        Page<ArticleDTO> paging = articleService.getArticlePage(pageable);

        // 페이징 정보를 확인
        // 전체 페이지 수
        int totalPage = paging.getTotalPages();
        int currentPage = paging.getNumber();
        System.out.println("totalPage = " + totalPage);
        System.out.println("currentPage = " + currentPage);

        // 페이지 블럭 처리
        List<Integer> barNumbers = paginationService
                .getPaginationBarNumber(currentPage, totalPage);
        System.out.println("========== " + barNumbers.toString());

        model.addAttribute("pageBars", barNumbers);
        model.addAttribute("articles", paging);
        return "/articles/show_all";
    }

    @GetMapping("new")
    public String newArticle(Model model) {
        model.addAttribute("dto", new ArticleDTO());
        return "/articles/new";
    }

    @PostMapping("create")
    public String createArticle(ArticleDTO dto) {
        articleService.insertArticle(dto);
        return "redirect:/articles";
    }

    @GetMapping("{id}")
    public String showOneArticle(@PathVariable("id") Long id, Model model) {
        ArticleDTO dto = articleService.getOneArticle(id);
        model.addAttribute("dto", dto);
        return "/articles/show";
    }

    @GetMapping("{id}/update")
    public String viewUpdateArticle(@PathVariable("id")Long id,
                                    Model model) {
        model.addAttribute("dto",
                articleService.getOneArticle(id));
        return "/articles/update";
    }

    @PostMapping("update")
    public String updateArticle(ArticleDTO dto) {
        String url = "redirect:" + dto.getId();
        articleService.updateArticle(dto);
        return url;
    }

    @GetMapping("{id}/delete")
    public String deleteArticle(@PathVariable("id")Long id,
                                RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        redirectAttributes.addFlashAttribute("msg","정상적으로 삭제되었습니다.");
        return "redirect:/articles";
    }
}