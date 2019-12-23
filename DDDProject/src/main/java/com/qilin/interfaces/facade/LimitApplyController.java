package com.qilin.interfaces.facade;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.qilin.interfaces.dto.LimitApplyDTO;
import com.qilin.interfaces.dto.LimitApplyRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/acl")
public class LimitApplyController {


    @RequestMapping(value = "/creditApplication",method = RequestMethod.POST)
    public String limitApply(@RequestBody @Valid LimitApplyDTO limitApplyDTO){
        System.out.printf("success:"+limitApplyDTO.getName()+"  "+limitApplyDTO.getShopid());

        LimitApplyRequest limitApplyRequest =DTOConvertReq(limitApplyDTO,LimitApplyRequest.class);

        System.out.printf("limitApply:"+limitApplyRequest.getName());


        return "";

    }

    public  <T> T DTOConvertReq(Object source, Class<T> target) {
        if(source==null){
            return null;
        }
        T object=null;
        try {
             object = target.newInstance();
            BeanUtils.copyProperties(source,object,getNullPropertyNames(source));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;

    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

/*    @RequestMapping(value = "/loanApplication",method = RequestMethod.POST)
    public String loan(){
        return "";

    }

    @RequestMapping(value = "/repayApplication",method = RequestMethod.POST)
    public String repayment(){
        return "";

    }*/
}
