import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = "Tower.findAll",
                query = "SELECT t FROM Tower t")
})
public class Tower {
    @Id
    @Getter
    private String tower_name;

    @Getter
    @Setter
    private int height;

    @OneToMany(mappedBy = "name", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    @Getter
    @ToString.Exclude
    private List<Mage> mages = new ArrayList<>();

    public void addMage(Mage mage){
        this.mages.add(mage);
        mage.setTower(this);
    }

    public Tower(String name, int height){
        this.tower_name = name;
        this.height = height;
    }

    public Tower(String name, int height, List<Mage> mages){
        this.tower_name = name;
        this.height = height;
        this.mages = mages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !this.getClass().getName().equals(o.getClass().getName())) return false;
        Tower tower = (Tower) o;
        return tower_name != null && Objects.equals(tower_name, tower.tower_name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}