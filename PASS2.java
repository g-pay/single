package single;


import java.io.*;
import java.util.HashMap;

public class PASS2 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader1 = new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\intermediate.txt");
        FileReader fileReader2 = new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\symtab.txt");
        FileReader fileReader3 = new FileReader("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\littab.txt"); 
        FileWriter f1 = new FileWriter("C:\\Users\\Win 10\\eclipse-workspace\\helloworld\\src\\lp1\\Pass2.txt");

        BufferedReader b1 = new BufferedReader(fileReader1);
        BufferedReader b2 = new BufferedReader(fileReader2);
        BufferedReader b3 = new BufferedReader(fileReader3);

        HashMap<Integer,String> symSymbol= new HashMap<Integer,String>();
        HashMap<Integer,String> litSymbol= new HashMap<Integer,String>();
        HashMap<Integer,String> litAddress= new HashMap<Integer,String>();

        String s;
        int symTabPointer = 1,litTabPointer = 1,offset;

        while ((s=b2.readLine())!=null) {
            String word[] = s.split("\t\t\t");
            symSymbol.put(symTabPointer++,word[1]);
        }

        while ((s=b3.readLine())!=null) {
            String word[] = s.split("\t\t");
            litSymbol.put(litTabPointer, word[0]);
            litAddress.put(litTabPointer++, word[1]);
        }

        while ((s=b1.readLine())!=null) {
            if (s.substring(1,6).compareToIgnoreCase("IS,00") == 0) {
                f1.write("+ 00 0 000 \n");
            }
            else if(s.substring(1,3).compareToIgnoreCase("IS")==0)
            {
                f1.write("+"+s.substring(4, 6)+" \n");
                if (s.charAt(9)==')') {
                    f1.write(s.charAt(8)+"\n");
                    offset = 3;
                }
                else
                {
                    f1.write("0 ");
                    offset=0;
                }
                if (s.charAt(8+offset)=='S') {
                    f1.write(symSymbol.get(Integer.parseInt(s.substring(10+offset, s.length()-1)))+"\n\n");
                }
                else
                {
                    f1.write(litAddress.get(Integer.parseInt(s.substring(10+offset, s.length()-1)))+"\n\n");
                }

            }
            else if(s.substring(1,6).compareToIgnoreCase("DL,01")==0)
            {
                String s1 = s.substring(10,s.length()-1);
                String s2 = "";
                for (int i = 0; i < 3-s1.length(); i++) {
                    s2+="0";
                }
                s2+=s1;
                f1.write("+ 00 0 "+s2+"\n");
            }
            else
            {
                f1.write("\n");
            }
        }

        fileReader1.close();
        fileReader2.close();
        fileReader3.close();
        f1.close();
        b1.close();
        b2.close();
        b3.close();

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
//littab.txt
//5		206
//1		207
//1		213
//symtab
//A			211			1
//LOOP			202			1
//B			212			1
//NEXT			208			1
//BACK			202			1
//LAST			210			1


