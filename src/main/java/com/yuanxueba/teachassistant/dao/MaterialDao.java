package com.yuanxueba.teachassistant.dao;

import com.yuanxueba.teachassistant.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialDao extends JpaRepository<Material,Long> {
    //查询题库
    @Query(value = "SELECT * FROM t_material limit ?1,?2",nativeQuery = true)
    List<Material> findMaterialLimited(int offset, int limit);
}
