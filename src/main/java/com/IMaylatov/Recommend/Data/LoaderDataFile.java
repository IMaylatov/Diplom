package com.IMaylatov.Recommend.Data;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 01.04.2015.
 */

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Rate.RateDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Загрузчик данных из файла
 */
public class LoaderDataFile implements LoaderData {
    //region Private field
    private static Logger log = Logger.getLogger(LoaderDataFile.class.getName());

    private String dataPackage;
    private final ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");

    private final static String defalutDataPackage = "data/";
    private final static String personFile = "person.dat";
    private final static String songFile = "song.dat";
    private final static String rateFile = "ratings.dat";

    //endregion

    //region Constructor
    public LoaderDataFile(){
        this(defalutDataPackage);
    }

    public LoaderDataFile(String dataPackage){
        this.dataPackage = dataPackage;
    }
    //endregion

    /**
     * Отметим. что в файлах данных используются идентификаторы, по которым сущности связываются
     * Однако мы не можем сохранять сущность под определенным id, так как генерируем его из последовательности
     * С другой стороны мы можем выполнить скрипт и вставить сразу несколько записей одним запросом
     */

    //region Public method
    @Override
    public void loadAll(){
        loadPerson();
        loadSong();
        loadRate();
    }

    @Override
    public void loadPerson() {
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream(defalutDataPackage + personFile);
            Scanner scanner = new Scanner(stream)){
            PersonDAO personDAO = (PersonDAO) context.getBean("PersonDAO");
            StringBuilder insertSql = new StringBuilder();
            insertSql.append("INSERT INTO Person (ID) VALUES ");

            while(scanner.hasNext()){
                String[] infoPerson = scanner.nextLine().split("\\|");
                String insert = "(" + infoPerson[0] + "),";
                insertSql.append(insert);
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            personDAO.executeSql(insertSql.toString());
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public void loadSong() {
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream(defalutDataPackage + songFile);
            Scanner scanner = new Scanner(stream)){
            SongDAO songDAO = (SongDAO) context.getBean("SongDAO");
            StringBuilder insertSql = new StringBuilder();
            insertSql.append("INSERT INTO Song (ID) VALUES ");

            while(scanner.hasNext()){
                String[] infoSong = scanner.nextLine().split("\\|");
                String insert = "(" + infoSong[0] + "),";
                insertSql.append(insert);
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            songDAO.executeSql(insertSql.toString());
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public void loadRate() {
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream(defalutDataPackage + rateFile);
            Scanner scanner = new Scanner(stream)){
            RateDAO rateDAO = (RateDAO) context.getBean("RateDAO");
            StringBuilder insertSql = new StringBuilder();
            insertSql.append("INSERT INTO Rate (PersonId, SongId, Value) VALUES ");

            while(scanner.hasNext()){
                String[] infoRate = scanner.nextLine().split("\t");
                String insert = "(" + infoRate[0] + "," + infoRate[1] + "," + infoRate[2] + "),";
                insertSql.append(insert);
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            rateDAO.executeSql(insertSql.toString());
        } catch (IOException e) {
            log.error(e);
        }
    }
    //endregion
}
