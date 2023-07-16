package com.alexandreloiola.salesmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class SellerService {
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Transactional
//    public SellerDto registerSeller(SellerRegisterForm registerSellerForm) {
//        try {
//            SellerForm sellerForm = new SellerForm();
//            sellerForm.setName(registerSellerForm.getName());
//            sellerForm.setEmail(registerSellerForm.getEmail());
//            sellerForm.setBirthDate(registerSellerForm.getBirthDate());
//            sellerForm.setCpf(registerSellerForm.getCpf());
//
//            UserForm userForm = new UserForm();
//            userForm.setEmail(registerSellerForm.getEmail());
//            userForm.setPassword(registerSellerForm.getPassword());
//
//            SellerDto sellerDto = this.insertSeller(sellerForm);
//            userService.insertUser(userForm);
//
//            return sellerDto;
//        } catch (DataIntegrityViolationException err) {
//            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Cadastro do cliente não foi(foram) devidamente preenchido(s).");
//        }
//    }
//
//    public List<SellerDto> getAllSellers() {
//        List<EmployeeModel> sellerList = sellerRepository.findAll();
//        return convertListToDto(sellerList);
//    }
//
//    public SellerDto getSellerById(long id) {
//        try {
//            EmployeeModel employeeModel = sellerRepository.findById(id).get();
//            return convertModelToDto(employeeModel);
//        } catch(NoSuchElementException err) {
//            throw new ObjectNotFoundException("Vendedor não encontrado!");
//        }
//    }
//
//    @Transactional
//    public SellerDto insertSeller(SellerForm sellerForm) {
//        try {
//            EmployeeModel newSeller = convertFormToModel(sellerForm);
//            Optional<EmployeeModel> byCpf = sellerRepository.findByCpf(newSeller.getCpf());
//
//            if (byCpf.isPresent()) {
//                throw new DataIntegrityException("Esse cpf já foi cadastrado em um vendedor");
//            }
//
//            newSeller.setIsActive(true);
//            newSeller = sellerRepository.save(newSeller);
//            return convertModelToDto(newSeller);
//        } catch (DataIntegrityViolationException err) {
//            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do vendedor não foi(foram) devidamente preenchido(s).");
//        }
//    }
//
//    @Transactional
//    public SellerDto updateSeller(long id, SellerUpdateForm sellerUpdateForm) {
//        try {
//            Optional<EmployeeModel> sellerModel = sellerRepository.findById(id);
//            if (sellerModel.isPresent()) {
//                EmployeeModel sellerUpdated = sellerModel.get();
//                sellerUpdated.setName(sellerUpdateForm.getName());
//                sellerUpdated.setBirthDate(sellerUpdateForm.getBirthDate());
//                sellerUpdated.setIsActive(sellerUpdateForm.getIsActive());
//
//                sellerRepository.save(sellerUpdated);
//                return convertModelToDto(sellerUpdated);
//            } else {
//                throw new DataIntegrityViolationException("O vendedor não pode ser atualizado");
//            }
//        } catch (DataIntegrityViolationException err) {
//            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do vendedor não foi(foram) devidamente preenchido(s).");
//        }
//    }
//
//    @Transactional
//    public void deleteSeller(long id) {
//        try {
//            if (sellerRepository.existsById(id)) {
//                sellerRepository.deleteById(id);
//            } else {
//                throw new DataIntegrityViolationException("O vendedor não pode ser deletado");
//            }
//        } catch (DataIntegrityViolationException err) {
//            throw new DataIntegrityViolationException("Não foi possível deletar o vendedor");
//        }
//    }
//
//    private EmployeeModel convertFormToModel(SellerForm sellerForm) {
//        EmployeeModel employeeModel = new EmployeeModel();
//
//        employeeModel.setName(sellerForm.getName());
//        employeeModel.setEmail(sellerForm.getEmail());
//        employeeModel.setBirthDate(sellerForm.getBirthDate());
//        employeeModel.setCpf(sellerForm.getCpf());
//
//        return employeeModel;
//    }
//
//    private SellerDto convertModelToDto(EmployeeModel employeeModel) {
//        SellerDto sellerDto = new SellerDto();
//
//        sellerDto.setName(employeeModel.getName());
//        sellerDto.setEmail(employeeModel.getEmail());
//        sellerDto.setBirthDate(employeeModel.getBirthDate());
//        sellerDto.setCpf(employeeModel.getCpf());
//        sellerDto.setIsActive(employeeModel.getIsActive());
//
//        return sellerDto;
//    }
//
//    private List<SellerDto> convertListToDto(List<EmployeeModel> list) {
//        List<SellerDto> sellerDtoList = new ArrayList<>();
//        for (EmployeeModel employeeModel : list) {
//            SellerDto sellerDto = this.convertModelToDto(employeeModel);
//            sellerDtoList.add(sellerDto);
//        }
//        return sellerDtoList;
//    }
}