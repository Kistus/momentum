import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Array;
import java.util.*;

public class PT_lab4 {

    private static Map<String, Tower> add(Scanner sc, MageRepository magerep, TowerRepository towerrep, Map<String, Tower> towers, List<String> mages){
        System.out.println("Who do you want to create?");
        System.out.println("- tower");
        System.out.println("- mage");
        String str = sc.nextLine();
        while(true) {
            if (str.equals("tower")) {
                System.out.println("Enter name of tower");
                String name = sc.nextLine();
                while(true){
                    if(!towers.containsKey(name)){
                        System.out.println("Enter integer height of tower");
                        int height = Integer.parseInt(sc.nextLine());
                        Tower tower = new Tower(name, height);
                        towers.put(name, tower);
                        towerrep.save(tower);
                        break;
                    }
                    else{
                        System.out.println("Name is oquppied. Try another");
                        name=  sc.nextLine();
                    }
                }
                break;
            } else if (str.equals("mage")) {
                System.out.println("Enter name of mage");
                String name = sc.nextLine();
                while(true) {
                    if (!mages.contains(name)) {
                        System.out.println("Enter integer level of mage");
                        int level = Integer.parseInt(sc.nextLine());
                        System.out.println("Enter iname of tower");
                        String tower = sc.nextLine();
                        while(true){
                            if(towers.containsKey(tower)){
                                towers.get(tower).addMage(new Mage(name, level));
                                towerrep.save(towers.get(tower));
                                break;
                            }
                            System.out.println("Ooops, wrong. Try again");
                            System.out.println("Enter name of existing tower");
                            tower = sc.nextLine();
                        }
                        break;
                    }
                    else{
                        System.out.println("This name is occupied. Try another");
                        name = sc.nextLine();
                    }
                }
                break;
            } else {
                System.out.println("Ooops, wrong. Try again");
                System.out.println("Who do you want to create?");
                System.out.println("- tower");
                System.out.println("- mage");
                str = sc.nextLine();
            }
        }
        return towers;
    }

    private static Map<String, Tower> delete(Scanner sc, MageRepository magerep, TowerRepository towerrep, Map<String, Tower> towers, List<String> mages){
        System.out.println("Who do you want to delete?");
        System.out.println("- tower");
        System.out.println("- mage");
        String str = sc.nextLine();
        while(true) {
            if (str.equals("tower")) {
                System.out.println("Enter name of tower you want to delete");
                String name = sc.nextLine();
                while(true){
                    if(towers.containsKey(name)){
                        towerrep.remove(towers.get(name));
                        towers.remove(name);
                        break;
                    }
                    else{
                        System.out.println("Sorry, no such a name");
                        name=  sc.nextLine();
                    }
                }
                break;
            } else if (str.equals("mage")) {
                System.out.println("Enter name of mage you want to delete");
                String name = sc.nextLine();
                while(true) {
                    if (mages.contains(name)) {
                        magerep.remove(magerep.findByName(name).get());
                        break;
                    }else{
                        System.out.println("There is no such name. Try another");
                        name = sc.nextLine();
                    }
                }
                break;
            } else {
                System.out.println("Ooops, wrong. Try again");
                System.out.println("Who do you want to create?");
                System.out.println("- tower");
                System.out.println("- mage");
                str = sc.nextLine();
            }
        }
        return towers;
    }

    private static void getAll(MageRepository magerep, TowerRepository towerrep, Scanner sc){
        System.out.println("Who do you want to get?");
        System.out.println("- tower");
        System.out.println("- mage");
        String str = sc.nextLine();
        while(true) {
            if (str.equals("tower")) {
                List<Tower> towers = towerrep.findAll();
                towers.forEach(System.out::println);
                break;
            } else if (str.equals("mage")) {
                List<Mage> mages = magerep.findAll();
                mages.forEach(System.out::println);
                break;
            } else {
                System.out.println("Ooops, wrong. Try again");
                System.out.println("Who do you want to get?");
                System.out.println("- tower");
                System.out.println("- mage");
                str = sc.nextLine();
            }
        }
    }

