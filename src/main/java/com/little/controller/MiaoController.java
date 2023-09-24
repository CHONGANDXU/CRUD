package com.little.controller;

import com.little.entity.Miao;
import com.little.service.impl.MiaoServiceImpl;
import com.little.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public ResultVO<List<Miao>> search(@RequestParam("chineseTerm") String chineseTerm,
                                       @RequestParam("pageSize") String pageSize,
                                       @RequestParam("currentPage") String currentPage) {
        // System.out.println("==================MiaoController中的方法使用啦====================");
        System.out.println(chineseTerm + " " + pageSize + " " + currentPage);
        List<Miao> search = miaoService.search(chineseTerm, currentPage, pageSize);

        ResultVO<List<Miao>> listResultVO = new ResultVO<>();
        if (search.isEmpty()) {
            listResultVO.fail("查询失败，数据库中没有匹配数据");
        } else {
            listResultVO.success("查询成功");
            listResultVO.add(search);
        }
        return listResultVO;
    }

    @Operation(summary = "MiaoController类的getTotalCount方法，对应/total接口")
    @GetMapping(value = "/total")
    @ResponseBody
    public ResultVO<Map<String, Long>> getTotalCount(@RequestParam("chineseTerm") String chineseTerm) {
        Long totalCount = miaoService.getTotalCount(chineseTerm);

        ResultVO<Map<String, Long>> mapResultVO = new ResultVO<>();
        if (totalCount == 0L) {
            mapResultVO.fail("查询结果为无，请更换您需要查询的条件!!!");
        } else {
            mapResultVO.success("查询成功");
        }
        Map<String, Long> map = new HashMap<>();
        map.put("count", totalCount);
        mapResultVO.add(map);
        return mapResultVO;
    }

    @Operation(summary = "MiaoController类的deleteById方法，对应/delete接口")
    @GetMapping(value = "/delete")
    @ResponseBody
    public ResultVO<Miao> deleteById(@RequestParam("code") String code) {
        int affectedRows = miaoService.deleteById(code);

        ResultVO<Miao> miaoResultVO = new ResultVO<>();
        if (affectedRows != 0) {
            miaoResultVO.success("删除成功");
        } else {
            miaoResultVO.fail("删除失败");
        }
        return miaoResultVO;
    }

    @Operation(summary = "MiaoController类的insertOne方法，对应/insert接口")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ResultVO<Miao> insertOne(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Miao entityMiao = getEntityMiao(map);
        System.out.println(entityMiao);
        int affectedRows = miaoService.insertOne(getEntityMiao(map));
        System.out.println("insertAffectedRows " + affectedRows);

        ResultVO<Miao> miaoResultVO = new ResultVO<>();
        if (affectedRows != 0) {
            miaoResultVO.success("增加成功");
        } else {
            miaoResultVO.fail("增加失败");
        }
        return miaoResultVO;
    }

    @Operation(summary = "MiaoController类的update方法，对应/update接口")
    @PostMapping("/update")
    @ResponseBody
    public ResultVO<Miao> update(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Miao entityMiao = getEntityMiao(map);
        System.out.println(entityMiao);
        int affectedRows = miaoService.update(entityMiao);
        System.out.println("insertAffectedRows " + affectedRows);

        ResultVO<Miao> miaoResultVO = new ResultVO<>();
        if (affectedRows != 0) {
            miaoResultVO.success("修改成功");
        } else {
            miaoResultVO.fail("修改失败");
        }
        return miaoResultVO;
    }

    @Operation(summary = "从ResponseBody中接收并初始化一个Miao实体类")
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

        if (!code.isEmpty()) {
            miao.setCode(Integer.parseInt(code));
        }
        if (!englishDefinitionDescription.isEmpty()) {
            miao.setEnglishDefinitionDescription(englishDefinitionDescription);
        }
        if (!chineseTerm.isEmpty()) {
            miao.setChineseTerm(chineseTerm);
        }
        if (!pinyinTerm.isEmpty()) {
            miao.setPinyinTerm(pinyinTerm);
        }
        if (!chineseSynonyms.isEmpty()) {
            miao.setSynonyms(chineseSynonyms);
        }
        if (!miaoLanguage.isEmpty()) {
            miao.setMiaoLanguage(miaoLanguage);
        }
        if (!englishTerm.isEmpty()) {
            miao.setEnglishTerm(englishTerm);
        }
        if (!synonyms.isEmpty()) {
            miao.setSynonyms(synonyms);
        }
        if (!source.isEmpty()) {
            miao.setSource(source);
        }
        return miao;
    }
}

