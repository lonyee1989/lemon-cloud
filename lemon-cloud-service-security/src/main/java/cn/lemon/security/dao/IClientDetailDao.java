package cn.lemon.security.dao;

import cn.lemon.framework.query.Query;
import cn.lemon.framework.query.QueryPage;
import cn.lemon.security.dto.ClientDetail;

import java.util.List;

/**
 * Created by lonyee on 2017/6/8.
 */
public interface IClientDetailDao {
    /**
     * 保存记录
     */
    int save(ClientDetail entity);

    /**
     * 更新记录
     */
    int update(ClientDetail entity);

    /**
     * 根据主键id删除记录，尽量使用软删除数据 deleteBySoft
     * <p>Deprecated</p>
     */
    @Deprecated
    int delete(Long id);

    /**
     * 根据主键id删除记录 (软删除)
     */
    int deleteBySoft(Long id);

    /**
     * 根据查询条件Query查询单个记录
     */
    ClientDetail find(Query query);

    /**
     * 根据主键查找单一记录
     */
    ClientDetail findById(Long id);

    /**
     * 根据查询条件Query查询全部记录
     */
    List<ClientDetail> findAll(Query query);

    /**
     * 根据查询条件Query和分页条件Pageable查询
     */
    List<ClientDetail> findPage(QueryPage pageable);

}