    private static void query(Scanner sc, EntityManager em, Map<String, Tower> towers){
        System.out.println("For what is that query?");
        System.out.println("1. SELECT t FROM Tower t WHERE t.height > :x");
        System.out.println("2. SELECT m FROM Mage m WHERE m.tower = :x AND m.level > :y");
        String str = sc.nextLine();
        while(true) {
            if (str.equals("1")){
                System.out.println("Okaaay, give me integer x");
                str = sc.nextLine();
                List<Tower> tmp = em.createQuery("SELECT t FROM Tower t WHERE t.height > :x").
                        setParameter("x", Integer.parseInt(str)).getResultList();
                tmp.forEach(System.out::println);
                break;
            }
            else if(str.equals("2")){
                System.out.println("Okaaay, give me tower name");
                String name = sc.nextLine();
                while(true){
                    if(towers.containsKey(name)){
                        Tower tower = towers.get(name);
                        System.out.println("Give me maximum level of mage");
                        name = sc.nextLine();
                        List<Mage> mages= em.createQuery("SELECT m FROM Mage m WHERE m.tower = :x AND m.level > :y").setParameter("x", tower)
                                .setParameter("y", Integer.parseInt(name)).getResultList();
                        mages.forEach(System.out::println);
                        break;
                    }
                    else{
                        System.out.println("Sorry, no such tower");
                        System.out.println("Okaaay, give me tower name");
                        name = sc.nextLine();
                    }
                }
                //List<Mage> lst = em.createQuery("SELECT m FROM Mage m WHERE m.tower LIKE :tower").setParameter("tower", tower3).getResultList();
                //lst.forEach(System.out::println);
                break;
            }
            else{
                System.out.println("Ooops, wrong. Try again");
                System.out.println("For what is that query?");
                System.out.println("1. SELECT t FROM Towers t WHERE t.height > :x");
                System.out.println("2. SELECT m FROM Mages m WHERE m.tower = :x AND m.level > :y");
                str = sc.nextLine();
            }
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labPu");
        EntityManager em = emf.createEntityManager();
        MageRepository magerep = new MageRepository(em);
        TowerRepository towerrep = new TowerRepository(em);

        Map<String, Tower> towers = new HashMap<String, Tower>();
        List<String> mages = new ArrayList<>();

        Tower tower1 = new Tower("tower1", 300);
        Mage mage1 = new Mage("Anno", 999);
        Mage mage2 = new Mage("Eric", 10);
        Mage mage3 = new Mage("Adam", 13);
        Mage mage4 = new Mage("Marcus", 12);
        tower1.addMage(mage1);
        tower1.addMage(mage2);
        tower1.addMage(mage3);
        tower1.addMage(mage4);
        mages.add(mage1.getName());
        mages.add(mage2.getName());
        mages.add(mage3.getName());
        mages.add(mage4.getName());


        Tower tower2 = new Tower("tower2", 500);

        Tower tower3 = new Tower("tower3", 200);

        Mage mage5 = new Mage("Albus", 100);
        Mage mage6 = new Mage("Steve", 10);
        Mage mage7 = new Mage("Cerus", 42);
        tower3.addMage(mage5);
        tower3.addMage(mage6);
        tower3.addMage(mage7);

        Tower tower4 = new Tower("tower4", 400);

        towers.put(tower1.getTower_name(), tower1);
        towers.put(tower2.getTower_name(), tower2);
        towers.put(tower3.getTower_name(), tower3);
        towers.put(tower4.getTower_name(), tower4);

        mages.add(mage5.getName());
        mages.add(mage6.getName());
        mages.add(mage7.getName());
        towerrep.save(tower1);
        towerrep.save(tower1);
        towerrep.save(tower2);
        towerrep.save(tower3);
        towerrep.save(tower4);

        String str;
        System.out.println("Enter what you want to do:");
        System.out.println("- new data");
        System.out.println("- delete");
        System.out.println("- get all");
        System.out.println("- query");
        System.out.println("- end");
        str = sc.nextLine();
        while(!str.equals("end")){
            switch (str){
                case "new data": {
                    towers = add(sc, magerep, towerrep, towers, mages);
                    for(Mage mage : magerep.findAll()){
                        if (!mages.contains(mage.getName())){
                            mages.add(mage.getName());
                        }
                    }
                    break;
                }
                case "get all":{
                    getAll(magerep, towerrep, sc);
                    break;
                }
                case "delete": {
                    towers = delete(sc, magerep, towerrep, towers, mages);
                    List<String> lost = new ArrayList<>();
                    for (String str1 : mages){
                        boolean was = false;
                        for (Mage mage: magerep.findAll()){
                            if(mage.getName().equals(str1)){
                                was = true;
                            }
                        }
                        if(!was){
                            lost.add(str1);
                        }
                    }
                    for(String str1: lost){
                        mages.remove(str1);
                    }
                    break;
                }
                case "query": {
                    query(sc, em, towers);
                }
                default:{
                    System.out.println("Sorry, wrong command");
                }
            }
            System.out.println("Enter what you want to do:");
            System.out.println("- new data");
            System.out.println("- delete");
            System.out.println("- get all");
            System.out.println("- query");
            System.out.println("- end");
            str = sc.nextLine();
        }
        em.close();
        emf.close();
    }
}
