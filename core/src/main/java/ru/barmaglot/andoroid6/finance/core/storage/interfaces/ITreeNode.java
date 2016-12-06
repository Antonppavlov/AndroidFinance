package ru.barmaglot.andoroid6.finance.core.storage.interfaces;


import java.util.List;

/**
 * Позволяет создать древовидную структуру из любого набора объектов, которые реализуют интерфейс ITreeNode
 * Паттерн "Компановщик" - вольная реализация
 */
public interface ITreeNode {

    long getId(); //каждый элемент дерева должен иметь свой униальный индетификатор

    String getName(); //каждый элемент должен иметь свое имя

    void add(ITreeNode child); // добавить дочерный элемент

    void remove(ITreeNode child); //удалить дочерний элемент

    List<ITreeNode> getChilds(); //получить список дочерних элеметонв

    ITreeNode getChild(long id); //получение дочернего элемента по id

    ITreeNode getParent(); //получение родительсного элемента

    void setParent(ITreeNode parent); //установка родительского элемента

    boolean hasChilds(); //проверяет есть ли дочерние элементы


}
