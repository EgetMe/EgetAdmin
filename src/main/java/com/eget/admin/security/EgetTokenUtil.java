package com.eget.admin.security;

import com.eget.admin.common.properties.EgetProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author geforce
 * @date 2017/12/11
 */
@Component
public class EgetTokenUtil extends AbstractTokenUtil {

    @Autowired
    private EgetProperties egetProperties;


    public EgetProperties getEgetProperties() {
        return egetProperties;
    }

    public void setEgetProperties(EgetProperties egetProperties) {
        this.egetProperties = egetProperties;
    }

    @Override
    public Long getExpiration() {
        return egetProperties.getToken().getExpiration();
    }

    @Override
    public String getKey() {
        return egetProperties.getToken().getKey();
    }


}
