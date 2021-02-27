/*
 * Author: jianqing
 * Date: Jun 19, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import cn.hutool.setting.Setting;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jianqing
 */
public class DatabaseConnector implements AutoCloseable
{

    private Connection dbConn;

    private String dbName;

    private static final Setting SETTING = new Setting("db.setting");
    public DatabaseConnector(String dbName, String host, String dbUsername, String dbPassword, boolean useSSL) throws SQLException, ClassNotFoundException
    {
        this.dbName = dbName;
        this.establishConnection(dbName, host, dbUsername, dbPassword, useSSL);
        //stablishConnection(dbName, dbName, dbUsername, dbPassword, true);); 
    }

    ////////////////////////////////////////////////////
    //////////////////////////VOCAUBLARIES TABLE/////////////
    /////////////////////////////////////////
    /////////////////////////////////////////////
    public void insertIntoTestTableInBatch(List<Vocabulary> vocabList, int testId, ProgressListener progressListener) throws SQLException
    {
        //override null progresslistener
        if (progressListener == null)
        {
            progressListener = new ProgressListener()
            {
                int maxProgress = 100;// = vocabList.size();

                @Override
                public void progressUpdated(int progress)
                {
                    System.out.println("Progress: " + (progress / maxProgress) + "%");
                }

                @Override
                public void maxProgressUpdated(int maxProgress)
                {
                    //System.out.println("Max progress : " + maxProgress);
                    this.maxProgress = maxProgress;
                }
            };
        }

        //init db operator
        progressListener.maxProgressUpdated(vocabList.size());
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO testTable VALUES(?,?,?)");
        for (int i = 0; i < vocabList.size(); i++)
        {
            //get each row and fill them in the blank.
            Vocabulary row = vocabList.get(i);
            ps.setInt(1, testId);
            ps.setString(2, row.getTerm());
            ps.setString(3, row.getTranslate());
            ps.addBatch();
            progressListener.progressUpdated(i);

            if (i % 10 == 0)
            {
                //execute the update for every 10 words, to allow user to keep track of the progress.
                ps.executeBatch();
                ps.clearBatch();

            }

            //final execution
            ps.executeBatch();

            progressListener.progressUpdated(0);//clear the progress bar.
        }

    }

    public void insertIntoVocabularyTableInBatch(List<Vocabulary> vocabList, ProgressListener pl) throws SQLException
    {
        //listid, term, translate
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO vocabularyTable VALUES(?,?,?)");
        Vocabulary vocab;

        pl.maxProgressUpdated(vocabList.size());
        //go through the list to add batch
        for (int i = 0; i < vocabList.size(); i++)
        {
            //get obj
            vocab = vocabList.get(i);
            ps.setInt(1, vocab.getListId());
            ps.setString(2, vocab.getTerm());
            ps.setString(3, vocab.getTranslate());
            ps.addBatch();

            if (i % 10 == 0)
            {
                //System.out.println("execute batch");
                ps.executeBatch();
                ps.clearBatch();
            }

            //System.out.println("Progress Updated");
            pl.progressUpdated(i);
        }

        //execute batch update
        ps.executeBatch();

        pl.progressUpdated(0);
    }

    public List<Vocabulary> selectFromVocabularyTableRamdom100(List<VocabRange> ranges) throws SQLException, IOException
    {
        //int counter=0;
        //Vocabulary[] vocabs = new Vocabulary[100];
        List<Vocabulary> vocabularys = new ArrayList<>();//= Arrays.asList(vocabs);
        Statement s = dbConn.createStatement();
        for (int i = 0; i < ranges.size(); i++)
        {
            VocabRange range = ranges.get(i);//get the range obj
            range.getVocabListIds();//get what lists will be selected from
            ArrayList<Vocabulary> list = selectFromVocabulary(range);//select from database for qualified vocabs
            Vocabulary[] selectedWords = randomSelectMembers(list, range.getWordQuantity());//randomly select words from the range
            for (int j = 0; j < range.getWordQuantity(); j++)
            {
                vocabularys.add(selectedWords[j]);
            }
        }
        //randomize the vocab list.
        //System.out.println(vocabularys);
        Collections.shuffle(vocabularys);
        return vocabularys;//return back the list.
    }

    public ArrayList<Vocabulary> selectFromVocabulary(VocabRange range) throws SQLException
    {
        ArrayList<Vocabulary> vocabList = new ArrayList<>();
        Statement s = dbConn.createStatement();
        String sql = "SELECT * FROM vocabularyTable WHERE " + range.getListIdsListedSQL() + " ";
        //System.out.println(sql);
        ResultSet rs = s.executeQuery(sql);

        while (rs.next())
        {
            vocabList.add(getVocabularyObjFromRs(rs));
        }
        return vocabList;
    }

