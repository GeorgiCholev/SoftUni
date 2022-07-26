package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<Integer, Private> privatesById = new HashMap<>();

        String soldierData;

        while (!"End".equals(soldierData = scanner.nextLine())) {

            String[] components = soldierData.split("\\s+");
            String fieldOfDuty = components[0];
            int id = Integer.parseInt(components[1]);
            String firstName = components[2];
            String lastName = components[3];
            double salary = Double.parseDouble(components[4]);
            String corp;

            switch (fieldOfDuty) {
                case "Private":

                    PrivateImpl privateSoldier = new PrivateImpl(id, firstName, lastName, salary);

                    privatesById.put(id, privateSoldier);
                    System.out.println(privateSoldier);

                    break;
                case "LieutenantGeneral":
                    LieutenantGeneralImpl general = new LieutenantGeneralImpl(id, firstName, lastName, salary);
                    for (int i = 5; i < components.length; i++) {

                        int privateId = Integer.parseInt(components[i]);
                        general.addPrivate(privatesById.get(privateId));

                    }
                    System.out.println(general);
                    break;
                case "Engineer":
                    corp = components[5];
                    EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, salary, corp);

                    if (engineer.getSoldierCorp() != null) {
                        for (int i = 6; i < components.length; i += 2) {
                            String repairPart = components[i];
                            int repairHours = Integer.parseInt(components[i + 1]);
                            engineer.addRepair(new Repair(repairPart, repairHours));
                        }

                        System.out.print(engineer);
                    }
                    break;
                case "Commando":
                    corp = components[5];
                    CommandoImpl commando = new CommandoImpl(id, firstName, lastName, salary, corp);

                    if (commando.getSoldierCorp() != null) {
                        for (int i = 6; i < components.length; i += 2) {

                            String missionCodeName = components[i];
                            String missionState = components[i + 1];
                            Mission mission = new Mission(missionCodeName, missionState);

                            if (mission.getMissionState() != null) {
                                commando.addMission(mission);
                            }
                        }
                        System.out.print(commando);
                    }
                    break;
                case "Spy":
                    int codeNumber = (int) salary;
                    System.out.println(new SpyImpl(id, firstName, lastName, codeNumber));
                    break;
            }

        }
    }

}
