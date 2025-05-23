package numerotation.generator_service.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numerotation.generator_service.CommunicationMicro.ConfigServiceClient;
import numerotation.generator_service.CommunicationMicro.CounterServiceClient;
import numerotation.generator_service.DTO.InscritDto;
import numerotation.generator_service.DTO.RuleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GeneratorService {

    @Autowired
    private ConfigServiceClient configServiceClient;

    @Autowired
    private CounterServiceClient counterServiceClient;

    public String generateNumber(InscritDto inscrit,Long id) {
        List<RuleDto> filteredRules = configServiceClient.getRules(inscrit.getId());


        filteredRules.sort(Comparator.comparingInt(RuleDto::getOrderIndex));


        int compteur = counterServiceClient.incrementCounter("inscrit");

        StringBuilder number = new StringBuilder();


        for (RuleDto rule : filteredRules) {
            String part = "";
            switch (rule.getField()) {
                case "firstName":
                    part = inscrit.getFirstName();
                    if (part.length() > rule.getLength()) {
                        part = part.substring(0, rule.getLength());
                    }
                    break;

                case "lastName":
                    part = inscrit.getLastName();
                    if (part.length() > rule.getLength()) {
                        part = part.substring(0, rule.getLength());
                    }
                    break;

                case "birthDate":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rule.getDateFormat());
                    part = inscrit.getBirthDate().format(formatter);
                    break;

                case "counter":
                    int initialValue = inscrit.getCounter() != null ? inscrit.getCounter() : 0;
                    part = String.format("%0" + rule.getLength() + "d", compteur + initialValue);
                    break;

                default:
                    continue;
            }

            if (rule.getPrefix() != null) {
                number.append(rule.getPrefix());
            }
            number.append(part);
            if (rule.getSuffix() != null) {
                number.append(rule.getSuffix());
            }
        }

        return number.toString();
    }

}

