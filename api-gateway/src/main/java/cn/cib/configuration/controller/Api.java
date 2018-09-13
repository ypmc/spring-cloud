package cn.cib.configuration.controller;

import cn.cib.configuration.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangkang lou
 */
@RestController
public class Api {
    @Autowired
    private VersionService versionService;

    @GetMapping("v")
    public String version() {
        return versionService.version();
    }
}
