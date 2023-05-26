public class PT_lab5 {
    public static void main(String[] args){

        MageRepository mageRepository = new MageRepository();
        MageController mageController = new MageController(mageRepository);

        System.out.println(mageController.save("John", 12));
        System.out.println(mageController.save("Marcus", 13));
        System.out.println(mageController.save("John", 14));

        System.out.println(mageController.find("John"));
        System.out.println(mageRepository.find("Perceus"));

        mageRepository.delete("Marcus");
   //   System.out.println(mageController.delete("Drako"));

        System.out.println(mageRepository.getCollection());
    }
}
