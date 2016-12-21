package ru.barmaglot.andoroid6.finance.core.storage.dao.decotation;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.barmaglot.andoroid6.finance.core.storage.dao.interfaces.ISourceDAO;
import ru.barmaglot.andoroid6.finance.core.storage.interfaces.source.ISource;
import ru.barmaglot.andoroid6.finance.core.storage.type.OperationType;
import ru.barmaglot.andoroid6.finance.core.storage.utils.TreeUtils;

import static ru.barmaglot.andoroid6.finance.core.storage.type.OperationType.CONVERT;
import static ru.barmaglot.andoroid6.finance.core.storage.type.OperationType.INCOME;
import static ru.barmaglot.andoroid6.finance.core.storage.type.OperationType.OUTCOME;
import static ru.barmaglot.andoroid6.finance.core.storage.type.OperationType.TRANSFER;


public class SourceSynchronizer implements ISourceDAO {
    private TreeUtils<ISource> treeUtils = new TreeUtils<>(); //строит деревья

    private List<ISource> sourceList = new ArrayList<>();//хранит все деревья без раздереления по типа операции
    private Map<OperationType, List<ISource>> sourceMap = new EnumMap<>(OperationType.class);//разделяет по типу операции

    private Map<Long, ISource> identityMap = new HashMap<>();// хранит все источники без учета их уровня в дереве


    private ISourceDAO iSourceDAO; //реализация слоя работы с бд

    public SourceSynchronizer(ISourceDAO iSourceDAO) {
        this.iSourceDAO = iSourceDAO;
        //iSourceDAO.getAll();
    }

    public List<ISource> getSourceList(OperationType operationType) {
        return sourceMap.get(operationType);
    }

    @Override
    public List<ISource> getAll() {
        sourceList = iSourceDAO.getAll();

        for (ISource iSource : sourceList) {
            treeUtils.addToTree(iSource.getParentId(), iSource, sourceList);
            identityMap.put(iSource.getId(), iSource);
        }

        distributionOperation(sourceList);
        return sourceList;
    }

    private void distributionOperation(List<ISource> sourceList) {
        List<ISource> incomeList = new ArrayList<>();
        List<ISource> outcomeList = new ArrayList<>();
        List<ISource> transferList = new ArrayList<>();
        List<ISource> convertList = new ArrayList<>();

        for (ISource iSource: sourceList){
            switch (iSource.getOperationType()) {
                case INCOME: {
                    incomeList.add(iSource);
                    break;
                }
                case OUTCOME: {
                    outcomeList.add(iSource);
                    break;
                }
                case TRANSFER: {
                    transferList.add(iSource);
                    break;
                }
                case CONVERT: {
                    convertList.add(iSource);
                    break;
                }
            }
        }

        sourceMap.put(INCOME, incomeList);
        sourceMap.put(OUTCOME, outcomeList);
        sourceMap.put(TRANSFER, transferList);
        sourceMap.put(CONVERT, convertList);
    }

    @Override
    public ISource get(long id) {
        return iSourceDAO.get(id);
    }

    @Override
    public boolean add(ISource object) {
        boolean add = iSourceDAO.add(object);
        if (add) {
            sourceList.add(object);
           // distributionOperation(object);
        }
        return add;
    }

    @Override
    public boolean update(ISource object) {
        return iSourceDAO.update(object);
    }

    @Override
    public boolean delete(ISource object) {
        boolean delete = iSourceDAO.delete(object);
        if (delete) {
            sourceList.remove(object);
            sourceMap.get(object.getOperationType()).remove(object);
        }
        return delete;
    }

    @Override
    public List<ISource> getListSource(OperationType operationType) {
        return iSourceDAO.getListSource(operationType);
    }
}



