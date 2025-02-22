package com.example.backend.services;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.backend.dao.FranchiseDao;
import com.example.backend.dto.franchise.CreateFranchiseDto;
import com.example.backend.dto.franchise.GetFranchiseDto;
import com.example.backend.dto.response.GetResponseDto;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.mappers.FranchiseMapper;
import com.example.backend.models.franchise.FranchiseModel;
import com.example.backend.models.user.UserModel;
import com.example.backend.repositories.FranchiseRepository;
import com.example.backend.repositories.UserRepository;

@Service
public class FranchiseService implements FranchiseDao {

        FranchiseRepository franchiseRepository;
        UserRepository userRepository;

        public FranchiseService(FranchiseRepository _franchiseRepository, UserRepository _userRepository) {
                this.franchiseRepository = _franchiseRepository;
                this.userRepository = _userRepository;
        }

        @Override
        public GetResponseDto getAllFranchise(int pageSize, int pageNo, String sortBy, String sortDir) {
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<FranchiseModel> franchisePage = franchiseRepository.findAll(pageable);

                Collection<FranchiseModel> pageContent = franchisePage.getContent();

                Collection<GetFranchiseDto> franchiseList = pageContent.stream()
                                .map(franchise -> FranchiseMapper.INSTANCE.franchiseToDto(franchise))
                                .collect(Collectors.toList());

                return GetResponseDto.builder()
                                .success(true)
                                .message("Franchise query successful")
                                .data(franchiseList)
                                .pageSize(franchisePage.getSize())
                                .pageNo(franchisePage.getNumber())
                                .totalNoPages(franchisePage.getTotalPages())
                                .totalNoElements(franchisePage.getNumberOfElements())
                                .isLastPage(franchisePage.isLast())
                                .build();

        }

        @Override
        public GetResponseDto getFranchiseByUser(UUID id, int pageSize, int pageNo, String sortBy,
                        String sortDir) {

                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<FranchiseModel> franchisePage = franchiseRepository.findAllFranchiseByUser(id, pageable);

                Collection<FranchiseModel> pageContent = franchisePage.getContent();

                Collection<GetFranchiseDto> franchiseList = pageContent.stream()
                                .map(franchise -> FranchiseMapper.INSTANCE.franchiseToDto(franchise))
                                .collect(Collectors.toList());

                return GetResponseDto.builder()
                                .success(true)
                                .message("Franchise query successful")
                                .data(franchiseList)
                                .pageSize(franchisePage.getSize())
                                .pageNo(franchisePage.getNumber())
                                .totalNoPages(franchisePage.getTotalPages())
                                .totalNoElements(franchisePage.getNumberOfElements())
                                .isLastPage(franchisePage.isLast())
                                .build();
        }

        @Override
        public GetFranchiseDto getFranchise(UUID id) {
                return FranchiseMapper.INSTANCE.franchiseToDto(franchiseRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Franchise with id:" + id + " not found.")));
        }

        @Override
        public GetFranchiseDto createFranchise(CreateFranchiseDto franchise) {
                // Try getting the user id or username from the token
                UserModel userModel = userRepository.findById(franchise.getOwner().id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Username with id:" + franchise.getOwner().id + "not found"));

                franchise.setOwner(userModel);

                FranchiseModel franchiseModel = FranchiseMapper.INSTANCE.franchiseDtoToModel(franchise);

                franchiseRepository.save(franchiseModel);
                return FranchiseMapper.INSTANCE.franchiseToDto(franchiseModel);

        }

        @Override
        public GetFranchiseDto deleteFranchise(UUID id) {
                FranchiseModel franchiseModel = franchiseRepository.findById(id).orElseThrow(
                                () -> new ResourceNotFoundException("Franchise with id:" + id + " not found."));

                franchiseRepository.deleteById(id);

                return FranchiseMapper.INSTANCE.franchiseToDto(franchiseModel);

        }

}
