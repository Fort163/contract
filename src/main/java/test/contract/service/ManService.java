package test.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.contract.dao.ManRepository;
import test.contract.dao.PasportRepository;
import test.contract.form.ManCreateForm;
import test.contract.form.ManSearchForm;
import test.contract.model.Man;
import test.contract.model.Pasport;
import test.contract.utils.DateHelpler;
import test.contract.utils.RepositoryHelper;
import test.contract.utils.ResultHelper;
import test.contract.vo.ManSearchVO;
import test.contract.vo.ManVO;

import java.util.List;

/**
 * Created by Павел on 23.01.2020.
 */
@Service
public class ManService implements IManService {

    @Autowired
    private ManRepository manRepository;

    @Autowired
    private PasportRepository pasportRepository;

    @Override
    @Transactional
    public List<ManSearchVO> search(ManSearchForm manSearchForm) {
        return ResultHelper.obtainVO(manRepository.findAllByLastNameContainingAndFirstNameContainingAndSecondNameContaining(
                manSearchForm.getLastName(),manSearchForm.getFirstName(),manSearchForm.getSecondName()), Man.class,ManSearchVO.class
        );
    }

    @Override
    @Transactional
    public Boolean create(ManCreateForm manCreateForm) {
        Man man = null;
        if(manCreateForm.getManId()==null){
            man = RepositoryHelper.createEntity(new Man());
            Pasport pasport = new Pasport();
            pasport = RepositoryHelper.createEntity(pasport);
            man.setPasport(pasport);
        }
        else {
            man = RepositoryHelper.updateEntity(manRepository.getOne(manCreateForm.getManId()));
        }
        man.setLastName(manCreateForm.getLastName());
        man.setFirstName(manCreateForm.getFirstName());
        man.setSecondName(manCreateForm.getSecondName());
        man.setBirthDay(DateHelpler.formatDateExt(manCreateForm.getBirthDay()));
        Pasport pasport = man.getPasport();
        pasport = RepositoryHelper.updateEntity(pasport);
        pasport.setNumDoc(manCreateForm.getNumDoc());
        pasport.setSeriesDoc(manCreateForm.getSeriesDoc());
        pasportRepository.save(pasport);
        manRepository.save(man);
        return true;
    }

    @Override
    @Transactional
    public ManVO getMan(Long manId) {
        return ResultHelper.obtainVO(manRepository.getOne(manId),Man.class,ManVO.class);
    }
}
