/*
 * Author: jianqing
 * Date: Jun 19, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jianqing
 */
public class VocabRange
{

    private ArrayList<Integer> vocabListIds;
    private String notation;
    private int wordQuantity;
    private boolean legalInput;

    public VocabRange(ArrayList<Integer> vocabListIds, String notation, int wordQuantity)
    {
        this.vocabListIds = vocabListIds;
        this.notation = notation;
        this.wordQuantity = wordQuantity;
        this.legalInput = true;
    }

    public VocabRange()
    {
        vocabListIds = new ArrayList<>();
        notation = null;
        wordQuantity = 0;
        this.legalInput = true;
    }

    public VocabRange(String notation, int wordQuantity)
    {
        //user defined notation
        this.notation = notation;
        this.wordQuantity = wordQuantity;
        try
        {
            //Flag input.
            this.legalInput = writeRangeIds(notation);
        } catch (NumberFormatException nfe)
        {
            //flag this as illegal input since number issue.
            this.legalInput = Boolean.FALSE;
        } catch (Exception ex)
        {
            //other unknown exceptions
            this.legalInput = false;
            Warning.createWarningDialog(ex);
            throw ex;//throw it out.
        }

    }

    //efficiency O3
    /**
     * Parse user input numbers and ranges into the number list.
     *
     * @param notation
     * @return
     * @throws NumberFormatException
     */
    public boolean writeRangeIds(String notation) throws NumberFormatException
    {
        String[] rules = notation.split(",");
        //give the range a new arraylist, since init again.
        this.vocabListIds = new ArrayList<>();
        for (String idRule : rules)
        {
            //split the lists from each other.
            if (idRule.contains("-"))
            {
                ///in case of rule bounds
                String[] bounds = idRule.split("-");
                int a, b;
                int min, max;
                int difference;
                //get the boundery of the ids
                a = Integer.parseInt(bounds[0]);
                b = Integer.parseInt(bounds[1]);
                //detect for negative numbers
                if (a <= 0 || b <= 0)
                {
                    //STOP here, tell user that there is a negative id.
                    return false;
                }
                //determine the min and the max of the bound
                min = Math.min(a, b);
                max = Math.max(a, b);
                //get the difference between the min and the max
                difference = max - min;
                //list the number and add them into the rule.
                for (int j = 0; j <= difference; j++)
                {
                    //list the number in ascending order.
                    System.out.println(j);
                    addVocabListIds(min + j);// example min = 1, difference = 3, then it will add 1, 2 ,3.
                }
                
            } else
            {
                addVocabListIds(Integer.parseInt(idRule));
            }
        }
        return true;
    }

    private void addVocabListIds(Integer id)
    {
        if (!vocabListIds.contains(id))
        {
            vocabListIds.add(id);
        }
    }

    public String getListIdsListedSQL()
    {
        String data="";
        for (int i = 0; i < vocabListIds.size(); i++)
        {
            data+=((i==0?"":" OR")+" listId="+vocabListIds.get(i));
        }
        return data;
    }
    public ArrayList<Integer> getVocabListIds()
    {
        return vocabListIds;
    }

    public void setVocabListIds(ArrayList<Integer> vocabListIds)
    {
        this.vocabListIds = vocabListIds;
    }

    public String getNotation()
    {
        return notation;
    }

    public void setNotation(String notation)
    {
        this.notation = notation;
    }

    public int getWordQuantity()
    {
        return wordQuantity;
    }

    public void setWordQuantity(int wordQuantity)
    {
        this.wordQuantity = wordQuantity;
    }

    @Override
    public String toString()
    {
        return "VocabRange{" + "vocabListIds=" + vocabListIds + ", notation=" + notation + ", wordQuantity=" + wordQuantity + ", legalInput=" + legalInput + '}';
    }
    
    /**
     * Get the notations all in one.
     * @param rangeList
     * @return 
     */
    public static String getCombinedNotation(List<VocabRange> rangeList)
    {
        String notation = "";
        for (int i = 0; i < rangeList.size(); i++)
        {
            VocabRange vocab = rangeList.get(i);
            notation+="("+vocab.getNotation()+")*"+vocab.getWordQuantity() + "; ";
        }
        return notation;
    }

    public static void main(String[] args)
    {
        VocabRange wr = new VocabRange("1,2,3-5,8", 100);
        System.out.println(wr);
    }
}
