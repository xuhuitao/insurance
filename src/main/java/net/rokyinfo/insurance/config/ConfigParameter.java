package net.rokyinfo.insurance.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yuanzhijian
 */
@Component("configParameter")
@Data
public class ConfigParameter {

    @Value("${insurance.res.image.request.urlPrefix.bill}")
    private String urlBillPrefix;

    @Value("${insurance.res.image.request.urlPrefix.scooter}")
    private String urlScooterPrefix;

}
