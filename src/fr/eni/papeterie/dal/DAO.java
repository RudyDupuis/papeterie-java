package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {
	public T selectById(int id) throws DALException;
	public List<T> selectAll() throws DALException;
	public void update(T t) throws DALException;
	public void insert(T t) throws DALException;
	public void delete(int id) throws DALException;
}
