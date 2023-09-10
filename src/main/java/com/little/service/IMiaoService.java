package com.little.service;

import com.little.entity.Miao;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FrankCheng
 * @since 2023-08-30
 */

public interface IMiaoService {

    List<Miao> search(String chineseTerm, String currentPage, String pageSize);

    Long getTotalCount(String keyword);

    int deleteById(String code);

    int insertOne(Miao miao);

    int update(Miao miao);
}
