package org.ballproject.knime.blastextras.types;

import org.knime.core.data.DataCell;

public class BlastHitCell extends DataCell implements BlastHitValue
{
	private double evalue;
	private String id;
	private String defline;
	private String query_seq;
	private String hit_seq;
	private String midline_seq;
	private double identity;
	
	public static BlastHitCell makeCell(String id, double evalue, double identity)
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

	@Override
	public String getDefline()
	{
		return this.defline;
	}

	@Override
	public void setDefline(String s)
	{
		this.defline = s;
	}

	@Override
	public void setQuerySequence(String s)
	{
		query_seq = s;
	}

	@Override
	public String getQuerySequence()
	{
		return query_seq;
	}

	@Override
	public void setHitSequence(String s)
	{
		hit_seq = s;
	}

	@Override
	public String getHitSequence()
	{
		return hit_seq;
	}

	@Override
	public void setMidlineSequence(String s)
	{
		midline_seq = s;
	}

	@Override
	public String getMidlineSequence()
	{
		return midline_seq;
	}

}
