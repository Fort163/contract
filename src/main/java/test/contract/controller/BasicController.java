package test.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kutyakov on 18.01.2020.
 */
@Controller
@RequestMapping(value="/page")
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

}
