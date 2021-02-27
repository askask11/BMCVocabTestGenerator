/*
 * Author: jianqing
 * Date: Jun 29, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.time.LocalDateTime;

/**
 *
 * @author jianqing Gao
 */
public class TestInfo
{
    private int testId;
    private String vocabRange;
    private LocalDateTime generateTime;
    private boolean completed;

    public TestInfo(int testId, String vocabRange, LocalDateTime generateTime, boolean completed)
    {
        this.testId = testId;
        this.vocabRange = vocabRange;
        this.generateTime = generateTime;
        this.completed = completed;
    }

    public TestInfo()
    {
        this.testId = 0;
        vocabRange=null;
        completed=false;
        generateTime=null;
    }

    public int getTestId()
    {
        return testId;
    }

    public void setTestId(int testId)
    {
        this.testId = testId;
    }

    public String getVocabRange()
    {
        return vocabRange;
    }

    public void setVocabRange(String vocabRange)
    {
        this.vocabRange = vocabRange;
    }

    public LocalDateTime getGenerateTime()
    {
        return generateTime;
    }

    public void setGenerateTime(LocalDateTime generateTime)
    {
        this.generateTime = generateTime;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }
    
    
    
    
}
