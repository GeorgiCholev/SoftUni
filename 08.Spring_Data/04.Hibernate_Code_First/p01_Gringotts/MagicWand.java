package entity;

import javax.persistence.*;

@Entity
@Table(name = "magic_wands")
public class MagicWand {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String creator;

    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Short size;

    public MagicWand() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public short getSize() {
        return size;
    }

    public void setSize(Short size) {
        this.size = size;
    }
}
