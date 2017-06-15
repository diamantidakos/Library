package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerCategoryDAO;
import com.mgiandia.library.domain.BorrowerCategory;

/**
 * @author Νίκος Διαμαντίδης
 */

public class BorrowerCategoryDAOMemory implements BorrowerCategoryDAO
{
    protected static List<BorrowerCategory> entities = new ArrayList<>();

    public void delete(BorrowerCategory entity)
    {
        entities.remove(entity);
    }

    public void save(BorrowerCategory entity)
    {
        if (! entities.contains(entity))
            entities.add(entity);
    }

    public BorrowerCategory find(int uid)
    {
        for(BorrowerCategory borrowerCategory : entities) {
            if (borrowerCategory.getId() == uid ) {
                return borrowerCategory;
            }
        }

        return null;
    }

    public List<BorrowerCategory> findAll()
    {
        return new ArrayList(entities);
    }

    public int nextId()
    {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
