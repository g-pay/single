package single;



import java.io.*;

public class PASS1
{
    public static void main(String args[]) throws Exception
    {
        // System.out.println("Helllo World");
        FileReader fp = new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\input.txt");
        BufferedReader bufferedReader = new BufferedReader(fp);

        String line = null;
        int line_count = 0, LocationCounter = 0, symTabLine = 0, opTabLine = 0, LiteralTabLine = 0, poolTabLine = 0;

        // Data Structures
        final int MAX = 100;
        String SymbolTab[][] = new String[MAX][3];
        String LiteralTab[][] = new String[MAX][3];
        String OpTab[][] = new String[MAX][3];
        int PoolTab[] = new int[MAX];
        // int LiteralTabAddress = 0;

        System.out.println("___________________________________________________________________________");

        while((line = bufferedReader.readLine()) !=null)
        {
            String[] tokens = line.split("\t");
            if(line_count == 0)
            {
                LocationCounter = Integer.parseInt(tokens[1]);
                // Set LocationCounter to operand of start
                for(int i =0;i<tokens.length;i++) //for printing the input program
                {
                    System.out.println(tokens[i]+"\t");
                }
                System.out.println("");
            }
            else
            {
                for(int i =0;i<tokens.length;i++) //for printing the input program
                {
                    System.out.println(tokens[i]+"\t");
                }
                System.out.println("");
                if(!tokens[0].equals(""))
                {
                    // Inserting into symbol table
                    SymbolTab[symTabLine][0] = tokens[0];
                    SymbolTab[symTabLine][1] = Integer.toString(LocationCounter);
                    SymbolTab[symTabLine][2] = Integer.toString(1);
                    symTabLine++;
                }
                else if(tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC"))
                {
                    // Inserting into symbol table for declarative statements
                    SymbolTab[symTabLine][0] = tokens[0];
                    SymbolTab[symTabLine][1] = Integer.toString(LocationCounter);
                    SymbolTab[symTabLine][2] = Integer.toString(1);
                    symTabLine++;
                }
                if(tokens.length == 3 && tokens[2].charAt(0) == '=')
                {
                    // Entry of literals into literal table
                    LiteralTab[LiteralTabLine][0] = tokens[2];
                    LiteralTab[LiteralTabLine][1] = Integer.toString(LocationCounter);
                    LiteralTabLine++;
                }
                else if(tokens[1] !=null)
                {
                    // Entry of Mnemonics in opcode table
                    OpTab[opTabLine][0] = tokens[1];

                    if(tokens[1].equalsIgnoreCase("START") || tokens[1].equalsIgnoreCase("END") || tokens[1].equalsIgnoreCase("LTORG")) // If Assembler Directives
                    {
                        OpTab[opTabLine][1] = "AD";
                        OpTab[opTabLine][2] = "R11";
                    }
                    else if(tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC"))
                    {
                        OpTab[opTabLine][1] = "DL";
                        OpTab[opTabLine][2] = "R7";
                    }
                    else
                    {
                        OpTab[opTabLine][1] = "IS";
                        OpTab[opTabLine][2] = "(04,1)";
                    }
                    opTabLine++;
                }
            }
            line_count++;
            LocationCounter++;
        }

        System.out.println("___________________________________________________________________________");

        // Printing Symbol Table
        System.out.println("\n\n\t SYMBOL TABLE \t");
        System.out.println("-------------------------");
        System.out.println("SYMBOL \tADDRESS \tLENGTH");
        System.out.println("-------------------------");
        for(int i = 0;i<symTabLine;i++)
        {
            System.out.println(SymbolTab[i][0] + "\t" + SymbolTab[i][1] + "\t" + SymbolTab[i][2] + "\t");
        }
        System.out.println("-------------------------");

        // Printing Opcode Table
        System.out.println("\n\n	OPCODE TABLE		");
			System.out.println("----------------------------");			
			System.out.println("MNEMONIC\tCLASS\tINFO");
			System.out.println("----------------------------");			
			for(int i=0;i<opTabLine;i++)
				System.out.println(OpTab[i][0]+"\t\t"+OpTab[i][1]+"\t"+OpTab[i][2]);
			System.out.println("----------------------------");

			//print literal table
			System.out.println("\n\n   LITERAL TABLE		");
			System.out.println("-----------------");			
			System.out.println("LITERAL\tADDRESS");
			System.out.println("-----------------");			
			for(int i=0;i<LiteralTabLine;i++)
				System.out.println(LiteralTab[i][0]+"\t"+LiteralTab[i][1]);
			System.out.println("------------------");
	

			//intialization of POOLTAB
			for(int i=0;i<LiteralTabLine;i++)
			{
				if(LiteralTab[i][0]!=null && LiteralTab[i+1][0]!=null ) //if literals are present
				{
					if(i==0)
					{
						PoolTab[poolTabLine]=i+1;
						poolTabLine++;
					}
					else if(Integer.parseInt(LiteralTab[i][1])<(Integer.parseInt(LiteralTab[i+1][1]))-1)
					{	
						PoolTab[poolTabLine]=i+2;
						poolTabLine++;
					}
				}
			}
			//print pool table
			System.out.println("\n\n   POOL TABLE		");
			System.out.println("-----------------");			
			System.out.println("LITERAL NUMBER");
			System.out.println("-----------------");			
			for(int i=0;i<poolTabLine;i++)
				System.out.println(PoolTab[i]);
			System.out.println("------------------");
			
		
		    // Always close files.
		    bufferedReader.close();


        fp.close();
        bufferedReader.close();
    }
};

// create input.txt and add that path to filereader and paste below code into input.txt
//START	100
//A	DS	3
//L1	MOVER	AREG,	B
//	ADD	AREG,   C
//	MOVEM 	AREG
//	MOVEM 	AREG
//D	EQU	A+1
//	LTORG  
//		='2'
//		='3'
//L2	PRINT	D
//	MOVEM 	AREG
//	MOVEM 	AREG
//	ORIGIN	L2+1
//	LTORG    
//		='4'
//		='5'
//B	DC	'19
//C	DC	'17
//	END
