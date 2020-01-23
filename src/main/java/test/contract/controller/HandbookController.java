package test.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.contract.form.HandbookForm;
import test.contract.model.CoefficientConstruction;
import test.contract.service.IHandbookService;
import test.contract.vo.HandbookVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/page/handbook")
public class HandbookController {

    @Autowired
    private IHandbookService iHandbookService;

    @RequestMapping("/getPropertyTypeList")
    public Map<String, List<HandbookVO>> getPropertyTypeList() {
        Map<String, List<HandbookVO>> map = new HashMap();
        map.put("result",iHandbookService.getPropertyTypeList());
        return map;
    }

    @RequestMapping("/getCoefficientAreaList")
    public Map<String, List<HandbookVO>> getCoefficientAreaList() {
        Map<String, List<HandbookVO>> map = new HashMap();
        map.put("result",iHandbookService.getCoefficientAreaList());
        return map;
    }

    @RequestMapping("/getCoefficientConstructionList")
    public Map<String, List<HandbookVO>> getCoefficientConstructionList() {
        Map<String, List<HandbookVO>> map = new HashMap();
        map.put("result",iHandbookService.getCoefficientConstructionList());
        return map;
    }

    @RequestMapping("/createPropertyType")
    public Boolean createPropertyType(HandbookForm handbookForm) {
        return iHandbookService.createPropertyType(handbookForm);
    }

    @RequestMapping("/createCoefficientArea")
    public Boolean createCoefficientArea(HandbookForm handbookForm) {
        return iHandbookService.createCoefficientArea(handbookForm);
    }

    @RequestMapping("/createCoefficientConstruction")
    public Boolean createCoefficientConstruction(HandbookForm handbookForm) {
        return iHandbookService.createCoefficientConstruction(handbookForm);
    }

    @RequestMapping("/archivePropertyType")
    public Boolean archivePropertyType(@RequestParam("id") Long id) {
        return iHandbookService.archivePropertyType(id);
    }

    @RequestMapping("/archiveCoefficientArea")
    public Boolean archiveCoefficientArea(@RequestParam("id") Long id) {
        return iHandbookService.archiveCoefficientArea(id);
    }

    @RequestMapping("/archiveCoefficientConstruction")
    public Boolean archiveCoefficientConstruction(@RequestParam("id") Long id) {
        return iHandbookService.archiveCoefficientConstruction(id);
    }

}
