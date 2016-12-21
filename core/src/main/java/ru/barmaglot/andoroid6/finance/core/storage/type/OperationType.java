package ru.barmaglot.andoroid6.finance.core.storage.type;


import java.util.HashMap;
import java.util.Map;

public enum OperationType {
    //доход
    INCOME(1),
    //расход
    OUTCOME(2),
    //перевод
    TRANSFER(3),
    //конвертация
    CONVERT(4),;

    private static Map<Integer, OperationType> map = new HashMap<>();

    static {
        for (OperationType operationType : OperationType.values()) {
            map.put(operationType.getId(), operationType);
        }
    }

    private Integer id;

    private OperationType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static OperationType getType(Integer id) {
        return map.get(id);
    }


}
