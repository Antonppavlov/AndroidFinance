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
    //Все коллекции хранят ссылки на одни и теже объекты, но в разных срезах
    //при удалении нужно удалять из всех коллекций
    private List<ISource> treeList = new ArrayList<>();//хранит все деревья без раздереления по типа операции
    private Map<OperationType, List<ISource>> sourceMap = new EnumMap<>(OperationType.class);//разделяет по типу операции
    private Map<Long, ISource> identityMap = new HashMap<>();// хранит все источники без учета их уровня в дереве //получение по id


    private ISourceDAO iSourceDAO; //реализация слоя работы с бд

    public SourceSynchronizer(ISourceDAO iSourceDAO) {
        this.iSourceDAO = iSourceDAO;
        init();
    }

    private void init() {
        List<ISource> sourceList = iSourceDAO.getAll();

        for (ISource iSource : sourceList) {
            identityMap.put(iSource.getId(), iSource);
            treeUtils.addToTree(iSource.getParentId(), iSource, treeList);
        }

        distributionOperation(treeList);
    }

    public List<ISource> getSourceList(OperationType operationType) {
        return sourceMap.get(operationType);
    }


    @Override
    public List<ISource> getAll() {
        return treeList;
    }

    private void distributionOperation(List<ISource> sourceList) {
        List<ISource> incomeList = new ArrayList<>();
        List<ISource> outcomeList = new ArrayList<>();
        List<ISource> transferList = new ArrayList<>();
        List<ISource> convertList = new ArrayList<>();

        for (ISource iSource : sourceList) {
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
        return identityMap.get(id);
    }

    @Override
    public boolean add(ISource object) {
        boolean add = iSourceDAO.add(object);
        if (add) {
            treeList.add(object);
            addToCollection(object);
        }
        return add;
    }


    @Override
    public boolean update(ISource object) {
        return iSourceDAO.update(object);
    }

    @Override
    public boolean delete(ISource object) {
        if (iSourceDAO.delete(object)) {
            removeToCollection(object);
            return true;
        }
        return false;
    }

    private void addToCollection(ISource object) {
        identityMap.put(object.getId(), object);
        if (object.hasParent()) {
            if(!object.getParent().getChilds().contains(object)){
                object.getParent().add(object);
            }
        } else {
            sourceMap.get(object.getOperationType()).add(object);
            treeList.add(object);
        }
    }

    private void removeToCollection(ISource object) {
        identityMap.remove(object.getId());
        if (object.hasParent()) {
            //если удаляем родительский элемент
            //то можно быстро удалить на него ссылку из родителя
            object.getParent().remove(object);
        } else {
            sourceMap.get(object.getOperationType()).remove(object);
            treeList.remove(object);
        }
    }

    @Override
    public List<ISource> getListSource(OperationType operationType) {
        return sourceMap.get(operationType);
    }

    public ISourceDAO getiSourceDAO() {
        return iSourceDAO;
    }

    public Map<Long, ISource> getIdentityMap() {
        return identityMap;
    }
}



