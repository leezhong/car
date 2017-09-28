package com.leezhong.shiro;

import com.alibaba.druid.util.StringUtils;
import com.leezhong.dao.ResourceMapper;
import com.leezhong.domain.Resource;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{

    @Autowired
    private ResourceMapper resourceMapper;

    private String filterChainDefinitions;
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public Ini.Section getObject() throws Exception {

        Ini ini = new Ini();
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

        List<Resource> resources = resourceMapper.getAll();
        resources.forEach(x->{
            String value = x.getValue();
            String permission = x.getPermission();
            if(!StringUtils.isEmpty(value)&&!StringUtils.isEmpty(permission)){
                section.put(value,permission);
            }
        });

        return section;
    }

    public Class<?> getObjectType() {
        return this.getClass();
    }

    public boolean isSingleton() {
        return false;
    }


}
