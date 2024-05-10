package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.admin.AdminAuthDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminCreateDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryChangeDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryCreateDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaCreateDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectChangeDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectCreateDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.*;
import cs.vsu.ru.expertise_server.data.mapper.*;
import cs.vsu.ru.expertise_server.data.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;

    private AdminMapper adminMapper;

    @Transactional
    public Boolean adminAuth(AdminAuthDto admin) {
        AdminEntity adminEntity = adminRepository.findAdminEntityByLogin(admin.getLogin());
        return adminEntity.getPassword().equals(DigestUtils.md5Hex(admin.getPassword()));
    }

    @Transactional
    public Boolean identification(String login) {
        AdminEntity admin = adminRepository.findAdminEntityByLogin(login);
        return admin == null;
    }

    @Transactional
    public void createAdmin(AdminCreateDto admin) {
        adminRepository.save(adminMapper.toEntity(admin));
    }

    @Transactional
    public void deleteAdmin(Integer adminId) {
        adminRepository.deleteById(adminId);
    }

    @Transactional
    public List<AdminDto> getAllAdmins() {
        List<AdminEntity> adminEntities = adminRepository.findAll();
        return adminEntities.stream().map(adminMapper::toDto).toList();
    }
}
