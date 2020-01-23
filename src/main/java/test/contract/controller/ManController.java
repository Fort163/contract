package test.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.contract.form.ManCreateForm;
import test.contract.form.ManSearchForm;
import test.contract.service.IManService;
import test.contract.vo.ManSearchVO;
import test.contract.vo.ManVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/page/man")
public class ManController {

    @Autowired
    private IManService iManService;

    @RequestMapping("/search")
    public Map<String, List<ManSearchVO>> search(ManSearchForm manSearchForm) {
        Map<String, List<ManSearchVO>> map = new HashMap();
        map.put("result",iManService.search(manSearchForm));
        return map;
    }

    @RequestMapping("/create")
    public Boolean search(ManCreateForm manCreateForm) {
        return iManService.create(manCreateForm);
    }

    @RequestMapping("/getMan")
    public ManVO getMan(@RequestParam(value = "manId")Long manId) {
        return iManService.getMan(manId);
    }



}
