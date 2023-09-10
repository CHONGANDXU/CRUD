package com.little.controller;

import com.little.entity.Miao;
import com.little.service.impl.MiaoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */

@RestController
@RequestMapping("/miao")
@CrossOrigin(origins = "*")
class MiaoController {

    private final MiaoServiceImpl miaoService;

    MiaoController(MiaoServiceImpl miaoService) {
        this.miaoService = miaoService;
    }

    @Operation(summary = "MiaoController类的search方法，对应/search接口")
    @GetMapping(value = "/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam("chineseTerm") String chineseTerm,
                                      @RequestParam("pageSize") String pageSize,
                                      @RequestParam("currentPage") String currentPage) {
        // System.out.println("==================MiaoController中的方法使用啦====================");
        System.out.println(chineseTerm + " " + pageSize + " " + currentPage);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("data", miaoService.search(chineseTerm, currentPage, pageSize));
        map1.put("code", 1);
        return map1;
    }

    @Operation(summary = "MiaoController类的getTotalCount方法，对应/total接口")
    @GetMapping(value = "/total")
    @ResponseBody
    public Map<String, Map<String, Long>> getTotalCount(@RequestParam("chineseTerm") String chineseTerm) {
        Map<String, Long> map1 = new HashMap<>();
        map1.put("count", miaoService.getTotalCount(chineseTerm));
        Map<String, Map<String, Long>> map2 = new HashMap<>();
        map2.put("data", map1);
        return map2;
    }

    @Operation(summary = "MiaoController类的deleteById方法，对应/delete接口")
    @GetMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> deleteById(@RequestParam("code") String code) {
        int affectedRows = miaoService.deleteById(code);
        Map<String, Object> map = new HashMap<>();
        if (affectedRows != 0) {
            map.put("code", 0);
            map.put("msg", "删除成功");
        } else {
            map.put("code", 1);
            map.put("msg", "删除失败");
        }
        return map;
    }

    @Operation(summary = "MiaoController类的insertOne方法，对应/insert接口")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Map<String, Object> insertOne(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Miao entityMiao = getEntityMiao(map);
        System.out.println(entityMiao);
        int affectedRows = miaoService.insertOne(getEntityMiao(map));
        System.out.println("insertAffectedRows "+affectedRows);

        Map<String, Object> responseMap = new HashMap<>();
        if (affectedRows != 0) {
            responseMap.put("code", 0);
            responseMap.put("msg", "添加成功");
        } else {
            responseMap.put("code", 1);
            responseMap.put("msg", "添加失败");
        }
        return responseMap;
    }

    @Operation(summary = "MiaoController类的update方法，对应/update接口")
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map<String, Object> map){
        System.out.println(map);
        Miao entityMiao = getEntityMiao(map);
        System.out.println(entityMiao);
        int affectedRows = miaoService.update(entityMiao);
        System.out.println("insertAffectedRows "+affectedRows);

        Map<String, Object> responseMap = new HashMap<>();
        if (affectedRows != 0) {
            responseMap.put("code", 0);
            responseMap.put("msg", "修改成功");
        } else {
            responseMap.put("code", 1);
            responseMap.put("msg", "修改失败");
        }
        return responseMap;
    }

    @Operation(summary="从ResponseBody中接收并初始化一个Miao实体类")
    private Miao getEntityMiao(Map<String, Object> map) {
        Miao miao = new Miao();

        String code = map.get("code") == null ? "" : map.get("code").toString();
        String englishDefinitionDescription = map.get("englishDefinitionDescription") == null ? "" : map.get("englishDefinitionDescription").toString();
        String chineseTerm = map.get("chineseTerm") == null ? "" : map.get("chineseTerm").toString();
        String pinyinTerm = map.get("pinyinTerm") == null ? "" : map.get("pinyinTerm").toString();
        String chineseSynonyms = map.get("chineseSynonyms") == null ? "" : map.get("chineseSynonyms").toString();
        String miaoLanguage = map.get("miaoLanguage") == null ? "" : map.get("miaoLanguage").toString();
        String englishTerm = map.get("englishTerm") == null ? "" : map.get("englishTerm").toString();
        String synonyms = map.get("synonyms") == null ? "" : map.get("synonyms").toString();
        String source = map.get("source") == null ? "" : map.get("source").toString();

        if(!code.isEmpty()){
            miao.setCode(Integer.parseInt(code));
        }
        if(!englishDefinitionDescription.isEmpty()){
            miao.setEnglishDefinitionDescription(englishDefinitionDescription);
        }
        if(!chineseTerm.isEmpty()){
           miao.setChineseTerm(chineseTerm);
        }
        if(!pinyinTerm.isEmpty()) {
            miao.setPinyinTerm(pinyinTerm);
        }
        if(!chineseSynonyms.isEmpty()) {
            miao.setSynonyms(chineseSynonyms);
        }
        if(!miaoLanguage.isEmpty()) {
            miao.setMiaoLanguage(miaoLanguage);
        }
        if(!englishTerm.isEmpty()) {
            miao.setEnglishTerm(englishTerm);
        }
        if(!synonyms.isEmpty()) {
            miao.setSynonyms(synonyms);
        }
        if(!source.isEmpty()){
            miao.setSource(source);
        }

        return miao;
    }

}

