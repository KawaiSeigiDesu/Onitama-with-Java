package method;

public class Convert {
    
    public static int encryptCOL(int col){
        if(col == 0)
            return -2;
        else if(col == 1)
            return -1;
        else if(col == 2)
            return 0;
        else if(col == 3)
            return 1;
        else
            return 2;
    }
    
    public static int encryptROW(int row){
        if(row == 0)
            return 2;
        else if(row == 1)
            return 1;
        else if(row == 2)
            return 0;
        else if(row == 3)
            return -1;
        else
            return -2;
    }
    
    public static int decryptCOL(int col){
        if(col == -2)
            return 0;
        else if(col == -1)
            return 1;
        else if(col == 0)
            return 2;
        else if(col == 1)
            return 3;
        else if (col == 2)
            return 4;
        else{
            return -100;
        }
    }
    
    public static int decryptROW(int row){
        if(row == 2)
            return 0;
        else if(row == 1)
            return 1;
        else if(row == 0)
            return 2;
        else if(row == -1)
            return 3;
        else if (row == -2)
            return 4;
        else{
            return -100;
        }
    }
}
