package com.zstu.ky.kyykt.demo.Controller;

import com.zstu.ky.RestResult.Result;
import com.zstu.ky.RestResult.ResultGenerator;
import com.zstu.ky.kyykt.demo.Model.JudgePage;
import com.zstu.ky.kyykt.demo.Service.SimHashService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SimHash")
public class SimHashController {

    final
    SimHashService simHashService;

    public SimHashController(SimHashService simHashService) {
        this.simHashService = simHashService;
    }

    @GetMapping
    public Result getPara() {
        return ResultGenerator.genSuccessResult("方法：Post，参数1：page1，参数2：page2，返回值：重复程度（由0-10，数值越小重复程度越大）");
    }

    @PostMapping
    public Result Caculat(@RequestBody JudgePage judgePage) {
        if (judgePage == null || judgePage.getPage1() == null || judgePage.getPage2() == null) {
            return ResultGenerator.genFailResult("Data is missing.");
        }

        return ResultGenerator.genSuccessResult(simHashService.CacuSimHash(judgePage.getPage1(), judgePage.getPage2()));
    }
}
