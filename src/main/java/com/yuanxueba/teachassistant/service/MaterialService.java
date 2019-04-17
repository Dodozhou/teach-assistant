package com.yuanxueba.teachassistant.service;

import com.yuanxueba.teachassistant.dao.MaterialDao;
import com.yuanxueba.teachassistant.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    MaterialDao materialDao;

    public List<Material> getMaterials(Integer offset, Integer limit) {
        return materialDao.findMaterialLimited(offset,limit);
    }

    public Material getById(Long id){
        return materialDao.findById(id).get();
    }

    public void add(Material material) {
        materialDao.save(material);
    }

    public void delete(Long materialId) {
        materialDao.deleteById(materialId);
    }
}
