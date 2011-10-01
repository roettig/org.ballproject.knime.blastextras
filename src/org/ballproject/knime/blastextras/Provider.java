package org.ballproject.knime.blastextras;

import java.util.ArrayList;
import java.util.List;

import org.ballproject.knime.base.mime.demangler.Demangler;
import org.ballproject.knime.base.mime.demangler.DemanglerProvider;
import org.ballproject.knime.blastextras.demanglers.BlastXMLDemangler;


public class Provider implements DemanglerProvider
{

	@Override
	public List<Demangler> getDemanglers()
	{
		List<Demangler> ret = new ArrayList<Demangler>();
		ret.add(new BlastXMLDemangler());
		return ret;
	}

}