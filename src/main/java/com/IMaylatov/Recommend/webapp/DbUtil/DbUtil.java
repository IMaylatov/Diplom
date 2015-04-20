package com.IMaylatov.recommend.webapp.dbUtil;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

/**
 * ���������, ����������� ������� � ��
 */
public interface DbUtil {
    /**
     * ��������� sql ������
     * @param query sql ������
     * @return ������ ������������ ��������
     */
    public int execute(String query);

    /**
     * ������� ������ ������� �� ���������� �������
     * @param query ������ �� ��������� ������
     * @return ������ �������� ����� ���������� �������
     */
    public <V> List<V> retrieve(String query);
}
