package nl.fhict.s3.mastermindlogic.entity;

public class Board implements IBoard {

    private final int id;

    public Colour[] code = new Colour[4];
    public Row[] rows = new Row[10];

    public int getId() {
        return id;
    }

    public Board(int id, Colour[] code) {
        this.id = id;
        this.code = code;
    }

    public Board() {
        id = 0;
    }

    public Board(int id) {
        this.id = id;
    }

    public Colour[] createCode(Colour[] codeCreation) {
        for (int i = 0; i < codeCreation.length; i++) {
            code[i] = codeCreation[i];
        }
        return code;
    }

    @Override
    public Row checkRow(Row rowToCheck) {
        EClueColour clues[] = new EClueColour[4];
        clues = getClues(rowToCheck.code);
        for (int i = 0; i < clues.length; i++) {
            rowToCheck.clues[i].seteClueColour(clues[i]);
        }
        return rowToCheck;
    }

    //only for sake for testing. Need a way around it.
     public EClueColour[] getClues(Colour[] input)
     {
         Colour[] tempColour=new Colour[4];
         System.arraycopy(code,0,tempColour,0,code.length);
         EClueColour[] clues=new EClueColour[4];
         clues=getBlackPins(clues,tempColour,input);
         clues=getWhitePins(clues,tempColour,input);
         sayClues(clues);
         return clues;

     }

     private EClueColour[] getBlackPins(EClueColour[] clues,Colour[] tempColour,Colour[] input)
     {
         for(int i=0;i<tempColour.length;i++)
         {
             if(input[i].getColour()==tempColour[i].getColour())
             {
                 //black pin.
                 clues[i]=EClueColour.BLACK;
                 tempColour[i]=null;
                 input[i]=null;
                 continue;
             }
             clues[i]=EClueColour.BLANK;
         }
         return clues;
     }

     private EClueColour[] getWhitePins(EClueColour[] clues,Colour[] tempColour,Colour[] input)
     {

         for(int i=0;i<tempColour.length;i++)
         {
             if(tempColour[i]==null)
             {
                 continue;
             }
             for(int j=0;j< input.length;j++)
             {
                 if(input[j]==null )
                 {
                     continue;
                 }
                 if(tempColour[i].getColour()==input[j].getColour())
                 {
                     clues[j]=EClueColour.WHITE;
                     tempColour[i]=null;
                     input[j]=null;
                     break;
                 }
             }
         }
         return clues;
     }

     private void sayClues(EClueColour[] clues)
     {
         for (int i=0;i<clues.length;i++)
         {
             System.out.println("Colour: "+ code[i].getColour());
             System.out.println("Index: "+i+" Clue: "+ clues[i]);
         }
     }

}
