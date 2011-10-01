package org.ballproject.knime.blastextras.demanglers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.ballproject.blast.knime.nodes.mimetypes.BlastXMLFileCell;
import org.ballproject.knime.base.mime.MIMEFileCell;
import org.ballproject.knime.base.mime.demangler.Demangler;
import org.ballproject.knime.blastextras.types.BlastHitCell;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataType;



public class BlastXMLDemangler implements Demangler
{

	@Override
	public DataType getSourceType()
	{
		return DataType.getType(BlastXMLFileCell.class);
	}

	@Override
	public DataType getTargetType()
	{
		return DataType.getType(BlastHitCell.class);
	}

	@Override
	public Iterator<DataCell> demangle(MIMEFileCell cell)
	{
		return new BlastXMLFileDemanglerDelegate(cell.getData());
	}

	@Override
	public MIMEFileCell mangle(Iterator<DataCell> iter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
	
	private static class BlastXMLFileDemanglerDelegate implements Iterator<DataCell>
	{
		private byte[] data;		
		private Document doc;
		private Element  root;
		private Iterator iter;
		private List<Node> hits;
		private InputStream in;
		
		public BlastXMLFileDemanglerDelegate(byte[] data)
		{
			this.data = data;
			in = new ByteArrayInputStream(this.data);
			SAXReader reader = new SAXReader();
	        try
			{
				doc = reader.read(in);
			} 
	        catch (DocumentException e)
			{
				e.printStackTrace();
				throw new RuntimeException("could not read from input stream");
			}
	        hits = doc.selectNodes("//Hit");
	        iter = hits.iterator();
		}
		
		@Override
		public boolean hasNext()
		{
			boolean ret = iter.hasNext();
			if(!ret)
			{
				try
				{
					in.close();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			return ret;
		}
		
		@Override
		public DataCell next()
		{
			Node n = (Node) iter.next();
			String id = n.valueOf("Hit_id/text()");
			
			String eval = n.valueOf("Hit_hsps/Hsp[1]/Hsp_evalue/text()");
			double evalue = Double.parseDouble(eval);
			
			String idS  = n.valueOf("Hit_hsps/Hsp[1]/Hsp_identity/text()");
			double iden = Double.parseDouble(idS);
			
			String lenS = n.valueOf("Hit_len/text()");
			double len  = Double.parseDouble(lenS);
			
			String defline   = n.valueOf("Hit_def/text()");
			String query_seq = n.valueOf("Hit_hsps/Hsp[1]/Hsp_qseq/text()");
			String hit_seq = n.valueOf("Hit_hsps/Hsp[1]/Hsp_hseq/text()");
			String mid_seq = n.valueOf("Hit_hsps/Hsp[1]/Hsp_midline/text()");
			
			BlastHitCell ret =  BlastHitCell.makeCell(id,evalue,(iden/len));
			ret.setDefline(defline);
			ret.setHitSequence(hit_seq);
			ret.setMidlineSequence(mid_seq);
			ret.setQuerySequence(query_seq);
			return ret;
		}

		@Override
		public void remove()
		{
			// NOP
		}
		
		public void close()
		{
		}
	}
}