    public ArrayList<Vocabulary> selectFromVocabulary() throws SQLException
    {
        ArrayList<Vocabulary> vocabList = new ArrayList<>();
        Statement s = dbConn.createStatement();
        String sql = "SELECT * FROM vocabularyTable";
        //System.out.println(sql);
        ResultSet rs = s.executeQuery(sql);

        while (rs.next())
        {
            vocabList.add(getVocabularyObjFromRs(rs));
        }
        return vocabList;
    }

    private Vocabulary getVocabularyObjFromRs(ResultSet rs) throws SQLException
    {
        return new Vocabulary(rs.getInt(1), rs.getString(2), rs.getString(3));

    }

    private Vocabulary[] randomSelectMembers(ArrayList<Vocabulary> vocabs, int amount) throws IOException
    {
        // int[] indexes = RandomNumberGenerator.generateTrueRandomInteger(amount, vocabs.size()-1, 0);
//        Arrays.sort(indexes);
//        System.out.println(Arrays.toString(indexes));
        Collections.shuffle(vocabs);
        Vocabulary[] data = new Vocabulary[amount];
        for (int i = 0; i < amount; i++)
        {
            data[i] = vocabs.get(i);
        }
        return data;
    }

    public ArrayList<String> selectFromVocabTableDisListId() throws SQLException
    {
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT DISTINCT listId FROM vocabularyTable");
        ArrayList<String> data = new ArrayList<>();

        while (rs.next())
        {
            data.add(rs.getString(1));
        }

        return data;

    }

    public int deleteFromVocabTableByListId(int vocabId) throws SQLException
    {
        Statement s = dbConn.createStatement();
        int rowsAffected = s.executeUpdate("DELETE FROM vocabularyTable WHERE listId=" + vocabId);
        return rowsAffected;
    }

    //////////////////
    ///////////////////////TEST TABLE//////////////////////
    //////////////////
    public ArrayList<String> selectFromTestTableDistTestId() throws SQLException
    {
        ArrayList<String> data = new ArrayList<>();
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT DISTINCT testId FROM testTable");
        while (rs.next())
        {
            data.add(rs.getString(1));
        }
        return data;
    }

    public ArrayList<Vocabulary> selectFromTestTableByTestId(int testId) throws SQLException
    {
        ArrayList<Vocabulary> vocabularys = new ArrayList<>();
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM testTable WHERE testId=" + testId);
        //read result
        while (rs.next())
        {
            vocabularys.add(new Vocabulary(-1, rs.getString(2), rs.getString(3)));
        }
        return vocabularys;
    }

    public int deleteTestFromDatabaseByTestId(int testId) throws SQLException
    {
        //int result;
        Statement s = dbConn.createStatement();

        return s.executeUpdate("DELETE FROM testTable WHERE testId=" + testId);
    }

