package single;

import java.io.*;

import java.util.*;


public class MACROPASS1{

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\macro1.txt"));
		
		FileWriter mnt=new FileWriter("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\mnt.txt");
		FileWriter mdt=new FileWriter("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\mdt.txt");
		FileWriter ir=new FileWriter("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\intermediate1.txt");
		LinkedHashMap<String, Integer> pntab=new LinkedHashMap<>();
		String line;
		String Macroname = null;
		int mdtp=1,kpdtp=0,paramNo=1,pp=0,kp=0,flag=0;
		while((line=br.readLine())!=null)
		{
			
			String parts[]=line.split("\\s+");
			if(parts[0].equalsIgnoreCase("MACRO"))
			{
				flag=1;
				line=br.readLine();
				parts=line.split("\\s+");
				Macroname=parts[0];
				if(parts.length<=1)
				{
					mnt.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+(kp==0?kpdtp:(kpdtp+1))+"\n");
					continue;
				}
				for(int i=1;i<parts.length;i++) //processing of parameters
				{
					parts[i]=parts[i].replaceAll("[&,]", "");
					//System.out.println(parts[i]);
					
					
				}
				mnt.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+(kp==0?kpdtp:(kpdtp+1))+"\n");
				
				//System.out.println("KP="+kp);
				
				
			}
			else if(parts[0].equalsIgnoreCase("MEND"))
			{
				mdt.write(line+"\n");
				flag=kp=pp=0;
				mdtp++;
				paramNo=1;
				
				
				
				
			}
			else if(flag==1)
			{
				for(int i=0;i<parts.length;i++)
				{
					if(parts[i].contains("&"))
					{
						parts[i]=parts[i].replaceAll("[&,]", "");
						mdt.write("(P,"+pntab.get(parts[i])+")\t");
					}
					else
					{
						mdt.write(parts[i]+"\t");
					}
				}
				mdt.write("\n");
				mdtp++;
			}
			else
			{
				ir.write(line+"\n");
			}
		}
		br.close();
		mdt.close();
		mnt.close();
		ir.close();
		
		System.out.println("MAcro PAss1 Processing done. :)");
	}

}


//create macro1.txt and give path in program
//
//START
//MACRO
//INCR &ARG3 &ARG2
//ADD AREG &ARG1
//MOVER BREG &ARG1
//MEND
//MACRO
//PVG &ARG2 &ARG1
//SUB AREG &ARG2
//MOVER CREG & ARG1
//MEND
//INCR
//DECR
//DATA2
//END
