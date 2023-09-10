package com.little.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.little.entity.Miao;
import com.little.mapper.MiaoMapper;
import com.little.service.IMiaoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */
@Service
public class MiaoServiceImpl implements IMiaoService {
    @Resource
    private final MiaoMapper miaoMapper;

    public MiaoServiceImpl(MiaoMapper miaoMapper) {
        this.miaoMapper = miaoMapper;
    }

    @Override
    public List<Miao> search(String chineseTerm, String currentPage, String pageSize) {
        if (chineseTerm != null) {
            QueryWrapper<Miao> wrapper = new QueryWrapper<>();
            wrapper
                    .like("Chinese_term", chineseTerm);
            if (currentPage != null && pageSize != null) {
                Page<Miao> page = new Page<>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
                miaoMapper.selectPage(page, wrapper);
                return page.getRecords();
            } else {
                return miaoMapper.selectList(wrapper);
            }
        } else {
            Page<Miao> page = new Page<>(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
            miaoMapper.selectPage(page, null);
            return page.getRecords();
        }
    }

    @Override
    public Long getTotalCount(String keyword) {
        QueryWrapper<Miao> wrapper = new QueryWrapper<>();
        wrapper
                .like("Chinese_term", keyword);
        return miaoMapper.selectCount(wrapper);
    }

    @Override
    public int deleteById(String code){
        return miaoMapper.deleteById(Integer.parseInt(code));
    }

    @Override
    public int insertOne(Miao miao){
        return miaoMapper.insert(miao);
    }

    @Override
    public int update(Miao miao){
        return miaoMapper.updateById(miao);
    }

}
