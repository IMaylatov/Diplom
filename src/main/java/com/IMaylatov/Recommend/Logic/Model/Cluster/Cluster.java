package com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * �������� �������
 */
@Entity
@Table(name = "Cluster")
public class Cluster {
    //region Private field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cluster_id_seq")
    @SequenceGenerator(name = "cluster_id_seq", sequenceName = "cluster_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ClusterID", updatable = false)
    private List<RateCluster> rateClusters;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ClusterID")
    private List<Person> persons;
    //endregion

    //region Constructor
    public Cluster(){
        rateClusters = new ArrayList<>();
        persons = new ArrayList<>();
    }
    //endregion

    //region Getter Setter
    public long getId(){
        return id;
    }

    /**
     * �������� � ������ ������ ��� �����
     * @param song ����������� �����
     * @param value �������� ������ ��� �����
     * @return ������ ��� �����
     */
    public RateCluster addRate(Song song, int value){
        RateCluster rate = getRate(song);
        if (rate == null){
            rate = new RateCluster(new RateCluster.PairKey(this, song), value);
            rateClusters.add(rate);
        }else
            rate.setValue(value);
        return rate;
    }

    /**
     * �������� ������ �������� ��� �����
     * @param song ����� ��� ������� ���������� ������ � ��������
     * @return ������ �������� ��� �����
     */
    public RateCluster getRate(Song song){
        for (RateCluster rate : rateClusters)
            if (rate.getSong().getId() == song.getId())
                return rate;
        return null;
    }

    /**
     * ������� ���������� ������ ��� �����
     * @param song �����, ��� ������� ��������� ������
     */
    public void removeRate(Song song){
        RateCluster rate = getRate(song);
        if (rate != null){
            rateClusters.remove(rate);
        }
    }

    public List<RateCluster> getRates(){
        List<RateCluster> cloneRateClusters = new ArrayList<>(rateClusters);
        return cloneRateClusters;
    }

    /**
     * �������� ������������ � �������
     * @param person ����������� ������������
     * @return ����������� ������������
     */
    public Person addPerson(Person person){
        Person p = getPerson(person.getId());
        if (p == null) {
            persons.add(person);
            return person;
        }
        return p;
    }

    /**
     * �������� ������������� �� id
     * @param id Id ������������. �������� ����� �����
     * @return ��������� ������������
     */
    public Person getPerson(long id){
        for (Person person : persons)
            if (person.getId() == id)
                return person;
        return null;
    }

    /**
     * ������� ������������ �� ��������
     * @param person ��������� �������
     */
    public void removePerson(Person person){
        persons.remove(person);
    }
    //endregion
}
