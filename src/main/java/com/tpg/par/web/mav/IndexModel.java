package com.tpg.par.web.mav;

import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

public class IndexModel {
    private MessageSource messageSource;

    public IndexModel(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void addAttributes(Locale locale, Model model) {
        String[] emptyValue = new String[0];

        String title = messageSource.getMessage("index.title", emptyValue, locale);
        String h1 = messageSource.getMessage("index.h1", emptyValue, locale);
        String h2 = messageSource.getMessage("index.h2", emptyValue, locale);
        String searchSummary = messageSource.getMessage("index.searchSummary", emptyValue, locale);
        String searchForTitle = messageSource.getMessage("index.searchForTitle", emptyValue, locale);
        String searchButtonText = messageSource.getMessage("search.button.text", emptyValue, locale);
        String footerInfo = messageSource.getMessage("footer.info", emptyValue, locale);

        model.addAttribute("offset", 0);
        model.addAttribute("limit", 10);
        model.addAttribute("pageNumber", 0);
        model.addAttribute("title", title);
        model.addAttribute("welcome", h1);
        model.addAttribute("simpleSearchSubTitle", h2);
        model.addAttribute("searchSummary", searchSummary);
        model.addAttribute("searchForTitle", searchForTitle);
        model.addAttribute("searchButtonText", searchButtonText);
        model.addAttribute("footerInfo", footerInfo);
    }
}
