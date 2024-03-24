package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import cs.vsu.ru.expertise_server.data.entity.OpinionEntity;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OpinionMapper {

    public OpinionEntity toEntity(OpinionCreateDto opinionDto, ProjectEntity project, ExpertEntity expert) {
        OpinionEntity opinionEntity = new OpinionEntity();
        opinionEntity.setText(opinionDto.getText());
        opinionEntity.setConclusion(opinionDto.getConclusion());
        String scores = String.join(";", opinionDto.getScores().stream().map(Object::toString).toArray(String[]::new));
        opinionEntity.setScores(scores);
        opinionEntity.setExpert(expert);
        opinionEntity.setProject(project);

        return opinionEntity;
    }

    public OpinionEntity toEntity(OpinionChangeDto opinion, OpinionEntity opinionEntity) {
        opinionEntity.setText(opinion.getText());
        String scores = String.join(";", opinion.getScores().stream().map(Object::toString).toArray(String[]::new));
        opinionEntity.setScores(scores);
        opinionEntity.setConclusion(opinion.getConclusion());

        return opinionEntity;
    }

    public OpinionDto toDto(OpinionEntity opinion, ProjectEntity project, ExpertEntity expert) {
        List<Integer> scores = Arrays.stream(opinion.getScores().split(";"))
                .map(Integer::parseInt)
                .toList();
        return new OpinionDto(project.getTheme(), expert.getName(), scores, opinion.getText(), opinion.getConclusion());
    }
}