    //////////////////////
    ///////////////////////TEST INFO TABLE
    /////////\//////////\//////\
    public int insertIntoTestInfoTable(TestInfo record) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO TestInfo VALUES(?,?,?,?,?)");
        ps.setInt(1, record.getTestId());
        ps.setString(2, record.getVocabRange());
        ps.setString(3, TimeConverter.formatDateTime(record.getGenerateTime()));
        ps.setBoolean(4, record.isCompleted());
        ps.setNull(5, java.sql.Types.SMALLINT);
        return ps.executeUpdate();
    }

    public ArrayList<TestInfo> selectFromTestInfo() throws SQLException
    {
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM TestInfo");
        ArrayList<TestInfo> list = new ArrayList<>();
        while (rs.next())
        {
            list.add(new TestInfo(rs.getInt(1), rs.getString(2), TimeConverter.formatDateTime(rs.getString(3)), rs.getBoolean(4)));
        }

        return list;
    }

    public int deleteFromTestInfo(int id) throws SQLException
    {
        Statement s = dbConn.createStatement();
        return s.executeUpdate("DELETE FROM TestInfo WHERE id=" + id);

    }

    ////////////////////////////////////////////////////
    //////////////////////////CONSTRUCTIONAL MEHTODS/////////////
    /////////////////////////////////////////
    /////////////////////////////////////////////
    public static DatabaseConnector getDefaultInstance() throws SQLException, ClassNotFoundException
    {
        return new DatabaseConnector(SETTING.get("dbName"), SETTING.get("host"), SETTING.get("user"), SETTING.get("pass"), true);
    }

    public void establishConnection(String dbName, String host, String dbUsername, String dbPassword, boolean useSSL) throws SQLException, ClassNotFoundException
    {
        //NO this.dbConn = dbConn;
        String connectionURL = "jdbc:mysql://" + host + "/" + dbName;
        this.dbConn = null;
        //Find the driver and make connection;

        Class.forName("com.mysql.cj.jdbc.Driver"); //URL for new version jdbc connector.
        Properties properties = new Properties(); //connection system property
        properties.setProperty("user", dbUsername);
        properties.setProperty("password", dbPassword);
        properties.setProperty("useSSL", Boolean.toString(useSSL));//set this true if domain suppotes SSL
        //"-u root -p mysql1 -useSSL false"
        this.dbConn = DriverManager.getConnection(connectionURL, properties);
    }

    ///////////////////////
    /////////////////////////WRONG WORDS TABLE
    ////////////////////////
    public ArrayList<WrongVocabulary> selectFromWrongWords() throws SQLException
    {
        //PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM wrongWords");
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM wrongWords");
        ArrayList<WrongVocabulary> wordList = new ArrayList<>();
        while (rs.next())
        {
            wordList.add(new WrongVocabulary(rs.getInt(3), 0, rs.getString(1), rs.getString(2)));
        }
        return wordList;
    }

    public int[] deleteFromWrongWords(String[] terms) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("DELETE FROM wrongWords WHERE term=?");
        for (String term : terms)
        {
            ps.setString(1, term);
            ps.addBatch();
        }

        return ps.executeBatch();
    }

    /////////////////////
    //////////////////// MY QUIZZES 
    ////////////////////
    public ArrayList<MyQuiz> selectFromMyQuizzes() throws SQLException
    {
        Statement s = dbConn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM quizzes");
        ArrayList<MyQuiz> rows = new ArrayList<>();
        while (rs.next())
        {
            rows.add(getMyQuizFromRS(rs));
        }
        return rows;
    }

    private MyQuiz getMyQuizFromRS(ResultSet rs) throws SQLException
    {
        MyQuiz quiz = new MyQuiz();
        quiz.setId(rs.getInt("id"));
        quiz.setDoclink(rs.getString("doclink"));
        quiz.setDate(rs.getString("date"));
        quiz.setMessage(rs.getString("message"));
        quiz.setScore(rs.getInt("score"));
        quiz.setAnswerLink(rs.getString("answerlink"));

        return quiz;
    }

    public int insertIntoQuizzes(MyQuiz quiz) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO quizzes VALUES(?,?,?,?,?,?)");
        ps.setInt(1, quiz.getId());
        ps.setString(2, quiz.getDoclink());
        ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
        ps.setString(4, quiz.getMessage());
        ps.setNull(5, java.sql.Types.TINYINT);
        ps.setString(6, "");
        return ps.executeUpdate();
    }

    ////////////////////////////
    ////////////////////////
    ////////////QUIZ ANSWERS///////////
    ///////////////////////
    /////////////////////////
    public int[] insertIntoQuizAnswersGroup(String[] answers, int quizid) throws SQLException
    {
        String sql = "INSERT INTO quizanswers VALUES(?,?,?,?,?)";
        PreparedStatement ps = dbConn.prepareStatement(sql);
        for (int i = 0; i < answers.length; i++)
        {
            ps.setInt(1, quizid);
            ps.setInt(2, i + 1);
            ps.setString(3, answers[i]);
            ps.setNull(4, java.sql.Types.TINYINT);
            ps.setNull(5, java.sql.Types.VARCHAR);
            ps.addBatch();
        }

        return ps.executeBatch();
    }

    ////////////////////////////
    ////////////////////////
    ////////////QUIZID///////////
    ///////////////////////
    /////////////////////////
    public int insertIntoQuizId(int id, String name) throws SQLException
    {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO quizid VALUES(?,?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        return ps.executeUpdate();
    }

    @Override
    public void close() throws SQLException
    {
        this.dbConn.close();
    }

    public static void main(String[] args)
    {
        try (DatabaseConnector dbConn = getDefaultInstance())
        {
            String[] answers = "A,D,8,D,D,32,3/2,3/4,D,3".split(",");
            System.out.println("size: " + answers.length);
            int ram = RandomNumberGenerator.randomInt(1000, 9999);
            System.out.println("id: " + ram);
            dbConn.insertIntoQuizAnswersGroup(answers, ram);
        } catch (Exception ex)
        {
            System.out.println("Ooops, error!");
            ex.printStackTrace();
        }

    }
}
