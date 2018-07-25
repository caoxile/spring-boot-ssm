package ${basePackage}.${module}.service.impl;

import ${basePackage}.${module}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.${module}.model.${modelNameUpperCamel};
import ${basePackage}.${module}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Author ${author}
 * @Create ${date}
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends BaseService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
