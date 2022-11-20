package single;
import java.util.*;
import java.io.*;

public class MACROPASS2 {

	public static void main(String[] args) throws Exception {
		BufferedReader irb=new BufferedReader(new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\intermediate.txt"));
		BufferedReader mdtb=new BufferedReader(new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\mdt.txt"));
		BufferedReader kpdtb=new BufferedReader(new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\kpdt.txt"));
		BufferedReader mntb=new BufferedReader(new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\mnt.txt"));
		
		FileWriter fr=new FileWriter("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\pass5.txt");
		
		HashMap<String,MNTEntry> mnt=new HashMap<>();
		HashMap<Integer, String> aptab=new HashMap<>();
		HashMap<String,Integer> aptabInverse=new HashMap<>();
		
		Vector<String>mdt=new Vector<String>();
		Vector<String>kpdt=new Vector<String>();
		
		int pp,kp,mdtp,kpdtp,paramNo;
		String line;
		while((line=mdtb.readLine())!=null)
		{
			mdt.addElement(line);
		}
		while((line=kpdtb.readLine())!=null)
		{
			kpdt.addElement(line);
		}
		while((line=mntb.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			mnt.put(parts[0], new MNTEntry(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
			
		}
		
		while((line=irb.readLine())!=null)
		{
			String []parts=line.split("\\s+");
			if(mnt.containsKey(parts[0]))
			{
				pp=mnt.get(parts[0]).getPp();
				kp=mnt.get(parts[0]).getKp();
				kpdtp=mnt.get(parts[0]).getKpdtp();
				mdtp=mnt.get(parts[0]).getMdtp();
				paramNo=1;
				for(int i=0;i<pp;i++)
				{ 
					parts[paramNo]=parts[paramNo].replace(",", "");
					aptab.put(paramNo, parts[paramNo]);
					aptabInverse.put(parts[paramNo], paramNo);
					paramNo++;
				}
				int j=kpdtp-1;
				for(int i=0;i<kp;i++)
				{
					String temp[]=kpdt.get(j).split("\t");
					aptab.put(paramNo,temp[1]);
					aptabInverse.put(temp[0],paramNo);
					j++;
					paramNo++;
				}
				
				for(int i=pp+1;i<parts.length;i++)
				{
					parts[i]=parts[i].replace(",", "");
					String splits[]=parts[i].split("=");
					String name=splits[0].replaceAll("&", "");
					aptab.put(aptabInverse.get(name),splits[1]);
				}
				int i=mdtp-1;
				while(!mdt.get(i).equalsIgnoreCase("MEND"))
				{
					String splits[]=mdt.get(i).split("\\s+");
					fr.write("+");
					for(int k=0;k<splits.length;k++)
					{
						if(splits[k].contains("(P,"))
						{
							splits[k]=splits[k].replaceAll("[^0-9]", "");//not containing number
							String value=aptab.get(Integer.parseInt(splits[k]));
							fr.write(value+"\t");
						}
						else
						{
							fr.write(splits[k]+"\t");
						}
					}
					fr.write("\n");	
					i++;
				}
				
				aptab.clear();
				aptabInverse.clear();
			}
			else
			{
				fr.write(line+"\n");
			}
			
	}
	
	fr.close();
	mntb.close();
	mdtb.close();
	kpdtb.close();
	irb.close();
	}
}

//create following files
//intermediate.txt
//(AD,01)(C,200)
//(IS,04)(1)(L,1)
//(IS,05)(1)(S,1)
//(IS,04)(1)(S,1)
//(IS,04)(3)(S,3)
//(IS,01)(3)(L,2)
//(IS,07)(6)(S,4)
//(DL,01)(C,5)
//(DL,01)(C,1)
//(IS,02)(1)(L,3)
//(IS,07)(1)(S,5)
//(IS,00)
//(AD,03)(S,2)+2
//(IS,03)(3)(S,3)
//(AD,03)(S,6)+1
//(DL,02)(C,1)
//(DL,02)(C,1)
//(AD,02)
//(DL,01)(C,1)
//mdt.txt
//MOVE #3,#1
//ADD #3,='1'
//MOVER #3,#2
//ADD #3,='5'
//MEND
//MOVER #3,#1
//MOVER #4,#2
//ADD #3,='15'
//ADD #4,='10'
//MEND
//kpdt.txt
//a	AREG
//b	-
//u	CREG
//v	DREG
//mnt.txt
//M1	2	2	1	1
//M2	2	2	6	3