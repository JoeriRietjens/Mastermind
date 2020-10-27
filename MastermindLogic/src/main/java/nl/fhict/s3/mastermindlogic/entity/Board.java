package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Board {

    private final int id;
    public Colour[] code=new Colour[4];
    public Row[] rows = new Row[9];

    public int getId() {
        return id;
    }

    public Board(int id, Colour[] code) {
        this.id = id;
        this.code = code;
    }


    public Row(Row row)
    {
        
    }

     public boolean guessCode(EPinColour ePinColour1,EPinColour ePinColour2, EPinColour ePinColour3, EPinColour ePinColour4)
     {
        return true;
     }



     public EClueColour[] GetClues(Colour[] input)
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
                 if(input[j]==null)
                 {
                     continue;
                 }
                 if(tempColour[i].getColour()==input[j].getColour())
                 {
                     clues[j]=EClueColour.WHITE;
                     tempColour[i]=null;
                     input[j]=null;
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
