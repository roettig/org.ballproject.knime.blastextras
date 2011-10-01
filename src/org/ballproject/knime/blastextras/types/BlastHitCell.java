package org.ballproject.knime.blastextras.types;

import org.knime.core.data.DataCell;

public class BlastHitCell extends DataCell implements BlastHitValue
{
	private double evalue;
	private String id;
	private double identity;
	
	public static DataCell makeCell(String id, double evalue, double identity)
	{
		return new BlastHitCell(id,evalue,identity);
	}
	
	public BlastHitCell(String id, double evalue, double identity)
	{
		this.id = id;
		this.evalue = evalue;
		this.identity = identity;
	}
	
	@Override
	public double getEvalue()
	{
		return evalue;
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public double getIdentity()
	{
		return identity;
	}

	@Override
	protected boolean equalsDataCell(DataCell arg0)
	{
		return false;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return String.format("[BlastHit id:%s evalue:%e",id,evalue);
	}

}
