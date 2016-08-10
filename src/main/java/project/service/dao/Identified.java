package project.service.dao;

import java.io.Serializable;

/**
 * Created by HOUSE on 12.07.2016.
 */
public interface Identified<PK extends Serializable> {

    /** Возвращает идентификатор объекта */
    public PK getId();
}
