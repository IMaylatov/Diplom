package com.IMaylatov.Recommend.Logic.LoaderData;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 01.04.2015.
 */
import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * Загрузчик данных из файла
 */
@Repository("LoaderData")
public class LoaderDataFile implements LoaderData {
    //region Private field
    private static Logger log = Logger.getLogger(LoaderDataFile.class.getName());
    @Autowired
    private DbUtil dbUtil;
    //endregion

    /**
     * Отметим. что в файлах данных используются идентификаторы, по которым сущности связываются
     * Однако мы не можем сохранять сущность под определенным id, так как генерируем его из последовательности
     * С другой стороны мы можем выполнить скрипт и вставить сразу несколько записей одним запросом
     */

    //region Public method
    @Override
    public void loadAll(Map<String, String> files){
        try(InputStream streamPerson = java.lang.ClassLoader.getSystemResourceAsStream(files.get("personId"));
            InputStream streamSong = java.lang.ClassLoader.getSystemResourceAsStream(files.get("song"));
            InputStream streamRate = java.lang.ClassLoader.getSystemResourceAsStream(files.get("rate"))){

            StringBuilder insertSql = insertQueryString(streamPerson, "Person", "ID");
            insertSql.append(";");

            insertSql.append(insertQueryString(streamSong, "Song", "ID"));
            insertSql.append(";");

            insertSql.append(insertQueryString(streamRate, "RatePerson", "PersonID", "SongID", "Value"));

            dbUtil.execute(insertSql.toString());
        }catch (IOException e){
            log.error(e);
        }
    }

    @Override
    @Transactional
    public void loadPerson(String personFile) {
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream(personFile)){
            dbUtil.execute(insertQueryString(stream, "Person", "ID").toString());
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public void loadSong(String songFile) {
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream(songFile)){
            dbUtil.execute(insertQueryString(stream, "Song", "ID").toString());
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public void loadRate(String rateFile) {
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream(rateFile)){
            dbUtil.execute(insertQueryString(stream, "RatePerson", "PersonID", "SongID", "Value").toString());
        } catch (IOException e) {
            log.error(e);
        }
    }
    //endregion

    //region Private method
    /**
     * Сформировать строку вставки в таблицу по указанным полям
     */
    private StringBuilder insertQueryString(InputStream stream, String table, String ... valuesName){
        try(Scanner scanner = new Scanner(stream)){
            StringBuilder insertSql = new StringBuilder(String.format("INSERT INTO %s (%s) VALUES ",
                    table,
                    String.join(",", valuesName)));

            while (scanner.hasNext()){
                String infoLine = "(" + String.join(",", scanner.nextLine().split(" ")) + "),";
                insertSql.append(infoLine);
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            return insertSql;
        }
    }
    //endregion
}
