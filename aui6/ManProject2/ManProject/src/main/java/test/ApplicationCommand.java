//package test;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@RequiredArgsConstructor
//@Component
//public class ApplicationCommand implements CommandLineRunner
//{
//    private final ProjectDefaultService projectService;
//    private final MemberDefaultService memberService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String commemberd;
//        main_loop:
//        while (true)
//        {
//            String[] allCommemberds = new String[] {"all_commemberds", "get_members", "get_projects", "get_project_member", "create", "delete", "quit"};
//
//            commemberd = scanner.next();
//            switch (commemberd)
//            {
//                case "all_commemberds" -> {
//                    System.out.println(Arrays.toString(allCommemberds));
//                }
//
//                case "get_members" -> {
//                    System.out.println(memberService.findAll());
//                }
//
//                case "get_projects" -> {
//                    System.out.println(projectService.findAll());
//                }
//
//                case "get_project_member" -> {
//                    UUID uuid = UUID.fromString(scanner.next());
//                    Optional<List<Member>> opt =  memberService.findAllByProject(uuid);
//                    if (opt.isPresent()) {
//                        System.out.println(opt.get());
//                    } else {
//                        System.out.println("Brak");
//                    }
//                }
//
//                case "create" -> {
//                    String commemberd1 = scanner.next();
//                    String commemberd2 = scanner.next();
//                    String commemberd3 = scanner.next();
//                    String commemberd4 = scanner.next();
//                    if(projectService.find(UUID.fromString(commemberd4)).isEmpty())
//                    {
//                        System.out.println("Nie znaleziono tej pracy");
//                    }
//                    else
//                    {
//                        Member m1 = Member.builder().id(UUID.randomUUID()).name(commemberd1).position(Integer.parseInt(commemberd2))
//                                .skill(Integer.parseInt(commemberd3)).project(projectService.find(UUID.fromString(commemberd4)).get()).build();
//                        memberService.create(m1);
//                        System.out.println("Stworzono osobe");
//                    }
//                }
//
//                case "delete" -> {
//                    String commemberd4 = scanner.next();
//                    if(memberService.find(UUID.fromString(commemberd4)).isEmpty())
//                    {
//                        System.out.println("Nie znaleziono tej osoby");
//                    }
//                    else
//                    {
//                        memberService.delete(UUID.fromString(commemberd4));
//                        System.out.println("Usunieto osobe");
//                    }
//                }
//
//                /*
//                case "get_character" -> {
//                    UUID uuid = UUID.fromString(scanner.next());
//                    try {
//                        System.out.println(writer.writeValueAsString(characterController.getCharacter(uuid)));
//                    } catch (NoSuchElememberstException ex) {
//                        System.out.println("NOT_FOUND");
//                    }
//                }
//                case "get_character_portrait" -> {
//                    UUID uuid = UUID.fromString(scanner.next());
//                    try {
//                        System.out.println(imageToAnsiArt.apply(characterController.getCharacterPortrait(uuid), 40));
//                    } catch (NoSuchElememberstException ex) {
//                        System.out.println("NOT_FOUND");
//                    }
//                }
//                case "delete_character" -> {
//                    try {
//                        UUID uuid = UUID.fromString(scanner.next());
//                        characterController.deleteCharacter(uuid);
//                    } catch (NoSuchElememberstException ex) {
//                        System.out.println("Not Found");
//                    }
//                }
//                case "put_character" -> {
//                    UUID uuid = UUID.fromString(scanner.next());
//                    PutCharacterRequest request = PutCharacterRequest.builder()
//                            .name(scanner.next())
//                            .profession(UUID.fromString(scanner.next()))
//                            .build();
//                    try {
//                        characterController.putCharacter(uuid, request);
//                    } catch (IllegalArgumemberstException ex) {
//                        System.out.println("Bad Request");
//                    }
//                }
//                */
//                case "quit" -> {
//                    break main_loop;
//                }
//
//                default -> {
//                    System.out.println("Nie znaleziono polecenie, poszukaj z listy dostepnych");
//                }
//            }
//        }
//    }
//}