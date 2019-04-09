package ${basePackage}.${module}.controller;

import ${basePackage}.common.core.Result;
import ${basePackage}.common.core.ResultGenerator;
import ${basePackage}.common.core.BaseController;
import ${basePackage}.common.core.QueryRequest;
import ${basePackage}.${module}.model.${modelNameUpperCamel};
import ${basePackage}.${module}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author ${author}
 * @Create ${date}
 */
@RestController
@RequestMapping("/${module}${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller extends BaseController{
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @PostMapping("/list")
    public Result list(@RequestParam QueryRequest request) {
        PageInfo pageInfo = selectByPage(request,()->${modelNameLowerCamel}Service.findAll());
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
