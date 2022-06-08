package com.heytaksi.heytaksibackend.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "heytaksi")
public class HeyTaksiSettings {

}
