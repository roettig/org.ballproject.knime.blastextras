package org.ballproject.knime.blastextras.types;

public interface BlastHitValue
{
	double getEvalue();
	String getId();
	String getDefline();
	void   setDefline(String s);
	double getIdentity();
	void setQuerySequence(String s);
	String getQuerySequence();
	void setHitSequence(String s);
	String getHitSequence();
	void setMidlineSequence(String s);
	String getMidlineSequence();
}
