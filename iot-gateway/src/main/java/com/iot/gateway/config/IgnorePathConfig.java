package com.iot.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "security")
public class IgnorePathConfig {
    private static final Logger log = LoggerFactory.getLogger(IgnorePathConfig.class);

    private Map<String, List<String>> ignorePath;

    public Map<String, List<String>> getIgnorePath() {
        return ignorePath;
    }

    public void setIgnorePath(Map<String, List<String>> ignorePath) {
        this.ignorePath = ignorePath;
    }

    @Override
    public String toString() {
        return "IgnorePathConfig{" +
                "ignorePath=" + ignorePath +
                '}';
    }

    public List<String> getMergePaths(){
        Map<String, List<String>> pathMap = getIgnorePath();
        if (Objects.isNull(pathMap)) {
            return List.of();
        }
        var list = pathMap.values().stream()
                .flatMap(Collection::stream)
                .distinct()
                .toList();
        log.info("ignore Path list={}", list);
        return list;
    }

    public Boolean exist(String path){
        return getMergePaths().contains(path);
    }


    public String[] getMergePathsArray(){
        return getMergePaths().toArray(new String[0]);
    }
}
