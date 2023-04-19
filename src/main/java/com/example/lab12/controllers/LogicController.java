package com.example.lab12.controllers;
import com.example.lab12.entities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogicController {
    private final Logic logic;

    @Autowired
    public LogicController(Logic logic) {

        this.logic = logic;
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("start") double start, @RequestParam("end") double end,
                            @RequestParam("step") double step, @RequestParam("a") double a, @RequestParam("b") double b, Model model) {

        if (end < start) {

            throw new IllegalArgumentException();
        }

        double[] xValues = logic.xArrayFill(start, end, step);
        double[] yValues = logic.yArrayFill(xValues, a, b);
        Result[] values = logic.convertToResult(xValues, yValues);

        model.addAttribute("minY", logic.getMinElement(yValues));
        model.addAttribute("minX", logic.getMinElementArgument(yValues));
        model.addAttribute("maxY", logic.getMaxElement(yValues));
        model.addAttribute("maxX", logic.getMaxElementArgument(yValues));
        model.addAttribute("average", logic.getAverage(yValues));
        model.addAttribute("sum", logic.getSum(yValues));
        model.addAttribute("result", values);

        return "calculate";
    }
}
