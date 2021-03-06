package com.IMaylatov.Recommend.webapp.DAO.Generic;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
    /**
     * ��������� ��������
     * @param entity ����������� ��������
     */
    void save(T entity);

    /**
     * �������� ��������
     * @param entity ����������� ��������
     */
    void update(T entity);

    /**
     * ������� ��������
     * @param entity ��������� ��������
     */
    void delete(T entity);
    int deleteAll();

    /**
     * ����� �������� �� id
     * @param id id ��������
     * @return ��������� ��������
     */
    T find(K id);

    /**
     * �������������� ���������� ����������� ��������
     */
    void flush();

    /**
     * ������ ���������
     */
    List<T> list();
    List<T> list(Criterion criterion);
}