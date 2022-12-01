package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static softuni.exam.util.Constants.*;

@Service
public class AgentServiceImpl implements AgentService {

    private static final Path AGENT_JSON_PATH =
            new File(String.format(BASIC_PATH, "json", "agents.json")).toPath();
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository,
                            Gson gson, ModelMapper modelMapper, Validator validator) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(AGENT_JSON_PATH);
    }

    @Override
    public String importAgents() throws IOException {
        String agentsJson = this.readAgentsFromFile();

        AgentImportDTO[] agentImports = gson.fromJson(agentsJson, AgentImportDTO[].class);

        List<String> results = new ArrayList<>();

        for (AgentImportDTO agentImport : agentImports) {
            Set<ConstraintViolation<AgentImportDTO>> violations = validator.validate(agentImport);

            if (violations.isEmpty()) {
                Optional<Agent> optAgentByName = this.agentRepository.findByFirstName(agentImport.getFirstName());
                Optional<Agent> optAgentByEmail = this.agentRepository.findByEmail(agentImport.getEmail());
                Optional<Town> optTown = this.townRepository.findByTownName(agentImport.getTownName());

                if (optAgentByName.isEmpty() && optAgentByEmail.isEmpty() && optTown.isPresent()) {
                    Town town = optTown.get();

                    Agent mappedAgent = modelMapper.map(agentImport, Agent.class);
                    mappedAgent.setTown(town);

                    this.agentRepository.save(mappedAgent);

                    results.add(String.format(CORRECT_DATA, mappedAgent));

                    continue;
                }
            }

            results.add(String.format(INCORRECT_DATA, "agent"));
        }

        return String.join(System.lineSeparator(), results);
    }
}
