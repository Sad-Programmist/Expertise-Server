package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import cs.vsu.ru.expertise_server.data.entity.OpinionEntity;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import cs.vsu.ru.expertise_server.data.mapper.ProjectMapper;
import cs.vsu.ru.expertise_server.data.repository.ExpertRepository;
import cs.vsu.ru.expertise_server.data.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {

    private ProjectRepository projectRepository;
    private ExpertRepository expertRepository;

    private ProjectMapper projectMapper;

    @Transactional
    public List<ProjectDto> getRating(Integer year) {
        List<ProjectEntity> projects = projectRepository.findProjectEntitiesByYearOrderByScoreDesc(year);
        return projects.stream().map(projectMapper::toDto).toList();
    }

    @Transactional
    public byte[] exportFile(Integer year) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Рейтинг");

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setWrapText(true);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        Cell themeCell = headerRow.createCell(0);
        themeCell.setCellValue("Тема");
        themeCell.setCellStyle(headerCellStyle);
        Cell authorCell = headerRow.createCell(1);
        authorCell.setCellValue("Автор");
        authorCell.setCellStyle(headerCellStyle);
        Cell finalScore = headerRow.createCell(2);
        finalScore.setCellValue("Сумма баллов");
        finalScore.setCellStyle(headerCellStyle);

        sheet.setColumnWidth(0, 30 * 256);
        sheet.setColumnWidth(1, 30 * 256);
        sheet.setColumnWidth(2, 10 * 256);

        CellStyle expertCellStyle = workbook.createCellStyle();
        expertCellStyle.setAlignment(HorizontalAlignment.CENTER);
        expertCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        expertCellStyle.setRotation((short) 45);

        List<ExpertEntity> experts = expertRepository.findAll();
        List<ExpertEntity> properExperts = new ArrayList<>();
        for (ExpertEntity expert : experts) {
            if (expert.getOpinions().isEmpty()) {
                continue;
            }
            for (OpinionEntity opinion : expert.getOpinions()) {
                if (opinion.getProject().getYear() == year) {
                    properExperts.add(expert);
                    break;
                }
            }
        }
        for (int i = 3; i < properExperts.size() + 3; i++) {
            Cell expertCell = headerRow.createCell(i);
            expertCell.setCellValue(properExperts.get(i - 3).getName());
            expertCell.setCellStyle(expertCellStyle);
            sheet.setColumnWidth(i, 10 * 256);
        }

        CellStyle textCellStyle = workbook.createCellStyle();
        textCellStyle.setWrapText(true);
        textCellStyle.setAlignment(HorizontalAlignment.LEFT);
        textCellStyle.setVerticalAlignment(VerticalAlignment.TOP);

        CellStyle scoreCellStyle = workbook.createCellStyle();
        scoreCellStyle.setWrapText(true);
        scoreCellStyle.setAlignment(HorizontalAlignment.CENTER);
        scoreCellStyle.setVerticalAlignment(VerticalAlignment.TOP);

        Font badScoreFont = workbook.createFont();
        badScoreFont.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());

        CellStyle scoreBadCellStyle = workbook.createCellStyle();
        scoreBadCellStyle.setWrapText(true);
        scoreBadCellStyle.setAlignment(HorizontalAlignment.CENTER);
        scoreBadCellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        scoreBadCellStyle.setFont(badScoreFont);

        List<ProjectEntity> projects = projectRepository.findProjectEntitiesByYearOrderByScoreDesc(year);
        for (int i = 1; i <= projects.size(); i++) {
            Row dataRow = sheet.createRow(i);
            ProjectEntity project = projects.get(i - 1);
            themeCell = dataRow.createCell(0);
            themeCell.setCellValue(project.getTheme());
            themeCell.setCellStyle(textCellStyle);
            authorCell = dataRow.createCell(1);
            authorCell.setCellValue(project.getAuthor());
            authorCell.setCellStyle(textCellStyle);
            finalScore = dataRow.createCell(2);
            finalScore.setCellValue(project.getScore());
            finalScore.setCellStyle(scoreCellStyle);
            List<OpinionEntity> projectOpinions = project.getOpinions().stream().toList();
            for (OpinionEntity opinion : projectOpinions) {
                int columnIndex = properExperts.indexOf(opinion.getExpert()) + 3;
                Cell scoreCell = dataRow.createCell(columnIndex);
                scoreCell.setCellValue(opinion.getFinalScore());
                scoreCell.setCellStyle(opinion.getConclusion() ? scoreCellStyle : scoreBadCellStyle);
            }
        }

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.getCellStyle().setBorderBottom(BorderStyle.THIN);
                cell.getCellStyle().setBorderLeft(BorderStyle.THIN);
                cell.getCellStyle().setBorderRight(BorderStyle.THIN);
                cell.getCellStyle().setBorderTop(BorderStyle.THIN);
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
