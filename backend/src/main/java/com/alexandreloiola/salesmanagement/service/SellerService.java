package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.SellerModel;
import com.alexandreloiola.salesmanagement.repository.SellerRepository;
import com.alexandreloiola.salesmanagement.rest.dto.SellerDto;
import com.alexandreloiola.salesmanagement.rest.form.SellerForm;
import com.alexandreloiola.salesmanagement.rest.form.SellerUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerDto> getAllSellers() {
        List<SellerModel> sellerList = sellerRepository.findAll();
        return convertListToDto(sellerList);
    }

    public SellerDto getSellerById(long id) {
        try {
            SellerModel sellerModel = sellerRepository.findById(id).get();
            return convertModelToDto(sellerModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Vendedor não encontrado!");
        }
    }

    @Transactional
    public SellerDto insertSeller(SellerForm sellerForm) {
        try {
            SellerModel newSeller = convertFormToModel(sellerForm);
            Optional<SellerModel> byCpf = sellerRepository.findByCpf(newSeller.getCpf());

            if (byCpf.isPresent()) {
                throw new DataIntegrityException("Esse cpf já foi cadastrado em um vendedor");
            }

            newSeller.setIsActive(true);
            newSeller = sellerRepository.save(newSeller);
            return convertModelToDto(newSeller);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do vendedor não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public SellerDto updateSeller(long id, SellerUpdateForm sellerUpdateForm) {
        try {
            Optional<SellerModel> sellerModel = sellerRepository.findById(id);
            if (sellerModel.isPresent()) {
                SellerModel sellerUpdated = sellerModel.get();
                sellerUpdated.setName(sellerUpdateForm.getName());
                sellerUpdated.setBirthDate(sellerUpdateForm.getBirthDate());
                sellerUpdated.setIsActive(sellerUpdateForm.getIsActive());

                sellerRepository.save(sellerUpdated);
                return convertModelToDto(sellerUpdated);
            } else {
                throw new DataIntegrityViolationException("O vendedor não pode ser atualizado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do vendedor não foi(foram) devidamente preenchido(s).");
        }
    }

    @Transactional
    public void deleteSeller(long id) {
        try {
            if (sellerRepository.existsById(id)) {
                sellerRepository.deleteById(id);
            } else {
                throw new DataIntegrityViolationException("O vendedor não pode ser deletado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o vendedor");
        }
    }

    private SellerModel convertFormToModel(SellerForm sellerForm) {
        SellerModel sellerModel = new SellerModel();

        sellerModel.setName(sellerForm.getName());
        sellerModel.setBirthDate(sellerForm.getBirthDate());
        sellerModel.setCpf(sellerForm.getCpf());

        return sellerModel;
    }

    private SellerDto convertModelToDto(SellerModel sellerModel) {
        SellerDto sellerDto = new SellerDto();

        sellerDto.setName(sellerModel.getName());
        sellerDto.setBirthDate(sellerModel.getBirthDate());
        sellerDto.setCpf(sellerModel.getCpf());
        sellerDto.setIsActive(sellerModel.getIsActive());

        return sellerDto;
    }

    private List<SellerDto> convertListToDto(List<SellerModel> list) {
        List<SellerDto> sellerDtoList = new ArrayList<>();
        for (SellerModel sellerModel : list) {
            SellerDto sellerDto = this.convertModelToDto(sellerModel);
            sellerDtoList.add(sellerDto);
        }
        return sellerDtoList;
    }
}