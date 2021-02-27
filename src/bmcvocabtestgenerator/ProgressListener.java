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
public interface ProgressListener
{
    public static final int STANDARD_MAX = 100;
    void progressUpdated(int progress);
    void maxProgressUpdated(int maxProgress);
}
