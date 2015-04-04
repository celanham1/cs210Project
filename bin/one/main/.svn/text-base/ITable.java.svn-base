package one.main;

import java.util.ArrayList;

import one.commands.ChrisException;

public interface ITable {

	public abstract Dataset projectRows(ArrayList<Integer> colNums, Table a)
			throws ChrisException;

	/**
	 * Joins two Tables
	 * @param b the Table being joined
	 * @return the Dataset
	 * @throws ChrisException 
	 */
	public abstract Dataset joinTable(Table b) throws ChrisException;

	/**
	 * Minus two Tables
	 * @param b the Table being minused
	 * @return the Dataset
	 * @throws ChrisException 
	 */
	public abstract Dataset minusTable(Table b) throws ChrisException;

	/**
	 * Union two Tables
	 * @param b the Table being unioned
	 * @return the Dataset
	 * @throws ChrisException 
	 */
	public abstract Dataset unionTable(Table b) throws ChrisException;

	/**
	 * Intersect two Tables
	 * @param b the Table being intersected 
	 * @return the Dataset
	 * @throws ChrisException 
	 */
	public abstract Dataset intersectTable(Table b) throws ChrisException;

}