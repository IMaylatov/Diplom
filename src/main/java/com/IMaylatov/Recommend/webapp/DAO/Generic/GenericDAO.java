package com.IMaylatov.recommend.webapp.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * ����� ��������� ��� ������ � ����������
 * @param <T> ��������
 */
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
}