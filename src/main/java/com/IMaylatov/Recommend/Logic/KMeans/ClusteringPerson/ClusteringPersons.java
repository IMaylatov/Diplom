package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */

public interface ClusteringPersons {
    /**
     * Создать k кластеров и распределить по ним людей в определенной метрике
     */
    List<Cluster> clustering(List<Person> persons, int k, Metric metric);
}
