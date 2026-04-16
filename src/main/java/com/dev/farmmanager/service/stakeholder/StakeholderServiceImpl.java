package com.dev.farmmanager.service.stakeholder;

import com.dev.farmmanager.domain.entity.Stakeholder;
import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.domain.payload.stakeholder.StakeholderPayload;
import com.dev.farmmanager.exception.handler.InvalidDocumentPairException;
import com.dev.farmmanager.exception.handler.StakeholderNotFoundException;
import com.dev.farmmanager.exception.handler.UserNotFoundException;
import com.dev.farmmanager.mapper.StakeholderMapper;
import com.dev.farmmanager.repository.StakeholderRepository;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StakeholderServiceImpl implements StakeholderService {

    private final StakeholderRepository repository;
    private final StakeholderMapper mapper;
    private final UserService userService;

    @Override
    public List<Stakeholder> findAll() {
        return repository.findAllByUserId(SecurityUtils.getCurrentUserId());
    }

    @Override
    public Optional<Stakeholder> getById(@NonNull final Integer id) {
        return repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId());
    }

    @Override
    public Stakeholder create(@NonNull final Integer userId, @NonNull final StakeholderPayload payload) {
        validateDocumentPair(payload);

        User user = userService.getById(userId).orElseThrow(UserNotFoundException::new);

        Stakeholder stakeholder = mapper.toEntity(payload);
        stakeholder.setUser(user);

        return repository.save(stakeholder);
    }

    @Override
    public Stakeholder update(@NonNull final Integer id, @NonNull final StakeholderPayload payload) {
        validateDocumentPair(payload);

        Stakeholder stakeholder = repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(StakeholderNotFoundException::new);

        stakeholder.setName(payload.name().trim());
        stakeholder.setCpf(payload.cpf());
        stakeholder.setCnpj(payload.cnpj());
        stakeholder.setType(payload.type());
        stakeholder.setPhone(payload.phone());

        return repository.save(stakeholder);
    }

    @Override
    public void delete(@NonNull final Integer id) {
        repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(StakeholderNotFoundException::new);
        repository.deleteById(id);
    }

    private void validateDocumentPair(StakeholderPayload payload) {
        boolean hasCpf = StringUtils.isNotBlank(payload.cpf());
        boolean hasCnpj = StringUtils.isNotBlank(payload.cnpj());
        if (hasCpf && hasCnpj) {
            throw new InvalidDocumentPairException();
        }
    }
}
