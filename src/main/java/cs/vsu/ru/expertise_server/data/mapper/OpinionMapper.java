package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.ShortOpinionDto;
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
        opinionEntity.setScores(opinionDto.getScores());
        opinionEntity.setFinalScore(opinionDto.getScores().stream().reduce(0, Integer::sum));
        opinionEntity.setExpert(expert);
        opinionEntity.setProject(project);

        return opinionEntity;
    }

    public OpinionEntity toEntity(OpinionChangeDto opinion, OpinionEntity opinionEntity) {
        opinionEntity.setText(opinion.getText());
        opinionEntity.setScores(opinion.getScores());
        opinionEntity.setFinalScore(opinion.getScores().stream().reduce(0, Integer::sum));
        opinionEntity.setConclusion(opinion.getConclusion());

        return opinionEntity;
    }

    public OpinionDto toDto(OpinionEntity opinion) {
        ProjectEntity project = opinion.getProject();
        ExpertEntity expert = opinion.getExpert();

        return new OpinionDto(project.getTheme(), expert.getName(), opinion.getScores(), opinion.getText(), opinion.getConclusion());
    }

    public ShortOpinionDto toShortDto(OpinionEntity opinion) {
        ProjectEntity project = opinion.getProject();

        return new ShortOpinionDto(project.getTheme(), project.getAuthor(), opinion.getFinalScore(), opinion.getConclusion());
    }
}
