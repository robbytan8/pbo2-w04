package com.robby.utility;

import java.util.List;

/**
 *
 * @author Robby
 */
public interface DaoService<E> {

    int addData(E object);

    int deleteData(E object);

    int updateData(E object);

    List<E> showAllData();
}
