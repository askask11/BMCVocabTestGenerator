/*
 * Author: jianqing
 * Date: Jun 19, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

/**
 *
 * @author jianqing
 */
public class Vocabulary
{
    private int listId;//tinyint
    private String term;//varchar(50)
    private String translate;//varchar(300)

    /**
     * Init a new vocabulary instance.
     * @param listId
     * @param term
     * @param translate 
     */
    public Vocabulary(int listId, String term, String translate)
    {
        this.listId = listId;
        this.term = term;
        this.translate = translate;
    }

    public Vocabulary()
    {
        listId=0;
        term = null;
        translate=null;
    }

    public int getListId()
    {
        return listId;
    }

    public void setListId(int listId)
    {
        this.listId = listId;
    }


    public String getTerm()
    {
        return term;
    }

    public void setTerm(String term)
    {
        this.term = term;
    }

    public String getTranslate()
    {
        return translate;
    }

    public void setTranslate(String translate)
    {
        this.translate = translate;
    }

    @Override
    public String toString()
    {
        return "Vocabulary{" + "listId=" + listId + ", term=" + term + ", translate=" + translate + '}' + '\n';
    }
    
    
    
}
