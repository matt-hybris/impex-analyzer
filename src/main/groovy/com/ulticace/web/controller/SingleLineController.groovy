package com.ulticace.web.controller

import com.ulticace.impex.SingleImpexLineAnalyzer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by Matt Rossner on 5/16/16.
 */
@Controller
class SingleLineController {

    @Autowired
    SingleImpexLineAnalyzer singleImpexLineAnalyzer

    @RequestMapping(value = "/single")
    def loadPage() {
        return 'analyzeOne'
    }

    @RequestMapping(value = "/analyze")
    def analyzeOneLine(Model model, @RequestParam String impexLine) {

        model.addAttribute("impexLine", impexLine)
        model.addAttribute("analysis", format(singleImpexLineAnalyzer.analyzeImpexLine(impexLine)))

        'analyzeOne'
    }

    def format(String result) {
        result.replace('\n', '<br/>').replace('\t', '&nbsp;&nbsp;&nbsp;&nbsp;')
    }
}
