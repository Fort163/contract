package test.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kutyakov on 18.01.2020.
 */
@Controller
public class BasicController {

    @RequestMapping(value={"/","/basic"}, method = RequestMethod.GET)
    public String basic()
    {
        return "basicPage";
    }

    @RequestMapping(value = "/mainContractPage",method = RequestMethod.GET)
    public String mainContractPage(){
        return "mainContract";
    }

    @RequestMapping(value = "/createContractPage",method = RequestMethod.GET)
    public ModelAndView createContractPage(@RequestParam(value = "contractId",required = false) Long contractId){
        ModelAndView model = new ModelAndView("createContract");
        model.addObject("contractId", contractId);
        return model;
    }

    @RequestMapping(value = "/handbook",method = RequestMethod.GET)
    public ModelAndView handbook(@RequestParam(value = "token") Long token){
        ModelAndView model = new ModelAndView("handbook");
        model.addObject("token", token);
        return model;
    }


}
